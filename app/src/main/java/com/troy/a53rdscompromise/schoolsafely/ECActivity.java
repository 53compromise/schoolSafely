package com.troy.a53rdscompromise.schoolsafely;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;


public class ECActivity extends AppCompatActivity{
    SearchView searchView;
    ListView listView;
    ArrayList<String> nameList;
    ArrayList<String> numList;
    ArrayList<String> docList;
    ArrayAdapter<String> adapter;
    TextView contactName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ec);


        searchView = (SearchView)findViewById(R.id.searchView);
        contactName = (TextView)findViewById(R.id.contactName);

        listView = (ListView)findViewById(R.id.contactsList);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ECActivity.this,ContactActivity.class);
                intent.putExtra("contactName.id", nameList.get(i));
                intent.putExtra("phoneNumber.id", numList.get(i));
                intent.putExtra("docId.id", docList.get(i));
                startActivity(intent);

            }


        });

        nameList = new ArrayList<>();
        numList = new ArrayList<>();
        docList = new ArrayList<>();

        MainActivity.db.collection("EmergencyContacts")
        .document("NynrGXK9rxiLrAuj4vC0").collection("Names").get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> map = document.getData();
                        nameList.add((String)map.get("name"));
                        numList.add((String)map.get("number"));
                        docList.add((String)document.getId());

                        Log.d("Yay", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w("Nay", "Error getting documents.", task.getException());
                }
            }
        });

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,nameList);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                if(nameList.contains(query)){
                    adapter.getFilter().filter(query);
                }else{
                    Toast.makeText(ECActivity.this,"No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText){
                // adapter.getFilter().filter(newText);
                return false;
            }
        });

        FloatingActionButton addContacts = (FloatingActionButton)findViewById(R.id.addContacts);

        addContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),AddContact.class);
                startActivity(startIntent);
            }
        });;

    }
}
