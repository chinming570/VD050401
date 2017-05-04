package com.example.yvtc.yvd050401;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    NotificationManager manager;
    final int NOTIFICATION_ID = 321;
    Intent serviceIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    public void click1(View v) {

    }
    public void click2(View v)
    {
        manager.cancel(NOTIFICATION_ID);
    }
    public void clickStartService(View v)
    {
        serviceIntent = new Intent(MainActivity.this, MyService.class);
        startService(serviceIntent);
    }
    public  void clickStopService(View v)
    {
        stopService(serviceIntent);
    }

}
