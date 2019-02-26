package com.roberto.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import com.roberto.myapplication.conection.ApacheConection;
import com.roberto.myapplication.controller.AsyncTaskController;
import com.roberto.myapplication.model.Sos;
import com.roberto.myapplication.model.Usuario;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static List<Usuario> usuariosMain = new ArrayList<Usuario>();
    public static List<Sos> sosMain = new ArrayList<Sos>();
    public static List<Sos> sosUsuarioMain = new ArrayList<Sos>();

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
        int id = sharedPreferences.getInt("id", 0);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
            }
        }

        if (id == 0) {
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                Toast.makeText(this, tm.getImei(), Toast.LENGTH_LONG).show();
                Toast.makeText(this, "" + id, Toast.LENGTH_LONG).show();
                AsyncTaskController asyncTaskController = new AsyncTaskController(this, "usuario", "inserir");
                asyncTaskController.execute(tm.getImei());
        }

        Toast.makeText(this, "Usuário: " + id, Toast.LENGTH_LONG).show();

        Intent i = new Intent(this, MenuActivity.class);
        startActivity(i);
    }
}
