package com.example.floris.onceuponatime;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Scanner;

public class Reader extends AppCompatActivity {

    TextView tvReader;
    Button btnReader1;
    Button btnReader2;
    public String output;
    public static Scanner scanner;
    line_selector lineSelector;
    Dialog dialog;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader);
        tvReader = (TextView) findViewById(R.id.StoryReader);
        tvReader.setMovementMethod(new ScrollingMovementMethod());
        tvReader.setText("");
        btnReader1 = (Button) findViewById(R.id.ReaderChoice1);
        btnReader2 = (Button) findViewById(R.id.ReaderChoice2);

        final Dialog dialog = new Dialog(this);

        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("answer","0");
        editor.putInt("line",0);
        editor.commit();



        scanner = null;
        try {
            scanner = new Scanner(getAssets().open("story.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        line_selector lineSelector = new line_selector();
        output = scanner.nextLine();
        String firstStory = lineSelector.getStory(output);
        tvReader.setText(firstStory);
        String[] choices = lineSelector.getChoices(output);
        btnReader1.setText(choices[0]);
        btnReader2.setText(choices[1]);
        btnReader1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final line_selector lineSelector = new line_selector();
                Log.d("BTN1.OUTPUTSTART", output);

                String[] choices = lineSelector.getChoices(output);


                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                int lineInt = prefs.getInt("line",1);
                Log.d("lineInt start", String.valueOf(lineInt));

                String answer = prefs.getString("answer", "");
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                if (choices[0].equals("c ") && answer.equals("1")) {
                    Log.d("AnswerIF-success", output);
                    int i = 8;
                    while (i > 0) {
                        scanner.nextLine();
                        i--;
                    }
                    output = scanner.nextLine();
                    tvReader.append(lineSelector.getStory(output));
                    choices = lineSelector.getChoices(output);
                    btnReader1.setText(choices[0]);
                    btnReader2.setText(choices[1]);
                    output = scanner.nextLine();
                    lineInt = prefs.getInt("line",1);
                    lineInt = lineInt + 9;
                    editor.putInt("line",lineInt);
                    editor.putString("answer","0");
                    editor.commit();

                } else {
                    if (choices[0].equals("x ") || choices[0].equals("c ") || choices[0].equals("abc ")) {
                        Log.d("BTN1.X/C/ABC", choices[0]);
                        tvReader.append(lineSelector.getStory(output));
                    } else {
                        Log.d("Trigger:Normal CASE1", output);
                        output = scanner.nextLine();
                        Log.d("Trigger:Normal CASE2", output);
                        lineInt = prefs.getInt("line",1);
                        lineInt++;
                        editor.putInt("line",lineInt);
                        editor.commit();
                        String[] choices1 = lineSelector.getChoices(output);
                        if (choices1[0].equals("d ")) {
                            Log.d("DialogIF-success", output);
                            prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                            answer = prefs.getString("answer", "");
                            Log.d("AnsweRLOG", answer);
                            dialog.setContentView(R.layout.riddle_dialog);
                            Log.d("DIALOGTAG", "dialog");
                            Button submitbutton = (Button) dialog.findViewById(R.id.button2);
                            submitbutton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Room room = new Room();
                                    EditText crosswordET = (EditText) dialog.findViewById(R.id.editText2);
                                    String answer = crosswordET.getText().toString();
                                    SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                                    int lineInt = prefs.getInt("line",0);
                                    String[] choices2 = lineSelector.getChoices(output);
                                    String correctAnswer = lineSelector.getAnswer(choices2[1]);
                                    Log.d("correctAnwer",correctAnswer);
                                    if (answer.equals(correctAnswer)) {
                                        Context context = getApplicationContext();
                                        CharSequence text = "Correct answer, hurry outside!";
                                        int duration = Toast.LENGTH_SHORT;

                                        Toast toast = Toast.makeText(context, text, duration);
                                        toast.show();
                                        SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                                        editor.putString("answer", "1");
                                        editor.commit();

                                    } else {
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
                        if (choices1[0].equals("x ") || choices1[0].equals("c ") || choices1[0].equals("d ") || choices1[0].equals("abc ")) {
                            Log.d("NORMAL.XTRIGGER", choices[0]);
                            tvReader.append(lineSelector.getStory(output));
                        } else {
                            if (choices1[0].equals("startroom ")) {
                                output = scanner.nextLine();
                                lineInt = prefs.getInt("line",1);
                                lineInt++;
                                editor.putInt("line",lineInt);
                                editor.commit();
                                Log.d("Normal Startroom2", output);
                            }
                            tvReader.append(lineSelector.getStory(output));
                            choices1 = lineSelector.getChoices(output);
                            btnReader1.setText(choices1[0]);
                            btnReader2.setText(choices1[1]);
//                            output = scanner.nextLine();
                            lineInt = prefs.getInt("line",1);
                            lineInt++;
                            editor.putInt("line",lineInt);
                            editor.commit();
                            Log.d("Output FINAL", output);
                        }
                    }
//                    tvReader.append(lineSelector.getStory(output));
//                    choices = lineSelector.getChoices(output);
//                    btnReader1.setText(choices[0]);
//                    btnReader2.setText(choices[1]);
//                    output = scanner.nextLine();
//                    Log.d("Output FINAL",output);


                }
            }
        });

        btnReader2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                line_selector lineSelector = new line_selector();
                SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
                String answer = prefs.getString("answer", "");
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                String[] choicestest = lineSelector.getChoices(output);
                Log.d("BTN2.OUTPUTSTART", output);

                if (choicestest[0].equals("endroom ") || choicestest[0].equals("abc ")) {
                    String LoopNumber = choicestest[1];
                    Log.d("LoopNumber",LoopNumber);

                    scanner = null;
                    try {
                        scanner = new Scanner(getAssets().open("story.txt")).useDelimiter("\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    output = scanner.nextLine();
                    while (!lineSelector.getChoices(output)[1].equals(LoopNumber)) {
                        output = scanner.nextLine();
                    }
                    Log.d("BTN2.ENDROOM CASE2", output);

                }

                String[] choices1 = lineSelector.getChoices(output);
                Log.d("trigger correct?",output);
                if (choices1[0].equals("x ") || choices1[0].equals("abc ") || choices1[0].equals("c ") || choices1[0].equals("d ")) {
                    Log.d("Btn2.XTRIGGER2", choices1[0]);
                    output = scanner.nextLine();
                    int lineInt = prefs.getInt("line",1);
                    lineInt++;
                    editor.putInt("line",lineInt);
                    editor.commit();
                    tvReader.append(lineSelector.getStory(output));
                    choices1 = lineSelector.getChoices(output);
                    btnReader1.setText(choices1[0]);
                    btnReader2.setText(choices1[1]);
                    Log.d("Btn2.XTRIGGER2", output);
                } else {
                    Log.d("Trigger:Normal CASE1",output);
                    output = scanner.nextLine();
                    int lineInt = prefs.getInt("line",1);
                    lineInt++;
                    editor.putInt("line",lineInt);
                    editor.commit();
                    Log.d("Trigger:Normal CASE2",output);
                    choices1 = lineSelector.getChoices(output);
                        if(choices1[0].equals("startroom ") || choices1[0].equals("x ") || choices1[0].equals("abc ") || choices1[0].equals("c ") || choices1[0].equals("d ") ){
                            Log.d("Normal Startroom",output);
                            output = scanner.nextLine();
                            lineInt = prefs.getInt("line",1);
                            lineInt++;
                            editor.putInt("line",lineInt);
                            editor.commit();
                            Log.d("Normal Startroom2",output);
                        }
                    Log.d("ENDROOM TRIGGER?",output);
                    if (choices1[0].equals("endroom ") || choices1[0].equals("abc ")) {
                        lineInt = prefs.getInt("line",1);
                        lineInt = lineInt - 4;
                        editor.putInt("line",lineInt);
                        editor.commit();
                        Log.d("BTN2.ENDROOM CASE1", output);
                        String LoopNumber = choices1[1];
                        Log.d("loopnumber",LoopNumber);
                        scanner = null;
                        try {
                            scanner = new Scanner(getAssets().open("story.txt")).useDelimiter("\n");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        output = scanner.nextLine();
                        lineInt = prefs.getInt("line",1);
                        lineInt++;
                        editor.putInt("line",lineInt);
                        editor.commit();
                        Log.d("BTN2.ENDROOM CASE", output);
                        while (!lineSelector.getChoices(output)[1].equals(LoopNumber)) {
                            output = scanner.nextLine();
                        }
                        output = scanner.nextLine();
                        lineInt = prefs.getInt("line",1);
                        lineInt++;
                        editor.putInt("line",lineInt);
                        editor.commit();
                        Log.d("BTN2.ENDROOM CASE2", output);
                    }
                        tvReader.append(lineSelector.getStory(output));
                        choices1 = lineSelector.getChoices(output);
                        btnReader1.setText(choices1[0]);
                        btnReader2.setText(choices1[1]);
//                        output = scanner.nextLine();
                    lineInt = prefs.getInt("line",1);
                    lineInt++;
                    editor.putInt("line",lineInt);
                    editor.commit();
                        Log.d("Output FINAL",output);}
                }
        });

    }
    public void closeActivity(){

        finish();

    }
}
