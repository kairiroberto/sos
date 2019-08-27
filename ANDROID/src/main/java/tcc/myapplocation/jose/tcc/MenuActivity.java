package tcc.myapplocation.jose.tcc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import tcc.myapplocation.jose.tcc.controller.AsyncDaoController;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    private Button bSosMenu3;
    private Button bSosMenu2;
    private Button bSosMenu;
    private Button bMapaMenu2;
    private Button bMapaMenu;
    private Button bMeusSosMenu;
    private Button bResidenciaMenu;
    private Button bPerfilMenu;

    private final String USUARIO = "usuario";
    private final String SOS = "sos";
    private final String INSERIR = "inserir";
    private final String ALTERAR = "alterar";
    private final String LISTAR = "listar";
    private final String SOS_USUARIO = "sosUsuario";
    private final String SOS_VISUALIZADO = "sosVisualizado";
    private final String SOS_ATENDIDO = "sosAtendido";
    private final String SOS_CENCELAR = "sosCancelar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        //atualizarListas();

        bSosMenu = (Button) findViewById(R.id.bSosMenu);
        bSosMenu.setOnClickListener(this);
        bSosMenu2 = (Button) findViewById(R.id.bSosMenu);
        bSosMenu2.setOnClickListener(this);
        bSosMenu3 = (Button) findViewById(R.id.bSosMenu);
        bSosMenu3.setOnClickListener(this);
        bMapaMenu2 = (Button) findViewById(R.id.bMapaMenu);
        bMapaMenu2.setOnClickListener(this);
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
        //atualizarListas();
        Intent i = null;
        if (v.getId() == R.id.bSosMenu) {
            i = new Intent(this, SosMenuActivity.class);
        } else if (v.getId() == R.id.bMapaMenu) {
            i = new Intent(this, MapsActivity3.class);
        } else if (v.getId() == R.id.bMeusSosMenu) {
            i = new Intent(this, SosListActivity.class);
        } else if (v.getId() == R.id.bResidenciaMenu) {
            i = new Intent(this, ResidenciaListActivity.class);
        } else if (v.getId() == R.id.bPerfilMenu) {
            i = new Intent(this, UserAddActivity.class);
        }
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        atualizarListas();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //atualizarListas();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //atualizarListas();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //atualizarListas();
    }

    @Override
    protected void onStop() {
        super.onStop();
        //atualizarListas();
    }

    private void atualizarListas() {
        AsyncDaoController asyncDaoController = new AsyncDaoController(this, SOS, LISTAR);
        asyncDaoController.execute();

        SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
        String celular = sharedPreferences.getString("celular", " ");

        AsyncDaoController asyncDaoController2 = new AsyncDaoController(this, SOS, SOS_USUARIO);
        asyncDaoController2.execute(celular);
    }
}
