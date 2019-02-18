package com.roberto.myapplication.dao;

import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;

public class UsuarioDao {

    public String add(String celular) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + celular);
            String p =  sb.toString();
            Log.i("UsuarioDao", "add: " + "http://sos.eyglys.com.br/index.php/usuario-rest/add.html?" + p);
            return apacheConection.post("http://sos.eyglys.com.br/index.php/usuario-rest/add.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String edit(String id, String nome, String celular, String logradouro, String numero, String bairro, String cidade, String email) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            sb.append("&nome=" + nome);
            sb.append("&celular=" + celular);
            sb.append("&logradouro=" + logradouro);
            sb.append("&numero=" + numero);
            sb.append("&bairro=" + bairro);
            sb.append("&cidade=" + cidade);
            sb.append("&email=" + email);
            String p =  sb.toString();
            Log.i("UsuarioDao", "put: " + "http://sos.eyglys.com.br/index.php/usuario-rest/edit.html?" + p);
            return apacheConection.post("http://sos.eyglys.com.br/index.php/usuario-rest/edit.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String delete(String id) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p = sb.toString();
            Log.i("UsuarioDao", "delete: " + "http://sos.eyglys.com.br/index.php/usuario-rest/delete.html?" + p);
            return apacheConection.delete("http://sos.eyglys.com.br/index.php/usuario-rest/delete.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String get() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            Log.i("UsuarioDao", "get: " + "http://sos.eyglys.com.br/index.php/usuario-rest/get.html");
            return apacheConection.get("http://sos.eyglys.com.br/index.php/usuario-rest/get.html");
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

}
