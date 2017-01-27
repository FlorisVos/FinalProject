package com.example.floris.onceuponatime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.Scanner;

public class Reader extends AppCompatActivity {

    TextView tvReader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        tvReader = (TextView) findViewById(R.id.tvreader);
        tvReader.setText("");

        Scanner scanner = null;
        try {
            scanner = new Scanner(getAssets().open("story.txt")).useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        while(scanner.hasNext()) {
            String output = scanner.next();
            String[] story;
            story = output.split("#");
            tvReader.append(story[1]);
//        }
    }
}
