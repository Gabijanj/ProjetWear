package lp.projetwear.utils;

import android.content.Context;
import android.content.Intent;

import java.util.TimerTask;

public class RandomHeartCounterTimeTask extends TimerTask {
    public static final String TAG="RandomHeartCounterTimerTask";
    private Context ctxt =null;
    public RandomHeartCounterTimeTask(Context ctxt){
        this.ctxt = ctxt.getApplicationContext();
        DailyHeart.updateHearts();
    }
    @Override
    public void run() {
        DailyHeart.updateHearts();
        Intent intent = new Intent();
        intent.setAction(WearHeartService.HEART_COUNT_MESSAGE);
        intent.putExtra(WearHeartService.HEART_COUNT_VALUE,DailyHeart.getHeart());
        ctxt.sendBroadcast(intent);
    }
}
