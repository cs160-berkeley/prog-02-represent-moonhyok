package com.cs160.joleary.catnip;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.*;

import io.fabric.sdk.android.Fabric;
import retrofit.http.GET;
import retrofit.http.Query;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DetailedView extends AppCompatActivity {

    private static final String SUNLIGHT_COMMITTEE_URL = "https://congress.api.sunlightfoundation.com/committees?member_ids=";
    private static final String SUNLIGHT_BILL_URL = "https://congress.api.sunlightfoundation.com/bills?sponsor_id=";

    private static final String TWITTER_KEY = "mKLOct918bq0ZWDZBvFXV9psa";
    private static final String TWITTER_SECRET = "xFQYQx8rGec4j8QH0PVVEUFvLllLjYIAe98LW9xy49xagXNElr";

    private static final String SUNLIGHT_KEY = "&apikey=85a524311c674e67986cdb2687d38c78";

    private ProgressBar progressBar;

    private GoogleApiClient client;

    private String name;
    private String party;
    private String end;
    private String bioguide;
    private String pic;
    private String tweet;

    private ImageView mPicture;
    private TextView mName;
    private TextView mDate;
    private TextView mCommittee;
    private TextView mBill;

    private TextView mInfoTerm;
    private TextView mInfoComm;
    private TextView mInfoBill;
    
    private ArrayList<String> committees;
    private ArrayList<String> bills;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mInfoTerm = (TextView) findViewById(R.id.info_term);
        mInfoComm = (TextView) findViewById(R.id.info_committee);
        mInfoBill = (TextView) findViewById(R.id.info_bill);

        progressBar.setVisibility(View.VISIBLE);
        mInfoTerm.setVisibility(View.GONE);
        mInfoComm.setVisibility(View.GONE);
        mInfoBill.setVisibility(View.GONE);

        Bundle extras = getIntent().getExtras();

        name = extras.getString("name");
        party = extras.getString("party");
        end = extras.getString("end");
        bioguide = extras.getString("bioguide");
        tweet = extras.getString("tweet");
        pic = extras.getString("pic");
        try {
            Log.d("picc",pic);
            Log.d("SUULMA??",tweet);
        } catch (Exception e){
            TwitterAuthConfig authConfig =  new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
            Fabric.with(this, new Twitter(authConfig));
            TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
                @Override
                public void success(Result<AppSession> appSessionResult) {
                    AppSession session = appSessionResult.data;
                    TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
                    twitterApiClient.getStatusesService().userTimeline(null, tweet, 1, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                        @Override
                        public void success(Result<List<Tweet>> listResult) {
                            Tweet result = listResult.data.get(0);
                            pic = result.user.profileImageUrlHttps.replace("_normal", "");
                            Log.d("picuter after", pic);
                        }

                        @Override
                        public void failure(TwitterException e) {
                            Log.d("FAILED11111", "FAILED"+e);
                            e.printStackTrace();
                        }
                    });
                }

                @Override
                public void failure(TwitterException e) {
                    e.printStackTrace();
                    Log.d("FAILED22222", "FAILED");
                }
            });
        }

        new RetrieveCommitteeInfo().execute();
        new RetrieveBillInfo().execute();


        mName = (TextView) findViewById(R.id.name);
        mDate = (TextView) findViewById(R.id.end_date);
        mPicture = (ImageView) findViewById(R.id.picture);
        mCommittee = (TextView) findViewById(R.id.current_committee);
        mBill = (TextView) findViewById(R.id.recent_bills);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                content();
            }
        }, 2000);

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


    }

    private void content(){
        progressBar.setVisibility(View.GONE);
        mInfoTerm.setVisibility(View.VISIBLE);
        mInfoComm.setVisibility(View.VISIBLE);
        mInfoBill.setVisibility(View.VISIBLE);


//
        mName.setText(name);
        mName.setTextColor(Color.parseColor(party));

        String committee = "* "+committees.get(0);
        for (int i =1;i<committees.size();i++){
            committee += "\n* "+committees.get(i);
        }
        mCommittee.setText(committee);

        String bill = "* "+bills.get(0);
        for (int i =1;i<bills.size();i++){
            bill += "\n* "+bills.get(i);
        }
        mBill.setText(bill);

        mDate.setText(end);

        Picasso.with(this)
                .load(pic)
                .resize(160,160)
                .into(mPicture);

    }
    class RetrieveCommitteeInfo extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                URL url = new URL(SUNLIGHT_COMMITTEE_URL + bioguide + SUNLIGHT_KEY);
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
//            Log.i("INFO", response.getClass().getSimpleName());
            // TODO: check this.exception
            // TODO: do something with the feed

            try {
                Map<String, Object> candidateInfo = new Gson().fromJson(response, new TypeToken<HashMap<Object,Object>>() {}.getType());
                ArrayList<Object> temp = ((ArrayList<Object>) candidateInfo.get("results"));

                committees = new ArrayList<String>();
                Map<String,String> data;
                int count = 0;
                for (int i = 0; i<temp.size();i++){
                    if (count==2){
                        break;
                    }
                    data = (Map<String,String>) temp.get(i);
//                    Log.d("STRING LENGTH",Integer.toString(data.get("name").length()));
                    if (data.get("name").length()<=100){
                        committees.add(count,data.get("name"));
                        count ++;
                    }

                }

//                Log.d("commitees",committees.toString());


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class RetrieveBillInfo extends AsyncTask<Void, Void, String> {

        private Exception exception;

        protected void onPreExecute() {
        }

        protected String doInBackground(Void... urls) {
            // Do some validation here

            try {
                URL url = new URL(SUNLIGHT_BILL_URL + bioguide + SUNLIGHT_KEY);
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
//            Log.i("INFO", response.getClass().getSimpleName());
            // TODO: check this.exception
            // TODO: do something with the feed
            //short_title
            try {
                Map<String, Object> candidateInfo = new Gson().fromJson(response, new TypeToken<HashMap<Object,Object>>() {}.getType());
                ArrayList<Map<String,String>> temp = ((ArrayList<Map<String,String>>) candidateInfo.get("results"));

                bills = new ArrayList<String>();
                Map<String,String> billData;
//                Log.d("number of bills",Integer.toString(temp.size()));
                int count = 0;
                for (int i = 0; i<temp.size();i++){
                    if (count==2){
                        break;
                    }
                    billData = temp.get(i);
                    try{
//                        Log.d("bills",billData.get("short_title").toString());
                        billData.get("short_title").length();
                        bills.add(count, billData.get("short_title"));
                        count++;
                    } catch (Exception e){
                        Log.d("NULLPOINTER","NULLPOINNNNTER");
                    }
                }

                Log.d("BILLS",bills.toString());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    class MyTwitterApiClient extends TwitterApiClient {
        public MyTwitterApiClient(TwitterSession session) {
            super(session);
        }

        public UsersService getUsersService() {
            return getService(UsersService.class);
        }
    }


    interface UsersService {
        @GET("/1.1/users/show.json")
        void show(@Query("user_id") Long userId,
                  @Query("screen_name") String screenName,
                  @Query("include_entities") Boolean includeEntities,
                  Callback<User> cb);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CongressionalMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.joleary.catnip/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "CongressionalMain Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.cs160.joleary.catnip/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


}
