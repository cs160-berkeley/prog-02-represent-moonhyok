package com.cs160.joleary.catnip;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.wearable.view.CardFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by moonhyok on 2/29/16.
 */
public class MyCardFragment extends CardFragment{
    String name;
    String party;
    String loc;
    String election_data;
    String tweet;

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";

    public static MyCardFragment newInstance(String name,String party, String loc, String tweet, String election_data) {
        MyCardFragment f = new MyCardFragment();

        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putString("party", party);
        args.putString("name", name);
        args.putString("loc", loc);
        args.putString("tweet", tweet);
        args.putString("election_data",election_data);
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.custom_card, null);

//        ImageView image = (ImageView) v.findViewById(R.id.pic);
//        LinearLayout background = (LinearLayout) v.findViewById(R.id.watch_background);
        TextView text = (TextView) v.findViewById(R.id.cand_name);


        name = getArguments().getString("name");
        party = getArguments().getString("party");
        loc = getArguments().getString("loc");
        tweet = getArguments().getString("tweet");
        Log.d("tweet in cardfragment", tweet);
        election_data = getArguments().getString("election_data");
        text.setText(name);
//        btn.setVisibility(View.VISIBLE);

//        if (party.matches(Republican)){
//            image.setImageResource(R.drawable.elephant);
//            background.setBackgroundColor(Color.parseColor(Republican));

//        } else if (party.matches(Democrat)){
//
//            image.setImageResource(R.drawable.donkey);
//            background.setBackgroundColor(Color.parseColor(Democrat));

//        }

//        Button mElectBtn = (Button) v.findViewById(R.id.elect);
////        mBackgnd = (LinearLayout) findViewById(R.id.background);
////        mName = (TextView) findViewById(R.id.name);
////        mPic = (ImageView) findViewById(R.id.pic);
//
//
//
//        mElectBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                    Intent sendIntent = new Intent(v.getContext(), ElectionView.class);
//                    sendIntent.putExtra("loc", loc);
//                    Log.d("traking card2", loc);
//                    sendIntent.putExtra("election_data",election_data);
//                    startActivity(sendIntent);
//            }
//        });


        return v;

    }


}
