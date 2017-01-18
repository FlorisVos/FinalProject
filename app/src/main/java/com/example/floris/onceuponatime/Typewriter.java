package com.example.floris.onceuponatime;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import android.os.Handler;

/**
 * Created by Floris on 1/10/2017.
 */

public class Typewriter extends TextView {

    private CharSequence mText;
    private int mIndex;
    private long mDelay = 500; //Default 500ms delay


    public Typewriter(Context context) {
        super(context);
    }

    public Typewriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if(mIndex < mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public void animateText(CharSequence text) {
        mText = text;
        mIndex = 0;

        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long millis) {
        mDelay = millis;
    }

    public void appendAnimateText(TextView view, CharSequence text) {
        mText = text;
        mIndex = 0;

        view.append(text);
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }
}

