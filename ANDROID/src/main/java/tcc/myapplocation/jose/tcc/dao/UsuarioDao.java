package tcc.myapplocation.jose.tcc.dao;

import android.util.Log;

import tcc.myapplocation.jose.tcc.conection.ApacheConection;

public class UsuarioDao {

    private String TAG = "UsuarioDao:";

    public String add(String celular) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + celular);
            String p =  sb.toString();
            Log.i(TAG + "add:", "add: " + ApacheConection.urlsos + "usuario-rest/add.html?" + p);
            return apacheConection.post(ApacheConection.urlsos + "usuario-rest/add.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "add:ERRO:", e.toString());
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
            Log.i(TAG + "edit:", "put: " + ApacheConection.urlsos + "usuario-rest/edit.html?" + p);
            return apacheConection.post(ApacheConection.urlsos + "usuario-rest/edit.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "edit:ERRO:", e.toString());
            return null;
        }
    }

    public String deleteId(String id) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p = sb.toString();
            Log.i(TAG + "deleteId:", "delete: " + ApacheConection.urlsos + "usuario-rest/deleteid.html?" + p);
            return apacheConection.delete(ApacheConection.urlsos + "usuario-rest/deleteid.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "deleteId:ERRO:", e.toString());
            return null;
        }
    }

    public String get() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            Log.i(TAG + "get:", "get: " + ApacheConection.urlsos + "usuario-rest/get.html");
            return apacheConection.get(ApacheConection.urlsos + "usuario-rest/get.html");
        } catch (Exception e) {
            Log.e(TAG + "get:ERRO:", e.toString());
            return null;
        }
    }

    public String editativo(String id, String ativo) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            sb.append("&ativo=" + ativo);
            String p =  sb.toString();
            Log.i(TAG + "editativo:", "put: " + ApacheConection.urlsos + "usuario-rest/editativo.html?" + p);
            return apacheConection.post(ApacheConection.urlsos + "usuario-rest/editativo.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "editativo:ERRO:", e.toString());
            return null;
        }
    }

    public String editfake(String id, String fake) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            sb.append("&fake=" + fake);
            String p =  sb.toString();
            Log.i(TAG + "editfake:", "put: " + ApacheConection.urlsos + "usuario-rest/editfake.html?" + p);
            return apacheConection.post(ApacheConection.urlsos + "usuario-rest/editfake.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "editfake:ERRO:", e.toString());
            return null;
        }
    }

    public String id(String id) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p =  sb.toString();
            Log.i(TAG + "id:", "get: " + ApacheConection.urlsos + "usuario-rest/id.html?" + p);
            return apacheConection.get(ApacheConection.urlsos + "usuario-rest/id.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "id:ERRO:", e.toString());
            return null;
        }
    }

    public String celular(String celular) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + celular);
            String p =  sb.toString();
            Log.i(TAG + "celular:", "get: " + ApacheConection.urlsos + "usuario-rest/celular.html?" + p);
            return apacheConection.get(ApacheConection.urlsos + "usuario-rest/celular.html?" + p);
        } catch (Exception e) {
            Log.e(TAG + "celular:ERRO:", e.toString());
            return null;
        }
    }

}
