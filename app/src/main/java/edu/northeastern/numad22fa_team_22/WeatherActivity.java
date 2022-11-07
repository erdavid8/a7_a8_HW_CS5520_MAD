package edu.northeastern.numad22fa_team_22;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


public class WeatherActivity extends AppCompatActivity {
    String cityNameStr;
    EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityName = findViewById(R.id.city_name);
    }

    public void run(View view) {
        cityNameStr = cityName.getText().toString();
        findViewById(R.id.progress_bar_loader).setVisibility(View.VISIBLE);
        findViewById(R.id.mainContainer).setVisibility(View.GONE);
        findViewById(R.id.error_textView).setVisibility(View.GONE);
        findViewById(R.id.city_name).setVisibility(View.GONE);
        findViewById(R.id.temperature_search_by_city_button).setVisibility(View.GONE);
        findViewById(R.id.progress_bar_loader).setVisibility(View.VISIBLE);
        backgroundWork();
    }


    public void backgroundWork() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String response = HttpRequester.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + cityNameStr + "&appid=ca6a7cb553b2a35cc3b91f9fea19b777");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        TextView cityAndCountryTextView = findViewById(R.id.city_and_country);
                        TextView weatherStatusTextView = findViewById(R.id.weather_status);
                        TextView weatherTemperatureTextView = findViewById(R.id.weather_temperature);
                        TextView minTemperatureTextView = findViewById(R.id.daily_min_temperature);
                        TextView maxTemperatureTextView = findViewById(R.id.daily_max_temperature);
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            JSONObject main = jsonObj.getJSONObject("main");
                            JSONObject sys = jsonObj.getJSONObject("sys");
                            JSONObject cityWeather = jsonObj.getJSONArray("weather").getJSONObject(0);
                            String currentTemperature = main.getString("temp") + "°F";
                            String minDailyTemperature = "Min Daily Temp : " + main.getString("temp_min") + "°F";
                            String maxDailyTemperature = "Max Daily Temp: " + main.getString("temp_max") + "°F";
                            String cityWeatherForecast = cityWeather.getString("description");
                            String cityAndCountry = jsonObj.getString("name") + ", " + sys.getString("country");
                            cityAndCountryTextView.setText(cityAndCountry);
                            weatherStatusTextView.setText(cityWeatherForecast.toUpperCase());
                            weatherTemperatureTextView.setText(currentTemperature);
                            minTemperatureTextView.setText(minDailyTemperature);
                            maxTemperatureTextView.setText(maxDailyTemperature);

                            findViewById(R.id.progress_bar_loader).setVisibility(View.GONE);
                            findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            findViewById(R.id.progress_bar_loader).setVisibility(View.GONE);
                            findViewById(R.id.error_textView).setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        }).start();
    }
}