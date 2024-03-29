package com.troy.a53rdscompromise.schoolsafely;

import android.Manifest;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    String contactName1;
    String phoneNumber1;
    String docId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if (getIntent().hasExtra("contactName.id")) {

            TextView contactName = (TextView) findViewById(R.id.contactName);
            contactName1 = getIntent().getExtras().getString("contactName.id");
            contactName.setText(contactName1);
        }

        if (getIntent().hasExtra("phoneNumber.id")) {

            TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber1);
            phoneNumber1 = getIntent().getExtras().getString("phoneNumber.id");
            phoneNumber.setText(phoneNumber1);
        }

        if (getIntent().hasExtra("docId.id")) {

            docId = getIntent().getExtras().getString("docId.id");
        }

        Button callButton = (Button)findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+phoneNumber1));

                if (ActivityCompat.checkSelfPermission(ContactActivity.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                startActivity(callIntent);
            }
        });

        Button editButton = (Button)findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ContactEditActivity.class);
                startIntent.putExtra("editName", contactName1);
                startIntent.putExtra("editPhoneNumber", phoneNumber1);
                startIntent.putExtra("docId", docId);
                startActivity(startIntent);
            }
        });

    }
}
