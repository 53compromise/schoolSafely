package com.troy.a53rdscompromise.schoolsafely;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class ProtocolActivity extends AppCompatActivity {
    ArrayList<String> protocolList;
    ArrayList<String> protocolDescriptionList;
    ArrayList<String> docList;
    ArrayAdapter<String> protocolAdapter;
    ListView protocolListView;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);

        protocolListView = findViewById(R.id.protocolList);
        protocolList = new ArrayList<>();
        docList = new ArrayList<>();
        protocolDescriptionList = new ArrayList<>();
        searchView = (SearchView) findViewById(R.id.protocolSearch);

        MainActivity.db.collection("Protocols").get()
        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Map<String, Object> map = document.getData();
                        protocolList.add((String)map.get("protocolName"));
                        protocolDescriptionList.add((String)map.get("description"));
                        docList.add((String)document.getId());

                        Log.d("Yay", document.getId() + " => " + document.getData());
                    }
                } else {
                    Log.w("Nay", "Error getting documents.", task.getException());
                }
            }
        });

        protocolListView = (ListView)findViewById(R.id.protocolList);
        protocolListView.setClickable(true);
        protocolListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(ProtocolActivity.this, ProtocolView.class);
                intent.putExtra("protocolName.id", protocolList.get(i));
                intent.putExtra("protocolDescription.id", protocolDescriptionList.get(i));
                intent.putExtra("docId.id", docList.get(i));
                startActivity(intent);

            }


        });




        protocolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, protocolList);
        protocolListView.setAdapter(protocolAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            /**
             * function onQueryTextSubmit overrides the superclass' function onQueryTextSubmit. It takes a
             * String query and checks if the query is inside the protocolList.
             * If the query is in protocolList, it returns true. Else, it returns false.
             * @param query
             * @return boolean
             */
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (protocolList.contains(query)) {
                    protocolAdapter.getFilter().filter(query);
                } else {
                    Toast.makeText(ProtocolActivity.this, "No Match found", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // adapter.getFilter().filter(newText);
                return false;
            }
        });
    }
}
