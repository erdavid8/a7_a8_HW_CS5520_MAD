package edu.northeastern.numad22fa_team_22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class user_login extends AppCompatActivity {

    EditText username;
    EditText sendTo;
    private static final String TAG = "user_login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        Button user_login = (Button) findViewById(R.id.btn_login);
        username = findViewById(R.id.input_Username);
        sendTo = findViewById(R.id.input_sendingTo);

        user_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){ runStickerSendingActivity(); }
        });
    }

    // method for click of login
    public void runStickerSendingActivity(){
        String myUsername;
        String mySendTo;
        Intent intent = new Intent(this, StickerSendingActivity.class);
        Bundle extras = new Bundle();

        // checks if username is empty then assign "NoName"
        if (username.getText().toString().equals("")) {
            myUsername = "NoName";
        } else {
            myUsername = username.getText().toString();
        }

        // checks if sendTo is empty then assign "NoName"
        if (sendTo.getText().toString().equals("")) {
            mySendTo = "NoName";
        } else {
            mySendTo = sendTo.getText().toString();
        }

        extras.putString("username", myUsername);
        extras.putString("sendTo", mySendTo);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close the User Log In activity?")
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