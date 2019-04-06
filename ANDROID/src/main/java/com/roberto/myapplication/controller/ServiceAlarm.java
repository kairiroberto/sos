package tcc.myapplocation.jose.tcc.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;

import java.util.Calendar;

public class ServiceAlarm extends Service {
    public ServiceAlarm() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent i = new Intent("SOS");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(ServiceAlarm.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        c.add(Calendar.SECOND, 10);
        long inicio = c.getTimeInMillis();
        long intervalo = 3000;
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, inicio, intervalo, pendingIntent);
        return super.onStartCommand(intent, flags, startId);
    }
}
