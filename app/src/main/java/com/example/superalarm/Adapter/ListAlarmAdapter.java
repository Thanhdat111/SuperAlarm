package com.example.superalarm.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.superalarm.Database.alarmdatabase.AlarmModel;
import com.example.superalarm.R;

import java.util.ArrayList;
import java.util.List;

public class ListAlarmAdapter extends  RecyclerView.Adapter<ListAlarmAdapter.RecyclerViewHolder> {


    private List<AlarmModel> data = new ArrayList<AlarmModel>();

    public ListAlarmAdapter(List<AlarmModel> data){
        this.data = data;
    }
    @NonNull
    @Override
    public ListAlarmAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view =  layoutInflater.inflate(R.layout.item_alarm_clock,parent,false);
        return new RecyclerViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListAlarmAdapter.RecyclerViewHolder holder, int position) {

        holder.txtAlarmName.setText(data.get(position).getName());
        String dateTime = Integer.toString(data.get(position).getTimeHour()) +":" + Integer.toString(data.get(position).getTimeMinute());
        holder.txtAlarmTime.setText(dateTime);
        if(data.get(position).isEnabled()==true){
            holder.alarmStatus.isChecked();
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        TextView txtAlarmName, txtAlarmTime;
        Switch alarmStatus;
        public RecyclerViewHolder(View itemView) {
            super(itemView);
            txtAlarmName = (TextView) itemView.findViewById(R.id.alarm_name);
            txtAlarmTime = (TextView) itemView.findViewById(R.id.alarm_time);
            alarmStatus  = (Switch) itemView.findViewById(R.id.alarm_status);
        }
    }
}
