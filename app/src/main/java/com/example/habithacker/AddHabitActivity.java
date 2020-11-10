package com.example.habithacker;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddHabitActivity extends AppCompatActivity {

    EditText editHabitName;
    EditText editDesc;
    EditText editFreq;
    EditText editProg;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        editHabitName = findViewById(R.id.editHabitName);
        editDesc = findViewById(R.id.editDesc);
        editFreq = findViewById(R.id.editFreq);
        editProg = findViewById(R.id.editProg);
        submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check whether eveyrthing is filled.
                // editProg.getText().toString.isEmpty();
                if(if any field is empty);
                    showToast("Please fill every section");
                    return;


                close();
            }
        });

    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void close(){
        Intent intent = new Intent();
        intent.putExtra("NAME", editHabitName.getText().toString());
        intent.putExtra("DESC", editDesc.getText().toString());
        intent.putExtra("FREQ", editFreq.getText().toString());
        intent.putExtra("PROG", editProg.getText().toString());
        //HERE
        setResult(RESULT_OK, intent);
        finish();
    }

}