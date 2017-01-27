package com.example.floris.onceuponatime;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Floris on 1/25/2017.
 */

public class Room extends story_activity {

        private String mGlobalVarValue;

        public String getGlobalVarValue() {
            return mGlobalVarValue;
        }

        public void setGlobalVarValue(String str) {
            mGlobalVarValue = str;
        }

    public String getStory(Context context,String string) {
        Scanner scanner = null;
        String[] story;
        try {
            scanner = new Scanner(context.getAssets().open("room.txt")).useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String output = scanner.next();
            story = output.split("-");
            Log.d("READERTAG output", output);
            if (story[0].equals(string)) {
                return story[1];
            }
        }
        return "void";
    }

    public String crossword(String string){
        String signal = "false";
        if(string.equals("Pigeon") || string.equals("pigeon")){
            signal = "true";
        }
        return signal;
    }
}
