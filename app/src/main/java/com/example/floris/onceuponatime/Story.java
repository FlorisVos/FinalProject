package com.example.floris.onceuponatime;

import android.support.annotation.RequiresPermission;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by Floris on 1/11/2017.
 */

public class Story {

    public String WriteStory(String i) {
        if (i.equals("1")) {
            return "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa";
        }
        if (i.equals("2")) {
            return "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb";
        }
        if (i.equals("3")) {
            return "ccccccccccccccccccccccccccccccccccccccccccccccccccccc";
        }
        if (i.equals("4")){
            return "dddddddddddddddddddddddddddddddd";
        }
        return "";
    }

}
