package com.cs160.joleary.catnip;

import android.app.Activity;
import android.app.Notification;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.twitter.sdk.android.Twitter;

import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.TweetUtils;
import com.twitter.sdk.android.tweetui.TweetView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import android.content.Intent;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import io.fabric.sdk.android.Fabric;
import retrofit.client.Response;
import retrofit.http.GET;

public class MainActivity extends AppCompatActivity implements  ConnectionCallbacks, OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "mKLOct918bq0ZWDZBvFXV9psa";
    private static final String TWITTER_SECRET = "xFQYQx8rGec4j8QH0PVVEUFvLllLjYIAe98LW9xy49xagXNElr";

    private static final String SUNLIGHT_URL = "https://congress.api.sunlightfoundation.com/legislators/locate?zip=";
    private static final String SUNLIGHT_KEY = "&apikey=85a524311c674e67986cdb2687d38c78";

    private static final String GOOGLE_URL = "https://maps.googleapis.com/maps/api/geocode/json?address=";
    private static final String GOOGLE_KEY = "&key=AIzaSyCPjA7cPTNhyq3FfTTxkea5Q8RRydDyU0Q";


    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";

    private Button mMyLocation;
    private Button mFindButton;
    private TextView mZipcode;
    private TextView mOr;
    private TextView mLocation;

    private ProgressBar progressBar;

    boolean succesfulChange = false;
    boolean proceed = false;
    boolean validZipcode = false;

    String senate_1_name;
    String senate_1_party;
    String senate_1_email;
    String senate_1_web;
    String senate_1_tweet;
    String senate_1_pic;
    String senate_1_bills;
    String senate_1_end;
    String senate_1_committee;
    String senate_1_bioguide;

    String senate_2_name;
    String senate_2_party;
    String senate_2_email;
    String senate_2_web;
    String senate_2_tweet;
    String senate_2_pic;
    String senate_2_bills;
    String senate_2_end;
    String senate_2_committee;
    String senate_2_bioguide;


    String house_1_name;
    String house_1_party;
    String house_1_email;
    String house_1_web;
    String house_1_tweet;
    String house_1_pic;
    String house_1_bills;
    String house_1_end;
    String house_1_committee;
    String house_1_bioguide;


    String house_2_name;
    String house_2_party;
    String house_2_email;
    String house_2_web;
    String house_2_tweet;
    String house_2_pic;
    String house_2_bills;
    String house_2_end;
    String house_2_committee;
    String house_2_bioguide;

    String election_data;


    String zipcode;
    String location;
    String county;

    String latitude;
    String longitude;

    ArrayList<Map<String, Object>> election2012;

    boolean cameFromWatch = false;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient mGoogleApiClient;
    Location mLastLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent fromWatch = getIntent();
        Bundle extras = fromWatch.getExtras();

        try {
            InputStream stream = getAssets().open("election_county_2012.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            String jsonString = new String(buffer, "UTF-8");
            election2012 = new Gson().fromJson(jsonString, new TypeToken<ArrayList<HashMap<Object,Object>>>() {}.getType());
            Log.d("jsonString", election2012.get(0).toString());
        } catch (Exception e){
            Log.d("Error parsing", "jsonString");
        }


        buildGoogleApiClient();

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mZipcode = (TextView) findViewById(R.id.zipcode);
        mLocation = (TextView) findViewById(R.id.location);
        mOr = (TextView) findViewById(R.id.or);
        mMyLocation = (Button) findViewById(R.id.use_current);
        mFindButton = (Button) findViewById(R.id.find_button);
        try {
            cameFromWatch = extras.getBoolean("cameFromWatch");
        } catch (Exception e){

        }

        if (extras != null && cameFromWatch){
            succesfulChange = false;
            validZipcode = false;
            zipcode = extras.getString("zipcode");
            new RetrieveCountyInfo().execute();
            cameFromWatch = false;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                    gotZipcode();
                }
            }, 2000);
        }

        mMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zipcode = mZipcode.getText().toString();

                location = latitude+" "+longitude;

                new RetrieveCountyInfo().execute();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        gotZipcode();
                    }
                }, 2000);

            }
        });
        mFindButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                zipcode = mZipcode.getText().toString();
                location = mLocation.getText().toString();

                if (zipcode.matches("") && location.matches("")) {
                    Toast.makeText(MainActivity.this, "Please write in at least one", Toast.LENGTH_LONG).show();
                    return;
                }

                new RetrieveCountyInfo().execute();

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run() {
                        gotZipcode();
                    }
                }, 2000);

            }
        });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }


    private void gotZipcode(){

        if (zipcode.matches("") ) {
            progressBar.setVisibility(View.GONE);
            mMyLocation.setVisibility(View.VISIBLE);
            mOr.setVisibility(View.VISIBLE);
            mZipcode.setVisibility(View.VISIBLE);
            mLocation.setVisibility(View.VISIBLE);
            mFindButton.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "Please provide a more specific location", Toast.LENGTH_LONG).show();
            return;
        }
        proceed = true;

        new RetrieveCandidateInfo().execute();

        Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            public void run() {
                if (proceed) {
                    moveOn();
                }
            }
        }, 3000);

    }


    private void moveOn(){
        if (!validZipcode){
            Toast.makeText(MainActivity.this, "Not a valid zipcode", Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            mMyLocation.setVisibility(View.VISIBLE);
            mOr.setVisibility(View.VISIBLE);
            mZipcode.setVisibility(View.VISIBLE);
            mLocation.setVisibility(View.VISIBLE);
            mFindButton.setVisibility(View.VISIBLE);
            return;
        }

//        Log.d("mycounty",county);
        for (int i = 0; i <election2012.size();i++){
            if (election2012.get(i).get("county-name").toString().equals(county)){
                election_data = election2012.get(i).get("obama-percentage").toString()+"@"+election2012.get(i).get("romney-percentage").toString();
                Log.d("electiondata",election_data);
            }
        }



        Intent sendPhoneIntent = new Intent(getBaseContext(), CongressionalMain.class);
        Intent sendWatchIntent = new Intent(getBaseContext(), PhoneToWatchService.class);

        if (senate_1_party.equals("D")) {
            senate_1_party = Democrat;
        } else if (senate_1_party.equals("R")) {
            senate_1_party = Republican;
        } else {
            senate_1_party = Independent;
        }

        if (senate_2_party.equals("D")) {
            senate_2_party = Democrat;
        } else if (senate_2_party.equals("R")) {
            senate_2_party = Republican;
        } else {
            senate_2_party = Independent;
        }

        if (house_1_party.equals("D")) {
            house_1_party = Democrat;
        } else if (house_1_party.equals("R")) {
            house_1_party = Republican;
        } else {
            house_1_party = Independent;
        }

        if (house_2_party.equals("D")) {
            house_2_party = Democrat;
        } else if (house_2_party.equals("R")) {
            house_2_party = Republican;
        } else if (house_2_party.equals("I")) {
            house_2_party = Independent;
        } else {
            house_2_party = "";
        }

        sendPhoneIntent.putExtra("senate_1_name", senate_1_name);
        sendPhoneIntent.putExtra("senate_2_name", senate_2_name);
        sendPhoneIntent.putExtra("house_1_name", house_1_name);
        sendPhoneIntent.putExtra("house_2_name", house_2_name);

        sendPhoneIntent.putExtra("senate_1_party", senate_1_party);
        sendPhoneIntent.putExtra("senate_2_party", senate_2_party);
        sendPhoneIntent.putExtra("house_1_party", house_1_party);
        sendPhoneIntent.putExtra("house_2_party", house_2_party);

        sendPhoneIntent.putExtra("senate_1_email", senate_1_email);
        sendPhoneIntent.putExtra("senate_2_email", senate_2_email);
        sendPhoneIntent.putExtra("house_1_email", house_1_email);
        sendPhoneIntent.putExtra("house_2_email", house_2_email);

        sendPhoneIntent.putExtra("senate_1_web", senate_1_web);
        sendPhoneIntent.putExtra("senate_2_web", senate_2_web);
        sendPhoneIntent.putExtra("house_1_web", house_1_web);
        sendPhoneIntent.putExtra("house_2_web", house_2_web);

        sendPhoneIntent.putExtra("senate_1_tweet", senate_1_tweet);
        sendPhoneIntent.putExtra("senate_2_tweet", senate_2_tweet);
        sendPhoneIntent.putExtra("house_1_tweet", house_1_tweet);
        sendPhoneIntent.putExtra("house_2_tweet", house_2_tweet);

        sendPhoneIntent.putExtra("senate_1_end", senate_1_end);
        sendPhoneIntent.putExtra("senate_2_end", senate_2_end);
        sendPhoneIntent.putExtra("house_2_end", house_2_end);
        sendPhoneIntent.putExtra("house_1_end", house_1_end);

        sendPhoneIntent.putExtra("senate_1_bioguide", senate_1_bioguide);
        sendPhoneIntent.putExtra("senate_2_bioguide", senate_2_bioguide);
        sendPhoneIntent.putExtra("house_2_bioguide", house_2_bioguide);
        sendPhoneIntent.putExtra("house_1_bioguide", house_1_bioguide);

        sendPhoneIntent.putExtra("zipcode", zipcode);
        sendPhoneIntent.putExtra("location", location);

        sendWatchIntent.putExtra("senate_1_name", senate_1_name);
        sendWatchIntent.putExtra("senate_2_name", senate_2_name);
        sendWatchIntent.putExtra("house_1_name", house_1_name);
        sendWatchIntent.putExtra("house_2_name", house_2_name);

        sendWatchIntent.putExtra("senate_1_party", senate_1_party);
        sendWatchIntent.putExtra("senate_2_party", senate_2_party);
        sendWatchIntent.putExtra("house_1_party", house_1_party);
        sendWatchIntent.putExtra("house_2_party", house_2_party);

        sendWatchIntent.putExtra("senate_1_tweet", senate_1_tweet);
        sendWatchIntent.putExtra("senate_2_tweet", senate_2_tweet);
        sendWatchIntent.putExtra("house_1_tweet", house_1_tweet);
        sendWatchIntent.putExtra("house_2_tweet", house_2_tweet);

        sendWatchIntent.putExtra("senate_1_end", senate_1_end);
        sendWatchIntent.putExtra("senate_2_end", senate_2_end);
        sendWatchIntent.putExtra("house_2_end", house_2_end);
        sendWatchIntent.putExtra("house_1_end", house_1_end);

        sendWatchIntent.putExtra("senate_1_bioguide", senate_1_bioguide);
        sendWatchIntent.putExtra("senate_2_bioguide", senate_2_bioguide);
        sendWatchIntent.putExtra("house_2_bioguide", house_2_bioguide);
        sendWatchIntent.putExtra("house_1_bioguide", house_1_bioguide);

        sendWatchIntent.putExtra("election_data",election_data);

        sendWatchIntent.putExtra("zipcode", zipcode);
        sendWatchIntent.putExtra("county", county);

        progressBar.setVisibility(View.GONE);
        mOr.setVisibility(View.VISIBLE);
        mMyLocation.setVisibility(View.VISIBLE);
        mZipcode.setVisibility(View.VISIBLE);
        mLocation.setVisibility(View.VISIBLE);
        mFindButton.setVisibility(View.VISIBLE);

        startService(sendWatchIntent);
        startActivity(sendPhoneIntent);
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

    @Override
    public void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            latitude = String.valueOf(mLastLocation.getLatitude());
            longitude = String.valueOf(mLastLocation.getLongitude());
            Log.d("LastLocation",mLastLocation.toString());
        }
        Log.d("Latitude and Longitude",latitude+" & "+longitude);
    }

    @Override
    public void onConnectionSuspended(int i) {
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }
//
//    @Override
//    public void onConnected(Bundle bundle) {
//
//    }
//
//    @Override
//    public void onConnectionSuspended(int i) {
//
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult connectionResult) {
//
//    }


    class RetrieveCandidateInfo extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                //TODO: Change location into zipcode or vise versa.
                URL url = new URL(SUNLIGHT_URL + zipcode + SUNLIGHT_KEY);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                    //TODO:move the senate data parsing here.
                    //TODO:add committee API pull based on names 
                    //TODO:bills API pull


                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
            // TODO: check this.exception
            // TODO: do something with the feed

            try {
                Map<String, Object> candidateInfo = new Gson().fromJson(response, new TypeToken<HashMap<Object,Object>>() {}.getType());
                Integer count  = ((Double) candidateInfo.get("count")).intValue();
                Log.d("count",Integer.toString(count));
                if (count == 0 ){
                    validZipcode = false;
                    throw exception;
                }
                validZipcode = true;
                ArrayList<Object> temp = ((ArrayList<Object>) candidateInfo.get("results"));

                Map<String,String> senate1Data = null;
                Map<String,String> senate2Data = null;
                Map<String,String> house1Data = null;
                Map<String,String> house2Data = null;
                Map<String,String> tempData;

//                if (count > 3){
                int senateCount = 0;
                int houseCount = 0;
                for (int i = 0; i<temp.size();i++){
                    tempData = (Map<String,String>) temp.get(i);
                    for (int j = 0; j <tempData.size();j++){
                        if (tempData.get("chamber").equals("senate")){
                            if (senateCount == 0){
                                senate1Data = tempData;
                            } else {
                                senate2Data = tempData;
                            }
                            senateCount++;
                        } else {
                            if (houseCount == 0){
                                house1Data = tempData;
                            } else {
                                house2Data = tempData;
                            }
                            houseCount++;
                        }
                    }
                }

                if (count == 3) {
                    house2Data = new HashMap();
                    house2Data.put("first_name", "");
                    house2Data.put("last_name", "");
                    house2Data.put("party", "");
                    house2Data.put("oc_email", "");
                    house2Data.put("twitter_id", "");
                    house2Data.put("website", "");
                    house2Data.put("term_end", "");
                    house2Data.put("bioguide_id", "");
                }
//                 Log.d("senate/candidate data",senate1Data.toString());
                // Log.d("senate/candidate data",senate2Data.toString());
                // Log.d("senate/candidate data",house1Data.toString());
                // Log.d("senate/candidate data",house2Data.toString());

                senate_1_name = senate1Data.get("first_name") + " " + senate1Data.get("last_name");
                senate_1_party = senate1Data.get("party");
                senate_1_email = senate1Data.get("oc_email");
                senate_1_web = senate1Data.get("website");
                senate_1_tweet = senate1Data.get("twitter_id");
                senate_1_end = senate1Data.get("term_end");
                senate_1_bioguide = senate1Data.get("bioguide_id");

                senate_2_name = senate2Data.get("first_name") + " " + senate2Data.get("last_name");
                senate_2_party = senate2Data.get("party");
                senate_2_email = senate2Data.get("oc_email");
                senate_2_web = senate2Data.get("website");
                senate_2_tweet = senate2Data.get("twitter_id");
                senate_2_end = senate2Data.get("term_end");
                senate_2_bioguide = senate2Data.get("bioguide_id");

                house_1_name = house1Data.get("first_name") + " " + house1Data.get("last_name");
                house_1_party = house1Data.get("party");
                house_1_email = house1Data.get("oc_email");
                house_1_web = house1Data.get("website");
                house_1_tweet = house1Data.get("twitter_id");
                house_1_end = house1Data.get("term_end");
                house_1_bioguide = house1Data.get("bioguide_id");

                house_2_name = house2Data.get("first_name") + " " + house2Data.get("last_name");
                house_2_party = house2Data.get("party");
                house_2_email = house2Data.get("oc_email");
                house_2_web = house2Data.get("website");
                house_2_tweet = house2Data.get("twitter_id");
                house_2_end = house2Data.get("term_end");
                house_2_bioguide = house2Data.get("bioguide_id");



            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class RetrieveCountyInfo extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
            mMyLocation.setVisibility(View.GONE);
            mOr.setVisibility(View.GONE);
            mZipcode.setVisibility(View.GONE);
            mLocation.setVisibility(View.GONE);
            mFindButton.setVisibility(View.GONE);
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                //TODO: Change location into zipcode or vise versa.
                String apiLocation;
                try {
                    if (!location.equals("")) {
                        apiLocation = location.replace(' ', '+');
                    } else {
                        apiLocation = zipcode;
                    }
                } catch (Exception e){
                    apiLocation = zipcode;
                }
                Log.d("apiLocation",apiLocation);
                URL url = new URL(GOOGLE_URL + apiLocation + GOOGLE_KEY);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                try {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        stringBuilder.append(line).append("\n");
                    }
                    bufferedReader.close();
                    return stringBuilder.toString();
                    //TODO:move the senate data parsing here.
                    //TODO:add committee API pull based on names 
                    //TODO:bills API pull


                }
                finally{
                    urlConnection.disconnect();
                }
            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }
        }

        protected void onPostExecute(String response) {
            if(response == null) {
                response = "THERE WAS AN ERROR";
            }
//            Log.i("INFO", response);
            // Log.i("INFO", response.getClass().getSimpleName());
            // TODO: check this.exception
            // TODO: do something with the feed

            try {

                Map<String, Object> candidateInfo = new Gson().fromJson(response, new TypeToken<HashMap<Object, Object>>() {
                }.getType());
//                Integer count  = ((Double) candidateInfo.get("count")).intValue();
                ArrayList<Object> temp = ((ArrayList<Object>) candidateInfo.get("results"));
                Log.d("FUCKING",temp.toString());
                Map<String,Object> locationData=(Map<String,Object>) temp.get(0);
                ArrayList<Object> semiZipcode = (ArrayList<Object>) locationData.get("address_components");
                county ="";

//                Log.d("zipcode/???", Boolean.toString(zipcodeData.get("types").toString().equals("[postal_code]")));
                for (int i=0; i<semiZipcode.size();i++){
                    Map<String,Object> zipcodeData = (Map<String,Object>) semiZipcode.get(i);
                    Log.d("TYPE",zipcodeData.get("types").toString());
                    if (zipcodeData.get("types").toString().equals("[postal_code]")){
                        zipcode = zipcodeData.get("short_name").toString();
                        succesfulChange = true;
                    } else if (zipcodeData.get("types").toString().equals("[administrative_area_level_2, political]")) {
                        county = zipcodeData.get("short_name").toString().replace(" County","");
                    }

                }

                if (!succesfulChange){
                    zipcode = "";
                }
                //TODO: Still needs to be parsed.
                Log.d("ZIPCODE?", zipcode);
                Log.d("COUNTY?", county);

                // Map<String,String> senate1Data;
               


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
