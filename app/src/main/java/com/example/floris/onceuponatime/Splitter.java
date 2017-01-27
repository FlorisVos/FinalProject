package com.example.floris.onceuponatime;

/**
 * Created by Floris on 1/25/2017.
 */

public class Splitter {
    // returns progress-code
    public String code(String string){
        // code-string is the first string in the string array
        String[] code = string.split("-");
        return code[0];
    }
    public String story(String string){
        String[] story = string.split("\\|");
        return story[1];
    }
    public String[] choices(String string){
        String[] string1 = string.split("-");
        String[] string2 = string1[1].split("\\|");
        String[] choices = string2[0].split("#");
        return choices;
    }
}
