package lp.projetwear.utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.util.Log;

import java.util.Timer;

public class WearHeartEmulatorService extends WearHeartService {
    public static final String TAG = "WearHeartEmulatorService";
    private static Timer mTimer =null ;


    @SuppressLint("LongLogTag")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (mTimer ==null) {
            mTimer = new Timer();
            long delay = 1000*2;
            long period = 1000*2;
            mTimer.scheduleAtFixedRate(new RandomHeartCounterTimeTask(getApplicationContext()), delay, period);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        mTimer.cancel();
        mTimer = null;
        super.onDestroy();
    }
}

