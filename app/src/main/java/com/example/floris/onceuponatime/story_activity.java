package com.example.floris.onceuponatime;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.DisplayContext;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.support.v7.app.ActionBar;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import static android.content.Context.MODE_PRIVATE;

public class story_activity extends AppCompatActivity {

    public static Scanner scanner;
    private Typewriter storyText;
    private Typewriter StoryText1;
    private Button Choice1Btn;
    private Button Choice2Btn;
    private Toolbar toolbar;
    private WebView webView;
    private Dialog dialog;
//    private ScrollView scroll;
//    private Firebase mRef;
    public static final String MY_PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_activity);

        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object
        setSupportActionBar(toolbar);

        // initialize Story class and Choice class
        final Story writer = new Story();
        final Choice choiceManager = new Choice();
        final LoadFile loadFile = new LoadFile();
        final Dialog dialog = new Dialog(this);
        final String pass = "";
        final Scanner scanner;
        try {
            scanner = new Scanner(getAssets().open("story.txt")).useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }





        // initialize preferences for saving/loading story-progress
        final SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        Choice1Btn = (Button) findViewById(R.id.Choice1);
        Choice2Btn = (Button) findViewById(R.id.Choice2);

        final String filename = getIntent().getStringExtra("filename");
        String previous_activity = getIntent().getStringExtra("previous_activity");
        Log.d("StatusTAG:",previous_activity);
        if(previous_activity.equals("fileselect")){
            String progress = prefs.getString(filename,"");
            final String loaded_story = loadFile.Load_File(getApplicationContext(),progress);
            Log.d("loadedTAG:", loaded_story);
            storyText = (Typewriter) findViewById(R.id.StoryText);
            storyText.setMovementMethod(new ScrollingMovementMethod());
            storyText.setText("");
            storyText.setCharacterDelay(1);
            storyText.animateText(loaded_story);
            String ChoicesAndStory = writer.WriteStory(getApplicationContext());
            String[] ChoicesNoStory = ChoicesAndStory.split("\\|");
            String[] choices = ChoicesNoStory[0].split("#");
            Choice1Btn.setText(choices[0]);
            Choice2Btn.setText(choices[1]);
        }
        else{
            String progress = prefs.getString(filename,"");
            progress = progress + "1";
            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
            editor.putString(filename, progress);
            editor.commit();
            Log.d("ProgRESs TAG:", progress);
//            String story1 = scanner.next();
            String story1 = writer.WriteStory(getApplicationContext());
            String[] splitstory = story1.split("\\|");

            storyText = (Typewriter) findViewById(R.id.StoryText);
            storyText.setMovementMethod(new ScrollingMovementMethod());
            storyText.setText("");
            storyText.setCharacterDelay(150);
            storyText.animateText(splitstory[1]);

            Log.d("SplitLog", splitstory[0]);
            String[] choices = splitstory[0].split("#");
            Log.d("SplitLog", choices[0]);
            Log.d("SplitLog", choices[1]);
            Log.d("ChoiceLOG",choices[0]);

            Choice1Btn.setText(choices[0]);
            Choice2Btn.setText(choices[1]);        }

        Choice1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Dysonisius points";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                String progress = prefs.getString(filename,"");
                if(progress.length() > 4 && progress.length() < 10){
                    Room room = new Room();
                    String roomstory = room.getStory(getApplicationContext(),progress + "2");
                    String progressplus2 = progress + "2";
                    if(progressplus2.equals("11111112")){
                        dialog.setContentView(R.layout.riddle_dialog);
                        Log.d("DIALOGTAG","dialog");
                        Button submitbutton = (Button) dialog.findViewById(R.id.button2);
                        submitbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Room room = new Room();
                                EditText crosswordET = (EditText) dialog.findViewById(R.id.editText2);
                                String answer = crosswordET.getText().toString();
                                if(answer.equals("pigeon") || answer.equals("Pigeon")){
                                    Context context = getApplicationContext();
                                    CharSequence text = "Correct answer!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                    editor.putString("crossword", "true");
                                    editor.commit();
                                }
                                else{
                                    Context context = getApplicationContext();
                                    CharSequence text = "Wrong answer";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });
                        dialog.show();
                    }
                    Log.d("ROOMSTORY",roomstory);
                    String crossword = prefs.getString("crossword","");
                    Log.d("crossword",crossword);

                    if(progressplus2.equals("111112") && crossword.equals("true")){
                        Log.d("OUTSIDEOKAY","HERE");
                        storyText.append("You go outside, *brrr* it's cold and has been raining. You go to your bike and unlock it.");
                        Choice1Btn.setText("Race to Science Park");
                        Choice2Btn.setText("Relax, take a scenic route");
                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                        editor.putString("crossword", "false");
                        editor.commit();
                    }
                    else if(roomstory.equals("Do crossword") || roomstory.equals("Do crossword ")){
                        dialog.setContentView(R.layout.riddle_dialog);
                        Log.d("DIALOGTAG","dialog");
                        Button submitbutton = (Button) dialog.findViewById(R.id.button2);
                        submitbutton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Room room = new Room();
                                EditText crosswordET = (EditText) dialog.findViewById(R.id.editText2);
                                String answer = crosswordET.getText().toString();
                                if(answer.equals("pigeon") || answer.equals("Pigeon")){
                                    Context context = getApplicationContext();
                                    CharSequence text = "Correct answer!";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                                else{
                                    Context context = getApplicationContext();
                                    CharSequence text = "Wrong answer";
                                    int duration = Toast.LENGTH_SHORT;

                                    Toast toast = Toast.makeText(context, text, duration);
                                    toast.show();
                                }
                            }
                        });

                    }
                    else{
                    storyText.append(roomstory);}

                }else{
                loadFile.Load_File(getApplicationContext(), progress);
                progress = progress + "1";
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(filename, progress);
                editor.commit();
                Log.d("ProgRESs TAG:", progress);
                String story1 = writer.WriteStory(getApplicationContext());
                String[] splitstory = story1.split("\\|");
                String[] choices = splitstory[0].split("#");
                storyText.append(splitstory[1]);
                Choice1Btn.setText(choices[0]);
                Choice2Btn.setText(choices[1]);
            }}
        });
        Choice2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Apollo points";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                String progress = prefs.getString(filename,"");
                if(progress.length() > 4){
                    Room room = new Room();
                    progress = progress + "1";
                    if(progress.equals("111111111")){
                        progress = "11111";
                    }
                    String roomstory = room.getStory(getApplicationContext() ,progress);
                    String[] refinedstory = roomstory.split("\\|");
                    storyText.append(refinedstory[1]);
                    String[] roomchoices = refinedstory[0].split("#");
                    Choice1Btn.setText(roomchoices[0]);
                    Choice2Btn.setText(roomchoices[1]);
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(filename, progress);
                    editor.commit();
                }
                else{
                Log.d("ProgRESs TAG:", progress);
                progress = progress + "1";
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString(filename, progress);
                editor.commit();
                Log.d("ProgRESs TAG:", progress);
                String story1 = writer.WriteStory(getApplicationContext());
                String[] splitstory = story1.split("\\|");
                String[] choices = splitstory[0].split("#");
                storyText.append(splitstory[1]);
                Choice1Btn.setText(choices[0]);
                Choice2Btn.setText(choices[1]);}

            }
        });




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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        final Dialog dialog = new Dialog(this);

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            webView = (WebView) findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://www.wikipedia.com");
        }

        return super.onOptionsItemSelected(item);
    }
}

