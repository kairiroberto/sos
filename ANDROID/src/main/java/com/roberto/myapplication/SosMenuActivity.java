package com.roberto.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class SosMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bCrimeSos;
    private Button bMariaPenhaSos;
    private Button bUrgenciaSos;
    private Button bAcidenteSos;
    private Button bBombeiroSos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_menu);
        bCrimeSos = (Button) findViewById(R.id.bCrimeSos);
        bCrimeSos.setOnClickListener(this);
        bMariaPenhaSos = (Button) findViewById(R.id.bMariaPenhaSos);
        bMariaPenhaSos.setOnClickListener(this);
        bUrgenciaSos = (Button) findViewById(R.id.bUrgenciaSos);
        bUrgenciaSos.setOnClickListener(this);
        bAcidenteSos = (Button) findViewById(R.id.bAcidenteSos);
        bAcidenteSos.setOnClickListener(this);
        bBombeiroSos = (Button) findViewById(R.id.bBombeiroSos);
        bBombeiroSos.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, SosAddActivity.class);
        if (v.getId() == R.id.bCrimeSos) {
            i.putExtra("sos", "CRIME");
        } else if (v.getId() == R.id.bMariaPenhaSos) {
            i.putExtra("sos", "MARIA DA PENHA");
        } else if (v.getId() == R.id.bUrgenciaSos) {
            i.putExtra("sos", "URGÊNCIA MÉDICA");
        } else if (v.getId() == R.id.bAcidenteSos) {
            i.putExtra("sos", "ACIDENTE");
        } else if (v.getId() == R.id.bBombeiroSos) {
            i.putExtra("sos", "BOMBEIROS");
        }
        startActivity(i);
    }

}
