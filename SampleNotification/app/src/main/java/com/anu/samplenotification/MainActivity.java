package com.anu.samplenotification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    final Calendar mCalendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button time = findViewById(R.id.time);
        final Button date = findViewById(R.id.date);
        final Button send = findViewById(R.id.send);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int hour = mCalendar.get(Calendar.HOUR_OF_DAY);
                int minute = mCalendar.get(Calendar.MINUTE);


                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int _hour, int _minute) {
                        mCalendar.set(Calendar.HOUR_OF_DAY, _hour);
                        mCalendar.set(Calendar.MINUTE, _minute);
                        mCalendar.set(Calendar.SECOND, 0);
                        time.setText(_hour + " : " + _minute);
                    }
                }, hour, minute, false).show();

            }
        });


        date.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {

                int year = mCalendar.get(Calendar.YEAR);
                int month = mCalendar.get(Calendar.MONTH);
                int day = mCalendar.get(Calendar.DAY_OF_MONTH);


                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int _year, int _month, int _day) {
                        mCalendar.set(Calendar.YEAR, _year);
                        mCalendar.set(Calendar.MONTH, _month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, _day);
                        date.setText(_year + " : " + _month + " : " + _day);
                    }
                }, year, month, day).show();
            }
        });


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                scheduleNotification(mCalendar.getTimeInMillis(), 1);
            }
        });
    }


    /**
     * show notification immediately
     */
    public void showImediateNotification() {

        String channelId = getApplication().getPackageName();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            // 2: get channel
            NotificationChannel channel = new NotificationChannel(channelId,
                    channelId+"_name",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(channelId+"_desc");
            channel.setShowBadge(true);


            // 3: register this notification to manager
            NotificationManager manager = getApplicationContext().getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }



        // 1: Create an explicit intent for an Activity in your app
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Title")
                .setContentText("Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Content"))
                .setAutoCancel(true) // which automatically removes the notification when the user taps it.
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);



        // 3: show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getApplicationContext());
        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(1001, builder.build());

    }




    /**
     *
     */
    public void scheduleNotification(long delay, int notificationId) {
        // 1 get channel id
//        String channelId = getApplication().getPackageName();
        String channelId = AlarmActivity.NOTIFICATION_CHANNEL_ID;


        // make notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), channelId)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Schedule Title")
                .setContentText("Schedule Content")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Content"))
                .setAutoCancel(true) // which automatically removes the notification when the user taps it.
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

        Notification notification = builder.build();

        // pass content
        Intent intent = new Intent(getApplicationContext(), AlarmActivity.class);
        intent.putExtra(AlarmActivity.NOTIFICATION_ID, notificationId); // put notification id
        intent.putExtra(AlarmActivity.NOTIFICATION, notification); // put notification
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), notificationId, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        // schedule
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, delay, pendingIntent);

        // print notify time
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(delay);
        System.out.println("Notify time would be");
        System.out.println(date);


    }
}
