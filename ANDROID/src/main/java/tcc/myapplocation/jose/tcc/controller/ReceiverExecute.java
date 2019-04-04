package tcc.myapplocation.jose.tcc.controller;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.TaskStackBuilder;

import tcc.myapplocation.jose.tcc.MensagemActivity;
import tcc.myapplocation.jose.tcc.R;

public class ReceiverExecute extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        // throw new UnsupportedOperationException("Not yet implemented");
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.fogo);
        builder.setContentTitle("SOS");
        builder.setContentText("");
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
