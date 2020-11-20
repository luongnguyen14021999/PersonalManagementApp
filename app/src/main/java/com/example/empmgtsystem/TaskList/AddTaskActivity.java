package com.example.empmgtsystem.TaskList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import com.example.empmgtsystem.R;

public class AddTaskActivity extends AppCompatActivity {


    Switch switch_Status;

    EditText edit_Status;

    EditText edit_Task;

    EditText edit_Location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        initViews();

        switch_Status = (Switch) findViewById(R.id.status);

        edit_Status = (EditText) findViewById(R.id.editStatus);

        switch_Status.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    edit_Status.setText("completed");
                } else {
                    edit_Status.setText("not completed");
                }
            }
        });

    }

    public void initViews() {
        edit_Task = findViewById(R.id.editTaskName);
        edit_Location = findViewById(R.id.editLocation);
        edit_Status = findViewById(R.id.editStatus);
    }

    public void btnAddTask (View view) {

        String Task = edit_Task.getText().toString().trim();
        String Location = edit_Location.getText().toString().trim();
        String Status = edit_Status.getText().toString().trim();

        if(Task.isEmpty()) {
            edit_Task.setError("Enter Task...");
            return;
        }

        if(Location.isEmpty()) {
            edit_Location.setError("Enter Location...");
            return;
        }
        if(Status.isEmpty()) {
            edit_Status.setError("Enter Status...");
            return;
        }

        DBManagerTask dbManagerTask = new DBManagerTask(this);
        Task task = new Task(Task,Location,Status);
        long result = dbManagerTask.addTaskRecord(task);
        if(result != -1) {
            Toast.makeText(this,"Added Sucessfully!",Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        }
    }

    public void btnShowAllTask (View view) {
        startActivity(new Intent(AddTaskActivity.this, ShowAllTaskActivity.class ));
    }

}