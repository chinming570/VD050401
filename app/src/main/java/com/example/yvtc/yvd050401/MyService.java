package com.example.yvtc.yvd050401;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.util.Log;

public class MyService extends Service {
    NotificationManager manager;
    final int NOTIFICATION_ID = 567;
    Handler handler = new Handler();
    Context context;
    int count;
    public MyService() {

    }

    Runnable showTime = new Runnable() {
        @Override
        public void run() {
            Log.d("SER1", "Time:" + new java.util.Date());
            if (count < 10)
            {
                count++;
                handler.postDelayed(this, 1000);
            }
            else
            {
                Intent it = new Intent(context, DetailActivity.class);
                String msg = "十秒到了!!";
                it.putExtra("msg",msg);
                PendingIntent pi = PendingIntent.getActivity(context, 123, it, PendingIntent.FLAG_UPDATE_CURRENT);
                Notification.Builder builder = new Notification.Builder(MyService.this);
                builder.setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("這是十秒通知")
                        .setContentText(msg)
                        .setContentIntent(pi)
                        .setAutoCancel(true);
                Notification notification = builder.build();
                manager.notify(NOTIFICATION_ID, notification);
            }

        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        Log.d("SER1", "onBind");
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("SER1", "This is onCreate");
    }

    @Override
    public int onStartCommand(Intent intent,  int flags, int startId) {
        Log.d("SER1", "This is onStartCommand");
        context = getApplicationContext();
        manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        count = 0;
        handler.post(showTime);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("SER1", "This is onDestory");
    }
}
