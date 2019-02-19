package com.roberto.myapplication.controller;

import android.os.AsyncTask;
import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;
import com.roberto.myapplication.dao.SosDao;
import com.roberto.myapplication.dao.UsuarioDao;

public class AsyncTaskController extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... strings) {
        SosDao sosDao = new SosDao();
        sosDao.usuario("352607097641803");
        sosDao.desc();
        sosDao.proximo("-6.263391", "-36.512363");
        sosDao.id("52");
        return null;
    }

}
