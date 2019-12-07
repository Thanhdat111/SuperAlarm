package com.example.superalarm.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import android.media.MediaPlayer;

import com.example.superalarm.AnswerQuestionsActivity;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receive","hello");

        String str = intent.getExtras().getString("extra");
        Log.e("chuoi string truyen",str);

        Intent myintent = new Intent(context,Sound.class);
        myintent.putExtra("extra",str);
        context.startService(myintent);

        Intent newIntent = new Intent(context, AnswerQuestionsActivity.class);
        newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
