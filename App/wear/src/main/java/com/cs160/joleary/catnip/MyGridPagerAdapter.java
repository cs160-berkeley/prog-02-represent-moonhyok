package com.cs160.joleary.catnip;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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



    private final Context mContext;
    private List mRows;
    private String[] senate_1_info;
    private String[] senate_2_info;
    private String[] house_1_info;
    private String[] house_2_info;

    public MyGridPagerAdapter(Context ctx, FragmentManager fm, Intent intent) {
        super(fm);
        mContext = ctx;
        Bundle extras = intent.getExtras();

        String location;

        location = extras.getString("location");


        //Get data
        String senate_1 = extras.getString("senate_1");
        senate_1_info = senate_1.split("@");

        String senate_2 = extras.getString("senate_2");
        senate_2_info = senate_2.split("@");

        String house_1 = extras.getString("house_1");
        house_1_info = house_1.split("@");

        String house_2 = extras.getString("house_2");
        if (house_2.matches("@")){
            Page house_2_p = new Page();
            house_2_p.titleRes = "";
            house_2_p.party = "";
            house_2_p.loc = location;
            PAGES[0][3] = house_2_p;

        } else{
            house_2_info = house_2.split("@");
            Log.d("hous2", house_2);
            Page house_2_p = new Page();
            house_2_p.titleRes = house_2_info[0];
            house_2_p.party = house_2_info[1];
            house_2_p.loc = location;
            // house_2_p.iconRes = R.drawable.donkey;
            PAGES[0][3] = house_2_p;

        }

        Page senate_1_p = new Page();
        senate_1_p.titleRes = senate_1_info[0];
        senate_1_p.party = senate_1_info[1];
        senate_1_p.loc = location;
        // senate_1_p.iconRes = R.drawable.donkey;
        PAGES[0][0] = senate_1_p;

        Page senate_2_p = new Page();
        senate_2_p.titleRes = senate_2_info[0];
        senate_2_p.party = senate_2_info[1];
        senate_2_p.loc = location;
        // senate_2_p.iconRes = R.drawable.donkey;
        PAGES[0][1] = senate_2_p;

        Page house_1_p = new Page();
        house_1_p.titleRes = house_1_info[0];
        house_1_p.party = house_1_info[1];
        house_1_p.loc = location;
        // house_1_p.iconRes = R.drawable.donkey;
        PAGES[0][2] = house_1_p;


    }

    static final int[] BG_IMAGES = new int[] {
            R.drawable.donkey, R.drawable.elephant
    };


    @Override
    public Fragment getFragment(int row, int col) {
        Page page = PAGES[row][col];
        String title = page.titleRes.matches("") ? null : page.titleRes;
        String text = null;
//        CardFragment temp = CardFragment.create(title, text);
        MyCardFragment temp = MyCardFragment.newInstance(page.titleRes, page.party, page.loc);
        return temp;
    }

    public  String getTitle(int row,int col){
        Page page = PAGES[row][col];
        return page.titleRes;
    }

    @Override
    public int getRowCount() {
        return PAGES.length;
    }

    // Obtain the number of pages (horizontal)
    @Override
    public int getColumnCount(int rowNum) {
        return PAGES[rowNum].length;
    }

    // A simple container for static data in each page
    private static class Page {
        // static resources
//        char name;
        String party;
        String titleRes;
        String loc;
        int cardGravity = 80;
        int expansionDirection = 1;
        float expansionFactor = 1;

    }

    // Create a static set of pages in a 2D array
    private final Page[][] PAGES = {{null,null,null,null}};

// Override methods in FragmentGridPagerAdapter

}
