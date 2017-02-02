package com.example.floris.onceuponatime;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Created by Floris on 1/11/2017.
 */

public class Story {



public void OpenScanner(Context context) throws FileNotFoundException {
    FileInputStream fis = context.openFileInput("story.txt");
    final InputStreamReader isr = new InputStreamReader(fis);
}

    public String WriteStory(Context context) {
                Scanner scanner = null;
        String[] story;
        try {
            scanner = new Scanner(context.getAssets().open("story.txt")).useDelimiter("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            String output = scanner.next();
            return output;
//            story = output.split("-");
//            Log.d("READERTAG output", output);
//            if (story[0].equals(i)) {
//
//                return story[1];
//            }
        }
        return "void";
    }
    }

//        Scanner scanner;
//        String[] story;
//        try {
//            scanner = new Scanner(context.getAssets().open("story.txt")).useDelimiter("\n");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        while (scanner.hasNext()) {
//            String output = scanner.next();
//            return output;
////            story = output.split("-");
////            Log.d("READERTAG output", output);
////            if (story[0].equals(i)) {
////
////                return story[1];
////            }
//        }
//        return "void";
//    }

