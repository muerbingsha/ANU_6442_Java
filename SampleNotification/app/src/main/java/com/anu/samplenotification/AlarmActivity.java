package com.anu.samplenotification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AlarmActivity extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION = "notification";
    public static String NOTIFICATION_CHANNEL_ID = "1";

    @Override
    public void onReceive(final Context context, Intent intent) {


        // print current time
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String date = formatter.format(Calendar.getInstance().getTimeInMillis());
        System.out.println("Notifiy time");
        System.out.println(date);

        // 1 - manager
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // 2 - get content
        // get notification
        Notification notification = intent.getParcelableExtra(NOTIFICATION);
        // get notification id
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);

        // 3 - creatt channel
        if (android.os.Build.VERSION. SDK_INT >= android.os.Build.VERSION_CODES. O ) {
            int importance = NotificationManager. IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel( NOTIFICATION_CHANNEL_ID , "NOTIFICATION_CHANNEL_NAME" , importance) ;
            assert notificationManager != null;
            notificationManager.createNotificationChannel(notificationChannel);
        }

        // 4 - show notification
        assert notificationManager != null;
        notificationManager.notify(notificationId, notification);
    }

}
