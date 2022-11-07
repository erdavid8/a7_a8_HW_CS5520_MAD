package edu.northeastern.numad22fa_team_22;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import org.json.JSONArray;
import org.json.JSONException;

public class random_cat_picture extends AppCompatActivity {

    ImageView catImage;
    Button btnSearch;
    private static final String TAG = "random_cat_picture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_cat_picture);

        catImage = findViewById(R.id.catImageView);
        btnSearch = findViewById(R.id.button2);
        findViewById(R.id.progressBarRanCat).setVisibility(View.VISIBLE);

        // button listener
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.progressBarRanCat).setVisibility(View.VISIBLE);
                getCatPicture();
            }
        });
    }

    // get cat picture
    private void getCatPicture() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                String response = HttpRequester.excuteGet("https://api.thecatapi.com/v1/images/search");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d(TAG, "run: " + response);
                        String urlResult = null;

                        try {
                            JSONArray obj = new JSONArray(response);
                            urlResult = obj.getJSONObject(0).getString("url");
                            System.out.println(urlResult);

                        } catch (JSONException e) {
                            findViewById(R.id.progressBarRanCat).setVisibility(View.GONE);
                            findViewById(R.id.errorTextViewRanCat).setVisibility(View.VISIBLE);
                            e.printStackTrace();
                        }

                        // use Glide to display image
                        Glide.with(random_cat_picture.this).load(urlResult).into(catImage);
                        findViewById(R.id.progressBarRanCat).setVisibility(View.GONE);
                    }
                });
             }
        }).start();
    }
}