package com.roberto.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.roberto.myapplication.controller.AsyncDaoController;
import com.roberto.myapplication.controller.AsyncDaoController;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SosAddActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvLongitudeSos;
    private TextView tvLatitudeSos;
    private TextView tvOcorrenciaSos;
    private TextView tvDataSos;
    private EditText etLocalSos;
    private EditText etDescricaoSos;
    private Button bSalvarSos;
    private Button bLigarSos;
    private Button bCancelarSos;
    private String latitudesos;
    private String longitudesos;
    private String ocorrencia;

    private final String SOS = "sos";
    private final String INSERIR = "inserir";

    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                tvLatitudeSos.setText("Latitude: " + location.getLatitude());
                tvLongitudeSos.setText("Longitude: " + location.getLongitude());
                latitudesos = String.valueOf(location.getLatitude());
                longitudesos = String.valueOf(location.getLongitude());
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sos_add);

            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Date data1 = new Date();
            int year = data1.getYear() + 1900;
            int month = data1.getMonth() + 1;
            String data = "" + data1.getDate() + "/" + month + "/" + year;
            String hora = "" + data1.getHours() + ":" + data1.getMinutes() + ":" + data1.getSeconds();

            tvDataSos = (TextView) findViewById(R.id.tvDataSos);
            tvDataSos.setText("Data: " + data + " - " + hora);

            Intent intent = getIntent();
            if (intent.getExtras().get("sos") != null) {
                ocorrencia = intent.getExtras().get("sos").toString();
            }

            tvOcorrenciaSos = (TextView) findViewById(R.id.tvOcorrenciaSos);
            tvOcorrenciaSos.setText("Tipo de ocorrência: " + ocorrencia);

            tvLatitudeSos = (TextView) findViewById(R.id.tvLatitudeSos);
            tvLongitudeSos = (TextView) findViewById(R.id.tvLongitudeSos);

            etLocalSos = (EditText) findViewById(R.id.etLocalSos);

            etDescricaoSos = (EditText) findViewById(R.id.etDescricaoSos);


            bSalvarSos = (Button) findViewById(R.id.bSalvarSos);
            bSalvarSos.setOnClickListener(this);
            bSalvarSos.setFocusable(true);
            bSalvarSos.requestFocus();

            bLigarSos = (Button) findViewById(R.id.bLigarSos);
            bLigarSos.setOnClickListener(this);

            bCancelarSos = (Button) findViewById(R.id.bCancelarSos);
            bCancelarSos.setOnClickListener(this);

        } catch (Exception e) {
            Log.i("RESULTADO", e.toString());
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        2);
            }
        } else {
            if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            } else {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
    }

    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
        String celular = sharedPreferences.getString("celular", "");
        if (v.getId() == R.id.bSalvarSos) {
            if (celular.equals("") || latitudesos == null || longitudesos == null) {
                Toast.makeText(this, "Aguarde o carregamento de todos os dados!", Toast.LENGTH_LONG).show();
            } else {
                salvarSos(v.getId(), celular);
            }
        } else if (v.getId() == R.id.bCancelarSos) {
            finish();
        } else if (v.getId() == R.id.bLigarSos) {
            if (celular.equals("") || latitudesos == null || longitudesos == null) {
                Toast.makeText(this, "Aguarde o carregamento de todos os dados!", Toast.LENGTH_LONG).show();
            } else {
                salvarSos(v.getId(), celular);
                Uri uri = Uri.parse("tel:190");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        }
    }

    public void salvarSos(int id, String celular) {
        AsyncDaoController asyncDaoController = new AsyncDaoController(this, SOS, INSERIR);
        asyncDaoController.execute(
                    celular,//1
                    ocorrencia,//2
                    latitudesos,//3
                    longitudesos,//4
                    ocorrencia + ";" + etLocalSos.getText().toString() + ";" + etDescricaoSos.getText().toString()//5
        );
        if (id == R.id.bSalvarSos) {
            finish();
        }
    }

}
