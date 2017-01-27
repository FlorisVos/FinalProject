package com.example.floris.onceuponatime;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Floris on 1/11/2017.
 */

public class Story {

    public void StatusReader(Context context) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(context.getAssets().open("story.txt")).useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        while(scanner.hasNext()) {
        String output = scanner.next();
        String[] story;
        story = output.split("#");
//        tvReader.append(story[1]);

    }

    public String WriteStory(Context context, String i) {
        Scanner scanner = null;
        String[] story;
        try {
            scanner = new Scanner(context.getAssets().open("story.txt")).useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String output = scanner.next();
            story = output.split("-");
            Log.d("READERTAG output", output);
            if (story[0].equals(i)) {

                return story[1];
            }
        }
        return "void";
    }
}
