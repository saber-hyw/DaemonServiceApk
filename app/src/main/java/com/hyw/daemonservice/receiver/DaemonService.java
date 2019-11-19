package com.hyw.daemonservice.receiver;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.hyw.daemonservice.R;

public class DaemonService extends Service {
    private static final String TAG = "DaemonService";

    // This method will be called only after service is bound, it must be implemented;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate()");
        mHandler.postDelayed(mRunnable, 2000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand()");

        /**
         * to do something for yourself
         */
        createNotificationChannel();
        Log.d(TAG, "onStartCommand: adapterService_ end");
        stopForeground(true);

        return START_STICKY;

//        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }

    // create notification
    private void createNotificationChannel() {
        Log.d(TAG, "hyw createNotificationChannel()");
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "commonservice_channel_01";
        CharSequence name = "commonservice";
        String description = "commonservice for test";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
        mChannel.setDescription(description);
        mNotificationManager.createNotificationChannel(mChannel);
        Notification notification = new Notification.Builder(this, CHANNEL_ID).setContentTitle("New Message")
                .setContentText("You`ve received new messages.").setSmallIcon(R.drawable.ic_launcher_foreground)
                .setChannelId(CHANNEL_ID).build();
        startForeground(1, notification);
    }

    // test code
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
//            System.out.println(">>>>> I'm live.");
            Log.e(TAG, ">>>>> I'm live.");
            mHandler.postDelayed(this, 2000);
        }
    };
}
