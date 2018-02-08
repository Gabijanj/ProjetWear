package lp.projetwear.utils;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class WearHeartService extends Service implements SensorEventListener {

    public static final String HEART_COUNT_VALUE =  "heartCountValue";
    public static final String HEART_COUNT_MESSAGE = "heartCountMessage";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        Log.d(TAG, "onSensorChanged");
        if (event.sensor.getType() == Sensor.TYPE_HEART_BEAT) {
            DailyHeart.updateHearts();
            sendHeartCountUpdate();
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        Log.d(TAG, "onAccuracyChanged");
        sendHeartCountUpdate();
    }
    private void sendHeartCountUpdate() {
        Intent intent = new Intent();
        intent.setAction(HEART_COUNT_MESSAGE);
        intent.putExtra(HEART_COUNT_VALUE,DailyHeart.getHeart());
        sendBroadcast(intent);
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart");
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        Sensor countSensor = sensorManager.getDefaultSensor(Sensor.TYPE_HEART_BEAT);
        sensorManager.registerListener(this, countSensor, SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);
    }
}
