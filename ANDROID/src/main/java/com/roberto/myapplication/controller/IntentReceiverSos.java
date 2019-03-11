package com.roberto.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import static android.content.Context.ALARM_SERVICE;

public class IntentReceiverSos extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        Log.i("SERVICO", "onReceiver");
        Intent it = new Intent(context, IntentServiceSos.class);
        boolean alarmActive = (PendingIntent.getService(context, 0, it, PendingIntent.FLAG_NO_CREATE) != null);
        if (!alarmActive) {
            Log.i("SERVICO", "SERVICO START IN BOOT");
            PendingIntent p = PendingIntent.getService(context, 0, it, 0);
            AlarmManager alarme = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            long alarmStartTime = System.currentTimeMillis();
            long alarmExecuteInterval = 10 * 1000;
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10*1000, p);
        }
    }
}
