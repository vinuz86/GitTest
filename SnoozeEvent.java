package com.brainbitz.jav.alarmclock;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by User on 06-07-2016.
 */
public class SnoozeEvent extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Snooze!!!", Toast.LENGTH_LONG).show();
  Toast();
    }
}
