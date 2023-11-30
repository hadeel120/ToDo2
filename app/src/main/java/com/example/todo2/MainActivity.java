package com.example.todo2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static List<Task> taskList;
    public static TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskList = SharedP.getTasks(this);
        if (taskList == null) {
            taskList = new ArrayList<>();
        }


        taskAdapter = new TaskAdapter(this, R.layout.task_item, taskList);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(taskAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task selectedTask = taskList.get(position);
                selectedTask.setStatus(!selectedTask.isStatus());
                taskAdapter.notifyDataSetChanged();


                SharedP.saveTasks(MainActivity.this, new ArrayList<>(taskList));
            }
        });

        taskAdapter.setOnCheckedChangeListener(new TaskAdapter.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(int position, boolean isChecked) {
                Task selectedTask = taskList.get(position);
                selectedTask.setStatus(isChecked);
                SharedP.saveTasks(MainActivity.this, taskList);
            }
        });


        findViewById(R.id.btnAddTask).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TaskDA.class));
            }
        });


        findViewById(R.id.btnDeleteAllTasks).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                taskList.clear();
                taskAdapter.notifyDataSetChanged();
                SharedP.saveTasks(MainActivity.this, taskList);
            }
        });
    }

}
