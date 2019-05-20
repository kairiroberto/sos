package tcc.myapplocation.jose.tcc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tcc.myapplocation.jose.tcc.controller.AsyncDaoController;
import tcc.myapplocation.jose.tcc.model.Sos;

public class ResidenciaListActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    Button bAddResidencia;
    ListView lvResidencia;
    List<String> lista = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residencia_list);
        bAddResidencia = (Button) findViewById(R.id.bAddResidencia);
        bAddResidencia.setOnClickListener(this);
        lvResidencia = (ListView) findViewById(R.id.lvResidencia);
        lista.add("casa1");
        lista.add("casa2");
        lista.add("casa3");
        lista.add("casa4");
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        lvResidencia.setAdapter(adapter);
        lvResidencia.setOnItemClickListener(this);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.bAddResidencia) {
            Intent i = new Intent(this, ResidenciaAddActivity.class);
            startActivity(i);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        final String residencia = lista.get(position);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Residência " + residencia);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Deseja excluir a residência?\n");
        builder.setMessage(stringBuffer);

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //AsyncDaoController asyncDaoController = new AsyncDaoController(SosListActivity.this, SOS, SOS_ATENDIDO);
                //asyncDaoController.execute(String.valueOf(sos.getIdsos()));
                //MainActivity.sosUsuarioMain.remove(sos);
                //if (sos.getAtendidoSos() == 1) {
                    //Toast.makeText(SosListActivity.this, "Ocorrência já foi atendida!", Toast.LENGTH_LONG).show();
                //} else {
                    //sosBD.atendido(sos);
                //}
                lista.remove(position);
                adapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.notifyDataSetChanged();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
