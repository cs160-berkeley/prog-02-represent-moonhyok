//package com.cs160.joleary.catnip;
//
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.wearable.view.FragmentGridPagerAdapter;
//
//import java.util.List;
//
///**
// * Created by moonhyok on 2/29/16.
// */
//class SampleGridPagerAdapter extends FragmentGridPagerAdapter {
//
//    private static final String Republican = "#EB5757";
//    private static final String Democrat = "#2F80ED";
//    private static final String Independent = "#BDBDBD";
//
//
//
//    private final Context mContext;
//    private List mRows;
//    private String[] senate_1_info
//    private String[] senate_2_info;
//    private String[] house_1_info;
//    private String[] house_2_info;
//
//    public SampleGridPagerAdapter(Context ctx, FragmentManager fm, Intent intent) {
//        super(fm);
//        mContext = ctx;
//        Bundle extras = intent.getExtras();
//
//
//        //Get datas
//        String senate_1 = extras.getString("senate_1");
//        senate_1_info = senate_1.split("@");
//
//        String senate_2 = extras.getString("senate_2");
//        senate_2_info = senate_2.split("@");
//
//        String house_1 = extras.getString("house_1");
//        house_1_info = house_1.split("@");
//
//        String house_2 = extras.getString("house_2");
//        house_2_info = house_2.split("@");
//
//        Page senate_1_p = new Page();
//        senate_1_p.titleRes = senate_1_info[0];
//        senate_1_p.party = senate_1_info[1];
//        // senate_1_p.iconRes = R.drawable.donkey;
//        PAGES[0][0] = senate_1_p;
//
//        Page senate_2_p = new Page();
//        senate_2_p.titleRes = senate_2_info[0];
//        senate_2_p.party = senate_2_info[1];
//        // senate_2_p.iconRes = R.drawable.donkey;
//        PAGES[0][1] = senate_2_p;
//
//        Page house_1_p = new Page();
//        house_1_p.titleRes = house_1_info[0];
//        house_1_p.party = house_1_info[1];
//        // house_1_p.iconRes = R.drawable.donkey;
//        PAGES[0][2] = house_1_p;
//
//        Page house_2_p = new Page();
//        house_2_p.titleRes = house_2_info[0];
//        house_2_p.party = house_2_info[1];
//        // house_2_p.iconRes = R.drawable.donkey;
//        PAGES[0][3] = house_2_p;
//    }
//
//    static final int[] BG_IMAGES = new int[] {
//        R.drawable.donkey, R.drawable.elephant
//    };
//
//    @Override
//    public Fragment getFragment(int row, int col) {
//        Page page = PAGES[row][col];
//        String title = page.titleRes.matches("") ? null : page.titleRes;
//        String text = null;
////        CardFragment temp = CardFragment.create(title,text);
////        CustomCard temp = CustomCard.newInstance(page.titleRes, page.party);
////        return temp;
//    }
//
//    @Override
//    public int getRowCount() {
//        return PAGES.length;
//    }
//
//    // Obtain the number of pages (horizontal)
//    @Override
//    public int getColumnCount(int rowNum) {
//        return PAGES[rowNum].length;
//    }
//
//    // A simple container for static data in each page
//    private static class Page {
//        // static resources
////        char name;
//        String party;
//        String titleRes;
//        int iconRes;
//        int cardGravity = 80;
//        int expansionDirection = 1;
//        float expansionFactor = 1;
//
//    }
//
//    // Create a static set of pages in a 2D array
//    private final Page[][] PAGES = {{null,null,null,null}};
//
//// Override methods in FragmentGridPagerAdapter
//}