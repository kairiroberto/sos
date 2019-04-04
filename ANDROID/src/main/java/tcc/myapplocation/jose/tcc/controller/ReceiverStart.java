package tcc.myapplocation.jose.tcc.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ReceiverStart extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        Intent i = new Intent(context, ServiceAlarm.class);
        context.startService(i);
    }
}
