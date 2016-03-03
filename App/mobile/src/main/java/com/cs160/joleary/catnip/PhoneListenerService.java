package com.cs160.joleary.catnip;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

import java.nio.charset.StandardCharsets;

/**
 * Created by joleary and noon on 2/19/16 at very late i the night. (early in the morning?)
 */
public class PhoneListenerService extends WearableListenerService {

//   WearableListenerServices don't need an iBinder or an onStartCommand: they just need an onMessageReceieved.
private static final String TOAST = "/send_toast";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        Log.d("T", "in PhoneListenerService, got: " + messageEvent.getPath());
        String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);


        if (messageEvent.getPath().equalsIgnoreCase("/congress")){
            Intent intent = new Intent(getBaseContext(),CongressionalMain.class);
            String[] candidates = value.split("&");
            String zipcode = candidates[0];
            String[] candidate1 = candidates[1].split("@");
            String[] candidate2 = candidates[2].split("@");
            String[] candidate3 = candidates[3].split("@");
            String[] candidate4 = candidates[4].split("@");

            Log.d("candidate: ",candidates.toString());
            Log.d("candidate[0]: ",candidates[0]);
            Log.d("candidate[1]: ",candidates[1]);
            Log.d("candidate[2]: ",candidates[2]);
            Log.d("candidate[3]: ",candidates[3]);






            intent.putExtra("zipcode",zipcode);
            intent.putExtra("location","");

            intent.putExtra("senate_1_name",candidate1[0]);
            intent.putExtra("senate_2_name",candidate2[0]);
            intent.putExtra("house_1_name",candidate3[0]);
            intent.putExtra("house_2_name",candidate4[0]);

            intent.putExtra("senate_1_party",candidate1[1]);
            intent.putExtra("senate_2_party",candidate2[1]);
            intent.putExtra("house_1_party",candidate3[1]);
            intent.putExtra("house_2_party",candidate4[1]);

            intent.putExtra("senate_1_pic",candidate1[2]);
            intent.putExtra("senate_2_pic",candidate2[2]);
            intent.putExtra("house_2_pic",candidate3[2]);
            intent.putExtra("house_1_pic",candidate4[2]);

            intent.putExtra("senate_1_email",candidate1[3]);
            intent.putExtra("senate_2_email",candidate2[3]);
            intent.putExtra("house_1_email",candidate3[3]);
            intent.putExtra("house_2_email",candidate4[3]);

            intent.putExtra("senate_1_web",candidate1[4]);
            intent.putExtra("senate_2_web",candidate2[4]);
            intent.putExtra("house_1_web",candidate3[4]);
            intent.putExtra("house_2_web",candidate4[4]);

            intent.putExtra("senate_1_tweet",candidate1[5]);
            intent.putExtra("senate_2_tweet",candidate2[5]);
            intent.putExtra("house_1_tweet",candidate3[5]);
            intent.putExtra("house_2_tweet", candidate4[5]);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);



        } else{

                // Make a toast with the String
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_SHORT;
//            System.out.println("I'm sending to the phone !@#$!@#!@$@!#!@#@!#");
//                    Toast.makeText(PhoneListenerService.this, "Please write in at least one", Toast.LENGTH_LONG).show();


                //Get name and party of Candidate
                String[] info = value.split("@");
                Intent intent = new Intent(getBaseContext(), DetailedView.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("name",info[0]);
                intent.putExtra("party",info[1]);
                Log.d("PhoneListener","I am listening~~~~ "+info[0]+" "+info[1]+" the original: "+value);
//            sendIntent.putExtra("name","temporary");
//            sendIntent.putExtra("party","#BDBDBD");
                startActivity(intent);

            }
            // Value contains the String we sent over in WatchToPhoneService, "good job"
//            String value = new String(messageEvent.getData(), StandardCharsets.UTF_8);
//
//            // Make a toast with the String
//            Context context = getApplicationContext();
//            int duration = Toast.LENGTH_SHORT;
////            System.out.println("I'm sending to the phone !@#$!@#!@$@!#!@#@!#");
////                    Toast.makeText(PhoneListenerService.this, "Please write in at least one", Toast.LENGTH_LONG).show();
//
//
//            //Get name and party of Candidate
//            String[] info = value.split("@");
//            Intent intent = new Intent(getBaseContext(), DetailedView.class);
//            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.putExtra("name",info[0]);
//            intent.putExtra("party",info[1]);
//            Log.d("PhoneListener","I am listening~~~~ "+info[0]+" "+info[1]+" the original: "+value);
////            sendIntent.putExtra("name","temporary");
////            sendIntent.putExtra("party","#BDBDBD");
//            startActivity(intent);




//            Toast toast = Toast.makeText(context, value, duration);
//            toast.show();

            // so you may notice this crashes the phone because it's
            //''sending message to a Handler on a dead thread''... that's okay. but don't do this.
            // replace sending a toast with, like, starting a new activity or something.
            // who said skeleton code is untouchable? #breakCSconceptions


    }
}
