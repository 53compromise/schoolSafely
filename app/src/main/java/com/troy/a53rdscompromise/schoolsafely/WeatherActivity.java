package com.troy.a53rdscompromise.schoolsafely;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;


public class WeatherActivity extends AppCompatActivity {
    String cityName;
    final String API = "5f46ba5e597f328209a63e7d39453ff9";
    EditText cityText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        cityText = findViewById(R.id.cityText);
        cityName = cityText.getText().toString();
    }
}
