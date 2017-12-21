package com.mukul.medication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.journaldev.sqlite.R;

/**
 * Created by Kenneth on 12/19/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    private static final int MY_NOTIFICATION_ID = 4;
    NotificationManager nfm;
    Notification ntf;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Take your pills!", Toast.LENGTH_LONG).show();
        //Intent myIntent = new Intent(context, MedListActivity.class); //SO THIS ACTIVITY IN SETTED ALARM TIME.
        //PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ntf = new NotificationCompat.Builder(context)
                .setContentTitle("YOUR ALARM WORKED")
                .setContentText("this is an Alarm Test")
                .setTicker("Notification Head")
                .setWhen(System.currentTimeMillis())
                //.setContentIntent(pendingIntent)
                .setDefaults(Notification.DEFAULT_SOUND) //PLAY DEFAULT SOUND
                .setAutoCancel(true) // REMOVE ALARM NOTIFICATION JUST BY SWIPE
                .setSmallIcon(R.mipmap.ic_launcher) //SHOWED IN STATUS BAR
                .build();
        nfm = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        nfm.notify(MY_NOTIFICATION_ID, ntf);




    }
}
