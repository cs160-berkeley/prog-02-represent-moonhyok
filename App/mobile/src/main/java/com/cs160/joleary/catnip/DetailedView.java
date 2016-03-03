package com.cs160.joleary.catnip;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        TextView mName = (TextView) findViewById(R.id.name);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString("name");
        String party = extras.getString("party");
        String email = extras.getString("email");
        String web = extras.getString("web");
        String tweet = extras.getString("tweet");
        String pic = extras.getString("pic");
//
        mName.setText(name);
        mName.setTextColor(Color.parseColor(party));


        

    }
}
