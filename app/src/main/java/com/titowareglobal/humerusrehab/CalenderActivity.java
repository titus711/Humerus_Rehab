package com.titowareglobal.humerusrehab;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.allyants.notifyme.NotifyMe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;

public class CalenderActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    Calendar mCalendar = Calendar.getInstance();
    TimePickerDialog tpd;
    DatePickerDialog dpd;
    EditText etContent;
    ImageView recordButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender);

        Button btnNotify = (Button)findViewById(R.id.btn_notify);
       // Button btnCance = (Button)findViewById(R.id.btn_cancel);
//        etTitle = (EditText)findViewById(R.id.edit_title);
        etContent = (EditText)findViewById(R.id.edit_content) ;
        recordButton = (ImageView)findViewById(R.id.btn_record);

        dpd = DatePickerDialog.newInstance(
                CalenderActivity.this,
                mCalendar.get(Calendar.YEAR),
                mCalendar.get(Calendar.MONTH),
                mCalendar.get(Calendar.DAY_OF_MONTH)
        );

        tpd = TimePickerDialog.newInstance(
                CalenderActivity.this,
                mCalendar.get(Calendar.HOUR_OF_DAY),
                mCalendar.get(Calendar.MINUTE),
                mCalendar.get(Calendar.SECOND),
                false
        );

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordSpeech();
            }
        });

//        btnCance.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                NotifyMe.cancel(getApplicationContext(),"test");
//            }
//        });

        btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(etContent.length()==0)
                {
                    etContent.requestFocus();
                    etContent.setError("Please schedule your exercise");
                } else {
                    dpd.show(getFragmentManager(),"datePickerDialog");
                }



            }
        });





        CharSequence theName = "SCHEDULE EXERCISE";
        SpannableString myS = new SpannableString(theName);
        myS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, theName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(myS);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(128, 3, 155, 229)));


    }


    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR,year);
        mCalendar.set(Calendar.MONTH,monthOfYear);
        mCalendar.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        tpd.show(getFragmentManager(),"Timepickerdialog");






    }

    @Override
    public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
        mCalendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
        mCalendar.set(Calendar.MINUTE,minute);
        mCalendar.set(Calendar.SECOND,second);

        NotifyMe notifyMe = new NotifyMe.Builder(getApplicationContext())
//                .title(etTitle.getText().toString())
                .content(etContent.getText().toString())
                .color(0,0,0,0)
                .led_color(0,0,0,0)
                .time(mCalendar)
                .addAction(new Intent(),"Snooze",true)
                .key("test")
                .addAction(new Intent(),"Dismiss",true,false)
                .addAction(new Intent(),"Done")
                .large_icon(R.drawable.myhumerusapp)
                .build();

        Toast.makeText(CalenderActivity.this, "SAVED !!!!", Toast.LENGTH_SHORT).show();

        finish();











    }

    private void recordSpeech() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "en-US");
        try {
            startActivityForResult(intent, 1);
        } catch (Exception e) {
            Toast.makeText(this, "Your device does not support Speech recognizer", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK && data != null) {
                ArrayList<String> text = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//                etTitle.setText(text.get(0));
                etContent.setText(text.get(0));

            }
        }
    }

}


