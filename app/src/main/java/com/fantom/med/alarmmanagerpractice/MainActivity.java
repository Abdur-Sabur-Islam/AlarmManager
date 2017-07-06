package com.fantom.med.alarmmanagerpractice;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button startRepeatingTimer;
    private Button cancelRepeatingTimer;
    private Button onetimeTimer;
    private AlarmManagerBroadCastReceiver alarm ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarm = new AlarmManagerBroadCastReceiver();
        startRepeatingTimer = (Button) findViewById(R.id.btStart);
        cancelRepeatingTimer = (Button) findViewById(R.id.btCancel);
        onetimeTimer = (Button) findViewById(R.id.btOneTime);

        startRepeatingTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alarm != null){
                    alarm.SetAlarm(getApplicationContext());
                }else{
                    Toast.makeText(getApplicationContext(), "Alarm Is Null", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelRepeatingTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alarm != null){
                    alarm.CancleAlarm(getApplicationContext());
                }else{
                    Toast.makeText(getApplicationContext(),"Alarm is Null", Toast.LENGTH_SHORT).show();
                }
            }
        });
        onetimeTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(alarm != null){
                    alarm.SetOneTimeTim(getApplicationContext());
                }else {
                    Toast.makeText(getApplicationContext(), "Alarm is null", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
