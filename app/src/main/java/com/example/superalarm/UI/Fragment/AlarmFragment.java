package com.example.superalarm.UI.Fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.superalarm.Alarm.AlarmReceiver;
import com.example.superalarm.Database.alarmdatabase.AlarmDBHelper;
import com.example.superalarm.Database.alarmdatabase.AlarmModel;
import com.example.superalarm.R;

import java.util.Calendar;

public class AlarmFragment extends Fragment {

    private AlarmDBHelper alarmDBHelper;
    private Button btn_setHour, btn_setAlarmClock, btn_stopAlarmClock;
    private CheckBox checkBox_allDay, checkBox_monDay,
            checkBox_tueDay,checkBox_wedDay,checkBox_thuDay,
            checkBox_friDay,checkBox_stuDay,checkBox_sunDay;
    private Calendar calendar = Calendar.getInstance();

    private TextView lb_time_alarmClock;
    private EditText txt_name_alarm;
    private int hours = 0;
    private int minutes = 0;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm, container, false);
        alarmDBHelper = new AlarmDBHelper(getContext());
        //get data
//        AlarmModel alarmModel =(AlarmModel) getArguments().getSerializable("CID");
//        Log.d("du lieu rmFragment",alarmModel.getName());

        btn_setHour = (Button) view.findViewById(R.id.btn_set_hours);
        btn_setAlarmClock = (Button) view.findViewById(R.id.btn_set_alarm_clock);
        btn_stopAlarmClock = (Button) view.findViewById(R.id.btn_stop_alarm_clock);
        lb_time_alarmClock = (TextView) view.findViewById(R.id.lb_timeAlarm);
        txt_name_alarm = (EditText) view.findViewById(R.id.txt_name_alarm);

        //checkBox
        checkBox_allDay = (CheckBox) view.findViewById(R.id.checkBox_allDay);
        checkBox_monDay = (CheckBox) view.findViewById(R.id.checkBox_monDay);
        checkBox_tueDay = (CheckBox) view.findViewById(R.id.checkBox_tueDay);
        checkBox_wedDay = (CheckBox) view.findViewById(R.id.checkBox_wedDay);
        checkBox_thuDay = (CheckBox) view.findViewById(R.id.checkBox_thuDay);
        checkBox_friDay = (CheckBox) view.findViewById(R.id.checkBox_friDay);
        checkBox_stuDay = (CheckBox) view.findViewById(R.id.checkBox_stuDay);
        checkBox_sunDay = (CheckBox) view.findViewById(R.id.checkBox_sunDay);

        alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);

        Intent intent_alarm = new Intent(getActivity(), AlarmReceiver.class);
        // set time alarm clock
        btn_setHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int hour = calendar.get(Calendar.HOUR_OF_DAY);
                int minute = calendar.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        hours = timePicker.getCurrentHour();
                        minutes = timePicker.getCurrentMinute();
                        calendar.set(Calendar.HOUR_OF_DAY,hours);
                        calendar.set(Calendar.MINUTE,minutes);
                        lb_time_alarmClock.setText(hours+":"+minutes+" ");

                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });

        btn_setAlarmClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               lb_time_alarmClock.setText(hours+":"+minutes+" ");
               Log.d("test", String.valueOf(calendar.getTimeInMillis()));
               Log.d("ngay va gio:" , hours +":" + minutes);

               intent_alarm.putExtra("extra","on");

               pendingIntent = PendingIntent.getBroadcast(getActivity(),0,intent_alarm,PendingIntent.FLAG_UPDATE_CURRENT);
               alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);

               //tranfer data to ListFragment
                boolean[] arrayRepeater  = new boolean[]{checkBox_monDay.isChecked(),checkBox_tueDay.isChecked(),checkBox_wedDay.isChecked(),
                checkBox_thuDay.isChecked(),checkBox_friDay.isChecked(),checkBox_stuDay.isChecked(),checkBox_sunDay.isChecked()};
                boolean repeatedWeeky = checkBox_allDay.isChecked();

                AlarmModel alarmModel = new AlarmModel(hours,minutes,arrayRepeater,repeatedWeeky,txt_name_alarm.getText().toString(),true);

                alarmDBHelper.createAlarm(alarmModel);
                Fragment fragment = new ListAlarmFragment();
                tranferFragment(fragment);

            }
        });

        btn_stopAlarmClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Stop alarm clock
                try {
                    pendingIntent.cancel();
                    Toast.makeText(getActivity(), "Đã hủy hẹn giờ", Toast.LENGTH_SHORT).show();
                    intent_alarm.putExtra("extra", "off");
                    getActivity().sendBroadcast(intent_alarm);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), "Đã hủy hẹn giờ", Toast.LENGTH_SHORT).show();
                }
            }

        });

        return view;
    }

    public void tranferFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_main,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


}
