package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.widget.TextView;

public class ElectionView extends Activity {

    private TextView mRomney;
    private TextView mObama;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_election_view);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        String loc = extras.getString("loc");
        mLocation = (TextView) findViewById(R.id.location);
        mLocation.setText(loc);

        mObama = (TextView) findViewById(R.id.obama);
        mRomney = (TextView) findViewById(R.id.romney);

        try{
            if (Integer.parseInt(loc)%2 == 0) {
                int prob = (int) (Math.random() * 100);
                mObama.setText(prob + "%");
                mRomney.setText((100 - prob) + "%");
            } else{
                int prob = (int) (Math.random() * 100);
                mObama.setText(prob + "%");
                mRomney.setText((100 - prob) + "%");
            }
        } catch (Exception e){
            int prob = (int) (Math.random() * 100);
            mObama.setText(prob + "%");
            mRomney.setText((100 - prob) + "%");
        }

//        if (Integer.parseInt(loc)%2 == 0) {
//            int prob = (int) (Math.random() * 100);
//            mObama.setText(prob + "%");
//            mRomney.setText((100 - prob) + "%")
//        } else{
//            int prob = (int) (Math.random() * 100);
//            mObama.setText(prob + "%");
//            mRomney.setText((100 - prob) + "%");
//        }

//        final WatchViewStub stub = (WatchViewStub) findViewById(R.id.watch_view_stub);
//        stub.setOnLayoutInflatedLitener(new WatchViewStub.OnLayoutInflatedListener() {
//            @Override
//            public void onLayoutInflated(WatchViewStub stub) {
//                mTextView = (TextView) stub.findViewById(R.id.text);
//            }
//        });
    }
}
