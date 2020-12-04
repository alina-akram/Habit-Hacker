package com.example.habithacker;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    EditText hour;
    EditText minute;
    Switch ampmSwitch;
    Button timeButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        hour = findViewById(R.id.hour);
        minute = findViewById(R.id.minute);
        ampmSwitch = findViewById(R.id.ampmSwitch);
        timeButton = findViewById(R.id.timeButton);

        ampmSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // if switch is checked, it is PM
                // if switch is not checked, it is AM
                if(b){
                    ampmSwitch.setText("PM");
                }else{
                    ampmSwitch.setText("AM");
                }
            }
        });

        timeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // checking whether hour and minute fields are empty for not
                if(hour.getText().toString().isEmpty()){
                    showToast("Please enter in hour");
                    return;
                }else if(minute.getText().toString().isEmpty()){
                    showToast("Please enter in minute");
                    return;
                }

                int hourNum = Integer.parseInt(hour.getText().toString());
                int minuteNum = Integer.parseInt(minute.getText().toString());
                boolean isPM = ampmSwitch.isChecked();

                // checking whether hour and minute fields are in a proper input or not
                if(hourNum < 1 || hourNum > 12){
                    showToast("Please enter a number between 1 and 12 in hour");
                    return;
                }else if(minuteNum < 0 || minuteNum > 59){
                    showToast("Please enter a number between 0 and 59 in minute");
                    return;
                }
                showToast("Alarm set to " + hourNum + ":" + minuteNum + ampmSwitch.getText().toString());
                close();
            }
        });


    }

    // close the activity and return hour, minute and ampm information
    public void close(){
        Intent intent = new Intent();
        intent.putExtra("HOUR", Integer.parseInt(hour.getText().toString()));
        intent.putExtra("MINUTE", Integer.parseInt(minute.getText().toString()));
        intent.putExtra("AMPM", ampmSwitch.isChecked());
        setResult(RESULT_OK, intent);
        finish();
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
