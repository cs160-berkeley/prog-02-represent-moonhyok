package com.cs160.joleary.catnip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wearable.view.BoxInsetLayout;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import android.util.Log;

import java.util.List;

/**
 * Created by moonhyok on 2/29/16.
 */
public class MyGridPagerAdapter extends FragmentGridPagerAdapter {

    private static final String Republican = "#EB5757";
    private static final String Democrat = "#2F80ED";
    private static final String Independent = "#BDBDBD";


    private Page[][] PAGES={{null,null,null,null}};

    private final Context mContext;
    private List mRows;
    private String[] senate_1_info;
    private String[] senate_2_info;
    private String[] house_1_info;
    private String[] house_2_info;
    private String election_data;

    int numColumn = 4;

    public MyGridPagerAdapter(Context ctx, FragmentManager fm, Intent intent) {
        super(fm);
        mContext = ctx;
        Bundle extras = intent.getExtras();

        String county;

        county = extras.getString("county");


        election_data = extras.getString("election_data");

        //Get data
        String senate_1 = extras.getString("senate_1");
        senate_1_info = senate_1.split("@");

        String senate_2 = extras.getString("senate_2");
        senate_2_info = senate_2.split("@");

        String house_1 = extras.getString("house_1");
        house_1_info = house_1.split("@");

        String house_2 = extras.getString("house_2");
        if (house_2.matches(" @@@@")){
            numColumn = 3;
            notifyDataSetChanged();
        } else{
            house_2_info = house_2.split("@");
            Log.d("hous2", house_2);
            Page house_2_p = new Page();
            house_2_p.titleRes = house_2_info[0];
            house_2_p.party = house_2_info[1];
            house_2_p.loc = county;
            house_2_p.end = house_2_info[2];
            house_2_p.bioguide = house_2_info[3];
            house_2_p.tweet = house_2_info[4];
            Log.d("tweet in mygrid", house_2_info[4]);
            PAGES[0][3] = house_2_p;
        }

        Page senate_1_p = new Page();
        senate_1_p.titleRes = senate_1_info[0];
        senate_1_p.party = senate_1_info[1];
        senate_1_p.loc = county;
        senate_1_p.end = senate_1_info[2];
        senate_1_p.bioguide = senate_1_info[3];
        senate_1_p.tweet = senate_1_info[4];
        Log.d("tweet in mygrid", senate_1_info[4]);
        PAGES[0][0] = senate_1_p;

        Page senate_2_p = new Page();
        senate_2_p.titleRes = senate_2_info[0];
        senate_2_p.party = senate_2_info[1];
        senate_2_p.loc = county;
        senate_2_p.end = senate_2_info[2];
        senate_2_p.bioguide = senate_2_info[3];
        senate_2_p.tweet = senate_2_info[4];
        Log.d("tweet in mygrid", senate_2_info[4]);
        PAGES[0][1] = senate_2_p;

        Page house_1_p = new Page();
        house_1_p.titleRes = house_1_info[0];
        house_1_p.party = house_1_info[1];
        house_1_p.loc = county;
        house_1_p.end = house_1_info[2];
        house_1_p.bioguide = house_1_info[3];
        house_1_p.tweet = house_1_info[4];
        Log.d("tweet in mygrid", house_1_info[4]);
        PAGES[0][2] = house_1_p;

        Log.d("traking mygridpager",county);


    }

    static final int[] BG_IMAGES = new int[] {
            R.drawable.donkey, R.drawable.elephant
    };


    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        String title = page.titleRes.matches("") ? null : page.titleRes;
        String text = null;
        MyCardFragment temp = MyCardFragment.newInstance(page.titleRes, page.party, page.loc, page.tweet, election_data);
        return temp;
    }

    public  String getTitle(int row,int col){
        Page page = PAGES[row][col];
        return page.titleRes;
    }

    public  String getParty(int row,int col){
        Page page = PAGES[row][col];
        return page.party;
    }

    public  String getEnd(int row,int col){
        Page page = PAGES[row][col];
        return page.end;
    }

    public  String getBioguide(int row,int col){
        Page page = PAGES[row][col];
        return page.bioguide;
    }


    public  String getTweet(int row,int col){
        Page page = PAGES[row][col];
        Log.d("tweet in getTweet",page.tweet);
        return page.tweet;
    }

    @Override
    public Drawable getBackgroundForPage(int row, int column) {
        Page page = PAGES[row][column];
        Drawable d;
        if (page.party.equals(Republican)) {
            d = new ColorDrawable(Color.parseColor(Republican));
        } else if (page.party.equals(Democrat)){
            d = new ColorDrawable(Color.parseColor(Democrat));
        } else {
            d = new ColorDrawable(Color.parseColor(Independent));
        }
        return d;

    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    // Obtain the number of pages (horizontal)
    @Override
    public int getColumnCount(int rowNum) {
        return numColumn;
    }

    // A simple container for static data in each page
    private static class Page {
        // static resources
//        char name;
        String party;
        String titleRes;
        String loc;
        String end;
        String bioguide;
        String tweet;
    }

// Override methods in FragmentGridPagerAdapter

}
