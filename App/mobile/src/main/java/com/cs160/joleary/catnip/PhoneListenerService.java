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
            Intent intent = new Intent(getBaseContext(),MainActivity.class);
            String[] candidates = value.split("&");
            String zipcode = candidates[0];
            boolean cameFromWatch = false;
            if (candidates[1] != null){
                cameFromWatch = true;
            }

            Log.d("candidate: ",candidates.toString());
            Log.d("candidate[0]: ",candidates[0]);
            Log.d("candidate[1]: ",candidates[1]);

            intent.putExtra("zipcode",zipcode);

            intent.putExtra("cameFromWatch",cameFromWatch);

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
                intent.putExtra("party", info[1]);
                intent.putExtra("end",info[2]);
                intent.putExtra("bioguide",info[3]);
                intent.putExtra("tweet",info[4]);

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
