package com.troy.a53rdscompromise.schoolsafely;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ProtocolView extends AppCompatActivity {

    String protocol1;
    String description1;
    String docId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol_view);

        if (getIntent().hasExtra("protocolName.id")) {

            TextView contactName = (TextView) findViewById(R.id.protocolLabel);
            protocol1 = getIntent().getExtras().getString("protocolName.id");
            contactName.setText(protocol1);
        }

        if (getIntent().hasExtra("protocolDescription.id")) {

            TextView phoneNumber = (TextView) findViewById(R.id.descriptionLabel);
            description1 = getIntent().getExtras().getString("protocolDescription.id");
            phoneNumber.setText(description1);
        }

        if (getIntent().hasExtra("docId.id")) {

            docId = getIntent().getExtras().getString("docId.id");
        }

    }
}
