package com.example.floris.onceuponatime;

import android.content.SharedPreferences;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.util.ArrayList;
import java.util.Map;

public class story_activity extends AppCompatActivity {

    private Typewriter storyText;
    private Typewriter StoryText1;
    private Button Choice1Btn;
    private Button Choice2Btn;
//    private ScrollView scroll;
//    private Firebase mRef;
    public static final String MY_PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_activity);
        final Story writer = new Story();
        final Choice choiceManager = new Choice();
//        getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        Choice1Btn = (Button) findViewById(R.id.Choice1);
        Choice2Btn = (Button) findViewById(R.id.Choice2);

        String filename = getIntent().getStringExtra("filename");
        String previous_activity = getIntent().getStringExtra("previous_activity");
        Log.d("StatusTAG:",previous_activity);
        if(previous_activity.equals("fileselect")){
            String progress = prefs.getString(filename,"");
            Log.d("ProgRESs TAG:", progress);
            LoadFile loadFile = new LoadFile();
            String loaded_story = loadFile.Load_File(progress);
            Log.d("loadedTAG:", loaded_story);
            storyText = (Typewriter) findViewById(R.id.StoryText);
            storyText.setMovementMethod(new ScrollingMovementMethod());
            storyText.setText("");
            storyText.setCharacterDelay(150);
            storyText.animateText(loaded_story);
            String[] choices = choiceManager.choice(progress.length());
            Choice1Btn.setText(choices[0]);
            Choice2Btn.setText(choices[1]);
        }
        else{
                        String story1 = writer.WriteStory("1");
            storyText = (Typewriter) findViewById(R.id.StoryText);
            storyText.setMovementMethod(new ScrollingMovementMethod());
            storyText.setText("");
            storyText.setCharacterDelay(150);
            storyText.animateText(story1);
            String[] choices = choiceManager.choice(1);
            Log.d("ChoiceLOG",choices[0]);

            Choice1Btn.setText(choices[0]);
            Choice2Btn.setText(choices[1]);
        }
////        scroll.postDelayed(new Runnable() {
////            @Override
////            public void run() {
////                //replace this line to scroll up or down
////                scroll.fullScroll(ScrollView.FOCUS_DOWN);
////            }
////        }, 100L);
//            String story1 = writer.WriteStory(1);
//            storyText = (Typewriter) findViewById(R.id.StoryText);
//            storyText.setMovementMethod(new ScrollingMovementMethod());
//            storyText.setText("");
//            storyText.setCharacterDelay(150);
//            storyText.animateText(story1);
////        final Firebase childRef = mRef.child(filename);
//
//            Choice1Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Choice1Btn.setEnabled(false);
//                    Choice2Btn.setEnabled(false);
//                    String filename = getIntent().getStringExtra("filename");
//                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                    editor.putString(filename, "1");
//                    editor.commit();
//                    Story writer = new Story();
//                    String story2 = writer.WriteStory(2);
//                    storyText = (Typewriter) findViewById(R.id.StoryText);
//                    storyText.append(story2);
////                childRef.setValue("1");
////                StoryText1 = (Typewriter) findViewById(R.id.StoryText1);
////                StoryText1.setText("");
////                StoryText1.setCharacterDelay(150);
////                StoryText1.animateText(story2);
////                StoryText1.append(story2);
//
//                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//                    Map<String, ?> allEntries = prefs.getAll();
//                    for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//                        Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
//                    }
//                }
//            });
//
//            Choice2Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Choice1Btn.setEnabled(false);
//                    Choice2Btn.setEnabled(false);
//                    String filename = getIntent().getStringExtra("filename");
//                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//                    editor.putString(filename, "2");
//                    editor.commit();
//                    Story writer = new Story();
//                    String story3 = writer.WriteStory(3);
//                    StoryText1 = (Typewriter) findViewById(R.id.StoryText1);
//                    StoryText1.setText("");
//                    StoryText1.setCharacterDelay(150);
//                    StoryText1.animateText(story3);
//                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//                    Map<String, ?> allEntries = prefs.getAll();
//                    for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
//                        Log.d("map values", entry.getKey() + ": " + entry.getValue().toString());
//                    }
//                }
//            });

//        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);

        }
}
