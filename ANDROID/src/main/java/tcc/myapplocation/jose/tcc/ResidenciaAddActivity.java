package tcc.myapplocation.jose.tcc;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ResidenciaAddActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etLatitudeArduino;
    private EditText etLongitudeArduino;
    private EditText etSerieArduino;
    private Button bSalvarResidencia;
    private Button bCancelarResidencia;

    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                etLatitudeArduino.setText("" + location.getLatitude());
                etLongitudeArduino.setText("" + location.getLongitude());
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residencia_add);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        etLatitudeArduino = (EditText) findViewById(R.id.etLatitudeArduino);
        etLongitudeArduino = (EditText) findViewById(R.id.etLongitudeArduino);
        etSerieArduino = (EditText) findViewById(R.id.etSerieArduino);
        bSalvarResidencia = (Button) findViewById(R.id.bSalvarResidencia);
        bSalvarResidencia.setOnClickListener(this);
        bCancelarResidencia = (Button) findViewById(R.id.bCancelarResidencia);
        bCancelarResidencia.setOnClickListener(this);
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
        int id = v.getId();
        if (id == R.id.bSalvarResidencia) {

        } else if (id == R.id.bCancelarResidencia) {

        }
        Intent i = new Intent(this, ResidenciaListActivity.class);
        startActivity(i);
    }
}
