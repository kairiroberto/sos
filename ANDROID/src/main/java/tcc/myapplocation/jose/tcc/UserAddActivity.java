package tcc.myapplocation.jose.tcc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import tcc.myapplocation.jose.tcc.controller.AsyncDaoController;

public class UserAddActivity  extends AppCompatActivity implements View.OnClickListener  {

    private EditText etNomeUsu, etCelularUsu, etRuaUsu, etNumeroUsu, etBairroUsu, etCidadeUsu, etEmailUsu;
    private Button bCadastrarUsu;

    private final String USUARIO = "usuario";
    private final String ALTERAR = "alterar";

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_add);

        etNomeUsu = (EditText) findViewById(R.id.etNomeUsu);
        etCelularUsu = (EditText) findViewById(R.id.etCelularUsu);
        etRuaUsu = (EditText) findViewById(R.id.etRuaUsu);
        etNumeroUsu = (EditText) findViewById(R.id.etNumeroUsu);
        etBairroUsu = (EditText) findViewById(R.id.etBairroUsu);
        etCidadeUsu = (EditText) findViewById(R.id.etCidadeUso);
        etEmailUsu = (EditText) findViewById(R.id.etEmailUso);

        SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);

        id = String.valueOf(sharedPreferences.getInt("id", 0));
        etNomeUsu.setText(sharedPreferences.getString("nome", "vazio"));
        etCelularUsu.setText(sharedPreferences.getString("celular", "vazio"));
        etRuaUsu.setText(sharedPreferences.getString("rua", "vazio"));
        etNumeroUsu.setText(sharedPreferences.getString("numero", "vazio"));
        etBairroUsu.setText(sharedPreferences.getString("bairro", "vazio"));
        etCidadeUsu.setText(sharedPreferences.getString("cidade", "vazio"));
        etEmailUsu.setText(sharedPreferences.getString("email", "vazio"));

        bCadastrarUsu = (Button) findViewById(R.id.bCadastrarUsu);
        bCadastrarUsu.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bCadastrarUsu) {
            AsyncDaoController asyncDaoController = new AsyncDaoController(this, USUARIO, ALTERAR);
            asyncDaoController.execute(
                    id,
                    etNomeUsu.getText().toString(),
                    etCelularUsu.getText().toString(),
                    etRuaUsu.getText().toString(),
                    etNumeroUsu.getText().toString(),
                    etBairroUsu.getText().toString(),
                    etCidadeUsu.getText().toString(),
                    etEmailUsu.getText().toString()
            );
        }
    }

}
