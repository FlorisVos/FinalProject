package com.example.floris.onceuponatime;

import android.content.SharedPreferences;
import android.util.Log;

import static android.content.Context.MODE_PRIVATE;


/**
 * Created by Floris on 1/19/2017.
 */

public class LoadFile {
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public String Load_File(String progress){
        Log.d("LoadClassTag",progress);
        Story writer = new Story();
        String loaded_story = "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        for (int i = 0; i < progress.length(); i++){
            char c = progress.charAt(i);
            loaded_story = loaded_story + writer.WriteStory(Character.toString(c));
            //Process char
        }
        Log.d("LoadClassTag",loaded_story);
        return loaded_story;
    }
}
