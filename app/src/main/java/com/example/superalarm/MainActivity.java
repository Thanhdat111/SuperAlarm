package com.example.superalarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.superalarm.Database.questiondatabase.DatabaseQuestion;
import com.example.superalarm.UI.Fragment.AlarmFragment;
import com.example.superalarm.UI.Fragment.QuestionFragment;
import com.example.superalarm.UI.Fragment.DiaryFragment;
import com.example.superalarm.UI.Fragment.SettingFragment;
import com.example.superalarm.UI.Login.PreLoginActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragment;
    private DrawerLayout dl;
    private NavigationView nav_view;
    private Toolbar toolbar;
    private DatabaseQuestion db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseQuestion(this);


        //toolbar
        toolbar = (Toolbar)findViewById(R.id.toolbar_mainActivity);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Báo thức");
        toolbar.setTitleTextColor(Color.WHITE);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);

        //nav drawer
        dl = (DrawerLayout)findViewById(R.id.drawerlayout);
        nav_view = (NavigationView)findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                // close drawer when item is tapped
                dl.closeDrawers();

                //open loginactivity
                if(id == R.id.sigin_nav){
                    Intent intent = new Intent(MainActivity.this, PreLoginActivity.class);
                    startActivity(intent);
                }else
                if(id == R.id.logout_nav){

                }
                return false;
            }
        });

        //default fragment
        Fragment fragment = new AlarmFragment();
        loadFragment(fragment);

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
                    Log.d("fragemt","alarm");
                    fragment = new AlarmFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.diary_navBottom:
                    Log.d("fragemt","diary");
                    fragment = new DiaryFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.question_navBottom:
                    Log.d("fragemt","question");
                    fragment = new QuestionFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.setting_navBottom:
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                dl.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
