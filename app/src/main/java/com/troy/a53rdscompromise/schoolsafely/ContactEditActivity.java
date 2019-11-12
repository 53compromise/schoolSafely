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

import java.util.HashMap;
import java.util.Map;

public class ContactEditActivity extends AppCompatActivity {


    /**
     * Override void method onCreate
     * onCreate initiates when the screen is created, sets out what to do.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_edit);

        if (getIntent().hasExtra("editName")) {

            TextView contactName = (TextView) findViewById(R.id.contactName2);
            String text = getIntent().getExtras().getString("editName");
            contactName.setText(text);
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
                // Create a new user with a first and last name

                EditText nameInput = (EditText)findViewById(R.id.nameInput);
                String newName = nameInput.getText().toString();
                EditText numberInput = (EditText)findViewById(R.id.phoneNumberInput);
                String newNumber = numberInput.getText().toString();
                Map<String, Object> user = new HashMap<>();
                user.put("name", newName);
                user.put("number", newNumber);

                Log.d("Hello", "wow! ");
                // Add a new document with a generated ID
                MainActivity.db.collection("EmergencyContacts").document("NynrGXK9rxiLrAuj4vC0").collection("Names")
                        .add(user)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Log.d("Activity", "DocumentSnapshot added with ID: " + documentReference.getId());
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure( Exception e) {
                                Log.w("Activity", "Error adding document", e);
                            }
                        });
            }
        });


    }
}
