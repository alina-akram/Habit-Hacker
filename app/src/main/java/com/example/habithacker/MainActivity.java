package com.example.habithacker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

import java.util.ArrayList;

import android.widget.AdapterView;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    Button addButton;
    ArrayList<Habit> habitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (habitList == null)
            habitList = new ArrayList<>();

        addButton = findViewById(R.id.button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //action when button is clicked
                System.out.println("Click!"); //button clicks as click is printed to console
                startAdding();
            }
        });
        recyclerView = findViewById(R.id.habitList); //conecting xml and code
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyRecyclerViewAdapter(this, habitList); //to complete after
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String resultName = data.getStringExtra("NAME");
                String resultDesc = data.getStringExtra("DESC");
                String resultFreq = data.getStringExtra("FREQ");
                String resultProg = data.getStringExtra("PROG");
                // HERE

                Habit habit = new Habit(resultName, resultDesc, resultFreq, resultProg);
                habitList.add(habit);

                adapter.notifyDataSetChanged();
            }
        }
    }

    public void startAdding(){
        Intent intent = new Intent(this, AddHabitActivity.class);
        startActivityForResult(intent, 1);
    }

    public void addHabit(View view){
        Intent intent = new Intent(this, AddHabitActivity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(View view, int position) {
        habitList.remove(position);
        adapter.notifyDataSetChanged();
    }
}