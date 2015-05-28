package com.rebellion.android.avadrone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.location.LocationServices;

import static java.lang.Thread.sleep;

/**
 * Created by prashant.singh on 14-May-15.
 */
public class SplashScreen extends ActionBarActivity {
    GoogleCloudMessaging gcm;
    String PROJECT_NUMBER = "279296091050";
    String regId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash_activity);
        Thread timer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                    String name = preferences.getString("GCMId", "");
                    if(!name.equalsIgnoreCase("")){
//                        name = name + "  Sethi";  /* Edit the value here*/
                    }else {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                        regId = gcm.register(PROJECT_NUMBER);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("GCMId",regId);
                        editor.apply();
                    }

                    sleep(3000);

                }catch (Exception e) {
                }
                finally {
                    Intent intent = new Intent(getApplicationContext(), MapActivity.class);
                    startActivity(intent);

                }

            }}
        );
        timer.start();
    }



}
