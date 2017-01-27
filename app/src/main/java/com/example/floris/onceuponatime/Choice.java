package com.example.floris.onceuponatime;

/**
 * Created by Floris on 1/19/2017.
 */

public class Choice {

    public int i = 0;
    public void ChoiceCounter(){
        i++;
    }

    public String[] choice(String i){
        if(i.equals("1")){
            String[] choices1 = new String[2];
            choices1[0] = "Go Left";
            choices1[1] = "Go Right";
            return choices1;
        }
        if(i.equals("12")){
            String[] choices2 = new String[2];
            choices2[0] = "Be nice";
            choices2[1] = "Talk business";
            return choices2;        }
        if(i.equals("11")){
            String[] choices3 = new String[2];
            choices3[0] = "Chocolate";
            choices3[1] = "Vanilla";
            return choices3;        }
        if(i.equals("111")){
            String[] choices4 = new String[2];
            choices4[0] = "Call mom";
            choices4[1] = "Call sis";
            return choices4;        }
        if(i.equals("112")){
            String[] choices4 = new String[2];
            choices4[0] = "Action!";
            choices4[1] = "Romance..";
            return choices4;        }
        String choices20[] = new String[2];
        return choices20;
    }

}
