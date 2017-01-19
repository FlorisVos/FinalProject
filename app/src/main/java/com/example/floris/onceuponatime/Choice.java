package com.example.floris.onceuponatime;

/**
 * Created by Floris on 1/19/2017.
 */

public class Choice {

    public int Choicecounter = 0;

    public String[] choice(int i){
        if(i == 1){
            String[] choices1 = new String[2];
            choices1[0] = "Choice 1.1";
            choices1[1] = "Choice 1.2";
            return choices1;
        }
        if(i == 2){
            String[] choices2 = new String[2];
            choices2[0] = "Choice 2.1";
            choices2[1] = "Choice 2.2";
            return choices2;        }
        if(i == 3){
            String[] choices3 = new String[2];
            choices3[0] = "Choice 3.1";
            choices3[1] = "Choice 3.2";
            return choices3;        }
        if(i == 4){
            String[] choices4 = new String[2];
            choices4[0] = "Choice 4.1";
            choices4[1] = "Choice 4.2";
            return choices4;        }
        String choices20[] = new String[2];
        return choices20;
    }

}
