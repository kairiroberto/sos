package com.roberto.myapplication.dao;

import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;

public class UsuarioDao {

    public String add() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + "352607097641808");
            String p =  sb.toString();
            Log.i("UsuarioDao", "add");
            return apacheConection.post("http://sos.eyglys.com.br/index.php/usuario-rest/add.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

}
