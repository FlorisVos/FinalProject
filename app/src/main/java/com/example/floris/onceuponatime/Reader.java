package com.example.floris.onceuponatime;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Scanner;

import static java.security.AccessController.getContext;

public class Reader extends AppCompatActivity {

    TextView tvReader;
    Button btnReader1;
    Button btnReader2;
    private Toolbar toolbar;
    private WebView webView;
    public String output;
    public static Scanner scanner;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        toolbar = (Toolbar) findViewById(R.id.tool_bar); // Attaching the layout to the toolbar object (code from Stack Overflow)
        setSupportActionBar(toolbar);
        tvReader = (TextView) findViewById(R.id.StoryReader);
        Typeface font = Typeface.createFromAsset(getAssets(), "font/URANIA CZECH.ttf");
        tvReader.setTypeface(font); // custom textfont
        tvReader.setMovementMethod(new ScrollingMovementMethod()); // scrollable text
        tvReader.setText("");
        btnReader1 = (Button) findViewById(R.id.ReaderChoice1);
        btnReader2 = (Button) findViewById(R.id.ReaderChoice2);
        btnReader1.setTypeface(font);
        btnReader2.setTypeface(font);

        final Dialog dialog = new Dialog(this);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("answer","0"); // Value storage for exiting riddle-loops
        editor.commit();



        scanner = null;
        try {
            scanner = new Scanner(getAssets().open("story.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        final line_selector lineSelector = new line_selector(); // Class that seperates story and choice texts
        output = scanner.nextLine();
        String firstStory = lineSelector.getStory(output); // Text for the story
        String[] choices = lineSelector.getChoices(output); //String array of texts for the buttons

        tvReader.setText(firstStory);
        btnReader1.setText(choices[0]);
        btnReader2.setText(choices[1]);

        btnReader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] choices = lineSelector.getChoices(output); // gets string array from ls.class
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String answer = prefs.getString("answer", "");
                if(choices[0].equals("ending ")){
                    Intent intent = new Intent(getApplicationContext(), Welcome_activity.class);
                    startActivity(intent); // end story
                }
                if (choices[0].equals("c ") && answer.equals("1")) { // Conditions for exiting riddle-loop
                    int i = 8;
                    while (i > 0) { // 9 lines have to be skipped to continue the story
                        scanner.nextLine();
                        i--;
                    }
                    output = scanner.nextLine(); // 9th line
                    tvReader.append(lineSelector.getStory(output));
                    choices = lineSelector.getChoices(output);
                    btnReader1.setText(choices[0]);
                    btnReader2.setText(choices[1]);
                    output = scanner.nextLine();
                    SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString("answer", "0"); // change value for exiting loop
                    editor.commit();

                } else {
                    if (choices[0].equals("x ") || choices[0].equals("c ") || choices[0].equals("abc ")) {
                        // these are the cases where scanner stays on the same line
                        tvReader.append(lineSelector.getStory(output));
                    } else {
                        output = scanner.nextLine();
                        choices = lineSelector.getChoices(output);
                        if (choices[0].equals("d ")) { // opens dialog for answering the riddle
                            dialog.setContentView(R.layout.riddle_dialog);
                            Button submitbutton = (Button) dialog.findViewById(R.id.button2);
                            submitbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    EditText crosswordET = (EditText) dialog.findViewById(R.id.editText2);
                                    String answer = crosswordET.getText().toString();
                                    String[] choices2 = lineSelector.getChoices(output);
                                    String correctAnswer = lineSelector.getAnswer(choices2[1]); // gets the answer to current riddle based on identifying value in .txt file
                                    if (answer.equals(correctAnswer)) {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Correct answer, you can continue!";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show(); // show user answer is correct
                                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                        editor.putString("answer", "1"); // change value for exiting loop
                                        editor.commit();

                                    } else {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Wrong answer";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show(); // show user answer is wrong
                                    }
                                }
                            });
                            dialog.show();
                        }
                        // checks for cases where scanner should stay on same line
                        if (choices[0].equals("x ") || choices[0].equals("c ") || choices[0].equals("d ") || choices[0].equals("abc ")) {
                            tvReader.append(lineSelector.getStory(output));
                        } else {
                            if (choices[0].equals("startroom ")) {
                                output = scanner.nextLine();
                            }
                            if(choices[0].equals("ending ")){
                                Intent intent = new Intent(getApplicationContext(), Welcome_activity.class);
                                startActivity(intent); // end story
                            }
                            tvReader.append(lineSelector.getStory(output));
                            choices = lineSelector.getChoices(output);
                            btnReader1.setText(choices[0]);
                            btnReader2.setText(choices[1]);
                        }
                    }
                }
            }
        });

        btnReader2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] choices = lineSelector.getChoices(output);

                if(choices[0].equals("ending ")){
                    Intent intent = new Intent(getApplicationContext(), Welcome_activity.class);
                    startActivity(intent); // end story
                }
                Log.d("k",output);
                if (choices[0].equals("endroom ") || choices[0].equals("abc ")) { // set scanner back to start of loop

                    String LoopNumber = choices[1]; //identifies loop
                    Log.d("x",output);

                    scanner = null;
                    try {
                        scanner = new Scanner(getAssets().open("story.txt")).useDelimiter("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    output = scanner.nextLine();
                    Log.d("z",output);

                    while (!lineSelector.getChoices(output)[1].equals(LoopNumber)) {
                        output = scanner.nextLine();
                        Log.d("ss",output);

                    }
                }

                Log.d("k2",output);


                choices = lineSelector.getChoices(output);

                if (choices[0].equals("x ") || choices[0].equals("abc ") || choices[0].equals("c ") || choices[0].equals("d ")) {
                    output = scanner.nextLine();
                    tvReader.append(lineSelector.getStory(output));
                    choices = lineSelector.getChoices(output);
                    btnReader1.setText(choices[0]);
                    btnReader2.setText(choices[1]);
                } else {

                    output = scanner.nextLine();
                    choices = lineSelector.getChoices(output);

                    // checks for cases where scanner should stay on same line
                    if(choices[0].equals("startroom ") || choices[0].equals("x ") || choices[0].equals("abc ") || choices[0].equals("c ") || choices[0].equals("d ") ){
                            output = scanner.nextLine();
                            choices = lineSelector.getChoices(output);

                    }
                    if(choices[0].equals("ending ")){
                        Intent intent = new Intent(getApplicationContext(), Welcome_activity.class);
                        startActivity(intent); // end story
                    }

                    if (choices[0].equals("endroom ") || choices[0].equals("abc ")) {

                        String LoopNumber = choices[1]; // identifier for loop

                        scanner = null;     // re open scanner to set it back

                        try {
                            scanner = new Scanner(getAssets().open("story.txt")).useDelimiter("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        output = scanner.nextLine();

                        // set scanner back to start of loop
                        while (!lineSelector.getChoices(output)[1].equals(LoopNumber)) {
                            output = scanner.nextLine();
                        }
                        output = scanner.nextLine();
                    }
                        tvReader.append(lineSelector.getStory(output));
                        choices = lineSelector.getChoices(output);
                        btnReader1.setText(choices[0]);
                        btnReader2.setText(choices[1]);

                }
                }
        });

    }

    // Code for toolbar
    // I've got this code from Stack-Overflow

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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            webView = (WebView) findViewById(R.id.webView1);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://www.wikipedia.com");
        }

        return super.onOptionsItemSelected(item);
    }
    }
