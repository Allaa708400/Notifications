package com.example.notifications;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import com.example.notifications.databinding.ActivityRvnotificationsBinding;

import java.util.ArrayList;
@SuppressLint({"UnspecifiedImmutableFlag", "ServiceCast"})
public class RVNotifications extends AppCompatActivity {



ActivityRvnotificationsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRvnotificationsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ArrayList<User> users = new ArrayList<>();

        users.add(new User("Ziyad Alaa","Ziyad@gmail.com",2550,R.drawable.ic_user_1));

        users.add(new User("Rodina Alaa","Rodina@gmail.com",2500,R.drawable.ic_user_2));

        UserAdapter adapter = new UserAdapter(users, new UserAdapter.OnUserClickListener() {
            @Override
            public void onUserClicked(User user) {

                showNotification(user);

            }
        });

        binding.usersRv.setAdapter(adapter);
        binding.usersRv.setLayoutManager(new LinearLayoutManager(this));
        binding.usersRv.setHasFixedSize(true);
    }

    private void showNotification(User user) {

        String channelID = "CH101";

        Intent intent = new Intent(getBaseContext(), RVNotifications.class);

        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, channelID);

        builder.setSmallIcon(user.getImage())
                .setOngoing(true)
                .setOnlyAlertOnce(true)
                .setContent(getCustomDesign(user))
                .setContentIntent(pi);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationChannel channel = null;
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(channelID,"Users",
                    NotificationManager.IMPORTANCE_DEFAULT);

            manager.createNotificationChannel(channel);
        }



        manager.notify(1, builder.build());
    }




        private RemoteViews getCustomDesign(User user){

              RemoteViews rv = new RemoteViews(getApplicationContext().getPackageName(),
                      R.layout.custom_user_item);

              rv.setTextViewText(R.id.user_tv_email, user.getEmail());
              rv.setTextViewText(R.id.user_tv_name, user.getName());
              rv.setTextViewText(R.id.user_tv_salary, user.getSalary()+" $");
              rv.setImageViewResource(R.id.user_iv, user.getImage());

              return rv;
        }


    }


