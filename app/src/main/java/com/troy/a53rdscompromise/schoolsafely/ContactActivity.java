package com.troy.a53rdscompromise.schoolsafely;

<<<<<<< HEAD
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
=======
import androidx.appcompat.app.AppCompatActivity;
>>>>>>> 5719b144509b3e698b9775f2c922f551e18b1916
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContactActivity extends AppCompatActivity {

    String contactName1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        if (getIntent().hasExtra("contactName.id")) {

            TextView contactName = (TextView) findViewById(R.id.contactName);
            contactName1 = getIntent().getExtras().getString("contactName.id");
            contactName.setText(contactName1);
        }

        Button editButton = (Button)findViewById(R.id.editButton);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), ContactEditActivity.class);
                startIntent.putExtra("editName", contactName1);
                startActivity(startIntent);
            }
        });

    }
}
