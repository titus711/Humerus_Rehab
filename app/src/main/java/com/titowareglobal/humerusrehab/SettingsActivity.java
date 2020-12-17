package com.titowareglobal.humerusrehab;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.titowareglobal.humerusrehab.Databases.HumerusrehabDB;

import java.util.Calendar;
import java.util.Date;

public class SettingsActivity extends AppCompatActivity {

    Button mBtnsave;
    RadioButton mRdiEasy, mRdiMedium, mRdiHard;
    RadioGroup mRdiGroup;
    HumerusrehabDB mHumerusrehabDB;
    ToggleButton mSwitchAlarm;
    //TimePicker mTimePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mBtnsave = (Button) findViewById(R.id.btn_save);

        mRdiGroup = (RadioGroup) findViewById(R.id.rdiGroup);
        mRdiEasy = (RadioButton) findViewById(R.id.rdiEasy);
        mRdiMedium = (RadioButton) findViewById(R.id.rdiMedium);
        mRdiHard = (RadioButton) findViewById(R.id.rdiHard);

        mSwitchAlarm = (ToggleButton) findViewById(R.id.switchAlarm);

        //mTimePicker = (TimePicker) findViewById(R.id.timePicker);

        mHumerusrehabDB = new HumerusrehabDB(this);

        int mode = mHumerusrehabDB.getSettingsMode();
        setRadioButton(mode);

        mBtnsave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                saveWorkOutMode();
                saveAlarm(mSwitchAlarm.isChecked());
                Toast.makeText(SettingsActivity.this, "SAVED !!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        CharSequence theName = "SETTINGS";
        SpannableString myS = new SpannableString(theName);
        myS.setSpan(new ForegroundColorSpan(Color.BLACK), 0, theName.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        getSupportActionBar().setTitle(myS);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.argb(128, 3, 155, 229)));


    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        if (checked) {

            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            Intent intent;
            PendingIntent pendingIntent;

            intent = new Intent(SettingsActivity.this, AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            Calendar calendar = Calendar.getInstance();
            Date toDay = Calendar.getInstance().getTime();
           // calendar.set(toDay.getYear(), toDay.getMonth(), toDay.getDay(), mTimePicker.getHour(), mTimePicker.getMinute());

//            assert manager != null;
            manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        } else {
            Intent intent = new Intent(SettingsActivity.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//            assert manager != null;
            manager.cancel(pendingIntent);


        }
    }

    private void saveWorkOutMode() {
        int selectedId = mRdiGroup.getCheckedRadioButtonId();
        if (selectedId == mRdiEasy.getId()) {
            mHumerusrehabDB.saveSettingsMode(0);
        } else if (selectedId == mRdiMedium.getId()) {
            mHumerusrehabDB.saveSettingsMode(1);
        } else if (selectedId == mRdiHard.getId()) {
            mHumerusrehabDB.saveSettingsMode(2);
        }
    }

    private void setRadioButton(int mode) {
        if (mode == 0) {
            mRdiGroup.check(R.id.rdiEasy);
        } else if (mode == 1) {
            mRdiGroup.check(R.id.rdiMedium);
        } else if (mode == 2) {
            mRdiGroup.check(R.id.rdiHard);
        }
    }
}
