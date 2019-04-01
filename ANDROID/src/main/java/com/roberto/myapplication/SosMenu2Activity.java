package com.roberto.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SosMenu2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button bCrimeSos2;
    private Button bMariaPenhaSos2;
    private Button bUrgenciaSos2;
    private Button bAcidenteSos2;
    private Button bBombeiroSos2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_menu2);
        bCrimeSos2 = (Button) findViewById(R.id.bCrimeSos2);
        bCrimeSos2.setOnClickListener(this);
        bMariaPenhaSos2 = (Button) findViewById(R.id.bMariaPenhaSos2);
        bMariaPenhaSos2.setOnClickListener(this);
        bUrgenciaSos2 = (Button) findViewById(R.id.bUrgenciaSos2);
        bUrgenciaSos2.setOnClickListener(this);
        bAcidenteSos2 = (Button) findViewById(R.id.bAcidenteSos2);
        bAcidenteSos2.setOnClickListener(this);
        bBombeiroSos2 = (Button) findViewById(R.id.bBombeiroSos2);
        bBombeiroSos2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, SosAddActivity.class);
        if (v.getId() == R.id.bCrimeSos2) {
            i.putExtra("sos", "CRIME");
        } else if (v.getId() == R.id.bMariaPenhaSos2) {
            i.putExtra("sos", "MARIA DA PENHA");
        } else if (v.getId() == R.id.bUrgenciaSos2) {
            i.putExtra("sos", "URGÊNCIA MÉDICA");
        } else if (v.getId() == R.id.bAcidenteSos2) {
            i.putExtra("sos", "ACIDENTE");
        } else if (v.getId() == R.id.bBombeiroSos2) {
            i.putExtra("sos", "BOMBEIROS");
        }
        startActivity(i);
    }

}
