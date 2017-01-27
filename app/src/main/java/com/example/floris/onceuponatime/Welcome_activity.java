package com.example.floris.onceuponatime;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Welcome_activity extends AppCompatActivity {
    private Typewriter typetext;
//    private Firebase mRef;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListener;
    public static final String MY_PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_activity);
        final Dialog dialog = new Dialog(this);
//        Firebase.setAndroidContext(this);
//        mAuth = FirebaseAuth.getInstance();

        Button startBtn = (Button) findViewById(R.id.startbtn);
        Button continueBtn = (Button) findViewById(R.id.continuebtn);

        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(getApplicationContext(), Fileselect.class);
                startActivity(intent);
            }
        });

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.custom_dialog);

//                dialog.setOnKeyListener(new Dialog.OnKeyListener() {
//
//                    @Override
//                    public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
//                        if (i == KeyEvent.KEYCODE_BACK) {
//                            dialog.dismiss();
//                        }
//                        return true;
//                    }
//                });

                typetext = (Typewriter) dialog.findViewById(R.id.tv);
                typetext.setText("");
                typetext.setCharacterDelay(150);
                typetext.animateText("Hi there, what's your name? ");

                final EditText editText = (EditText) dialog.findViewById(R.id.editText);
                final Button btnSave          = (Button) dialog.findViewById(R.id.save);

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String filename = editText.getText().toString(); //maxlines 1
                        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
//                        String restoredText = prefs.getString(filename, null);
                        if (prefs.contains(filename)) {
                            Context context = getApplicationContext();
                            CharSequence text = "Name already exists";
                            int duration = Toast.LENGTH_SHORT;

                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                            }
                        else{
                            SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                            editor.putString(filename, "");
                            editor.commit();
                            Intent startIntent = new Intent(Welcome_activity.this, story_activity.class);
                            startIntent.putExtra("filename", filename);
                            startIntent.putExtra("previous_activity", "welcome");
                            startActivity(startIntent);
                        }

                    }
                });


                dialog.show();
            }
        });


    }
    public void closeActivity(){

        finish();

    }
}


