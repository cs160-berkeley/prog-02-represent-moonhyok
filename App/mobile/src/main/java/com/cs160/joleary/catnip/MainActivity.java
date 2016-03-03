package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";

    private Button mFindButton;
    private TextView mZipcode;
    private TextView mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFindButton = (Button) findViewById(R.id.find_button);
//        mSwap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Does nothing yet, but soon!
//                Intent myIntent = new Intent(CrunchTime.this, GoalCalories.class);
//                CrunchTime.this.startActivity(myIntent);
//            }
//        });

        mFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent sendPhoneIntent = new Intent(getBaseContext(), CongressionalMain.class);
                Intent sendWatchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);


                mZipcode = (TextView) findViewById(R.id.zipcode);
                mLocation = (TextView) findViewById(R.id.location);

                String zipcode = mZipcode.getText().toString();
                String location = mLocation.getText().toString();

                Log.d("I AM SAM ", location + " &&& " + zipcode);


                if (zipcode.matches("") && location.matches("")){
                    Toast.makeText(MainActivity.this, "Please write in at least one", Toast.LENGTH_LONG).show();
                    return;
                }


                //calculate and find all candidates here.

                String senate_1_name;
                String senate_1_party;
                String senate_1_email;
                String senate_1_web;
                String senate_1_tweet;
                String senate_1_pic;


                String senate_2_name;
                String senate_2_party;
                String senate_2_email;
                String senate_2_web;
                String senate_2_tweet;
                String senate_2_pic;

                String house_1_name;
                String house_1_party;
                String house_1_email;
                String house_1_web;
                String house_1_tweet;
                String house_1_pic;

                String house_2_name;
                String house_2_party;
                String house_2_email;
                String house_2_web;
                String house_2_tweet;
                String house_2_pic;

                if (zipcode.equals("")){
                    //find candidates based on location

                    senate_1_name = "Barbara Boxer";
                    senate_1_party = "Republican";
                    senate_1_email = "barbaraboxer@senate.gov";
                    senate_1_web = "barbaraboxer.government.gov";
                    senate_1_tweet = "senate 1 tweettttinggg";
                    senate_1_pic = "barbara_boxer";

                    senate_2_name = "Dianne Feinstein";
                    senate_2_party = "Republican";
                    senate_2_email = "diannefeinstein@senate.gov";
                    senate_2_web = "diannefeinstein.government.gov";
                    senate_2_tweet = "senate 2 tweettttinggg";
                    senate_2_pic = "dianne_feinstein";


                    house_1_name = "Barbara Lee";
                    house_1_party = "Democrat";
                    house_1_email = "barbaralee@house.gov";
                    house_1_web = "barbaralee.government.gov";
                    house_1_tweet = "house 1 tweettttinggg";
                    house_1_pic = "barbara_lee";



                    house_2_name = "";
                    house_2_party = "";
                    house_2_email = "";
                    house_2_web = "";
                    house_2_tweet = "";
                    house_2_pic = "";


                } else{
                    //find candidates based on zipcode

                    senate_1_name = "Dianne Feinstein";
                    senate_1_party = "Democrat";
                    senate_1_email = "diannefeinstein@senate.gov";
                    senate_1_web = "diannefeinstein.government.gov";
                    senate_1_tweet = "senate 1 tweettttinggg";
                    senate_1_pic = "dianne_feinstein";

                    senate_2_name = "Barbara Boxer";
                    senate_2_party = "Republican";
                    senate_2_email = "barbaraboxer@senate.gov";
                    senate_2_web = "barbaraboxer.government.gov";
                    senate_2_tweet = "senate 2 tweettttinggg";
                    senate_2_pic = "barbara_boxer";


                    house_1_name = "Barbara Lee";
                    house_1_party = "Republican";
                    house_1_email = "barbaralee@house.gov";
                    house_1_web = "barbaralee.government.gov";
                    house_1_tweet = "house 1 tweettttinggg";
                    house_1_pic = "barbara_lee";



                    house_2_name = "Pikachu";
                    house_2_party = "Independent";
                    house_2_email = "pikachu@house.gov";
                    house_2_web = "pikachu.government.gov";
                    house_2_tweet = "pika pika pikachu pika pika?";
                    house_2_pic = "pikachu";
                }


                if (senate_1_party.matches("Democrat")){
                    senate_1_party = Democrat;
                } else if (senate_1_party.matches("Republican")){
                    senate_1_party = Republican;
                } else {
                    senate_1_party = Independent;
                }

                if (senate_2_party.matches("Democrat")){
                    senate_2_party = Democrat;
                } else if (senate_2_party.matches("Republican")){
                    senate_2_party = Republican;
                } else {
                    senate_2_party = Independent;
                }

                if (house_1_party.matches("Democrat")){
                    house_1_party = Democrat;
                } else if (house_1_party.matches("Republican")){
                    house_1_party = Republican;
                } else {
                    house_1_party = Independent;
                }

                if (house_2_party.matches("Democrat")){
                    house_2_party = Democrat;
                } else if (house_2_party.matches("Republican")){
                    house_2_party = Republican;
                } else if (house_2_party.matches("Independent")){
                    house_2_party = Independent;
                } else{
                    house_2_party ="";
                }

                sendPhoneIntent.putExtra("senate_1_name",senate_1_name);
                sendPhoneIntent.putExtra("senate_2_name",senate_2_name);
                sendPhoneIntent.putExtra("house_1_name",house_1_name);
                sendPhoneIntent.putExtra("house_2_name",house_2_name);

                sendPhoneIntent.putExtra("senate_1_party",senate_1_party);
                sendPhoneIntent.putExtra("senate_2_party",senate_2_party);
                sendPhoneIntent.putExtra("house_1_party",house_1_party);
                sendPhoneIntent.putExtra("house_2_party",house_2_party);

                sendPhoneIntent.putExtra("senate_1_email",senate_1_email);
                sendPhoneIntent.putExtra("senate_2_email",senate_2_email);
                sendPhoneIntent.putExtra("house_1_email",house_1_email);
                sendPhoneIntent.putExtra("house_2_email",house_2_email);

                sendPhoneIntent.putExtra("senate_1_web",senate_1_web);
                sendPhoneIntent.putExtra("senate_2_web",senate_2_web);
                sendPhoneIntent.putExtra("house_1_web",house_1_web);
                sendPhoneIntent.putExtra("house_2_web",house_2_web);

                sendPhoneIntent.putExtra("senate_1_tweet",senate_1_tweet);
                sendPhoneIntent.putExtra("senate_2_tweet",senate_2_tweet);
                sendPhoneIntent.putExtra("house_1_tweet",house_1_tweet);
                sendPhoneIntent.putExtra("house_2_tweet", house_2_tweet);

                sendPhoneIntent.putExtra("senate_1_pic",senate_1_pic);
                sendPhoneIntent.putExtra("senate_2_pic",senate_2_pic);
                sendPhoneIntent.putExtra("house_2_pic",house_2_pic);
                sendPhoneIntent.putExtra("house_1_pic",house_1_pic);

                sendPhoneIntent.putExtra("zipcode",zipcode);
                sendPhoneIntent.putExtra("location",location);

                sendWatchIntent.putExtra("senate_1_name",senate_1_name);
                sendWatchIntent.putExtra("senate_2_name",senate_2_name);
                sendWatchIntent.putExtra("house_1_name",house_1_name);
                sendWatchIntent.putExtra("house_2_name",house_2_name);

                sendWatchIntent.putExtra("senate_1_party",senate_1_party);
                sendWatchIntent.putExtra("senate_2_party",senate_2_party);
                sendWatchIntent.putExtra("house_1_party",house_1_party);
                sendWatchIntent.putExtra("house_2_party",house_2_party);

                sendWatchIntent.putExtra("zipcode",zipcode);
                sendWatchIntent.putExtra("location",location);

                startService(sendWatchIntent);
                startActivity(sendPhoneIntent);



//                startService(sendIntent);
            }
        });

//        mLexyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Lexy");
//                startService(sendIntent);
//            }
//        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
