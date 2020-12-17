package com.titowareglobal.humerusrehab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.titowareglobal.humerusrehab.Adapter.RecyclerViewAdapter;
import com.titowareglobal.humerusrehab.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    static final float END_SCALE = 0.7f;
    DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    ImageView mMenu_icon;
    LinearLayout contentView;

    List<Exercise> mExerciseList = new ArrayList<>();
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView mRecyclerView;
    RecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        mMenu_icon = findViewById(R.id.menu_icon);
        contentView = findViewById(R.id.lin_content);


        mNavigationView.bringToFront();
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        navigationDrawer();
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(mExerciseList, getBaseContext());
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);


    }

    private void initData() {

        //strengthening and stretching


        mExerciseList.add(new Exercise(R.drawable.pendulum_final, "PENDULUM (CIRCULAR)", "Holding the side of a table with the good arm, bend over at the waist, and let your affected arm hang straight down.  Swing it back and forth like a pendulum, then in circles that start small and gradually grow larger.  Do this for at least 5 minutes, 3 times a day. You can start your exercises. Please press the start button above"));
        mExerciseList.add(new Exercise(R.drawable.third, "SCAP SETS", "Pull your shoulders back pinching the shoulder blades together. Do not let the shoulders come forward. Hold 5 to 10 seconds. Repeat 10 times. Do one session per day "));
        mExerciseList.add(new Exercise(R.drawable.ten, "WRIST STRETCH", " "));
        mExerciseList.add(new Exercise(R.drawable.second, "GRIP STRENGTHENING", "Hold and squeeze a ball in your hand.Repeat 10 –  15 times, 3 times a day. Please press the Start button above"));
        mExerciseList.add(new Exercise(R.drawable.nine, "ABDUCTION SCAPULAR", "With arm resting on the table , palm up, bring head down towards arm and simultaneously move trunk away from the table. Hold for 30 seconds, Repeat 1 to 4 times. Do 1 session per day "));
        mExerciseList.add(new Exercise(R.drawable.seven, "FLEXION", "Sitting upright , slide forearm forward along table bending from waist until stretch is felt. Hold 30 seconds, Repeat 1 to 4 times. Do 1 session per day"));




        mExerciseList.add(new Exercise(R.drawable.eight, "TOWEL STRETCHING", "Behind your back hold one end of the towel with your good arm at shoulder level as shown.  Hold the other end with the injured arm. Raise the towel with your good arm as high as you can without pain. Hold 5 seconds, slowly lower, relax, and repeat 10 –  15 times, 3 times a day.Please go to the settings screen on the menu and set this exercise to level 5 seconds. Come back and  press the start button above for 5 seconds interval sets .You can start your exercises "));
        mExerciseList.add(new Exercise(R.drawable.pulley_exercises, "PULLEY EXERCISES", "Attach a pulley over a door or onto a hook overhead.  Hold one end of the rope with each hand.  Pull down with the good arm, allow the injured arm to rise.  Hold 5 seconds, then slowly allow injured arm to lower.  Repeat 10 –  15 times, 3 times a day. Please press the Start button above"));
        mExerciseList.add(new Exercise(R.drawable.six, "", ""));
        mExerciseList.add(new Exercise(R.drawable.eleven, "", ""));
        mExerciseList.add(new Exercise(R.drawable.twelve, "FORWARD FLEXION", " "));
        mExerciseList.add(new Exercise(R.drawable.thirteen, "", ""));
        mExerciseList.add(new Exercise(R.drawable.fourteen, "", " "));
        mExerciseList.add(new Exercise(R.drawable.fefteen, "", ""));
        mExerciseList.add(new Exercise(R.drawable.sixteen, "PRONE SCAPULAR RETRACTION IN ABDUCTION", ""));



    }

    private void navigationDrawer() {

        mNavigationView.bringToFront();
        mNavigationView.setNavigationItemSelectedListener(this);
        mNavigationView.setCheckedItem(R.id.nav_home);

        mMenu_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                } else {

                    mDrawerLayout.openDrawer(GravityCompat.START);


                }
            }
        });

        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {
        mDrawerLayout.setScrimColor(getResources().getColor(R.color.colorAccent));

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 - diffScaledOffset;
                contentView.setScaleX(offsetScale);
                contentView.setScaleY(offsetScale);


                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();


        if (id == R.id.nav_home) {

            Intent myIntent = new Intent(HomeActivity.this, HomeActivity.class);
            startActivity(myIntent);

        } else if (id == R.id.nav_scheduler) {
            Intent myIntent = new Intent(HomeActivity.this, CalenderActivity.class);
            startActivity(myIntent);
        }  else if (id == R.id.nav_settings) {
            Intent myIntent = new Intent(HomeActivity.this, SettingsActivity.class);
            startActivity(myIntent);

        }   else if (id == R.id.nav_about_app) {
            Intent myIntent = new Intent(HomeActivity.this, AboutAppActivity.class);
            startActivity(myIntent);


        }else if (id == R.id.nav_share) {
            Intent myIntent = new Intent(Intent.ACTION_SEND);
            myIntent.setType("text/plain");
            String shareSub = "Humerus Rehab application link";
            String shareBody = "https://play.google.com/store/apps/details?id=com.titowareglobal.humerusrehab";
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
            myIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
            startActivity(Intent.createChooser(myIntent, "Share Humerus Rehab app using"));


        } else if (id == R.id.nav_exit) {
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);

        }
        return true;

    }
}
