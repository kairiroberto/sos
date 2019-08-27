package tcc.myapplocation.jose.tcc;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tcc.myapplocation.jose.tcc.conection.SosBD;
import tcc.myapplocation.jose.tcc.controller.AsyncDaoController;
import tcc.myapplocation.jose.tcc.model.Sos;
import tcc.myapplocation.jose.tcc.model.Usuario;

public class MainActivity extends AppCompatActivity {

    public static List<Sos> sosMain = new ArrayList<Sos>();
    public static List<Sos> sosUsuarioMain = new ArrayList<Sos>();
    public static List<Usuario> usuariosMain = new ArrayList<Usuario>();

    private final String USUARIO = "usuario";
    private final String INSERIR = "inserir";

    private int id = 0;
    private int progressStatus = 0;
    private String usuario = null;

    private Context context;
    private Activity activity;

    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressMain);
        textView = (TextView) findViewById(R.id.tvMain);
        context = this;
        activity = this;
        MainAsync async = new MainAsync();
        async.execute();
    }

    private void iniciarServico() {
        Intent it = new Intent();
        it.setAction("START_SERVICE_SOS");
        sendBroadcast(it);
    }

    class MainAsync extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            SharedPreferences sharedPreferences = getSharedPreferences("ACESSO", Context.MODE_PRIVATE);
            int idshared = sharedPreferences.getInt("id", 0);

            if (idshared == 0) {
                TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
                WifiInfo wInfo = wifiManager.getConnectionInfo();
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                }
                usuario = tm.getDeviceId();
                if (usuario == null) {
                    usuario = tm.getLine1Number();
                } else if (usuario == null) {
                    usuario = tm.getSimSerialNumber();
                } else if (usuario == null) {
                    usuario = wInfo.getMacAddress();
                } else if (usuario == null) {
                    usuario = wInfo.getBSSID();
                }
                createUsuario(usuario);
            } else {
                usuario = sharedPreferences.getString("celular", null);
            }
            return usuario;
        }

        protected void createUsuario(String usuario) {
            AsyncDaoController asyncDaoController = new AsyncDaoController(context, USUARIO, INSERIR);
            asyncDaoController.execute(usuario);
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            if (aVoid != null) {
                if (!context.getDatabasePath("sos").exists()) {
                    SosBD sos = new SosBD(activity);
                }
                iniciarServico();
                Intent i = new Intent(context, MenuActivity.class);
                startActivity(i);
            } else {
                Intent i = new Intent(context, Main2Activity.class);
                startActivity(i);
            }
        }

    }

}