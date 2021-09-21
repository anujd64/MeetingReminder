package com.example.meetingreminder;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

import mehdi.sakout.fancybuttons.FancyButton;

public class MainActivity extends AppCompatActivity {
    private FancyButton set;
    private TextView txt;
    private TimePicker tp;

    @Override
    @RequiresApi(api = Build.VERSION_CODES.M)
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set =(FancyButton) findViewById(R.id.setbutton);
        set.setOnClickListener(this::onClick);
        txt = findViewById(R.id.txt);
        tp = findViewById(R.id.time);
    }

    int hour, minutes, am_pm;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View view) {

        Calendar cal= Calendar.getInstance();
        EditText inputMessage =(EditText) findViewById(R.id.inputtext);
        String s = inputMessage.getText().toString();
        try {
            if(s.contains(":")||s.contains("AM")|| s.contains("PM")){
                String timestring = s.substring(s.indexOf("21 ") + 3, s.indexOf("M") + 1);
                if(timestring.substring(timestring.indexOf("M") -1,timestring.indexOf("M") + 1).equals("AM")){
                    am_pm = 0;
                }
                else {
                    am_pm = 9;
                }
                hour = Integer.parseInt(timestring.substring(0,timestring.indexOf(':')));
                minutes = Integer.parseInt(timestring.substring(timestring.indexOf(':')+1,timestring.indexOf(":")+3));
                String display =" Hour: " +hour+ " Min: "+minutes+" AM or PM:"+am_pm;
                txt.setText(display);
                cal.set(Calendar.HOUR,hour);
                cal.set(Calendar.MINUTE,minutes);
                cal.set(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)
                );
            }else{
                cal.set(cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH),
                        tp.getHour(),
                        tp.getMinute(),
                        0);
            }
            Alarm_set(cal.getTimeInMillis());
        }
        catch (Exception e){
            txt.setText("Invalid input" + e.toString());
        }



    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void Alarm_set(long timeInMillis) {
        AlarmManager alarmManager=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Intent intent= new Intent(this, Alarm.class);
        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,1,intent,0);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,timeInMillis,pendingIntent);
        Toast.makeText(this,"Your Alarm is Set",Toast.LENGTH_LONG).show();

    }

}
