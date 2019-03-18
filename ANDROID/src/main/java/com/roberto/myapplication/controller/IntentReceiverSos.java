package com.roberto.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class IntentReceiverSos extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");

        Log.i("SERVICO", "onReceiver");
        Intent it = new Intent(context, IntentServiceAlarm.class);
        context.startService(it);

        context.startService(it);

    }

}
