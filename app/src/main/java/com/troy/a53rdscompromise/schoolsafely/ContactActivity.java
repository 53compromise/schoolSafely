package com.troy.a53rdscompromise.schoolsafely;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if (getIntent().hasExtra("contactName.id")) {

            TextView contactName = (TextView) findViewById(R.id.contactName);
            String text = getIntent().getExtras().getString("contactName.id");
            contactName.setText(text);
        }

    }
}
