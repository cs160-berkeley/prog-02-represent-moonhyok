package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;


public class ElectionView extends Activity {

    private TextView mRomney;
    private TextView mObama;
    private TextView mLocation;

    private LinearLayout mHide1;
    private LinearLayout mHide2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String county = extras.getString("loc");
        String[] election_data = extras.getString("election_data").split("@");

        mLocation = (TextView) findViewById(R.id.location);
        mObama = (TextView) findViewById(R.id.obama);
        mRomney = (TextView) findViewById(R.id.romney);

        mHide1 = (LinearLayout) findViewById(R.id.hide1);
        mHide2 = (LinearLayout) findViewById(R.id.hide2);

        try {
            Log.d("county in Election",county);
            if (county == ""){
                mLocation.setText("API did not provide the info");
                mHide1.setVisibility(View.GONE);
                mHide2.setVisibility(View.GONE);
            } else {
                mLocation.setText(county + " County");
                mObama.setText(election_data[0] + "%");
                mRomney.setText(election_data[1] + "%");
                mHide1.setVisibility(View.VISIBLE);
                mHide2.setVisibility(View.VISIBLE);
            }
        } catch (Exception e){
            mLocation.setText("API did not provide the info");
            mHide1.setVisibility(View.GONE);
            mHide2.setVisibility(View.GONE);
        }

    }
}
