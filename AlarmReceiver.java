package com.brainbitz.jav.alarmclock;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

/**
 * Created by User on 02-07-2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Wake Up!", Toast.LENGTH_LONG).show();

        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, SnoozeEvent.class);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pi = PendingIntent.getActivity(context, 0, notificationIntent, PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("WKAKEEEEEEE!!!");
        builder.setContentText("Gud Mrng");
        builder.setSmallIcon(R.drawable.icon);
        builder.build();
        builder.setLatestEventInfo(context, "Jai Ganesh", "Wake Up...", pi);
        note.flags = Notification.FLAG_INSISTENT;
        note.sound = (Uri.parse(intent.getStringExtra("Ringtone")));
        manager.notify(1, note);


        /*Intent in = new Intent(context, SnoozeEvent.class);
        in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent Sender = PendingIntent.getActivity(context, 0, in, PendingIntent.FLAG_UPDATE_CURRENT);*//*
        NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
   *//*    Notification notification = new Notification(R.drawable.icon, "Wake up alarm", System.currentTimeMillis());*//*
        Notification notification= new Notification();
        notification.sound = (Uri)(Uri.parse(intent.getStringExtra("Ringtone")));
        manager.notify(1, notification);*/
    }
}
