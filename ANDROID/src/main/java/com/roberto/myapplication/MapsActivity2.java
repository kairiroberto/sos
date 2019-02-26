package com.roberto.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.roberto.myapplication.controller.AsyncTaskController;
import com.roberto.myapplication.model.Sos;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private Double latitudesos;
    private Double longitudesos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
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
        AsyncTaskController asyncTaskController = new AsyncTaskController(this, "sos", "listar");
        asyncTaskController.execute();
        for (Sos sos : MainActivity.sosMain) {
            Double lat = sos.getLatitudeSos();
            Double lng = sos.getLongitudeSos();
            LatLng ponto = new LatLng(lat, lng);
            if (sos.getOcorrencia() == 1) {
                mMap.addMarker(new MarkerOptions()
                        .position(ponto)
                        .title("SOS " + sos.getIdsos())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
            } else if (sos.getOcorrencia() == 2) {
                mMap.addMarker(new MarkerOptions()
                        .position(ponto)
                        .title("SOS " + sos.getIdsos())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)));
            } else if (sos.getOcorrencia() == 4) {
                mMap.addMarker(new MarkerOptions()
                        .position(ponto)
                        .title("SOS " + sos.getIdsos())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)));
            } else if (sos.getOcorrencia() == 5) {
                mMap.addMarker(new MarkerOptions()
                        .position(ponto)
                        .title("SOS " + sos.getIdsos())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            } else if (sos.getOcorrencia() == 6) {
                mMap.addMarker(new MarkerOptions()
                        .position(ponto)
                        .title("SOS " + sos.getIdsos())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            }
            mMap.setOnInfoWindowClickListener(this);
        }
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(MapsActivity2.this, marker.getTitle(), Toast.LENGTH_SHORT).show();
    }
}
