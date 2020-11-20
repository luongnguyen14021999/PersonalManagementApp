package com.example.empmgtsystem.TaskList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.empmgtsystem.HomeActivity;
import com.example.empmgtsystem.R;

import java.util.List;

public class ShowAllTaskActivity extends AppCompatActivity {

    ListView listView1;
    List<Task> list1;
    ArrayAdapter<Task> arrayAdapter1;

    ListView listView2;
    List<Task> list2;
    ArrayAdapter<Task> arrayAdapter2;

    DBManagerTask dbManagerTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_task);

        dbManagerTask = new DBManagerTask(this);

        completedTasks();

        uncompletedTasks();
    }

    public void completedTasks() {
        listView1 = findViewById(R.id.listview1);

        list1 = dbManagerTask.getAllTaskCompletedRecord();

        arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list1);

        listView1.setAdapter(arrayAdapter1);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task task = list1.get(position);
                Intent intent = new Intent(ShowAllTaskActivity.this, UpdateCompletedTaskActivity.class);
                intent.putExtra("TASK", task);
                startActivity(intent);
            }
        });

        listView1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllTaskActivity.this);
                builder.setMessage("Are you sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Task task = list1.get(position);
                        int e_id = task.getId();
                        int result = dbManagerTask.deleteTaskRecord(e_id);
                        if (result > 0) {
                            Toast.makeText(ShowAllTaskActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            list1.remove(task);
                            arrayAdapter1.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ShowAllTaskActivity.this, "Somnething Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", null);
                builder.show();

                return true;
            }
        });
    }

    public void uncompletedTasks() {
        listView2 = findViewById(R.id.listview2);


        list2 = dbManagerTask.getAllTaskNotCompletedRecord();

        arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list2);

        listView2.setAdapter(arrayAdapter2);

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Task task = list2.get(position);
                Intent intent = new Intent(ShowAllTaskActivity.this, UpdateUncompletedTaskActivity.class);
                intent.putExtra("TASK", task);
                startActivity(intent);
            }
        });

        listView2.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllTaskActivity.this);
                builder.setMessage("Are you sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Task task = list2.get(position);
                        int e_id = task.getId();
                        int result = dbManagerTask.deleteTaskRecord(e_id);
                        if (result > 0) {
                            Toast.makeText(ShowAllTaskActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            list2.remove(task);
                            arrayAdapter2.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ShowAllTaskActivity.this, "Somnething Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", null);
                builder.show();

                return true;
            }
        });
    }

    public void backToHome(View view) {
        startActivity(new Intent(ShowAllTaskActivity.this, HomeActivity.class ));
    }


    public void addTasks(View view) {
        startActivity(new Intent(ShowAllTaskActivity.this, AddTaskActivity.class ));
    }

}