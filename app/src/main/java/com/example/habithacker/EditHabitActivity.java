package com.example.habithacker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditHabitActivity extends AppCompatActivity {

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

        String nameInfo = getIntent().getStringExtra("NAME");
        String descInfo = getIntent().getStringExtra("DESC");
        String freqInfo = getIntent().getStringExtra("FREQ");
        String progInfo = getIntent().getStringExtra("PROG");

        editHabitName.setText(nameInfo);
        editDesc.setText(descInfo);
        editFreq.setText(freqInfo);
        editProg.setText(progInfo);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // check whether everything is filled or not
                if (editHabitName.getText().toString().isEmpty() || editDesc.getText().toString().isEmpty() || editFreq.getText().toString().isEmpty()
                        || editProg.getText().toString().isEmpty()) {
                    showToast("Please fill every section");

                    return;
                }

                close();

            }


        });
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // close the activity and return name, description, frequency and progress information
    public void close(){
        Intent intent = new Intent();
        intent.putExtra("NAME", editHabitName.getText().toString());
        intent.putExtra("DESC", editDesc.getText().toString());
        intent.putExtra("FREQ", editFreq.getText().toString());
        intent.putExtra("PROG", editProg.getText().toString());
        intent.putExtra("ID", getIntent().getIntExtra("ID", 0));
        setResult(RESULT_OK, intent);
        finish();
    }

}