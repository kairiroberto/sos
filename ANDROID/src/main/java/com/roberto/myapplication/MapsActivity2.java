package com.roberto.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roberto.myapplication.controller.AsyncDaoController;
import com.roberto.myapplication.controller.AsyncDaoController;
import com.roberto.myapplication.model.Sos;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private Double latitudesos = -6.2623957078904;
    private Double longitudesos = -36.512761032209;
    private LocationManager locationManager;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            latitudesos = location.getLatitude();
            longitudesos = location.getLongitude();
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

    private List<Sos> lista = new ArrayList<Sos>();

    private final String SOS = "sos";
    private final String SOS_VISUALIZADO = "sosVisualizado";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_COARSE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION},
                        2);
            }
        }
        mMap.setMyLocationEnabled(true);
        CameraPosition position = new CameraPosition.Builder().target(new LatLng(latitudesos, longitudesos)).zoom(15).build();
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(position));
        mMap.setOnInfoWindowClickListener(this);
        atualizarLista();
    }

    private void atualizarLista() {
        for (Sos sos : MainActivity.sosMain) {
            lista.add(sos);
            Double lat = sos.getLatitudeSos();
            Double lng = sos.getLongitudeSos();
            LatLng ponto = new LatLng(lat, lng);
            adicionarMarcador(mMap, ponto, sos);
        }
    }

    private void adicionarMarcador(GoogleMap mMap, LatLng ponto, Sos sos) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ponto);
        markerOptions.title(String.valueOf(sos.getIdsos()));
        if (sos.getOcorrencia() == 1) {
            markerOptions.snippet("CRIME");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(240));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.arma2));
        } else if (sos.getOcorrencia() == 2) {
            markerOptions.snippet("MARIA DA PENHA");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(300));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.mulher2));
        } else if (sos.getOcorrencia() == 3) {
            markerOptions.snippet("ROUBO");
            float[] cor = new float[3];
            Color.colorToHSV(Color.parseColor("#FFFFFF"), cor);
            float cor2 = BitmapDescriptorFactory.HUE_AZURE;
            //.icon(BitmapDescriptorFactory.defaultMarker(cor[0])));
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(120));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.arma2));
        } else if (sos.getOcorrencia() == 4) {
            markerOptions.snippet("URG. HOSPITALAR");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(180));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.emergencia2));
        } else if (sos.getOcorrencia() == 5) {
            markerOptions.snippet("ACIDENTE");
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.acidente2));
        } else if (sos.getOcorrencia() == 6) {
            markerOptions.snippet("BOMBEIROS");
            //markerOptions.icon(BitmapDescriptorFactory.defaultMarker(30));
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.fogo2));
        }
        Marker marker = mMap.addMarker(markerOptions);
    }

    @Override
    public void onInfoWindowClick(final Marker marker) {
        final AsyncDaoController asyncDaoController = new AsyncDaoController(MapsActivity2.this, SOS, SOS_VISUALIZADO);
        asyncDaoController.execute(marker.getTitle());
        AlertDialog.Builder builder = new AlertDialog.Builder(MapsActivity2.this);
        builder.setTitle("SOS " + marker.getTitle());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Deseja atender a ocorrência?\n");
        for (Sos s : lista) {
            if (s.getIdsos() == Integer.parseInt(marker.getTitle())) {
                stringBuffer.append(s.getDescricaoSos());
            }
        }
        builder.setMessage(stringBuffer);
        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MapsActivity2.this, "SOS ATENDIDO", Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse("tel:190");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                startActivity(intent);
            }
        });
        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MapsActivity2.this, "SOS NÃO ATENDIDO", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        atualizarLista();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
    }

}
