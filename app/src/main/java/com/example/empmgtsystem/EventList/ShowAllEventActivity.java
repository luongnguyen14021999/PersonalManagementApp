package com.example.empmgtsystem.EventList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.example.empmgtsystem.HomeActivity;
import com.example.empmgtsystem.R;

import java.util.List;

public class ShowAllEventActivity extends AppCompatActivity {

    ListView listView;
    List<Event> list;
    ArrayAdapter<Event> arrayAdapter;
    DBManagerEvent dbManagerEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_event);


        listView = findViewById(R.id.listview);

        dbManagerEvent = new DBManagerEvent(this);

        list = dbManagerEvent.getAllEventRecord();


        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);


        listView.setAdapter(arrayAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Event event = list.get(position);
                Intent intent = new Intent(ShowAllEventActivity.this, UpdateEventActivity.class);
                intent.putExtra("EVENT", event);
                startActivity(intent);
            }
        });
    }

    public void addDetails(View view)  {
        startActivity(new Intent(ShowAllEventActivity.this, AddEventActivity.class ));
    }

    public void backToHome(View view) {
        startActivity(new Intent(ShowAllEventActivity.this, HomeActivity.class ));
    }

    public void deleteRecord(View view) {
        startActivity(new Intent(ShowAllEventActivity.this, ShowAllEventWithCheckboxActivity.class ));
    }
}