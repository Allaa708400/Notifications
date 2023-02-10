package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.notifications.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
ActivityMainBinding binding;
    private static final String CHANNEL_ID = "x_channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(binding.getRoot());


binding.mainBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        displayNotification();
    }
});


    }

    private void displayNotification() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "Channel display name",
                    NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);


        }

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID);
        builder.setSmallIcon(R.drawable.ic_baseline_notifications_paused_24)
                .setContentTitle("Title")
                .setContentText("Text")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("data"))
                .addAction(R.drawable.ic_baseline_notifications_paused_24, "Replay", pi);

        NotificationManagerCompat nmc = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        nmc.notify(10, builder.build());

    }
}