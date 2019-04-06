package tcc.myapplocation.jose.tcc.controller;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import tcc.myapplocation.jose.tcc.MensagemActivity;
import tcc.myapplocation.jose.tcc.R;

public class ReceiverExecute extends BroadcastReceiver {

    private final String urlsos = "http://sos.eyglys.com.br/index.php/";

    private Context context;

    private static String latitudesos = "0";
    private static String longitudesos = "0";

    private LocationManager locationManager;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                ReceiverExecute.latitudesos = String.valueOf(location.getLatitude());
                ReceiverExecute.longitudesos = String.valueOf(location.getLongitude());
                Log.i("RESULTADO", latitudesos + "-" + longitudesos);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }
        NotificationTask task = new NotificationTask(context);
        task.execute();
    }

    public void proximos(String s){
        Log.i("PROXIMO", s);
        String quantidade = s;
        int verificar = 0;
        verificar = Integer.parseInt(s.toString().trim().replace("\"", ""));
        if (verificar > 0) {
            Notification.Builder builder = new Notification.Builder(context);
            builder.setSmallIcon(R.drawable.fogo);
            builder.setContentTitle("SOS");
            builder.setContentText("SOS: " + quantidade);
            Intent i = new Intent(context, MensagemActivity.class);
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
            stackBuilder.addParentStack(MensagemActivity.class);
            stackBuilder.addNextIntent(i);
            PendingIntent pendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(0, builder.build());
        }
    }

    public String getProximos() {
        String lat = ReceiverExecute.latitudesos;
        String lon = ReceiverExecute.longitudesos;
        try {
            URL url = new URL(urlsos + "sos-rest/proximo.html?lat="+lat+"&lon="+lon);
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
                Log.i("PROXIMO", urlsos + "sos-rest/proximo.html?lat="+lat+"&lon="+lon + " - get: " + sb);
                return sb.toString();
            } else {
                urlConnection.disconnect();
                Log.i("PROXIMO", urlsos + "sos-rest/proximo.html?lat="+lat+"&lon="+lon + " - get: " + responseCode);
                return "0";
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("PROXIMO", urlsos + "sos-rest/proximo.html?lat="+lat+"&lon="+lon + " - get: " + e.toString());
            return "0";
        }
    }

    class NotificationTask extends AsyncTask<String, String, String>  {

        private Context context;

        public NotificationTask(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            return getProximos();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            proximos(s);
        }
    }

}
