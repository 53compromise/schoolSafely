package com.troy.a53rdscompromise.schoolsafely;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProtocolActivity extends AppCompatActivity {
    ArrayList<String> protocolList;
    ArrayAdapter<String> protocolAdapter;
    ListView protocolListView;
    SearchView searchView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protocol);

        protocolListView = findViewById(R.id.protocolList);
        protocolList = new ArrayList<>();
        searchView = (SearchView) findViewById(R.id.protocolSearch);
        protocolList.add("Earthquake drill");

        protocolAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, protocolList);
        protocolListView.setAdapter(protocolAdapter);


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
