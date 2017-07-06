package com.fantom.med.alarmmanagerpractice;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import java.text.Format;

/**
 * Created by Admin on 7/6/2017.
 */

public class AlarmManagerBroadCastReceiver  extends BroadcastReceiver{

    final public static  String oneTime = "oneTime";


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "yourLock");
        wl.acquire();

        Bundle extras = intent.getExtras();
        StringBuilder msgStr = new StringBuilder();

        if(extras != null && extras.getBoolean(oneTime, Boolean.FALSE)){
            msgStr.append("One time Timer : ");

        }
        Format formatter = new SimpleDateFormat("hh:mm:ss a");
        Toast.makeText(context, msgStr, Toast.LENGTH_LONG).show();
        wl.release();
    }

    public void SetAlarm(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmManagerBroadCastReceiver.class);
        intent.putExtra(oneTime, Boolean.FALSE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis(),1000*5,pi);
    }
    public void CancleAlarm(Context context){
        Intent intent = new Intent(context,AlarmManagerBroadCastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
    }
    public void SetOneTimeTim(Context context){
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context,AlarmManagerBroadCastReceiver.class);
        intent.putExtra(oneTime, Boolean.TRUE);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), pi);
    }
}
