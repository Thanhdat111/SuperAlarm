package com.example.superalarm.UI.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.superalarm.Database.alarmdatabase.AlarmDBHelper;
import com.example.superalarm.Database.alarmdatabase.AlarmModel;
import com.example.superalarm.R;

public class DiaryFragment extends Fragment {



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_diary, container, false);



        return view;
    }
}
