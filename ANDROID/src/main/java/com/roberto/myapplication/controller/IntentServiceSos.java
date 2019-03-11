package com.roberto.myapplication.controller;

import android.app.AlertDialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

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
        ConnectivityManager cm =(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (isConnected) {
            Log.i("SERVICO", "Listas atualizadas");

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
