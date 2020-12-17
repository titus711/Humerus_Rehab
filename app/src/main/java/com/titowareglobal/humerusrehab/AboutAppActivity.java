package com.titowareglobal.humerusrehab;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

public class AboutAppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_app);

        CharSequence theName = "INTRODUCTION";
        SpannableString myS = new SpannableString(theName);
        myS.setSpan(new ForegroundColorSpan(Color.BLACK),0,theName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(myS);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(128, 3, 155, 229)));





    }
}
