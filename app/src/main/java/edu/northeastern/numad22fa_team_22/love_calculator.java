package edu.northeastern.numad22fa_team_22;
// reference: https://www.youtube.com/watch?v=XAcJcVN9nm4

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class love_calculator extends AppCompatActivity {

    EditText yourName, partnerName;
    Button btnCompatibility;
    TextView resultCompatibility;
    private static final String TAG = "love_calculator";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love_calculator);

        yourName = findViewById(R.id.input_yourName);
        partnerName = findViewById(R.id.input_partnerName);
        btnCompatibility = findViewById(R.id.button);
        resultCompatibility = findViewById(R.id.compatibilityResult);

        btnCompatibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String yName = yourName.getText().toString();
                String pName = partnerName.getText().toString();
                findViewById(R.id.progressBarComp).setVisibility(View.VISIBLE);
                
                if (yName.trim().length() != 0 && pName.trim().length() !=0) {
                    Log.d(TAG, "onClick: " + yName + ", " + pName);
                }
                getCompatibility(yName, pName);
            }
        });

    }

    // get name compatibility
    private void getCompatibility(String yName, String pName) {

        final OkHttpClient getResult = new OkHttpClient();

        String compatibilityUrl = "https://love-calculator.p.rapidapi.com/getPercentage?sname="+yName+"&fname="+pName;
        Request req = new Request.Builder()
                .url(compatibilityUrl)
                .get()
                .addHeader("X-RapidAPI-Host", "love-calculator.p.rapidapi.com")
                .addHeader("X-RapidAPI-Key", "5aaf36ca2dmshb3f108cce9c0f91p1cd7b4jsn862fe8852a78")
                .build();

        getResult.newCall(req).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Toast.makeText(love_calculator.this, "Error getting data from website", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String res = response.body().string();
                    Log.d(TAG, "onClick successful: " + yName + ", " + pName);
                    love_calculator.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                JSONObject obj = new JSONObject(res);
                                String result = obj.getString("percentage");
                                resultCompatibility.setText(result);
                                Log.d(TAG, "onClick result: " + result);
                                findViewById(R.id.progressBarComp).setVisibility(View.GONE);

                            } catch (JSONException e) {
                                findViewById(R.id.progressBarComp).setVisibility(View.GONE);
                                findViewById(R.id.errorTextViewComp).setVisibility(View.VISIBLE);
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

}