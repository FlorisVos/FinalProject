package com.example.floris.onceuponatime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AnimatedTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_text);
        final Typewriter tv = (Typewriter) findViewById(R.id.tv);
        tv.setText("");
        tv.setCharacterDelay(150);
        tv.animateText("Once upon a time...");

    }
}
