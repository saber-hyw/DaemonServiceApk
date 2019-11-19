package com.hyw.daemonservice.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "BootReceiver";

    // receive boot broadcast
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "received boot broadcast");
        // XXX.class is self start service
        Intent service = new Intent(context, DaemonService.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Log.e(TAG, "start AdapterService by startForegroundService()");
            context.startForegroundService(service);
        } else {
            context.startService(service);
        }
    }
}
