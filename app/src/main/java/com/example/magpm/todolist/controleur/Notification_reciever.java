package com.example.magpm.todolist.controleur;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

public class Notification_reciever extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        Intent repeating_intent = new Intent(context,Repeating_activity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        String nomTache = intent.getStringExtra("KEY_NOM_TACHE");
        int requestCode = intent.getIntExtra("KEY_REQUEST_CODE", -1); // pour multiple notif
        PendingIntent pendingIntent = PendingIntent.getActivity(context,requestCode, repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setSmallIcon(android.R.drawable.arrow_down_float)
                .setContentTitle(nomTache)
                .setContentText("")
                .setAutoCancel(true);

        notificationManager.notify(100,builder.build());
    }
}
