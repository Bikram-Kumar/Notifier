package com.bikram.notifier;


import android.os.Build;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.AlarmManager;


import java.util.HashMap;


public class NotifManager extends BroadcastReceiver {
    
    String CHANNEL_ID = "NormalPriorityChannel";
    
    HashMap<Long,String> notifList = new HashMap<Long,String>();
    
    
    
    private Context context;
    private Intent intent;
    
    @Override
    public void onReceive(Context ctx, Intent _intent) {
        context = ctx;
        intent = _intent;
        
        
        
        notifList.put(Long.valueOf(System.currentTimeMillis() + 1000), "Do it");
        notifList.put(Long.valueOf(9), "Now");
        notifList.put(Long.valueOf(7), "The time has come");
        
        
        
        createNotificationChannel();
        checkAllNotifs();
        
        /*
        Intent i = new Intent();
        i.setClassName("com.bikram.flashlight", "com.bikram.flashlight.MainActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        */
        
        
        
        
    }
    
    
    //  set alarm for this service for the next nearest time in notifList
    //  check all notifList entries and give notifs for time which has passed
    //  
    //  notifList will be in json ordered chronologically
    //
    
    
    
    
    void checkAllNotifs() {
        // time is key, so add 1 at the time of setting if two notifs are on same time
        for (long timeKey : notifList.keySet()) {
            if (timeKey < System.currentTimeMillis()) {
                sendNotif(timeKey);
            }
        }
    }
    
    
    void sendNotif(Long timeKey) {
        // build notif and notify
        
        Intent i = new Intent(context, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, i, PendingIntent.FLAG_IMMUTABLE);
        
      
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Notification").setContentText(notifList.get(timeKey));
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent).setAutoCancel(true);
        
        
        
        
        
        
        //createNotificationChannel();
        
        
        NotificationManagerCompat.from(context).notify(timeKey.intValue(), builder.build());
        
        
        
        
        setAlarmForNextNotif();
    }
    
    
    void setAlarmForNextNotif() {
        
        Intent nextAlarmIntent = new Intent(context, NotifManager.class);
        PendingIntent nextAlarmPIntent = PendingIntent.getBroadcast(context,0,nextAlarmIntent,0);
        
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, nextAlarmPIntent);
       
    }
    
    
    
        
    void createNotificationChannel() {
        // return if API is below 26... NO SUPPORT
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Normal Priority Channel", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("For normal priority notifications.");
        context.getSystemService(NotificationManager.class).createNotificationChannel(channel);
        
    }
    
    
    
    
    
}