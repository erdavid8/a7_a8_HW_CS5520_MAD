package edu.northeastern.numad22fa_team_22;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import android.content.DialogInterface;

public class AtYourServiceActivity extends AppCompatActivity {
    WebView webview;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_at_your_service);

        Button b1 = (Button) findViewById(R.id.weather_button);
        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                runWeatherActivity();
            }
        });

        Button b2 = (Button) findViewById(R.id.randomCatPixButton);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { runRandomCatViewerActivity(); }
        });

        Button b3 = (Button) findViewById(R.id.compatibility_button);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { runCompatibilityActivity(); }
        });
    }


    public void runWeatherActivity(){
        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
    }

    public void runCompatibilityActivity(){
        Intent intentCompatibility = new Intent(this, love_calculator.class);
        startActivity(intentCompatibility);
    }

    public void runRandomCatViewerActivity(){
        Intent catViewer = new Intent(this, random_cat_picture.class);
        startActivity(catViewer);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing Activity")
                .setMessage("Are you sure you want to close the Services activity?")
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