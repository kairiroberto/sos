package com.roberto.myapplication;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
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
    private LocationManager locationManager;
    private Double latitudesos;
    private Double longitudesos;

    private List<Sos> lista = new ArrayList<Sos>();

    private final String SOS = "sos";
    private final String LISTAR = "listar";
    private final String SOS_ATENDIDO = "sosAtendido";
    private final String SOS_VISUALIZADO = "sosVisualizado";
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        context = this;
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
        AsyncDaoController asyncDaoController = new AsyncDaoController(this, SOS, LISTAR);
        asyncDaoController.execute();
        for (Sos sos : MainActivity.sosMain) {
            lista.add(sos);
            Double lat = sos.getLatitudeSos();
            Double lng = sos.getLongitudeSos();
            LatLng ponto = new LatLng(lat, lng);
            adicionarMarcador(mMap, ponto, sos);
        }
        mMap.setOnInfoWindowClickListener(this);
    }

    private void adicionarMarcador(GoogleMap mMap, LatLng ponto, Sos sos) {
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(ponto);
        markerOptions.title(String.valueOf(sos.getIdsos()));
        if (sos.getOcorrencia() == 1) {
            markerOptions.snippet("CRIME");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(240));
        } else if (sos.getOcorrencia() == 2) {
            markerOptions.snippet("MARIA DA PENHA");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(300));
        } else if (sos.getOcorrencia() == 3) {
            markerOptions.snippet("ROUBO");
            float[] cor = new float[3];
            Color.colorToHSV(Color.parseColor("#FFFFFF"), cor);
            float cor2 = BitmapDescriptorFactory.HUE_AZURE;
            //.icon(BitmapDescriptorFactory.defaultMarker(cor[0])));
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(120));
        } else if (sos.getOcorrencia() == 4) {
            markerOptions.snippet("URG. HOSPITALAR");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(180));
        } else if (sos.getOcorrencia() == 5) {
            markerOptions.snippet("ACIDENTE");
        } else if (sos.getOcorrencia() == 6) {
            markerOptions.snippet("BOMBEIROS");
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(30));
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
                String local = s.getDescricao_sos().substring(0, s.getDescricao_sos().indexOf("_"));
                String descricao = s.getDescricao_sos().substring(s.getDescricao_sos().indexOf("_")).replace("_", " ");
                stringBuffer.append("Descrição: " + local + "(local) - " + descricao);
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
}
