package com.example.superalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.superalarm.UI.Fragment.AlarmFragment;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragment.beginTransaction();
        fragmentTransaction.add(R.id.fragment_main,new AlarmFragment());
        fragmentTransaction.commit();
    }
}
