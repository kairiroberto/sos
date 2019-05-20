package tcc.myapplocation.jose.tcc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by kairi on 10-5-19.
 */

public class SosMenu3Activity extends AppCompatActivity implements View.OnClickListener  {

    private Button bCrimeSos3;
    private Button bMariaPenhaSos3;
    private Button bUrgenciaSos3;
    private Button bAcidenteSos3;
    private Button bBombeiroSos3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_menu3);
        bCrimeSos3 = (Button) findViewById(R.id.bCrimeSos3);
        bCrimeSos3.setOnClickListener(this);
        bMariaPenhaSos3 = (Button) findViewById(R.id.bMariaPenhaSos3);
        bMariaPenhaSos3.setOnClickListener(this);
        bUrgenciaSos3 = (Button) findViewById(R.id.bUrgenciaSos3);
        bUrgenciaSos3.setOnClickListener(this);
        bAcidenteSos3 = (Button) findViewById(R.id.bAcidenteSos3);
        bAcidenteSos3.setOnClickListener(this);
        bBombeiroSos3 = (Button) findViewById(R.id.bBombeiroSos3);
        bBombeiroSos3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent(this, SosAddActivity.class);
        if (v.getId() == R.id.bCrimeSos3) {
            i.putExtra("sos", "CRIME");
        } else if (v.getId() == R.id.bMariaPenhaSos3) {
            i.putExtra("sos", "MARIA DA PENHA");
        } else if (v.getId() == R.id.bUrgenciaSos3) {
            i.putExtra("sos", "URGÊNCIA MÉDICA");
        } else if (v.getId() == R.id.bAcidenteSos3) {
            i.putExtra("sos", "ACIDENTE");
        } else if (v.getId() == R.id.bBombeiroSos3) {
            i.putExtra("sos", "BOMBEIROS");
        }
        startActivity(i);
    }
}
