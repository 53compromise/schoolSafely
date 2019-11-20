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

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

        Button add = (Button)findViewById(R.id.AddContact);

        add.setOnClickListener(new View.OnClickListener() {

            /**
             * onClick is in a Listener that triggers when a button is pressed
             * onClick overrides its superclass and items provided above to the firebase database.
             * @param view
             */
            @Override
            public void onClick(View view) {

                // get data from userInput
                EditText nameInput = (EditText)findViewById(R.id.editText);
                String newName = nameInput.getText().toString();
                EditText numberInput = (EditText)findViewById(R.id.editPhone);
                String newNumber = numberInput.getText().toString();

                // Create a new user with a name and phone number
                Map<String, Object> user = new HashMap<>();
                user.put("name", newName);
                user.put("number", newNumber);

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
