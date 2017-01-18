package com.example.floris.onceuponatime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TypeWriterView_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_writer_view_activity);

        final TypewriterView typewriterView = (TypewriterView) findViewById(R.id.typewriter);
        typewriterView.setText("Hello ");
        typewriterView.pause(3000)
                .type("nice").pause()
                .type(" world").pause()
                .run(new Runnable() {
                    @Override
                    public void run() {
                        // Finalize the text if user fiddled with it during animation.
                        typewriterView.setText("Hello nice world");
                        typewriterView.setEnabled(false);
                    }
                });

    }
}
