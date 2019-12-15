package com.roberto.myapplication.conection;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApacheConection {

    public static final String urlsos = "<host>/index.php/";
    //public static final String urlsos = "http://192.168.1.189/sos/web/index.php/";

    public String post(String urlString) {
        try {
            Log.i("POST", urlString);
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("User-Agent", "ANDROID");
            DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
            dataOutputStream.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String sb = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb += line;
            }
            bufferedReader.close();
            dataOutputStream.close();
            urlConnection.disconnect();
            Log.i("RESULTADO", sb);
            return sb;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RESULTADO", e.toString());
            return "";
        }
    }

    public String put(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("PUT");
            urlConnection.setRequestProperty("User-Agent", "ANDROID");
            DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
            dataOutputStream.flush();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String sb = "";
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                sb += line;
            }
            bufferedReader.close();
            urlConnection.disconnect();
            Log.i("RESULTADO", sb);
            return sb;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RESULTADO", e.toString());
            return "";
        }
    }

    public String delete(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("DELETE");
            urlConnection.setRequestProperty("User-Agent", "ANDROID");
            DataOutputStream dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
            dataOutputStream.flush();
            urlConnection.disconnect();
            Log.i("RESULTADO", urlString);
            return urlString;
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RESULTADO", e.toString());
            return "";
        }
    }

    public String get(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setDoOutput(true);
            urlConnection.setDoInput(true);
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("User-Agent", "ANDROID");
            urlConnection.connect();
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                bufferedReader.close();
                urlConnection.disconnect();
                Log.i("RESULTADO", "get: " + sb);
                return sb.toString();
            } else {
                urlConnection.disconnect();
                Log.i("RESULTADO", "get: " + responseCode);
                return String.valueOf(responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("RESULTADO", e.toString());
            return "";
        }
    }

}
