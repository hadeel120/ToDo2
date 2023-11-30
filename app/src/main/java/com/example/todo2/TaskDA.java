package com.example.todo2;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class TaskDA extends AppCompatActivity {
    private EditText etTitle, etNote, etTimeDate;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_detail);

        etTitle = findViewById(R.id.etTitle);
        etNote = findViewById(R.id.etNote);
        etTimeDate = findViewById(R.id.etTimeDate);
        btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {

                    Task newTask = new Task();
                    newTask.setTitle(etTitle.getText().toString());
                    newTask.setNote(etNote.getText().toString());
                    newTask.setTimeDate(etTimeDate.getText().toString());
                    newTask.setStatus(false);
                    MainActivity.taskList.add(newTask);
                    MainActivity.taskAdapter.notifyDataSetChanged();
                    SharedP.saveTasks(TaskDA.this, MainActivity.taskList);

                    Log.d("TaskDA", "Task added successfully");

                    finish();
                } catch (Exception e) {

                    Log.e("TaskDA", "Exception: " + e.getMessage(), e);
                }
            }
        });

    }
}
