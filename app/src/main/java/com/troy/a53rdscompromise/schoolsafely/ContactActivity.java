package com.troy.a53rdscompromise.schoolsafely;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        TextView contactName = (TextView)findViewById(R.id.contactName);
        contactName.setText();
    }
}
