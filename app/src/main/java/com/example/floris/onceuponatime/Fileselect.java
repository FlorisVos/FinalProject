package com.example.floris.onceuponatime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static android.R.attr.value;
import static android.R.id.list;
import static android.R.layout.simple_list_item_1;

public class Fileselect extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fileselect);


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        final ArrayList<String> listarray = new ArrayList<String>();
        Map<String, ?> allEntries = prefs.getAll();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
            listarray.add(entry.getKey());
        }

        mListView = (ListView) findViewById(R.id.mListview);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, simple_list_item_1, listarray);
        mListView.setAdapter(arrayAdapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String filename = listarray.get(i);
                Log.d("ClickLog",filename);
                Intent displayIntent = new Intent(Fileselect.this,story_activity.class);
                displayIntent.putExtra("filename",filename);
                displayIntent.putExtra("previous_activity","fileselect");
                startActivity(displayIntent);
            }
        });    }
//        public void showCinemas(Map<String, String> allEntries) {
//            MyAdapter adapter = new MyAdapter(allEntries);
//            mListView.setAdapter(adapter);
//        }

        }
