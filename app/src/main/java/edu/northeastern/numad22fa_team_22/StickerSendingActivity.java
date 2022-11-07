package edu.northeastern.numad22fa_team_22;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
public class StickerSendingActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    TextView receiver, sendTo;
    String pictureID, sentDate;
    double stickerPrice;
    Sticker sticker;
    User user_sender, user_receiver;
    ImageView ice_cream, cola_drink, ball, balloons;
    private static final String TAG = "StickerSendingActivity";
    String username = "NoName", sendToString = "NoName";

    List<Sticker> stickerList;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stricker_sending);

        sendTo = findViewById(R.id.tv_showSendTo);
        receiver = findViewById(R.id.curr_username);
        ice_cream = findViewById(R.id.img_ice_cream);
        cola_drink = findViewById(R.id.img_cola_drink);
        ball = findViewById(R.id.img_ball);
        balloons = findViewById(R.id.img_colorful_balloons);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        // get data from previous activity user_login
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        username = extras.getString("username");
        sendToString = extras.getString("sendTo");

        // show username and sendTo in UI
        sendTo.setText(sendToString);
        receiver.setText(username);

        user_sender = new User(username, 0, 0);
        createNotificationChannel();
        mDatabase.child("users").addChildEventListener(
                new ChildEventListener() {

                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        if(!(Objects.equals(dataSnapshot.getKey(), user_sender.getUsername()))){
                            sendNotification();
                        }
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {
                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext()
                                , "DBError: " + databaseError, Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Button sendMsgButton = findViewById(R.id.btn_sendMessage);
        sendMsgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (user_receiver == null) {
                    // user_receiver = new User(receiver.getText().toString(), 0, 0);
                    user_receiver = new User(sendToString, 0, 0);
                }

                // ensure NO crash when sticker is empty
                if (sticker == null) {
                    sticker = new Sticker("NoName", "NoDate", 0.0, "NoName");
                }

                user_sender.setStickerCount(user_sender.getStickerCount() + 1);
                user_sender.setTotalStickerCost(user_sender.getTotalStickerCost() + sticker.getStickerCost());

                user_sender.addSenderStickerList(sticker);

                user_receiver.addReceiverStickerList(sticker);
                user_receiver.setStickerCount(user_receiver.getReceiveStickerList().size());

                mDatabase.child("users").child(user_sender.username).setValue(user_sender);
                mDatabase.child("users").child(user_receiver.username).setValue(user_receiver);

                sendNotification();
            }
        });

    }

    // launch history activity
    public void runHistoryActivity(View view) {
         Intent intent = new Intent(this, sticker_history.class);
         intent.putExtra("username", user_sender.getUsername());
         startActivity(intent);
    }

    // toast for the images
    public void clickImage(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    // show image for ice cream
    public void showIceCream(View view) {
        pictureID = ice_cream.getContentDescription().toString();
        clickImage(pictureID);
        stickerPrice = 0.5;
        sentDate = java.time.LocalDate.now().toString();
        sticker = new Sticker(user_sender.getUsername(), sentDate, stickerPrice, pictureID);
        Drawable highlight = getResources().getDrawable(R.drawable.highlight);
        ice_cream.setBackground(highlight);
        cola_drink.setBackgroundResource(0);
        ball.setBackgroundResource(0);
        balloons.setBackgroundResource(0);
    }

    // show image for cola drink
    public void showColaDrink(View view) {
        pictureID = cola_drink.getContentDescription().toString();
        clickImage(pictureID);
        stickerPrice = 0.10;
        sentDate = java.time.LocalDate.now().toString();
        sticker = new Sticker(user_sender.getUsername(), sentDate, stickerPrice, pictureID);
        Drawable highlight = getResources().getDrawable(R.drawable.highlight);
        cola_drink.setBackground(highlight);
        ice_cream.setBackgroundResource(0);
        ball.setBackgroundResource(0);
        balloons.setBackgroundResource(0);
    }

    // show image for basket ball
    public void showBall(View view) {
        pictureID = ball.getContentDescription().toString();
        clickImage(pictureID);
        stickerPrice = 0.15;
        sentDate = java.time.LocalDate.now().toString();
        sticker = new Sticker(user_sender.getUsername(), sentDate, stickerPrice, pictureID);
        Drawable highlight = getResources().getDrawable(R.drawable.highlight);
        ball.setBackground(highlight);
        cola_drink.setBackgroundResource(0);
        ice_cream.setBackgroundResource(0);
        balloons.setBackgroundResource(0);
    }

    // show colorful balloons
    public void showColorfulBalloons(View view) {
        pictureID = balloons.getContentDescription().toString();
        clickImage(pictureID);
        stickerPrice = 0.20;
        sentDate = java.time.LocalDate.now().toString();
        sticker = new Sticker(user_sender.getUsername(), sentDate, stickerPrice, pictureID);
        Drawable highlight = getResources().getDrawable(R.drawable.highlight);
        balloons.setBackground(highlight);
        cola_drink.setBackgroundResource(0);
        ball.setBackgroundResource(0);
        ice_cream.setBackgroundResource(0);
    }

    /**
     * Discussed with TA and we will be using this part for the notification.
     * This code was taken from the given lecturer source code:
     *              https://github.khoury.northeastern.edu/danf/firebasesample
     *
     */
    public void createNotificationChannel() {
        // This must be called early because it must be called before a notification is sent.
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channel_id), name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * Discussed with TA and we will be using this part for the notification.
     * This code was taken from the given lecturer source code:
     *              https://github.khoury.northeastern.edu/danf/firebasesample
     *
     */

    public void sendNotification() {

        // Prepare intent which is triggered if the
        // notification is selected
        Intent intent = new Intent(this, StickerSendingActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
        PendingIntent callIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(),
                new Intent(this, StickerSendingActivity.class), 0);


        // Build notification
        // Need to define a channel ID after Android Oreo
        String channelId = getString(R.string.channel_id);
        NotificationCompat.Builder notifyBuild = new NotificationCompat.Builder(this, channelId)
                //"Notification icons must be entirely white."
                .setSmallIcon(R.drawable.images)
                .setContentTitle("New Sticker from:  " + user_sender.getUsername())
                .setContentText("Sticker: " + pictureID)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // hide the notification after its selected
                .setAutoCancel(true)
                .addAction(R.drawable.images, "Call", callIntent)
                .setContentIntent(pIntent);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        // // notificationId is a unique int for each notification that you must define
        notificationManager.notify(0, notifyBuild.build());

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close the Sticker send activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}