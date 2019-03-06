package com.roberto.myapplication.controller;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.roberto.myapplication.dao.ResidenciaDao;
import com.roberto.myapplication.dao.SosDao;
import com.roberto.myapplication.dao.UsuarioDao;

public class AsyncDaoController extends AsyncTask<String, String, String> {

    private Context context;

    private String tabela;
    private String acao;

    private final String USUARIO = "usuario";
    private final String SOS = "sos";
    private final String INSERIR = "inserir";
    private final String ALTERAR = "alterar";
    private final String LISTAR = "listar";
    private final String SOS_ATENDIDO = "sosAtendido";
    private final String SOS_VISUALIZADO = "sosVisualizado";
    private final String USUARIO_LISTAR = "usuarioListar";

    private SosDao sosDao = new SosDao();
    private UsuarioDao usuarioDao = new UsuarioDao();
    private ResidenciaDao residenciaDao = new ResidenciaDao();

    private DataController dataController;

    public AsyncDaoController(Context context, String tabela, String acao) {
        this.context = context;
        this.tabela = tabela;
        this.acao = acao;
        dataController = new DataController(context, tabela, acao);
    }

    @Override
    protected String doInBackground(String... strings) {

        Log.i("AsyncTaskController", tabela + acao);

        if (tabela == SOS) {
            if (acao == INSERIR) {
                return sosDao.add(strings[0], strings[1], strings[2], strings[3], strings[4]);
            } else if (acao == LISTAR) {
                return sosDao.desc();
            } else if (acao == SOS_ATENDIDO) {
                return sosDao.editatendido(strings[0]);
            } else if (acao == SOS_VISUALIZADO) {
                return sosDao.editvisualizado(strings[0]);
            }
        }

        else if (tabela == USUARIO) {
            if (acao == INSERIR) {
                return usuarioDao.add(strings[0]);
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        if (s != null) {
            dataController.converteJSONfromString(s);
        }
    }

}
