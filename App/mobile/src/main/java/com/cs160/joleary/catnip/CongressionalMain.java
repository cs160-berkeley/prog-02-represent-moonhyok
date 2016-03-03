package com.cs160.joleary.catnip;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class CongressionalMain extends Activity {

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";

    private TextView mLocation;

    private TextView mSenate1Name;
    private LinearLayout mSenate1Party;
    private ImageView mSenate1Pic;
    private TextView mSenate1Email;
    private TextView mSenate1Tweet;
    private TextView mSenate1Web;
    private TextView mSenate2Name;
    private LinearLayout mSenate2Party;
    private ImageView mSenate2Pic;
    private TextView mSenate2Email;
    private TextView mSenate2Tweet;
    private TextView mSenate2Web;
    private TextView mHouse1Name;
    private LinearLayout mHouse1Party;
    private ImageView mHouse1Pic;
    private TextView mHouse1Email;
    private TextView mHouse1Tweet;
    private TextView mHouse1Web;
    private TextView mHouse2Name;
    private LinearLayout mHouse2Party;
    private ImageView mHouse2Pic;
    private TextView mHouse2Email;
    private TextView mHouse2Tweet;
    private TextView mHouse2Web;

    private String senate_1_name;
    private String senate_1_party;
    private String senate_1_tweet;
    private String senate_1_email;
    private String senate_1_web;
    private String senate_1_pic;

    private String senate_2_name;
    private String senate_2_party;
    private String senate_2_tweet;
    private String senate_2_email;
    private String senate_2_web;
    private String senate_2_pic;

    private String house_1_name;
    private String house_1_party;
    private String house_1_tweet;
    private String house_1_email;
    private String house_1_web;
    private String house_1_pic;
    
    private String house_2_name;
    private String house_2_party;
    private String house_2_tweet;
    private String house_2_email;
    private String house_2_web;
    private String house_2_pic;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_main);

        mLocation = (TextView) findViewById(R.id.show_location);

        mSenate1Name = (TextView) findViewById(R.id.senate_1_name);
        mSenate1Party = (LinearLayout) findViewById(R.id.senate_1_party);
        mSenate1Pic = (ImageView) findViewById(R.id.senate_1_pic);
        mSenate1Email = (TextView) findViewById(R.id.senate_1_email);
        mSenate1Tweet = (TextView) findViewById(R.id.senate_1_tweet);
        mSenate1Web = (TextView) findViewById(R.id.senate_1_web);

        mSenate2Name = (TextView) findViewById(R.id.senate_2_name);
        mSenate2Party = (LinearLayout) findViewById(R.id.senate_2_party);
        mSenate2Pic = (ImageView) findViewById(R.id.senate_2_pic);
        mSenate2Email = (TextView) findViewById(R.id.senate_2_email);
        mSenate2Tweet = (TextView) findViewById(R.id.senate_2_tweet);
        mSenate2Web = (TextView) findViewById(R.id.senate_2_web);

        mHouse1Name = (TextView) findViewById(R.id.house_1_name);
        mHouse1Party = (LinearLayout) findViewById(R.id.house_1_party);
        mHouse1Pic = (ImageView) findViewById(R.id.house_1_pic);
        mHouse1Email = (TextView) findViewById(R.id.house_1_email);
        mHouse1Tweet = (TextView) findViewById(R.id.house_1_tweet);
        mHouse1Web = (TextView) findViewById(R.id.house_1_web);

        mHouse2Name = (TextView) findViewById(R.id.house_2_name);
        mHouse2Party = (LinearLayout) findViewById(R.id.house_2_party);
        mHouse2Pic = (ImageView) findViewById(R.id.house_2_pic);
        mHouse2Email = (TextView) findViewById(R.id.house_2_email);
        mHouse2Tweet = (TextView) findViewById(R.id.house_2_tweet);
        mHouse2Web = (TextView) findViewById(R.id.house_2_web);

        Bundle extras = getIntent().getExtras();

        String location = extras.getString("location");
        String zipcode = extras.getString("zipcode");

        if (zipcode.equals("")){
            mLocation.setText(location);
        } else {
            mLocation.setText(zipcode);
        }

        senate_1_name = extras.getString("senate_1_name");
        senate_1_party = extras.getString("senate_1_party");
        senate_1_tweet = extras.getString("senate_1_tweet");
        senate_1_email = extras.getString("senate_1_email");
        senate_1_web = extras.getString("senate_1_web");
        senate_1_pic = extras.getString("senate_1_pic");

        senate_2_name = extras.getString("senate_2_name");
        senate_2_party = extras.getString("senate_2_party");
        senate_2_tweet = extras.getString("senate_2_tweet");
        senate_2_email = extras.getString("senate_2_email");
        senate_2_web = extras.getString("senate_2_web");
        senate_2_pic = extras.getString("senate_2_pic");

        house_1_name = extras.getString("house_1_name");
        house_1_party = extras.getString("house_1_party");
        house_1_tweet = extras.getString("house_1_tweet");
        house_1_email = extras.getString("house_1_email");
        house_1_web = extras.getString("house_1_web");
        house_1_pic = extras.getString("house_1_pic");

        house_2_name = extras.getString("house_2_name");
        house_2_party = extras.getString("house_2_party");
        house_2_tweet = extras.getString("house_2_tweet");
        house_2_email = extras.getString("house_2_email");
        house_2_web = extras.getString("house_2_web");
        house_2_pic = extras.getString("house_2_pic");

        mSenate1Name.setText(senate_1_name);
        mSenate1Party.setBackgroundColor(Color.parseColor(senate_1_party));
        mSenate1Email.setText(senate_1_email);
        mSenate1Tweet.setText(senate_1_tweet);
        mSenate1Web.setText(senate_1_web);

        Resources res = getResources();
        int resIDS1 = res.getIdentifier(senate_1_pic , "drawable", getPackageName());
        Drawable drawableS1 = res.getDrawable(resIDS1);
        mSenate1Pic.setImageDrawable(drawableS1);

        mSenate2Name.setText(senate_2_name);
        mSenate2Party.setBackgroundColor(Color.parseColor(senate_2_party));
        mSenate2Email.setText(senate_2_email);
        mSenate2Tweet.setText(senate_2_tweet);
        mSenate2Web.setText(senate_2_web);

        int resIDS2 = res.getIdentifier(senate_2_pic , "drawable", getPackageName());
        Drawable drawableS2 = res.getDrawable(resIDS2);
        mSenate2Pic.setImageDrawable(drawableS2);

        mHouse1Name.setText(house_1_name);
        mHouse1Party.setBackgroundColor(Color.parseColor(house_1_party));
        mHouse1Email.setText(house_1_email);
        mHouse1Tweet.setText(house_1_tweet);
        mHouse1Web.setText(house_1_web);

        int resIDH1 = res.getIdentifier(house_1_pic , "drawable", getPackageName());
        Drawable drawableH1 = res.getDrawable(resIDH1);
        mHouse1Pic.setImageDrawable(drawableH1);

        ImageView icon = (ImageView) findViewById(R.id.house_2_icon);
        if (house_2_name.matches("")){
            icon.setVisibility(View.INVISIBLE);

        } else{
            icon.setVisibility(View.VISIBLE);

            mHouse2Name.setText(house_2_name);
            mHouse2Party.setBackgroundColor(Color.parseColor(house_2_party));
            mHouse2Email.setText(house_2_email);
            mHouse2Tweet.setText(house_2_tweet);
            mHouse2Web.setText(house_2_web);

            int resIDH2 = res.getIdentifier(house_2_pic , "drawable", getPackageName());
            Drawable drawableH2 = res.getDrawable(resIDH2);
            mHouse2Pic.setImageDrawable(drawableH2);
        }


    }

    public void onClick(View v) {
        Intent sendIntent = new Intent(getBaseContext(), DetailedView.class);
        String clicked = v.getTag().toString();
        if (clicked.matches("senate1")){
            sendIntent.putExtra("name",senate_1_name);
            sendIntent.putExtra("party",senate_1_party);
            sendIntent.putExtra("email",senate_1_email);
            sendIntent.putExtra("web",senate_1_web);
            sendIntent.putExtra("tweet",senate_1_tweet);
            sendIntent.putExtra("pic",senate_1_pic);
        } else if (clicked.matches("senate2")){
            sendIntent.putExtra("name",senate_2_name);
            sendIntent.putExtra("party",senate_2_party);
            sendIntent.putExtra("email",senate_2_email);
            sendIntent.putExtra("web",senate_2_web);
            sendIntent.putExtra("tweet",senate_2_tweet);
            sendIntent.putExtra("pic",senate_2_pic);
        } else if (clicked.matches("house1")){
            sendIntent.putExtra("name",house_1_name);
            sendIntent.putExtra("party",house_1_party);
            sendIntent.putExtra("email",house_1_email);
            sendIntent.putExtra("web",house_1_web);
            sendIntent.putExtra("tweet",house_1_tweet);
            sendIntent.putExtra("pic",house_1_pic);
        } else {
            sendIntent.putExtra("name",house_2_name);
            sendIntent.putExtra("party",house_2_party);
            sendIntent.putExtra("email",house_2_email);
            sendIntent.putExtra("web",house_2_web);
            sendIntent.putExtra("tweet",house_2_tweet);
            sendIntent.putExtra("pic",house_2_pic);
        }

        startActivity(sendIntent);
//        Toast.makeText(Congress
// 9876ionalMain.this, "Please write in at leastone", Toast.LENGTH_LONG).show();



    }

}
