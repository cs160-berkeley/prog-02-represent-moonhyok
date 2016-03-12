package com.cs160.joleary.catnip;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
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
import com.koushikdutta.ion.Ion;
import com.squareup.picasso.Picasso;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.*;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.core.models.User;
import com.twitter.sdk.android.core.services.StatusesService;
import com.twitter.sdk.android.tweetui.*;

import io.fabric.sdk.android.Fabric;
import retrofit.http.GET;
import retrofit.http.Query;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.net.URL;
import java.util.List;


public class CongressionalMain extends AppCompatActivity {

    private static final String TWITTER_KEY = "mKLOct918bq0ZWDZBvFXV9psa";
    private static final String TWITTER_SECRET = "xFQYQx8rGec4j8QH0PVVEUFvLllLjYIAe98LW9xy49xagXNElr";

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";

    private ProgressBar progressBar;

    private TextView mLocation;
    private LinearLayout mLocationInfo;

    private TextView mSenate;
    private TextView mHouse;

    private TextView mSenate1Name;
    private RelativeLayout mSenate1Party;
    private ImageView mSenate1Pic;
    private TextView mSenate1Email;
    private TextView mSenate1Tweet;
    private TextView mSenate1Web;
    private TextView mSenate2Name;
    private RelativeLayout mSenate2Party;
    private ImageView mSenate2Pic;
    private TextView mSenate2Email;
    private TextView mSenate2Tweet;
    private TextView mSenate2Web;
    private TextView mHouse1Name;
    private RelativeLayout mHouse1Party;
    private ImageView mHouse1Pic;
    private TextView mHouse1Email;
    private TextView mHouse1Tweet;
    private TextView mHouse1Web;
    private TextView mHouse2Name;
    private RelativeLayout mHouse2Party;
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
    private String senate_1_end;
    private String senate_1_bioguide;

    private String senate_2_name;
    private String senate_2_party;
    private String senate_2_tweet;
    private String senate_2_email;
    private String senate_2_web;
    private String senate_2_pic;
    private String senate_2_end;
    private String senate_2_bioguide;

    private String house_1_name;
    private String house_1_party;
    private String house_1_tweet;
    private String house_1_email;
    private String house_1_web;
    private String house_1_pic;
    private String house_1_end;
    private String house_1_bioguide;

    private String house_2_name;
    private String house_2_party;
    private String house_2_tweet;
    private String house_2_email;
    private String house_2_web;
    private String house_2_pic;
    private String house_2_end;
    private String house_2_bioguide;

    private Long senate_1_tweet_id;
    private Long senate_2_tweet_id;
    private Long house_1_tweet_id;
    private Long house_2_tweet_id;


    private String location;
    private String zipcode;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        mLocation = (TextView) findViewById(R.id.show_location);
        mLocationInfo = (LinearLayout) findViewById(R.id.location_info);

        mSenate = (TextView) findViewById(R.id.senate);
        mHouse = (TextView) findViewById(R.id.house);
        mSenate1Party = (RelativeLayout) findViewById(R.id.senate_1_party);
        mSenate2Party = (RelativeLayout) findViewById(R.id.senate_2_party);
        mHouse1Party = (RelativeLayout) findViewById(R.id.house_1_party);
        mHouse2Party = (RelativeLayout) findViewById(R.id.house_2_party);

        mLocationInfo.setVisibility(View.GONE);
        mSenate.setVisibility(View.GONE);
        mHouse.setVisibility(View.GONE);
        mSenate1Party.setVisibility(View.GONE);
        mSenate2Party.setVisibility(View.GONE);
        mHouse1Party.setVisibility(View.GONE);
        mHouse2Party.setVisibility(View.GONE);



        progressBar.setVisibility(View.VISIBLE);



        Bundle extras = getIntent().getExtras();

        location = extras.getString("location");
        zipcode = extras.getString("zipcode");

        senate_1_name = extras.getString("senate_1_name");
        senate_1_party = extras.getString("senate_1_party");
        senate_1_tweet = extras.getString("senate_1_tweet");
        senate_1_email = extras.getString("senate_1_email");
        senate_1_web = extras.getString("senate_1_web");
        senate_1_end = extras.getString("senate_1_end");
        senate_1_bioguide = extras.getString("senate_1_bioguide");

        senate_2_name = extras.getString("senate_2_name");
        senate_2_party = extras.getString("senate_2_party");
        senate_2_tweet = extras.getString("senate_2_tweet");
        senate_2_email = extras.getString("senate_2_email");
        senate_2_web = extras.getString("senate_2_web");
        senate_2_pic = extras.getString("senate_2_pic");
        senate_2_end = extras.getString("senate_2_end");
        senate_2_bioguide = extras.getString("senate_2_bioguide");

        house_1_name = extras.getString("house_1_name");
        house_1_party = extras.getString("house_1_party");
        house_1_tweet = extras.getString("house_1_tweet");
        house_1_email = extras.getString("house_1_email");
        house_1_web = extras.getString("house_1_web");
        house_1_pic = extras.getString("house_1_pic");
        house_1_end = extras.getString("house_1_end");
        house_1_bioguide = extras.getString("house_1_bioguide");

        house_2_name = extras.getString("house_2_name");
        house_2_party = extras.getString("house_2_party");
        house_2_tweet = extras.getString("house_2_tweet");
        house_2_email = extras.getString("house_2_email");
        house_2_web = extras.getString("house_2_web");
        house_2_pic = extras.getString("house_2_pic");
        house_2_end = extras.getString("house_2_end");
        house_2_bioguide = extras.getString("house_2_bioguide");


        TwitterAuthConfig authConfig =  new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        TwitterCore.getInstance().logInGuest(new Callback<AppSession>() {
            @Override
            public void success(Result<AppSession> appSessionResult) {
                AppSession session = appSessionResult.data;
                TwitterApiClient twitterApiClient = TwitterCore.getInstance().getApiClient(session);
                twitterApiClient.getStatusesService().userTimeline(null, senate_1_tweet, 10, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        Tweet result = listResult.data.get(0);
                        senate_1_pic = result.user.profileImageUrlHttps.replace("_normal", "");
                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });
                twitterApiClient.getStatusesService().userTimeline(null, senate_2_tweet, 10, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        Tweet result = listResult.data.get(0);
                        senate_2_pic = result.user.profileImageUrlHttps.replace("_normal", "");
                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });
                twitterApiClient.getStatusesService().userTimeline(null, house_1_tweet, 10, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> listResult) {
                        Tweet result = listResult.data.get(0);
                        house_1_pic = result.user.profileImageUrlHttps.replace("_normal", "");
                    }

                    @Override
                    public void failure(TwitterException e) {
                        e.printStackTrace();
                    }
                });
                if (!house_2_name.equals(" ")){
                    twitterApiClient.getStatusesService().userTimeline(null, house_2_tweet, 10, null, null, false, false, false, true, new Callback<List<Tweet>>() {
                        @Override
                        public void success(Result<List<Tweet>> listResult) {
                            Tweet result = listResult.data.get(0);
                            house_2_pic = result.user.profileImageUrlHttps.replace("_normal", "");
                        }

                        @Override
                        public void failure(TwitterException e) {
                            e.printStackTrace();
                        }
                    });
                }

            }

            @Override
            public void failure(TwitterException e) {
                e.printStackTrace();
            }
        });


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                showContent();

            }
        }, 3000);




    }

    public void onClick(View v) {
        Intent sendIntent = new Intent(getBaseContext(), DetailedView.class);
        String clicked = v.getTag().toString();
        if (clicked.matches("senate1")) {
            sendIntent.putExtra("name", senate_1_name);
            sendIntent.putExtra("party", senate_1_party);
            sendIntent.putExtra("end", senate_1_end);
            sendIntent.putExtra("bioguide", senate_1_bioguide);
            sendIntent.putExtra("pic", senate_1_pic);
        } else if (clicked.matches("senate2")) {
            sendIntent.putExtra("name", senate_2_name);
            sendIntent.putExtra("party", senate_2_party);
            sendIntent.putExtra("end", senate_2_end);
            sendIntent.putExtra("bioguide", senate_2_bioguide);
            sendIntent.putExtra("pic", senate_2_pic);
        } else if (clicked.matches("house1")) {
            sendIntent.putExtra("name", house_1_name);
            sendIntent.putExtra("party", house_1_party);
            sendIntent.putExtra("end", house_1_end);
            sendIntent.putExtra("bioguide", house_1_bioguide);
            sendIntent.putExtra("pic", house_1_pic);
        } else {
            sendIntent.putExtra("name", house_2_name);
            sendIntent.putExtra("party", house_2_party);
            sendIntent.putExtra("end", house_2_end);
            sendIntent.putExtra("bioguide", house_2_bioguide);
            sendIntent.putExtra("pic", house_2_pic);
        }

        startActivity(sendIntent);
    }

    public void twitterOnClick(View v) {
        Intent sendIntent = new Intent(getBaseContext(), DetailedTweet.class);
        String clicked = v.getTag().toString();
        if (clicked.matches("senate1")) {
            sendIntent.putExtra("tweet", senate_1_tweet);
        } else if (clicked.matches("senate2")) {
            sendIntent.putExtra("tweet", senate_2_tweet);
        } else if (clicked.matches("house1")) {
            sendIntent.putExtra("tweet", house_1_tweet);
        } else {
            sendIntent.putExtra("tweet", house_2_tweet);
        }

        Log.d("tweeid@@!EDSAD",Long.toString(sendIntent.getExtras().getLong("tweet_id")));

        startActivity(sendIntent);
    }

    private void showContent(){
        progressBar.setVisibility(View.GONE);

        mLocationInfo.setVisibility(View.VISIBLE);
        mSenate.setVisibility(View.VISIBLE);
        mHouse.setVisibility(View.VISIBLE);
        mSenate1Party.setVisibility(View.VISIBLE);
        mSenate2Party.setVisibility(View.VISIBLE);
        mHouse1Party.setVisibility(View.VISIBLE);
        mHouse2Party.setVisibility(View.VISIBLE);


        mSenate1Name = (TextView) findViewById(R.id.senate_1_name);
        mSenate1Pic = (ImageView) findViewById(R.id.senate_1_pic);
        mSenate1Email = (TextView) findViewById(R.id.senate_1_email);
//        mSenate1Tweet = (TextView) findViewById(R.id.senate_1_tweet);
        mSenate1Web = (TextView) findViewById(R.id.senate_1_web);

        mSenate2Name = (TextView) findViewById(R.id.senate_2_name);
        mSenate2Pic = (ImageView) findViewById(R.id.senate_2_pic);
        mSenate2Email = (TextView) findViewById(R.id.senate_2_email);
//        mSenate2Tweet = (TextView) findViewById(R.id.senate_2_tweet);
        mSenate2Web = (TextView) findViewById(R.id.senate_2_web);

        mHouse1Name = (TextView) findViewById(R.id.house_1_name);
        mHouse1Pic = (ImageView) findViewById(R.id.house_1_pic);
        mHouse1Email = (TextView) findViewById(R.id.house_1_email);
//        mHouse1Tweet = (TextView) findViewById(R.id.house_1_tweet);
        mHouse1Web = (TextView) findViewById(R.id.house_1_web);

        mHouse2Name = (TextView) findViewById(R.id.house_2_name);
        mHouse2Pic = (ImageView) findViewById(R.id.house_2_pic);
        mHouse2Email = (TextView) findViewById(R.id.house_2_email);
//        mHouse2Tweet = (TextView) findViewById(R.id.house_2_tweet);
        mHouse2Web = (TextView) findViewById(R.id.house_2_web);


        mLocation.setText(zipcode);


        mSenate1Name.setText(senate_1_name);
        mSenate1Party.setBackgroundColor(Color.parseColor(senate_1_party));
        mSenate1Email.setText(senate_1_email);
//        mSenate1Tweet.setText(senate_1_tweet);
        mSenate1Web.setText(senate_1_web);

        Picasso.with(this)
                .load(senate_1_pic)
                .resize(95, 85)
                .into(mSenate1Pic);

        mSenate2Name.setText(senate_2_name);
        mSenate2Party.setBackgroundColor(Color.parseColor(senate_2_party));
        mSenate2Email.setText(senate_2_email);
//        mSenate2Tweet.setText(senate_2_tweet);
        mSenate2Web.setText(senate_2_web);

        Picasso.with(this)
                .load(senate_2_pic)
                .resize(95, 85)
                .into(mSenate2Pic);

        mHouse1Name.setText(house_1_name);
        mHouse1Party.setBackgroundColor(Color.parseColor(house_1_party));
        mHouse1Email.setText(house_1_email);
//        mHouse1Tweet.setText(house_1_tweet);
        mHouse1Web.setText(house_1_web);

        Picasso.with(this)
                .load(house_1_pic)
                .resize(95, 85)
                .into(mHouse1Pic);


        RelativeLayout house2 = (RelativeLayout) findViewById(R.id.house_2_party);
        if (house_2_name.equals(" ")) {
            house2.setVisibility(View.GONE);
        } else {
//            house2.setVisibility(View.VISIBLE);

            mHouse2Name.setText(house_2_name);
            mHouse2Party.setBackgroundColor(Color.parseColor(house_2_party));
            mHouse2Email.setText(house_2_email);
//            mHouse2Tweet.setText(house_2_tweet);
            mHouse2Web.setText(house_2_web);

            Picasso.with(this)
                    .load(house_2_pic)
                    .resize(95,85)
                    .into(mHouse2Pic);
        }

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
}
