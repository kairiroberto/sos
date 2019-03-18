package com.roberto.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.roberto.myapplication.conection.ApacheConection;
import com.roberto.myapplication.conection.SosBD;
import com.roberto.myapplication.controller.AsyncDaoController;
import com.roberto.myapplication.controller.IntentServiceAlarm;
import com.roberto.myapplication.controller.IntentServiceSos;
import com.roberto.myapplication.model.Sos;
import com.roberto.myapplication.model.Usuario;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Sos> sosMain = new ArrayList<Sos>();
    public static List<Sos> sosUsuarioMain = new ArrayList<Sos>();

    private final String USUARIO = "usuario";
    private final String INSERIR = "inserir";

    private int id = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarServico();

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NETWORK_STATE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_NOTIFICATION_POLICY)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_NETWORK_STATE,
                                Manifest.permission.INTERNET,
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_NOTIFICATION_POLICY},
                        2);
            }
        }

        SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);

        if (id == 0) {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            AsyncDaoController asyncDaoController = new AsyncDaoController(this, USUARIO, INSERIR);
            asyncDaoController.execute(tm.getImei());
        }

        if (!this.getDatabasePath("sos").exists()){
            SosBD sos = new SosBD(this);
        } else {
            SosBD sos = new SosBD(this);
            sos.delete();
        }

        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }

    private void iniciarServico() {
        Intent it = new Intent(this, IntentServiceAlarm.class);
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
    }

}
