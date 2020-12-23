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


        mExerciseList.add(new Exercise(R.drawable.pendulum_final, "PENDULUM (CIRCULAR)", "Holding the side of a table with the good arm, bend over at the waist, and let your affected arm hang straight down.  Swing it back and forth like a pendulum, then in circles that start small and gradually grow larger.  Do this for at least 5 minutes, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.third, "SCAP SETS", "Pull your shoulders back , gently drawing the shoulder blades together as far as possible. Do not let the shoulders come forward. Hold 5 to 10 seconds. Return slowly. Repeat 10 - 15 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.ten, "WRIST STRETCH", "With your elbow bent at 45 degrees, make a fist and start moving your wrist upwards and downwards. Repeat 10 - 15 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page  "));
        mExerciseList.add(new Exercise(R.drawable.second, "GRIP STRENGTHENING", "Hold and squeeze a ball in your hand for 5 seconds. Repeat 10 –  15 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page."));
        mExerciseList.add(new Exercise(R.drawable.nine, "ABDUCTION SCAPULAR", "With arm resting on the table , palm up, bring head down towards arm and simultaneously move trunk away from the table. Hold for 30 seconds, Repeat 10 - 15 times , 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page "));
        mExerciseList.add(new Exercise(R.drawable.seven, "FLEXION", "Sitting upright , slide forearm forward along table bending from waist until stretch is felt. Hold for 30 seconds, Return slowly. Repeat 10 - 15 times , 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.eight, "TOWEL STRETCHING", "Behind your back hold one end of the towel with your good arm at shoulder level as shown.  Hold the other end with the injured arm. Raise the towel with your good arm as high as you can without pain. Hold 5 seconds, slowly lower, relax, and repeat 10 –  15 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.pulley_exercises, "PULLEY EXERCISES", "Attach a pulley over a door  or  onto a hook overhead.  Hold one end of the rope with each hand.  Pull down with the good arm, allow the injured arm to rise.  Hold 5 seconds, then slowly allow injured arm to lower.  Repeat 10 –  15 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.six, "WAND EXERCISES", "While standing, hold a cane or broom handle in both hands with elbows straight over your head .Raise cane over your head, or as high as you can .If pain free, attempt same exercise while sitting upright in a chair. Hold for 5 seconds. Repeat 5 to 10 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.eleven, "T-POSITION", "Lying on your back with your knees bent, arms to the side in a T-position holding handweights. Lift your arms above your face keeping the elbows slightly bent. Lower arms back down. Repeat 5 to 10 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page "));
        mExerciseList.add(new Exercise(R.drawable.twelve, "FORWARD FLEXION", "Using a one metre elastic band tied to a stable object, begin with your arm straight at your side. While keeping good posture, push arm backwards and up to horizontal. Hold for 5 seconds. Lower to starting position slowly. Repeat 10 - 15 times , 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page" ));
        mExerciseList.add(new Exercise(R.drawable.thirteen, "EXTERNAL ROTATION", "Begin with elbow bent to 90 degrees, at your side. While good posture, pull hand outwards. Keep your elbow bent for 5 seconds. Return slowly. Repeat 10 - 15 times, 2 sessions per day.  You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.fourteen, "ABDUCTION", " Using a one metre elastic band tied to a stable object, begin with your arm straight at your side. While keeping good posture, lift arm out to your side and up to horizontal. Hold for 5 seconds. Lower to starting position slowly. Repeat 5 to 10 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));
        mExerciseList.add(new Exercise(R.drawable.fefteen, "BEND-OVER HORIZONTAL ABDUCTION", "Lie on your stomach on a table or bed with your injured arm hanging over the side. Bent your arm elbow and slowly raise it up to eye level.Slowly lower it back to the starting position. Repeat 10 - 15 times , 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page "));
        mExerciseList.add(new Exercise(R.drawable.sixteen, "SCAPULAR RETRACTION ", "Lie on your stomach on a table or bed with both your arms hanging over the sides whilst holding handweights. Place a pillow under your forehead for comfort if required. Keep your elbows straight and lift the weights slowly. Gently draw your shoulder blades together as far as possible. Return slowly to the start position and repeat 10 –  15 times, 2 sessions per day. You can press the optional start button above to time yourself. The timer is adjusted in the settings page"));



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
