package com.bikram.notifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.content.Intent;
import android.app.PendingIntent;

import java.util.HashMap;


public class NotifManager extends BroadcastReceiver {
    
    String CHANNEL_ID = "MainChannel";
    
    HashMap<Long,String> notifList = new HashMap<Long,String>();
    
    
    @Override
    public void onReceive(Context context, Intent intent1) {
        
        notifList.put(Long.valueOf(5), "Do it");
        notifList.put(Long.valueOf(9), "Now");
        notifList.put(Long.valueOf(7), "The time has come");
        
        Intent i = new Intent();
        i.setClassName("com.bikram.flashlight", "com.bikram.flashlight.MainActivity");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
        
        
        
        
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        
      
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Notification").setContentText("DO IT!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent).setAutoCancel(true);
        
        
        
        
        
        
        //createNotificationChannel();
        
        
        NotificationManagerCompat.from(context).notify(1, builder.build());
        
        
        
    }
    
    
    //  set alarm for this service for the next nearest time in notifList
    //  check all notifList entries and give notifs for time which has passed
    //  
    //  notifList will be in json ordered chronologically
    //
    
    
    
    
    void checkAllNotifs() {
        for (long i : notifList.keySet()) {
            if (i < System.currentTimeMillis()) {
                sendNotif();
            }
        }
    }
    
    
    void sendNotif() {
        // build notif and notify
        
        
        
        setAlarmForNextNotif();
    }
    
    
    void setAlarmForNextNotif() {
        
    }
    
}