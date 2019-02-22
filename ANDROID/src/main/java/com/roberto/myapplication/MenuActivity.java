package com.roberto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bSosMenu;
    private Button bMapaMenu;
    private Button bMeusSosMenu;
    private Button bResidenciaMenu;
    private Button bPerfilMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        bSosMenu = (Button) findViewById(R.id.bSosMenu);
        bSosMenu.setOnClickListener(this);
        bMapaMenu = (Button) findViewById(R.id.bMapaMenu);
        bMapaMenu.setOnClickListener(this);
        bMeusSosMenu = (Button) findViewById(R.id.bMeusSosMenu);
        bMeusSosMenu.setOnClickListener(this);
        bResidenciaMenu = (Button) findViewById(R.id.bResidenciaMenu);
        bResidenciaMenu.setOnClickListener(this);
        bPerfilMenu = (Button) findViewById(R.id.bPerfilMenu);
        bPerfilMenu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = null;
        if (v.getId() == R.id.bSosMenu) {
            i = new Intent(this, SosMenuActivity.class);
        } else if (v.getId() == R.id.bMapaMenu) {
            i = new Intent(this, MapsActivity.class);
        } else if (v.getId() == R.id.bMeusSosMenu) {
            i = new Intent(this, SosListActivity.class);
        } else if (v.getId() == R.id.bResidenciaMenu) {
            i = new Intent(this, ResidenciaListActivity.class);
        } else if (v.getId() == R.id.bPerfilMenu) {
            i = new Intent(this, UserAddActivity.class);
        }
        startActivity(i);
    }

}
