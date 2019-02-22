package com.roberto.myapplication.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.roberto.myapplication.MainActivity;
import com.roberto.myapplication.conection.ApacheConection;
import com.roberto.myapplication.dao.ResidenciaDao;
import com.roberto.myapplication.dao.SosDao;
import com.roberto.myapplication.dao.UsuarioDao;
import com.roberto.myapplication.model.Sos;
import com.roberto.myapplication.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class AsyncTaskController extends AsyncTask<String, String, String> {

    private Context context;
    private String tabela;
    private String acao;

    public AsyncTaskController(Context context, String tabela, String acao) {
        this.context = context;
        this.tabela = tabela;
        this.acao = acao;
    }

    @Override
    protected String doInBackground(String... strings) {
        SosDao sosDao = new SosDao();
        UsuarioDao usuarioDao = new UsuarioDao();
        ResidenciaDao residenciaDao = new ResidenciaDao();

        if (tabela.equals("sos")) {
            if (acao.equals("inserir")) {
                return sosDao.add(strings[0], strings[1], strings[2], strings[3], strings[4]);
            } else if (acao.equals("alterar")) {
                return null;
            }
        }

        else if (tabela.equals("usuario")) {
            if (acao.equals("inserir")) {
                return usuarioDao.add(strings[0]);
            } else if (acao.equals("alterar")) {
                return null;
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            converteJSONfromString(s);
        }
    }

    private void converteJSONfromString(String s) {
        Log.i("converteJSONfromString", s);
        MainActivity.sosMain.clear();
        MainActivity.usuariosMain.clear();
        MainActivity.sosUsuarioMain.clear();
        try {
            if (!s.contains("[") && !s.contains("]")) {
                JSONObject jsonObject = new JSONObject(s);
                salvarSharedPreferences(jsonObject);
            } else {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (tabela.equals("usuario")) {
                        addMainListUsuario(jsonObject);
                    }

                    else if (tabela.equals("sos")) {
                        if (acao.equals("inserir")) {
                            addMainListSos(jsonObject);
                        } else if (acao.equals("listar")) {
                            addMainListSos(jsonObject);
                        }
                    }
                }
            }
        } catch (Exception e) {
            imprimir("converteJSONfromString: " + e.toString());
            Log.e("converteJSONfromString", e.toString());
        }
    }

    private void salvarSharedPreferences(JSONObject jsonObject) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences("ACESSO", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("id", Integer.parseInt(jsonObject.getString("idusuario")));
            editor.putString("celular", jsonObject.getString("celular_usu"));
            editor.putString("nome", jsonObject.getString("nome_usu"));
            editor.putString("rua", jsonObject.getString("logradouro_uso"));
            editor.putString("numero", jsonObject.getString("numero_uso"));
            editor.putString("bairro", jsonObject.getString("bairro_uso"));
            editor.putString("cidade", jsonObject.getString("cidade_uf_uso"));
            editor.putString("email", jsonObject.getString("email_uso"));
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void addMainListUsuario(JSONObject jsonObject) throws JSONException {
        try {
            Usuario usuario = new Usuario();
            usuario.setIdusuario(Integer.parseInt(jsonObject.getString("idusuario")));
            usuario.setCelular_usu(jsonObject.getString("celular_usu"));
            MainActivity.usuariosMain.clear();
            MainActivity.usuariosMain.add(usuario);
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("addMainListUsuario: " + e.toString());
        }
    }

    private void addMainListSos(JSONObject jsonObject) {
        try {
            Sos sos = new Sos();
            sos.setIdsos(Integer.parseInt(jsonObject.getString("idsos")));
            sos.setDataSos(jsonObject.getString("data_sos"));
            sos.setHoraSos(jsonObject.getString("hora_sos"));
            sos.setUsuario(Integer.parseInt(jsonObject.getString("usuario")));
            sos.setOcorrencia(Integer.parseInt(jsonObject.getString("ocorrencia")));
            sos.setLatitudeSos(Double.parseDouble(jsonObject.getString("latitude_sos")));
            sos.setLongitudeSos(Double.parseDouble(jsonObject.getString("longitude_sos")));
            sos.setDescricao_sos(jsonObject.getString("descricao_sos"));
            MainActivity.sosMain.clear();
            MainActivity.sosMain.add(sos);
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("addMainListSos: " + e.toString());
        }
    }

    private void addListSosHistorico(JSONObject jsonObject) {
        try {
            Sos sos = new Sos();
            sos.setIdsos(Integer.parseInt(jsonObject.getString("idsos")));
            sos.setDataSos(jsonObject.getString("data_sos"));
            sos.setHoraSos(jsonObject.getString("hora_sos"));
            sos.setUsuario(Integer.parseInt(jsonObject.getString("usuario")));
            sos.setOcorrencia(Integer.parseInt(jsonObject.getString("ocorrencia")));
            sos.setLatitudeSos(Double.parseDouble(jsonObject.getString("latitude_sos")));
            sos.setLongitudeSos(Double.parseDouble(jsonObject.getString("longitude_sos")));
            sos.setDescricao_sos(jsonObject.getString("descricao_sos"));
            MainActivity.sosUsuarioMain.clear();
            MainActivity.sosUsuarioMain.add(sos);
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("addListSosHistorico: " + e.toString());
        }
    }

    protected void imprimir(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

}
