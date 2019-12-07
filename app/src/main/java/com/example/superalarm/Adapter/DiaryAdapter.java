package com.example.superalarm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.superalarm.Model.DiaryDetail;
import com.example.superalarm.R;

import java.util.List;

public class DiaryAdapter extends ArrayAdapter<DiaryDetail> {

    Context context;
    List<DiaryDetail> DiaryDetails;

    public DiaryAdapter(@NonNull Context context, int resource, List<DiaryDetail> objects) {
        super(context, resource, objects);
        this.context = context;
        this.DiaryDetails = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;

        if(listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.item_diary,parent,false);
        }

        DiaryDetail diaryCurrent = DiaryDetails.get(position);

        TextView time = (TextView)listItem.findViewById(R.id.time_itemDiary);
        time.setText(diaryCurrent.getTime());

        TextView result = (TextView)listItem.findViewById(R.id.result_diary);
        result.setText(diaryCurrent.getResult());
        return listItem;
    }
}
