package com.cs160.joleary.catnip;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.wearable.CapabilityApi;
import com.google.android.gms.wearable.CapabilityInfo;
import com.google.android.gms.wearable.MessageApi;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.Wearable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by joleary and noon on 2/1916 at very late in the night. (early in the morning?)
 */
public class WatchToPhoneService extends Service implements GoogleApiClient.ConnectionCallbacks {

    private GoogleApiClient mWatchApiClient;
    private List<Node> nodes = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize the googleAPIClient for message passing
        mWatchApiClient = new GoogleApiClient.Builder( this )
                .addApi( Wearable.API )
                .addConnectionCallbacks(this)
                .build();
        //and actually connect it
        mWatchApiClient.connect();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWatchApiClient.disconnect();
    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override //alternate method to connecting: no longer create this in a new thread, but as a callback
    public void onConnected(Bundle bundle) {
        Log.d("T", "in onconnected");
//        System.out.println(bundle.getString("name")+ " KJIWJQDAKSJDLK");
        Wearable.NodeApi.getConnectedNodes(mWatchApiClient)
                .setResultCallback(new ResultCallback<NodeApi.GetConnectedNodesResult>() {
                    @Override
                    public void onResult(NodeApi.GetConnectedNodesResult getConnectedNodesResult) {
                        nodes = getConnectedNodesResult.getNodes();
                        Log.d("T", "am I first? or found nodes");
                        //when we find a connected node, we populate the list declared above
                        //finally, we can send a message
//                        sendMessage("/send_toast", "Dianne Feinstein" + "@" + "#EB5757");
                        Log.d("T", "sent");
                    }
                });
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Which cat do we want to feed? Grab this info from INTENT
        // which was passed over when we called startService
//        mWatchApiClient.connect();
        Bundle extras = intent.getExtras();
        final String path;
        final String message;
        if (extras.getBoolean("congress")){
            path = "/congress";
            message = extras.getString("zipcode")+"&"+extras.getString("massive");
        } else {
            path = "/kk";
            message = extras.getString("name")+"@#2F80ED";
        }
        final String name = extras.getString("name");
//        final String senate_1_party = extras.getString("senate_1_party");
//        final String senate_2_name = extras.getString("senate_2_name");
//        final String senate_2_party = extras.getString("senate_2_party");
//        final String house_2_name = extras.getString("house_2_name");
//        final String house_2_party = extras.getString("house_2_party");
//        final String house_1_name = extras.getString("house_1_name");
//        final String house_1_party = extras.getString("house_1_party");

        // Send the message with the cat name
        new Thread(new Runnable() {
            @Override
            public void run() {
                //first, connect to the apiclient
                Log.d("WatchToPhone","I get called first!! "+name);
                mWatchApiClient.connect();
                //now that you're connected, send a massage with the cat name
                sendMessage(path, message);
            }
        }).start();

        return START_STICKY;
    }

    @Override //we need this to implement GoogleApiClient.ConnectionsCallback
    public void onConnectionSuspended(int i) {}

//    private void sendMessage( final String path, final String text ) {
//        //one way to send message: start a new thread and call .await()
//        //see watchtophoneservice for another way to send a message
//        new Thread( new Runnable() {
//            @Override
//            public void run() {
//                NodeApi.GetConnectedNodesResult nodes = Wearable.NodeApi.getConnectedNodes( mWatchApiClient ).await();
//                for(Node node : nodes.getNodes()) {
//                    //we find 'nodes', which are nearby bluetooth devices (aka emulators)
//                    //send a message for each of these nodes (just one, for an emulator)
//                    MessageApi.SendMessageResult result = Wearable.MessageApi.sendMessage(
//                            mWatchApiClient, node.getId(), path, text.getBytes() ).await();
//                    //4 arguments: api client, the node ID, the path (for the listener to parse),
//                    //and the message itself (you need to convert it to bytes.)
//                }
//            }
//        }).start();
//    }
//
    private void sendMessage(final String path, final String text ) {
        for (Node node : nodes) {
            Wearable.MessageApi.sendMessage(
                    mWatchApiClient, node.getId(), path, text.getBytes());
        }
    }

}
