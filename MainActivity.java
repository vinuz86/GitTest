package com.brainbitz.jav.alarmclock;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.app.TimePickerDialog.*;

public class MainActivity extends Activity {

    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textAlarmPrompt = (TextView) findViewById(R.id.alarmprompt);
        buttonstartSetDialog = (Button) findViewById(R.id.startAlaram);

        buttonstartSetDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textAlarmPrompt.setText("");
                openTimePickerDialog(false);

            }
        });
    }
            private void openTimePickerDialog(boolean is24Hr) {
                Calendar calendar = Calendar.getInstance();

                timePickerDialog = new TimePickerDialog(MainActivity.this,
                        onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY),
                        calendar.get(Calendar.MINUTE), is24Hr);

                timePickerDialog.setTitle("Set Alarm Time");
                timePickerDialog.show();
            }

             OnTimeSetListener onTimeSetListener = new OnTimeSetListener() {
                 @Override
                 public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                     Calendar calNow = Calendar.getInstance();
                     Calendar calSet = (Calendar) calNow.clone();

                     calSet.set(Calendar.HOUR_OF_DAY, hourOfDay);
                     calSet.set(Calendar.MINUTE, minute);
                     calSet.set(Calendar.SECOND, 0);
                     calSet.set(Calendar.MILLISECOND, 0);

                     if (calSet.compareTo(calNow) <= 0) {
                         // Today Set time passed, count to tomorrow
                         calSet.add(Calendar.DATE, 1);


                     }

                     setAlarm(calSet);


                 }
             };

                 private void setAlarm(Calendar calSet) {
                     textAlarmPrompt.setText("\n\n***\n" + "Alarm is set "
                             + calSet.getTime() + "\n" + "***\n");
                     Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
                     intent.putExtra("Ringtone", getResources().getResourceName(R.raw.anbe));
                     PendingIntent pendingIntent = PendingIntent.getBroadcast(
                             getBaseContext(), RQS_1, intent, 0);
                     AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                     alarmManager.set(AlarmManager.RTC_WAKEUP, calSet.getTimeInMillis(),pendingIntent);

                    /* AlmMgr.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() +
                             (60 * 1000), (24 * 60 * 60 * 1000), Sender);*/

                 }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
