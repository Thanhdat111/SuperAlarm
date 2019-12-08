package com.example.superalarm.UI.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.superalarm.Adapter.ListAlarmAdapter;
import com.example.superalarm.Database.alarmdatabase.AlarmContract;
import com.example.superalarm.Database.alarmdatabase.AlarmDBHelper;
import com.example.superalarm.Database.alarmdatabase.AlarmModel;
import com.example.superalarm.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class ListAlarmFragment extends Fragment {
    private AlarmDBHelper alarmDBHelper;
    private AlarmModel alarmModel;
    private FloatingActionButton btn_newAlarm ;
    private ListAlarmAdapter listAlarmRecyclerView;
    private RecyclerView recyclerView;
    private  int REQ_CODE_SECOND_FRAGMENT =1234;
    List<AlarmModel> listAlarmModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_alarm, container, false);

      //  AlarmModel alarmModel = (AlarmModel) getArguments().getSerializable("alarm");

      //  Log.d("du lieu rmFragment",alarmModel.getName());

        btn_newAlarm = view.findViewById(R.id.btn_fta_add_alarm);
        recyclerView = view.findViewById(R.id.listview_list_alarm);


        btn_newAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new AlarmFragment();
               loadFragment(fragment);
            }
        });
        alarmDBHelper = new AlarmDBHelper(this.getContext());
        boolean[] arrayRepeated  = new boolean[7];
        for(int i =0;i<arrayRepeated.length;i++){
            arrayRepeated[i] = true;
        }
        Bundle args = new Bundle();
        alarmModel = new AlarmModel(10,30,arrayRepeated,false,"test bao thuc ",true);
      //  alarmDBHelper.createAlarm(alarmModel);
        listAlarmModel = new ArrayList<AlarmModel>();
        listAlarmModel = alarmDBHelper.getAlarms();
        Log.d("fragemnt","listAlarmFragment");
        for(int i =0; i<listAlarmModel.size();i++){
            Log.d("ten bao thuc la :",listAlarmModel.get(i).getName());
        }
        listAlarmRecyclerView  = new ListAlarmAdapter(listAlarmModel);
            LinearLayoutManager layoutManager  = new LinearLayoutManager(getActivity().getApplicationContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAlarmRecyclerView);
        listAlarmRecyclerView.notifyDataSetChanged();

        return view;


    }
    public void loadFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
