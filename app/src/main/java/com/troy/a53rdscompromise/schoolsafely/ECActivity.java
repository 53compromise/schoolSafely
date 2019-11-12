package com.troy.a53rdscompromise.schoolsafely;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ECActivity extends AppCompatActivity{
    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
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
                intent.putExtra("contactName.id", (String)listView.getItemAtPosition(i));
                startActivity(intent);

            }


        });


        list = new ArrayList<>();
        list.add("Mom");
        list.add("Dad");
        list.add("Sister");

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextSubmit(String query){
                if(list.contains(query)){
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

    }
}
