//package com.cs160.joleary.catnip;
//
//import android.app.Fragment;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
///**
// * Created by moonhyok on 2/27/16.
// */
//public class CustomCard extends Fragment {
//    String name;
//    String party;
//
//    private static final String Republican = "#EB5757";
//    private static final String Democrat = "#2F80ED";
//    private static final String Independent = "#BDBDBD";
//
//    public static CustomCard newInstance(String name,String party) {
//        CustomCard f = new CustomCard();
//
//        // Supply index input as an argument.
//        Bundle args = new Bundle();
//        args.putString("party", party);
//        args.putString("name", name);
////        System.out.prinln(args.keySet().toArray()[0]+"  k   " +args.keySet().toArray()[1]);
//        f.setArguments(args);
//
//        return f;
//    }
//
//
//    @Override
//    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
//        super.onCreateView(inflater,container,savedInstanceState);
//        View v = inflater.inflate(R.layout.activity_main,container,false);
//        ImageView image = (ImageView) v.findViewById(R.id.pic);
//        LinearLayout background = (LinearLayout) v.findViewById(R.id.watch_background);
//        TextView text = (TextView) v.findViewById(R.id.cand_name);
//
//        name = getArguments().getString("name");
//        party = getArguments().getString("party");
//        text.setText(name);
////        btn.setVisibility(View.VISIBLE);
//
//        if (party.matches(Republican)){
//            image.setImageResource(R.drawable.elephant);
//            background.setBackgroundColor(Color.parseColor(Republican));
//
//        } else if (party.matches(Democrat)){
//
//            image.setImageResource(R.drawable.donkey);
//            background.setBackgroundColor(Color.parseColor(Democrat));
//
//        }
//
//        return v;
//    }
//}
