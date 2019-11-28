package com.example.superalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.superalarm.UI.Fragment.AlarmFragment;
import com.example.superalarm.UI.Fragment.QuestionFragment;
import com.example.superalarm.UI.Fragment.DiaryFragment;
import com.example.superalarm.UI.Fragment.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private ActionBar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = getSupportActionBar();
//       toolbar.setTitle("Alarm");


        BottomNavigationView navigationView = (BottomNavigationView) findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.alarm_navBottom:
   //                 toolbar.setTitle("Alarm");
                    Log.d("fragemt","alarm");
                    fragment = new AlarmFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.diary_navBottom:
//                    toolbar.setTitle("Diary");
                    Log.d("fragemt","diary");
                    fragment = new DiaryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.question_navBottom:
 //                   toolbar.setTitle("Question");
                    Log.d("fragemt","question");
                    fragment = new QuestionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.setting_navBottom:
//                    toolbar.setTitle("Setting");
                    Log.d("fragemt","setting");
                    fragment = new SettingFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    private void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
