package com.example.floris.onceuponatime;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Floris on 1/27/2017.
 */
//checks for line-type
public class line_selector {

    public String getStory(String string){
        String[] choicesAndStory = string.split("\\|");
            return choicesAndStory[1];
    }
    public String story(String string){
        String[] story = string.split("\\|");
        return story[1];
    }
    public String[] getChoices(String string){
        String[] choicesAndStory = string.split("\\|");
        Log.d("ChoicesAndStory",choicesAndStory[0]);
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
    public String getAnswer(String i){
        if(i.equals(" 1 ")){
            return "pigeon";
        }
        else{return "stone";}
    }

}
