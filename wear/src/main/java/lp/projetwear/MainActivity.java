package lp.projetwear;

import android.app.FragmentManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;

import java.util.ArrayList;

import lp.projetwear.fragments.HeartRateFragment;
import lp.projetwear.utils.DrawerItem;

public class MainActivity extends WearableActivity implements
    WearableNavigationDrawerView.OnItemSelectedListener{

    public final static int ITEM_MENU_STEP_COUNTER=0;
    public final static int ITEM_MENU_HEART_RATE=1;

    private WearableNavigationDrawerView mWearableNavigationDrawer;
    private ArrayList<DrawerItem> drawer_itemArrayList;
    private int mSelectedScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_itemArrayList = initializeScreenSystem();
        mSelectedScreen = 0;

        HeartRateFragment hrf = new HeartRateFragment();
        FragmentManager fragmentManager2 = getFragmentManager();
        fragmentManager2.beginTransaction().replace(R.id.content_frame, hrf).commit();

        // Top Navigation Drawer
        mWearableNavigationDrawer =
                (WearableNavigationDrawerView) findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(
                new WearableNavigationDrawerView.WearableNavigationDrawerAdapter(){

                    @Override
                    public int getCount() {
                        return drawer_itemArrayList.size();
                    }

                    @Override
                    public String getItemText(int pos) {
                        return drawer_itemArrayList.get(pos).getName();
                    }

                    @Override
                    public Drawable getItemDrawable(int pos) {
                        String navigationIcon = drawer_itemArrayList.get(pos).getNavigationIcon();

                        int drawableNavigationIconId =
                                getResources().getIdentifier(navigationIcon, "drawable", getPackageName());
                        return getDrawable(drawableNavigationIconId);
                    }
                });
        // Peeks navigation drawer on the top.
        mWearableNavigationDrawer.getController().peekDrawer();
        mWearableNavigationDrawer.addOnItemSelectedListener(this);
    }

    private ArrayList<DrawerItem> initializeScreenSystem() {
        ArrayList<DrawerItem> screens = new ArrayList<DrawerItem>();
        String[] FragmentArrayNames = getResources().getStringArray(R.array.screens);

        for (int i = 0; i < FragmentArrayNames.length; i++) {
            String fragmentName = FragmentArrayNames[i];
            int FragmentResourceId =
                    getResources().getIdentifier(fragmentName, "array", getPackageName());
            String[] fragmentInformation = getResources().getStringArray(FragmentResourceId);

            screens.add(new DrawerItem(
                    fragmentInformation[0],   // Name
                    fragmentInformation[1])); // Icon
        }

        return screens;
    }

    @Override
    public void onItemSelected(int position) {
        mSelectedScreen = position;

        switch (mSelectedScreen) {
            case ITEM_MENU_HEART_RATE:
                HeartRateFragment sectionFragment = new HeartRateFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, sectionFragment)
                        .commit();
                break;
        }
    }

}
