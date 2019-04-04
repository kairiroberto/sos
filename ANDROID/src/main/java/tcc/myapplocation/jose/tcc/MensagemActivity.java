package tcc.myapplocation.jose.tcc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MensagemActivity extends AppCompatActivity {

    private TextView tvMensagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem);
        tvMensagem = (TextView) findViewById(R.id.tvMensagem);
        String msg = getIntent().getStringExtra("msg");
        tvMensagem.setText(msg);
    }
}
