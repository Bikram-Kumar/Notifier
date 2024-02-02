package com.bikram.notifier;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    private String CHANNEL_ID = "MainChannel";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        
        
        sendBroadcast(new Intent(this, NotifManager.class));
        
        
        
        
    }
    

    
}