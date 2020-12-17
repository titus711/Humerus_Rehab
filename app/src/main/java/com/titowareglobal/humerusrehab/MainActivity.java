package com.titowareglobal.humerusrehab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 5000;

    TextView mTitlePage;
    TextView mSubTitlePage;

    ImageView mImageView;
    ImageView mImageView2;

    Animation mAnimpage;
    Animation mSecondAnimpage;
    Animation mThirdAnimPage;
    Animation mFourthAnimPage;
    Animation mLeftToRightAnimPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        mAnimpage = AnimationUtils.loadAnimation(this, R.anim.animimgpage);
        mSecondAnimpage = AnimationUtils.loadAnimation(this, R.anim.secondanimpage);
        mThirdAnimPage = AnimationUtils.loadAnimation(this, R.anim.thirdanimpage);
        mFourthAnimPage = AnimationUtils.loadAnimation(this, R.anim.fourthanimpage);
        mLeftToRightAnimPage = AnimationUtils.loadAnimation(this, R.anim.lefttorightanimpage);

        mTitlePage = (TextView) findViewById(R.id.title_page);
        mSubTitlePage = (TextView) findViewById(R.id.subtitle_page);
        mImageView = (ImageView) findViewById(R.id.image);
        mImageView2 = (ImageView) findViewById(R.id.imageview_two);

        mImageView.startAnimation(mLeftToRightAnimPage);
        mImageView2.startAnimation(mLeftToRightAnimPage);
        mSubTitlePage.startAnimation(mLeftToRightAnimPage);
        mTitlePage.startAnimation(mSecondAnimpage);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //This method is used so that your splash activity
        //can cover the entire screen.

        //this will bind your MainActivity.class file with activity_main.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(MainActivity.this,
                        HomeActivity.class);
                //Intent is used to switch from one activity to another.

                startActivity(i);
                //invoke the SecondActivity.

                finish();
                //the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }


}
