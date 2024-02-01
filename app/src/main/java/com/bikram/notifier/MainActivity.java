package com.bikram.notifier;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.content.Intent;
import android.app.PendingIntent;
import android.app.AlarmManager;

public class MainActivity extends AppCompatActivity {

    private String CHANNEL_ID = "MainChannel";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        
      
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_launcher_background);
        builder.setContentTitle("Notification").setContentText("DO IT!");
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT);
        builder.setContentIntent(pendingIntent).setAutoCancel(true);
        
        
        createNotificationChannel();
        
        NotificationManagerCompat.from(this).notify(1, builder.build());
        MainActivity self = this;
        
        
        
        Intent nextAlarmIntent = new Intent(this, MainActivity.class);
        PendingIntent nextAlarmPIntent = PendingIntent.getActivity(this,0,nextAlarmIntent,0);
        
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + 5000, nextAlarmPIntent);
       
               
        
        NotificationManagerCompat.from(self).notify(2, builder.build());
                    
       
                

        
        
        
    }
    
    
    
    
    private void createNotificationChannel() {
        // return if API is below 26... NO SUPPORT
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;
        
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Notif name", NotificationManager.IMPORTANCE_DEFAULT);
        channel.setDescription("Description of Channel");
        getSystemService(NotificationManager.class).createNotificationChannel(channel);
        
    }
    
    
}