package com.example.habithacker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Calendar;

import android.widget.AdapterView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    Button addButton, settingButton;
    ArrayList<Habit> habitList;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        habitList = db.getAllHabit();

        addButton = findViewById(R.id.button);
        settingButton = findViewById(R.id.settingButton);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAdding();
            }
        });
        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToSetting();
            }
        });

        // setting up recyclerview
        recyclerView = findViewById(R.id.habitList); //conecting xml and code
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, habitList); //to complete after
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    // fetching information from completed activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String resultName = data.getStringExtra("NAME");
                String resultDesc = data.getStringExtra("DESC");
                String resultFreq = data.getStringExtra("FREQ");
                String resultProg = data.getStringExtra("PROG");

                Habit habit = new Habit(resultName, resultDesc, resultFreq, resultProg);
                long id = db.insert(habit);


                habitList.clear();
                habitList.addAll(db.getAllHabit());

                adapter.notifyDataSetChanged();
            }
        }
        if(requestCode == 2){
            if(resultCode == RESULT_OK){
                int hour = data.getIntExtra("HOUR", 0);
                int minute = data.getIntExtra("MINUTE", 0);
                boolean ampm = data.getBooleanExtra("AMPM", false);
                settingNotification(hour, minute, ampm);
            }
        }
        if(requestCode == 3){
            String resultName = data.getStringExtra("NAME");
            String resultDesc = data.getStringExtra("DESC");
            String resultFreq = data.getStringExtra("FREQ");
            String resultProg = data.getStringExtra("PROG");
            int resultID = data.getIntExtra("ID", 0);

            Habit habit = new Habit(resultName, resultDesc, resultFreq, resultProg);
            db.updateHabit(habit, resultID);
            
            habitList.clear();
            habitList.addAll(db.getAllHabit());

            adapter.notifyDataSetChanged();
        }
    }

    // opens add habit activity
    public void goToAdding(){
        Intent intent = new Intent(this, AddHabitActivity.class);
        startActivityForResult(intent, 1);
    }

    // opens setting activity
    public void goToSetting(){
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, 2);
    }

    // when item on the list is long pressed
    @Override
    public void onItemClick(View view, int position) {
        Habit editHabit = habitList.get(position);
        Intent intent = new Intent(this, EditHabitActivity.class);
        intent.putExtra("NAME", editHabit.getName());
        intent.putExtra("DESC", editHabit.getDescription());
        intent.putExtra("FREQ", editHabit.getFrequency());
        intent.putExtra("PROG", editHabit.getProgress());
        intent.putExtra("ID", editHabit.getId());
        startActivityForResult(intent, 3);
    }

    // when delete button from the list is pressed
    @Override
    public void onDeleteClick(View view, int position) {
        Habit deleteHabit = habitList.get(position);
        db.deleteHabit(deleteHabit);

        habitList.clear();
        habitList.addAll(db.getAllHabit());

        adapter.notifyDataSetChanged();
    }

    // create a notification for future time
    public void settingNotification(int hour, int minute, boolean isPM){
        if(isPM && hour != 12){
            hour = hour + 12;
        }
        if(!isPM && hour == 12){
            hour = hour - 12;
        }
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);

        Intent intent = new Intent(this, Mote.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 1253, intent, PendingIntent.FLAG_UPDATE_CURRENT|  Intent.FILL_IN_DATA);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),pendingIntent );
    }

}