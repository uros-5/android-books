package com.example.knjizara;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationCompat;

public class ServicePlacanje extends Service {

    public ServicePlacanje() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent,int flags,int startId) {
        try {
            Thread.sleep(6000);
            onDestroy();

        }
        catch (Exception e) {

        }
        return START_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();

    }



}
