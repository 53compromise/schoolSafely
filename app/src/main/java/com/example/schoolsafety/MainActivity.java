package com.example.schoolsafety;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button ECbutton = (Button) findViewById(R.id.ECbutton);
        Button ProtocolsButton = (Button) findViewById(R.id.PROTOCOLbutton);
        Button WeatherButton = (Button) findViewById(R.id.WEATHERbutton);
        Button ShareButton = (Button) findViewById(R.id.SHAREbutton);
        Button SettingsButton = (Button) findViewById(R.id.SETTINGbutton);

        ECbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEmergencyContacts();
            }
        });
        WeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWeather();
            }
        });
        ProtocolsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProtocols();
            }
        });
        ShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openShare();
            }
        });
        SettingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSettings();
            }
        });



    }
    public void openEmergencyContacts()
    {
        Intent intent = new Intent(this, EmergencyContacts.class);
        startActivity(intent);
    }
    public void openWeather()
    {
        Intent intent = new Intent(this, Weather.class);
        startActivity(intent);
    }
    public void openProtocols()
    {
        Intent intent = new Intent(this, Protocols.class);
        startActivity(intent);
    }
    public void openShare()
    {
        Intent intent = new Intent(this, Share.class);
        startActivity(intent);
    }
    public void openSettings()
    {
        Intent intent = new Intent(this, Settings.class);
        startActivity(intent);
    }

}
