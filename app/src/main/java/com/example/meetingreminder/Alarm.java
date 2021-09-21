package com.example.meetingreminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;


public class Alarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
//        MediaPlayer mp = MediaPlayer.create(context, Settings.System.DEFAULT_ALARM_ALERT_URI);
//        mp.start();
        Toast.makeText(context, "You have a Meeting!", Toast.LENGTH_LONG).show();
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);

        ringtone.play();
    }
}

