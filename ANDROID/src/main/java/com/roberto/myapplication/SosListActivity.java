package com.roberto.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.roberto.myapplication.controller.AsyncDaoController;
import com.roberto.myapplication.model.Sos;

import java.util.ArrayList;
import java.util.List;

public class SosListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lvSosUsuario;
    private ArrayAdapter<Sos> arrayAdapter;
    private String celular = "";

    private final String SOS = "sos";
    private final String SOS_ATENDIDO = "sosAtendido";
    private final String SOS_CENCELAR = "sosCancelar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos_list);
        lvSosUsuario = (ListView) findViewById(R.id.lvSosUsuario);

        arrayAdapter = new ArrayAdapter<Sos>(
                this,
                android.R.layout.simple_list_item_1,
                MainActivity.sosUsuarioMain
        ){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                View view = super.getView(position,convertView,parent);
                if(position %2 == 1)
                {
                    view.setBackgroundColor(Color.parseColor("#ffb3b3"));
                }
                else
                {
                    view.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                return view;
            }
        };
        lvSosUsuario.setAdapter(arrayAdapter);
        lvSosUsuario.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final Sos sos = MainActivity.sosUsuarioMain.get(position);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("SOS " + sos.getIdsos());
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Deseja cancelar ou encerrar a ocorrência?\n");
        builder.setMessage(stringBuffer);
        builder.setPositiveButton("ENCERRAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AsyncDaoController asyncDaoController = new AsyncDaoController(SosListActivity.this, SOS, SOS_ATENDIDO);
                asyncDaoController.execute(String.valueOf(sos.getIdsos()));
                MainActivity.sosUsuarioMain.remove(sos);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AsyncDaoController asyncDaoController = new AsyncDaoController(SosListActivity.this, SOS, SOS_CENCELAR);
                asyncDaoController.execute(String.valueOf(sos.getIdsos()));
                MainActivity.sosUsuarioMain.remove(sos);
                arrayAdapter.notifyDataSetChanged();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
