package com.troy.a53rdscompromise.schoolsafely;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.WriteBatch;

import java.util.HashMap;
import java.util.Map;

public class ContactEditActivity extends AppCompatActivity {

    private String docId;
    private String name;
    private String phone;

    /**
     * Override void method onCreate
     * onCreate initiates when the screen is created, sets out what to do.
     * @param savedInstanceState - passed onto super class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        if (getIntent().hasExtra("editName")) {

            TextView contactName = (TextView) findViewById(R.id.contactName2);
            String temp = getIntent().getExtras().getString("editName");
            contactName.setText(temp);
        }

        if (getIntent().hasExtra("editPhoneNumber")) {

            TextView phoneNumber = (TextView) findViewById(R.id.phoneNumber);
            String temp = getIntent().getExtras().getString("editPhoneNumber");
            phoneNumber.setText(temp);
        }

        if (getIntent().hasExtra("docId")) {

            docId = getIntent().getExtras().getString("docId");
        }

        Button change = (Button)findViewById(R.id.changeButton);

        change.setOnClickListener(new View.OnClickListener() {

            /**
             * onClick is in a Listener that triggers when a button is pressed
             * onClick overrides its superclass and items provided above to the firebase database.
             * @param view
             */
            @Override
            public void onClick(View view) {

                // get data from userInput
                EditText nameInput = (EditText)findViewById(R.id.nameInput);
                String newName = nameInput.getText().toString();
                EditText numberInput = (EditText)findViewById(R.id.phoneNumberInput);
                String newNumber = numberInput.getText().toString();

                // Create a new user with a name and phone number
                Map<String, Object> user = new HashMap<>();
                user.put("name", newName);
                user.put("number", newNumber);

                Log.d("Hello", "name: " + newName + ", newNumber: " + newNumber +" " + docId);
                // Add a new document with a generated ID
                WriteBatch batch = MainActivity.db.batch();
                DocumentReference ref = MainActivity.db.collection("EmergencyContacts").document("NynrGXK9rxiLrAuj4vC0").collection("Names").document(docId);
                batch.update(ref, "name", newName);
                batch.update(ref, "number", newNumber);

                batch.commit();
            }
        });


    }
}
