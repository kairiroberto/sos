package com.roberto.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class IntentServiceAlarm extends Service {
    public IntentServiceAlarm() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent it = new Intent(this, IntentServiceSos.class);
        boolean alarmActive = (PendingIntent.getService(this, 0, it, PendingIntent.FLAG_NO_CREATE) != null);
        if (!alarmActive) {
            Log.i("SERVICO", "NOVO SERVICO");
            PendingIntent p = PendingIntent.getService(this, 0, it, 0);
            AlarmManager alarme = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            long alarmStartTime = System.currentTimeMillis();
            long alarmExecuteInterval = 10 * 1000;
            alarme.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10*1000, p);
        }
        startService(it);
        return super.onStartCommand(intent, flags, startId);
    }
}
