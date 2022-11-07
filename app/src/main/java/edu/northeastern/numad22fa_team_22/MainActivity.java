package edu.northeastern.numad22fa_team_22;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button) findViewById(R.id.services_button);
        button1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                runAtYourServiceActivity();
            }
        });


        Button button2 = (Button) findViewById(R.id.btn_stickLogin);
        button2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                runUserLoginActivity();
            }
        });

        Button button3 = (Button) findViewById(R.id.about_button);
        button3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                runAboutTeamActivity();
            }
        });
    }


    public void runAtYourServiceActivity(){
        Intent intent = new Intent(this, AtYourServiceActivity.class);
        startActivity(intent);
    }

    public void runUserLoginActivity(){
        Intent intent = new Intent(this, user_login.class);
        startActivity(intent);
    }

    public void runAboutTeamActivity(){
        Intent intent = new Intent(this, AboutTeamActivity.class);
        startActivity(intent);
    }

//    public void showToast(View v){
//        Toast.makeText(this, "Full name: Orkhan Dadashov", Toast.LENGTH_LONG).show();
//        Toast.makeText(this, "Email: dadashov.o@northeastern.edu", Toast.LENGTH_LONG).show();
//    }
}
