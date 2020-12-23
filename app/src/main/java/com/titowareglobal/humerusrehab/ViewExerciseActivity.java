package com.titowareglobal.humerusrehab;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.speech.tts.TextToSpeech;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.titowareglobal.humerusrehab.Databases.HumerusrehabDB;
import com.titowareglobal.humerusrehab.Utils.Common;

import java.util.Locale;

public class ViewExerciseActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    int image_id;
    String name;
    String myDescription;

    Button btnStart;
    Button btnVoiceInstructions;
    Button btnTextInstructions;
    Boolean isRunning = false;


    private TextToSpeech tts;

    TextView timer;
    TextView title;
    ImageView detail_image;

    HumerusrehabDB mHumerusrehabDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_exercise);





        mHumerusrehabDB = new HumerusrehabDB(this);

        timer = (TextView) findViewById(R.id.timer);
       // title = (TextView) findViewById(R.id.title);
        detail_image = (ImageView) findViewById(R.id.detail_image);

        tts = new TextToSpeech(this, this);

        btnStart = (Button)findViewById(R.id.btn_start);
        btnTextInstructions = (Button) findViewById(R.id.btn_ext_instructions);
        btnVoiceInstructions = (Button) findViewById(R.id.btn_voice_instructions);




        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isRunning){
                    btnStart.setText("DONE");

                    int timeLimit = 0;
                    if (mHumerusrehabDB.getSettingsMode() == 0){
                        timeLimit = Common.TIME_LIMIT_EASY;
                    } else if (mHumerusrehabDB.getSettingsMode() == 1){
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                    } else if (mHumerusrehabDB.getSettingsMode() == 2){
                        timeLimit = Common.TIME_LIMIT_HARD;
                    }
                    new CountDownTimer(timeLimit,1000){

                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText("" + millisUntilFinished/1000);

                        }

                        @Override
                        public void onFinish() {
                            Toast.makeText(ViewExerciseActivity.this, "Finished!!!", Toast.LENGTH_SHORT).show();
                            finish();

                        }
                    }.start();

                } else{
                    Toast.makeText(ViewExerciseActivity.this, "Finished!!!", Toast.LENGTH_SHORT).show();
                    finish();


                }

                isRunning = !isRunning;
            }
        });

        timer.setText("");

        if (getIntent() != null){
            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");
            myDescription = getIntent().getStringExtra("my_Description");




            CharSequence theName = name;
            SpannableString myS = new SpannableString(theName);
            myS.setSpan(new ForegroundColorSpan(Color.BLACK),0,theName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            getSupportActionBar().setTitle(myS);
            getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(128, 3, 155, 229)));









            detail_image.setImageResource(image_id);
            //title.setText(name);
            // trial
            btnVoiceInstructions.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Toast.makeText(ViewExerciseActivity.this, "Voice instructions starting....", Toast.LENGTH_LONG).show();

                    speakOut();

                }
            });

            btnTextInstructions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myTextInstructions();

                }
            });



        }

    }

    private void myTextInstructions() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(name);
        builder.setMessage(myDescription);
        builder.setNegativeButton("EXIT", null);
        builder.show();
    }


    private void speakOut() {
        tts.speak(myDescription, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }


    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                btnVoiceInstructions.setEnabled(true);
                //speakOut();
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }
}
