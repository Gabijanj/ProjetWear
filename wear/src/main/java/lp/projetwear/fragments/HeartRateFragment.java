package lp.projetwear.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import lp.projetwear.utils.HeartBeatView;
import lp.projetwear.utils.WearHeartEmulatorService;
import lp.projetwear.utils.WearHeartService;

import lp.projetwear.R;

import static lp.projetwear.utils.WearHeartService.HEART_COUNT_MESSAGE;


public class HeartRateFragment extends Fragment {

    private TextView mTextView;
    private HeartBeatView heartbeat;
    private BroadcastReceiver br;
    private Intent heartServiceIntent;

    public HeartRateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.heart_rate_fragment, container, false);
        heartServiceIntent = new Intent(getActivity(), WearHeartEmulatorService.class);
        br = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int heart = intent.getExtras().getInt(WearHeartService.HEART_COUNT_VALUE);
                mTextView = (TextView) rootView.findViewById(R.id.tvheartrate);
                mTextView.setText(Integer.toString(heart));
            }
        };
        heartbeat = (HeartBeatView)rootView.findViewById(R.id.heartbeat);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        getActivity().startService(heartServiceIntent);
        getActivity().registerReceiver(br, new IntentFilter(HEART_COUNT_MESSAGE));

    }

    @Override
    public void onPause() {
        super.onPause();
        getActivity().stopService(heartServiceIntent);
        getActivity().unregisterReceiver(br);
    }


}
