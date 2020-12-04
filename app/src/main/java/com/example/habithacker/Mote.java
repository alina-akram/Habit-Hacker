package com.example.habithacker;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class Mote extends BroadcastReceiver{

    public void onReceive(Context context, Intent intent) {
        notification(context);
    }

    public void notification(Context context){

        String channelId = "myNotificationChannel"; // Store channel ID as String or String resource
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel notificationChannel = new NotificationChannel(channelId , "Notify", NotificationManager.IMPORTANCE_HIGH);


            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notification = new NotificationCompat.Builder(context, channelId) // Use  the same channelId String while creating notification
                .setContentTitle("Habit Tracker")
                .setContentText("Remember to complete your task!")
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(1, notification.build());
    }
}