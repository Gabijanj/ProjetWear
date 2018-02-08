package lp.projetwear.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import lp.projetwear.R;


public class HeartBeatView extends AppCompatImageView {

    private static Drawable heartDrawable;

    public HeartBeatView(Context context) {
        super(context);
        init();
    }

    public HeartBeatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartBeatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init() {
        heartDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_heart_red_24dp);
        setImageDrawable(heartDrawable);
    }
}