package com.roberto.myapplication.dao;

import android.util.Log;

import com.roberto.myapplication.conection.ApacheConection;

public class SosDao {

    public String add() {
        try {
            ApacheConection apacheConection = new ApacheConection();
            StringBuffer sb = new StringBuffer();
            sb.append("celular=" + "352607097641803");
            sb.append("&ocorrencia=" + "1");
            sb.append("&lat=" + "2");
            sb.append("&lon=" + "1");
            sb.append("&des=" + "1");
            String p =  sb.toString();
            Log.i("SosDao", "add");
            return apacheConection.put("http://sos.eyglys.com.br/index.php/sos-rest/add.html?" + p);
        } catch (Exception e) {
            Log.e("RESULTADO", e.toString());
            return null;
        }
    }

}
