package com.roberto.myapplication.controller;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;
import com.roberto.myapplication.MensagemActivity;

public class IntentServiceSos extends Service {

    private final String SOS = "sos";
    private final String LISTAR = "listar";
    private final String SOS_USUARIO = "sosUsuario";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.i("SERVICO", "SERVICO START IN BOOT");
        PendingIntent p = PendingIntent.getService(this, 0, intent, 0);
        AlarmManager alarme = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        long alarmStartTime = System.currentTimeMillis();
        long alarmExecuteInterval = 10 * 1000;
        alarme.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 10*1000, p);

        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected) {
            Log.i("SERVICO", "Listas atualizadas");
            Toast.makeText(this, "Dados atualizados com o serviço", Toast.LENGTH_LONG).show();

            Intent i = new Intent(this, MensagemActivity.class);
            i.putExtra("msg", "Teste");

            TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(this);
            taskStackBuilder.addParentStack(i.getComponent());
            taskStackBuilder.addNextIntent(i);

            PendingIntent resultPendingIntent = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setSmallIcon(android.support.v4.R.drawable.notification_icon_background);
            mBuilder.setContentTitle("My notification");
            mBuilder.setContentText("Hello World!");
            mBuilder.setFullScreenIntent(resultPendingIntent, false);
            mBuilder.setContentIntent(resultPendingIntent);
            mBuilder.setAutoCancel(true);

            NotificationManagerCompat mNotificationManager = NotificationManagerCompat.from(this);
            mNotificationManager.notify(1, mBuilder.build());

            AsyncDaoController asyncDaoController = new AsyncDaoController(this, SOS, LISTAR);
            asyncDaoController.execute();

            SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
            String celular = sharedPreferences.getString("celular", " ");

            AsyncDaoController asyncDaoController2 = new AsyncDaoController(this, SOS, SOS_USUARIO);
            asyncDaoController2.execute(celular);

        }

        return super.onStartCommand(intent, flags, startId);

    }
}
