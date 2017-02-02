package com.example.floris.onceuponatime;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Floris on 1/27/2017.
 */

// Returns appropriate output to Reader activity

public class line_selector {

    // seperates the story from the choices
    // returns storytext as string
    public String getStory(String string){
        String[] choicesAndStory = string.split("\\|");
            return choicesAndStory[1];
    }

    // seperates the choices from the story text
    // choices go in a string array
    public String[] getChoices(String string){
        String[] choicesAndStory = string.split("\\|");
        String choices = choicesAndStory[0];
        String[] choicesArray = choices.split("#");
        Log.d("LSChoices",choicesArray[0]);
        if(choicesArray.length > 1) {
            Log.d("LSChoices-NO ERROR", choicesArray[1]);
            return choicesArray;
        }else{
            Log.d("Error","Index error");
        return choicesArray;}
    }

    // Storage of answers
    // gives back answer based on loopnumber
    public String getAnswer(String i){
        if(i.equals(" 1 ")){
            return "pigeon";
        }
        if(i.equals(" 2 ")){return "stone";}
        else{
            return "befunque";
        }
    }

}
