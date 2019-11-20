package com.troy.a53rdscompromise.schoolsafely;

import android.os.AsyncTask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.lang.System;


public class WeatherActivity extends AppCompatActivity {
    String cityName = "92630";
    final String API = "5f46ba5e597f328209a63e7d39453ff9";
    EditText cityText;
    TextView temperature;
    Button submit;
    GetURL httpClient = new GetURL();
    String response;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityText = findViewById(R.id.cityText);
        submit = findViewById(R.id.submit);
        temperature = findViewById(R.id.temperature);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityName = cityText.getText().toString();
                System.out.println("button pressed");
                new weatherTask().execute();
            }
        });
        new weatherTask().execute();
    }


    /**
     * class weatherTask
     */
    class weatherTask extends AsyncTask<String, Void, String> {

        protected String doInBackground(String... args) {
            try {
                 response = httpClient.run("https://api.openweathermap.org/data/2.5/weather?q=" + cityName +",us" + "&units=metric&appid=" + API);
            } catch (IOException e) {
                System.out.println("exception caught" + e);
            }
            System.out.println(response);
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                try {
                    System.out.println("postexecute");
                    JSONObject jsonObj = new JSONObject(result);
                    JSONObject main = jsonObj.getJSONObject("main");
                    JSONObject sys = jsonObj.getJSONObject("sys");
                    JSONObject wind = jsonObj.getJSONObject("wind");
                    JSONObject weather = jsonObj.getJSONArray("weather").getJSONObject(0);

                    Long updatedAt = jsonObj.getLong("dt");
                    String updatedAtText = "Updated at: " + new SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH).format(new Date(updatedAt * 1000));
                    String temp = main.getString("temp") + "°C";
                    String tempMin = "Min Temp: " + main.getString("temp_min") + "°C";
                    String tempMax = "Max Temp: " + main.getString("temp_max") + "°C";
                    String pressure = main.getString("pressure");
                    String humidity = main.getString("humidity");

                    Long sunrise = sys.getLong("sunrise");
                    Long sunset = sys.getLong("sunset");
                    String windSpeed = wind.getString("speed");
                    String weatherDescription = weather.getString("description");

                    String address = jsonObj.getString("name") + ", " + sys.getString("country");

                    temperature.setText(temp);


                } catch (JSONException e) {
                    System.out.println("JSON Exception" + e);
                    temperature.setText("Error");

                }
            } else {
                System.out.println("no data recieved");
                return;
            }
        }
    }
}
