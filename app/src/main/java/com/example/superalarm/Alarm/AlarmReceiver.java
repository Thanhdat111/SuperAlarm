package com.example.superalarm.Alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("Receive","hello");
        Intent myintent = new Intent(context,Sound.class);
        context.startService(myintent);
    }
}
