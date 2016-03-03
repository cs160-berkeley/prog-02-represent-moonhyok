package com.cs160.joleary.catnip;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.DotsPageIndicator;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.support.wearable.view.GridPagerAdapter;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    private Button mElectBtn;
    String[] senate_1_info;
    Boolean clickable = false;
    private SensorManager mSensorManager;
    private Sensor mSensor;
    MyGridPagerAdapter mAdapter;
    Intent intent;
    Boolean alreadyShaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DotsPageIndicator mPageIndicator;

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
//        mSensor = mSensorManager.getDefaultSensor(Senso.TYPE_ACCELEROMETER);

        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);

//
        intent = getIntent();
        Bundle extras = intent.getExtras();
//


        if (extras != null){
            clickable = true;
//            String senate_1 = extras.getString("senate_1");
//            senate_1_info = senate_1.split("@");

            mPageIndicator = (DotsPageIndicator) findViewById(R.id.page_indicator);

            // Assigns an adapter to provide the content for this pager
            final GridViewPager pager = (GridViewPager) findViewById(R.id.pager);
            mAdapter = new MyGridPagerAdapter(this, getFragmentManager(), intent);
            pager.setAdapter(mAdapter);
//            Log.d("2dpicker", "Rows: " + Integer.toString(testAdapter.getRowCount()));
//            Log.d("2dpicker", "Cols: " + Integer.toString(testAdapter.getColumnCount(0)));

            mPageIndicator.setPager(pager);
        }


//        mElectBtn = (Button) findViewById(R.id.elect);
////        mBackgnd = (LinearLayout) findViewById(R.id.background);
////        mName = (TextView) findViewById(R.id.name);
////        mPic = (ImageView) findViewById(R.id.pic);
//
//
//
//        mElectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (clickable){
//                    Intent sendIntent = new Intent(MainActivity.this, ElectionView.class);
////                    sendIntent.putExtra("senate_1", senate_1_info);
//                    startActivity(sendIntent);
//                } else {
//                    Toast.makeText(MainActivity.this, "Choose a candidate first", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    public void onClick(View v) {
        Intent sendIntent = new Intent(MainActivity.this, WatchToPhoneService.class);
//        TextView mName = (TextView) v.findViewById(R.id.cand_name);
//        Log.d("nameSent",mName.getText()+" is sent when clicked.");
//        sendIntent.putExtra("name",mName.getText());
//        sendIntent.putExtra("party",);
//        Toast.makeText(MainActivity.this, "Ouch you pressed me!?", Toast.LENGTH_LONG).show();

        GridViewPager mPage = (GridViewPager) findViewById(R.id.pager);
        Point mCoord = (Point) mPage.getCurrentItem();
        String mName = mAdapter.getTitle(mCoord.y, mCoord.x);
        sendIntent.putExtra("congress",false);
        sendIntent.putExtra("name",mName);

        startService(sendIntent);
//        Toast.makeText(MainActivity.this, "Please write in at least one", Toast.LENGTH_LONG).show();
    }




    private int mAccel=0; // acceleration apart from gravity
    private int mAccelCurrent=0; // current acceleration including gravity
    private int mAccelLast=0; // last acceleration including gravity

    private final SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent se) {
            float x = se.values[0];
            float y = se.values[1];
            float z = se.values[2];
            mAccelLast = mAccel;
            mAccelCurrent = (int) Math.sqrt((double) (x*x + y*y + z*z));
            mAccel = mAccelCurrent - mAccelLast;
//            Log.d("sensor", "mAccel: "+Integer.toString(mAccel)+"mAccelCurrent: "+Integer.toString(mAccelCurrent)+"mAccelLast: "+Integer.toString(mAccelLast)+" Sensor changed. x: "+Float.toString(x)+" y: "+ Float.toString(y)+" z: "+Float.toString(z));
            if (mAccel > 20 && !alreadyShaken) {
//                Intent intent
                alreadyShaken = true;
//                Log.d("Sensed!", "sensor reading" + mAccel);
                changeEverything();
            }
//            mAccel = mAccel * 0.9f + delta * 0.1f; // perform low-cut filter
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    private void changeEverything() {
//        location = extras.getString("zipcode");
//    } else {
//        location = extras.getString("location");
//    }
//
//
//    //Get datas
//    String senate_1 = extras.getString("senate_1");
//    senate_1_info = senate_1.split("@");
//
//    String senate_2 = extras.getString("senate_2");
//    senate_2_info = senate_2.split("@");
//
//    String house_1 = extras.getString("house_1");
//    house_1_info = house_1.split("@");
//
//    String house_2 = extras.getString("house_2");

        Intent redoWatch = new Intent(getBaseContext(),MainActivity.class);
        Intent redoPhone = new Intent(getBaseContext(),WatchToPhoneService.class);


        redoWatch.putExtra("zipcode","92635");
        redoWatch.putExtra("location","");
        redoWatch.putExtra("senate_1","pikachu@#2F80ED");
        redoWatch.putExtra("senate_2","pikachu@#BDBDBD");
        redoWatch.putExtra("house_1","pikachu@#EB5757");
        redoWatch.putExtra("house_2","pikachu@#2F80ED");
        redoPhone.putExtra("congress",true);
        redoPhone.putExtra("zipcode","92635");
        redoPhone.putExtra("location","");
        redoPhone.putExtra("massive","pikachu@#2F80ED@pikachu@pikachu.pikapika.edu@pikachu.pokemon.com@pikcachus will take over the world&pikachu@#BDBDBD@pikachu@pikachu.pikapika.edu@pikachu.pokemon.com@pikcachus will take over the world&pikachu@#EB5757@pikachu@pikachu.pikapika.edu@pikachu.pokemon.com@pikcachus will take over the world&pikachu@#2F80ED@pikachu@pikachu.pikapika.edu@pikachu.pokemon.com@pikcachus will take over the world");
        startActivity(redoWatch);
        startService(redoPhone);



    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }


}

//class SampleGridPagerAdapter extends FragmentGridPagerAdapter {
//
//    private static final String Republican = "#EB5757";
//    private static final String Democrat = "#2F80ED";
//    private static final String Independent = "#BDBDBD";
//
//
//
//    private final Context mContext;
//    private List mRows;
//    private String[] senate_1_info;
//    private String[] senate_2_info;
//    private String[] house_1_info;
//    private String[] house_2_info;
//
//    public SampleGridPagerAdapter(Context ctx, FragmentManager fm, Intent intent) {
//        super(fm);
//        mContext = ctx;
//        Bundle extras = intent.getExtras();
//
//
//        //Get datas
//        String senate_1 = extras.getString("senate_1");
//        senate_1_info = senate_1.split("@");
//
//        String senate_2 = extras.getString("senate_2");
//        senate_2_info = senate_2.split("@");
//
//        String house_1 = extras.getString("house_1");
//        house_1_info = house_1.split("@");
//
//        String house_2 = extras.getString("house_2");
//        house_2_info = house_2.split("@");
//
//        Page senate_1_p = new Page();
//        senate_1_p.titleRes = senate_1_info[0];
//        senate_1_p.party = senate_1_info[1];
//        // senate_1_p.iconRes = R.drawable.donkey;
//        PAGES[0][0] = senate_1_p;
//
//        Page senate_2_p = new Page();
//        senate_2_p.titleRes = senate_2_info[0];
//        senate_2_p.party = senate_2_info[1];
//        // senate_2_p.iconRes = R.drawable.donkey;
//        PAGES[0][1] = senate_2_p;
//
//        Page house_1_p = new Page();
//        house_1_p.titleRes = house_1_info[0];
//        house_1_p.party = house_1_info[1];
//        // house_1_p.iconRes = R.drawable.donkey;
//        PAGES[0][2] = house_1_p;
//
//        Page house_2_p = new Page();
//        house_2_p.titleRes = house_2_info[0];
//        house_2_p.party = house_2_info[1];
//        // house_2_p.iconRes = R.drawable.donkey;
//        PAGES[0][3] = house_2_p;
//    }
//
//    static final int[] BG_IMAGES = new int[] {
//        R.drawable.donkey, R.drawable.elephant
//    };
//
//    @Override
//    public Fragment getFragment(int row, int col) {
//        Page page = PAGES[row][col];
////        String title = page.titleRes.matches("") ? null : page.titleRes;
////        String text = null;
////        CardFragment temp = CardFragment.create(title,text);
//        CustomCard temp = CustomCard.newInstance(page.titleRes, page.party);
//        return temp;
//    }
//
//    @Override
//    public int getRowCount() {
//        return PAGES.length;
//    }
//
//    // Obtain the number of pages (horizontal)
//    @Override
//    public int getColumnCount(int rowNum) {
//        return PAGES[rowNum].length;
//    }
//
//    // A simple container for static data in each page
//    private static class Page {
//        // static resources
////        char name;
//        String party;
//        String titleRes;
//        int iconRes;
//        int cardGravity = 80;
//        int expansionDirection = 1;
//        float expansionFactor = 1;
//
//    }
//
//    // Create a static set of pages in a 2D array
//    private final Page[][] PAGES = {{null,null,null,null}};
//
//// Override methods in FragmentGridPagerAdapter
//}

