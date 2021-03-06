package tcc.myapplocation.jose.tcc.controller;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import tcc.myapplocation.jose.tcc.MainActivity;
import tcc.myapplocation.jose.tcc.conection.SosBD;
import tcc.myapplocation.jose.tcc.model.Sos;

import static android.content.Context.MODE_PRIVATE;

public class DataController {

    private Context context;

    private SosBD sosBD;

    private String tabela;
    private String acao;

    private final String USUARIO = "usuario";
    private final String SOS = "sos";
    private final String INSERIR = "inserir";
    private final String ALTERAR = "alterar";
    private final String LISTAR = "listar";
    private final String SOS_USUARIO = "sosUsuario";
    private final String SOS_VISUALIZADO = "sosVisualizado";
    private final String SOS_ATENDIDO = "sosAtendido";
    private final String SOS_CENCELAR = "sosCancelar";

    public DataController(Context context, String tabela, String acao) {
        this.context = context;
        this.tabela = tabela;
        this.acao = acao;
        this.sosBD = new SosBD(context);
    }

    public void converteJSONfromString(String s) {
        try {
            if (!s.contains("[") && !s.contains("]")) {
                JSONObject jsonObject = new JSONObject(s);
                if (tabela == USUARIO) {
                    if (acao == INSERIR || acao == ALTERAR) {
                        salvarSharedPreferences(jsonObject);
                    }
                }
            } else {
                JSONArray jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (tabela == SOS) {
                        if (acao == INSERIR || acao == SOS_ATENDIDO || acao == SOS_CENCELAR || acao == SOS_VISUALIZADO) {
                            addMainListSos(jsonObject);
                            addListSosHistorico(jsonObject);
                        } else if (acao == LISTAR) {
                            addMainListSos(jsonObject);
                        } else if (acao == SOS_USUARIO) {
                            addListSosHistorico(jsonObject);
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
            imprimir("Usuário salvo localmente!");
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("salvarSharedPreferences: " + e.toString());
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
            sos.setDescricaoSos(jsonObject.getString("descricao_sos"));
            sos.setAtendidoSos(Integer.parseInt(jsonObject.getString("atendido_sos")));
            sos.setVizualizadoSos(Integer.parseInt(jsonObject.getString("vizualizado_sos")));
            sos.setCanceladoSos(Integer.parseInt(jsonObject.getString("cancelar")));
            if (!MainActivity.sosMain.contains(sos)) {
                MainActivity.sosMain.add(sos);
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
            int id = sharedPreferences.getInt("id", 0);
            if (sos.getUsuario() == id) {
                sosBD.save(sos);
            }
            //imprimir("addMainListSos: " + MainActivity.sosMain.size());
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
            sos.setDescricaoSos(jsonObject.getString("descricao_sos"));
            sos.setAtendidoSos(Integer.parseInt(jsonObject.getString("atendido_sos")));
            sos.setVizualizadoSos(Integer.parseInt(jsonObject.getString("vizualizado_sos")));
            Log.i("CANCELAR", jsonObject.getString("cancelar"));
            if (jsonObject.getString("cancelar").equals("1")) {
                sos.setCanceladoSos(1);
            } else {
                sos.setCanceladoSos(0);
            }
            sosBD.save(sos);
            if (!MainActivity.sosUsuarioMain.contains(sos)) {
                MainActivity.sosUsuarioMain.add(sos);
            }
            //imprimir("addListSosHistorico: " + MainActivity.sosUsuarioMain.size());
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("addListSosHistorico: " + e.toString());
        }
    }

    public void imprimir(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

}
