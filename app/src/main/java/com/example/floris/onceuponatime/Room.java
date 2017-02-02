package com.example.floris.onceuponatime;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Floris on 1/25/2017.
 */

public class Room extends story_activity {

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
}
