package com.mahmoud.mostafa.psuhnotification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Services extends FirebaseMessagingService {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        String title, body, chooser;

        if (remoteMessage.getData().size() > 0) {
            title = remoteMessage.getData().get("title") + "world!";
            body = remoteMessage.getData().get("body") + "world!";
            chooser = remoteMessage.getData().get("chooser");
            setupNotification(title, body, chooser);
        }

    }

    private PendingIntent setupPendingIntent(String chooser) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("chooser", chooser);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1, intent,
                PendingIntent.FLAG_ONE_SHOT);
        return pendingIntent;
    }


    private void setupNotification(String title, String body, String chooser) {
        String channel_id = "myChannel";
        int importance = NotificationManager.IMPORTANCE_HIGH;
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channel_id)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_light_normal)
                .setContentTitle(title)
                .setContentText(body)
                .setContentIntent(setupPendingIntent(chooser))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channel_id, "channel_name", importance);
            channel.setDescription("channel_description");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }


}
