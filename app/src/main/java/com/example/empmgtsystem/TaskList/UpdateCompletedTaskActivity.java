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

public class UpdateCompletedTaskActivity extends AppCompatActivity {

    EditText edit_Task;
    EditText edit_Location;
    EditText edit_Status;
    int id;
    DBManagerTask dbManagerTask;
    Switch switch_Status1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_completed_task);

        initViews();
        dbManagerTask = new DBManagerTask(this);
        Task task = (Task) getIntent().getExtras().getSerializable("TASK");
        id = task.getId();

        switch_Status1 = (Switch) findViewById(R.id.status);

        switch_Status1.setChecked(true);

        switch_Status1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                  edit_Status.setText("completed");
                } else {
                    edit_Status.setText("not completed");
                }
            }
        });


        edit_Task.setText(task.getTaskName());
        edit_Location.setText(task.getLocation());
        edit_Status.setText(task.getStatus());
    }

    public void initViews() {
        edit_Task = findViewById(R.id.editTaskName);
        edit_Location = findViewById(R.id.editLocation);
        edit_Status = findViewById(R.id.editStatus);
    }

    public void btnUpdateTask(View view) {

        String taskname = edit_Task.getText().toString();
        String location = edit_Location.getText().toString();
        String status = edit_Status.getText().toString();

        if(taskname.isEmpty()) {
            edit_Task.setError("Enter Task...");
            return;
        }

        if(location.isEmpty()) {
            edit_Location.setError("Enter Location...");
            return;
        }
        if(status.isEmpty()) {
            edit_Status.setError("Enter Status...");
            return;
        }

        Task task = new Task(id,taskname,location,status);

        int result = dbManagerTask.updateTaskInfo(task);


        if(result > 0) {
            Toast.makeText(this,"Updated Sucessfully!",Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(UpdateCompletedTaskActivity.this, ShowAllTaskActivity.class ));
    }
}