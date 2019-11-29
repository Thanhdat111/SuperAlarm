package com.example.superalarm.Alarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.superalarm.R;

public class Sound extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String key_receive = intent.getExtras().getString("extra");
        Log.e("key receive Sound",key_receive);
        if(key_receive.equals("on")) {
            int songid = this.getRawResIdByName("faded");
            mediaPlayer = MediaPlayer.create(this, songid);

            mediaPlayer.start();
        }else
            if(key_receive.equals("off")){
                mediaPlayer.stop();
                mediaPlayer.reset();
            }

        Log.e("Sound","hello");
        return START_NOT_STICKY;
    }

    public int getRawResIdByName(String resName)  {
        String pkgName = this.getPackageName();
        // Return 0 if not found.
        // Trả về 0 nếu không tìm thấy.
        int resID = this.getResources().getIdentifier(resName, "raw", pkgName);
        return resID;
    }
}
