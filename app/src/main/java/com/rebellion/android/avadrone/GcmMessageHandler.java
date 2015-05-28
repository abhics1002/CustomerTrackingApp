package com.rebellion.android.avadrone;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

/**
 * Created by prashant.singh on 14-May-15.
 */
public class GcmMessageHandler extends IntentService {

    String mes;
    private Handler handler;

    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        mes = extras.getString("data");
        String[] p = mes.split("~~");
        String latitude = p[0].substring(p[0].lastIndexOf("=")+1);
        String longitude = p[1].substring(p[1].lastIndexOf("=")+1);

        showToast();
        Log.i("GCM", "Received : (" + messageType + ")  " + extras.getString("title"));

        Intent i = new Intent("MapActivity.action");
        i.putExtra("longitude",longitude);
        i.putExtra("latitude",latitude);

        sendBroadcast(i);

        GcmBroadCastReceiver.completeWakefulIntent(intent);

    }

    public void showToast(){
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG).show();
            }
        });

    }

}
