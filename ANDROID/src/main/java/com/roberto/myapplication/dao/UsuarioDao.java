package com.roberto.myapplication.dao;

import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;

public class UsuarioDao {

    public String add() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + "352607097641809");
            String p =  sb.toString();
            Log.i("UsuarioDao", "add: " + "http://sos.eyglys.com.br/index.php/usuario-rest/add.html?" + p);
            return apacheConection.post("http://sos.eyglys.com.br/index.php/usuario-rest/add.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String edit() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + "15");
            sb.append("&nome=" + "JOSE");
            sb.append("&celular=" + "123456789");
            sb.append("&logradouro=" + "123");
            sb.append("&numero=" + "123");
            sb.append("&bairro=" + "123");
            sb.append("&cidade=" + "123");
            sb.append("&email=" + "jose@gmail.com");
            String p =  sb.toString();
            Log.i("UsuarioDao", "put: " + "http://sos.eyglys.com.br/index.php/usuario-rest/edit.html?" + p);
            return apacheConection.post("http://sos.eyglys.com.br/index.php/usuario-rest/edit.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String delete() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + "18");
            String p = sb.toString();
            Log.i("UsuarioDao", "delete: " + "http://sos.eyglys.com.br/index.php/usuario-rest/delete.html?" + p);
            return apacheConection.delete("http://sos.eyglys.com.br/index.php/usuario-rest/delete.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

}
