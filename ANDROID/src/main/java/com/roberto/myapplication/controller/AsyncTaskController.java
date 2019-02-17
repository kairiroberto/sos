package com.roberto.myapplication.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;
import com.roberto.myapplication.dao.SosDao;
import com.roberto.myapplication.dao.UsuarioDao;

public class AsyncTaskController extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        UsuarioDao usuarioDao = new UsuarioDao();
        usuarioDao.add();
        return null;
    }

}
