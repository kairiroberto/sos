package com.roberto.myapplication.dao;

import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;

public class SosDao {

    public String add(String celular, String ocorrencia, String lat, String lon, String des) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            ocorrencia = ocorrencia.replace(" ", "_")
                    .replace("Ê", "E")
                    .replace("É", "E");
            des = des.replace(" ", "_");
            sb.append("celular=" + celular);
            sb.append("&ocorrencia=" + ocorrencia);
            sb.append("&lat=" + lat);
            sb.append("&lon=" + lon);
            sb.append("&des=" + des);
            String p =  sb.toString();
            Log.i("SosDao", "add: " + ApacheConection.urlsos + "sos-rest/add.html?" + p);
            return apacheConection.post(ApacheConection.urlsos + "sos-rest/add.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String editcancelar(String id) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p =  sb.toString();
            Log.i("SosDao", "edit");
            return apacheConection.put(ApacheConection.urlsos + "sos-rest/editcancelar.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String editatendido(String id) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p =  sb.toString();
            Log.i("SosDao", "edit");
            return apacheConection.put(ApacheConection.urlsos + "sos-rest/editatendido.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String editvisualizado(String id) {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p =  sb.toString();
            Log.i("SosDao", "edit");
            return apacheConection.put(ApacheConection.urlsos + "sos-rest/editvisualizado.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String usuario(String celular) {
        try{
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + celular);
            String p =  sb.toString();
            Log.i("SosDao", "usuario");
            return apacheConection.get(ApacheConection.urlsos + "sos-rest/usuario.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String id(String id) {
        try{
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("id=" + id);
            String p =  sb.toString();
            Log.i("SosDao", "id");
            return apacheConection.get(ApacheConection.urlsos + "sos-rest/id.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String desc() {
        try{
            ApacheConection apacheConection = new ApacheConection();
            Log.i("SosDao", "desc");
            return apacheConection.get(ApacheConection.urlsos + "sos-rest/desc.html");
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String asc() {
        try{
            ApacheConection apacheConection = new ApacheConection();
            Log.i("SosDao", "desc");
            return apacheConection.get(ApacheConection.urlsos + "sos-rest/asc.html");
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

    public String proximo(String lat, String lon) {
        try{
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("&lat=" + lat);
            sb.append("&lon=" + lon);
            String p =  sb.toString();
            Log.i("SosDao", "desc");
            return apacheConection.get(ApacheConection.urlsos + "sos-rest/proximo.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

}
