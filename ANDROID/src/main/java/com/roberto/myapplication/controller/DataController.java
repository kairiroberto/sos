package com.roberto.myapplication.controller;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.roberto.myapplication.MainActivity;
import com.roberto.myapplication.model.Sos;
import com.roberto.myapplication.model.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.content.Context.MODE_PRIVATE;

public class DataController {

    private Context context;

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
                if ((jsonArray == null || jsonArray.isNull(0)) && tabela == SOS) {
                    MainActivity.sosMain.clear();
                    MainActivity.sosUsuarioMain.clear();
                } else {
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        if (tabela == SOS) {
                            if (acao == INSERIR || acao == SOS_ATENDIDO || acao == SOS_CENCELAR || acao == SOS_VISUALIZADO) {
                                addMainListSos(jsonObject);
                                addListSosHistorico(jsonObject);
                            } else if (acao == LISTAR) {
                                addMainListSos(jsonObject);
                            } else if (acao == SOS_VISUALIZADO) {
                                addListSosVisualizado(jsonObject);
                            } else if (acao == SOS_USUARIO) {
                                addListSosHistorico(jsonObject);
                            }
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
            sos.setCanceladoSos(Boolean.parseBoolean(jsonObject.getString("cancelar")));
            if (!MainActivity.sosMain.contains(sos)) {
                MainActivity.sosMain.add(sos);
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
            sos.setCanceladoSos(Boolean.parseBoolean(jsonObject.getString("cancelar")));
            if (!MainActivity.sosUsuarioMain.contains(sos)){
                MainActivity.sosUsuarioMain.add(sos);
            }
            //imprimir("addListSosHistorico: " + MainActivity.sosUsuarioMain.size());
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("addListSosHistorico: " + e.toString());
        }
    }

    private void addListSosVisualizado(JSONObject jsonObject) {
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
            sos.setCanceladoSos(Boolean.parseBoolean(jsonObject.getString("cancelar")));
            if (!MainActivity.sosVisualizadoMain.contains(sos)) {
                MainActivity.sosVisualizadoMain.add(sos);
            }
            //imprimir("addListSosVisualizado: " + MainActivity.sosVisualizadoMain.size());
        } catch (JSONException e) {
            e.printStackTrace();
            imprimir("addListSosVisualizado: " + e.toString());
        }
    }

    public void imprimir(String s) {
        Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

}
