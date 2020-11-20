package com.example.empmgtsystem.EventList;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.empmgtsystem.R;
import java.util.List;

public class ShowAllEventWithCheckboxActivity extends AppCompatActivity {


    ListView listView;
    List<Event> list;
    boolean[] checkBoxes;
    int boxIndex = 0;
    EventAdapter adapter;
    ArrayAdapter<Event> arrayAdapter;
    DBManagerEvent dbManagerEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_event_with_checkbox);


        listView = findViewById(R.id.listview);

        dbManagerEvent = new DBManagerEvent(this);

        list = dbManagerEvent.getAllEventRecord();

        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);

        adapter = new EventAdapter(this,list);

        listView.setAdapter(adapter);

    }

    public void backToEvent(View view) {
        startActivity(new Intent(ShowAllEventWithCheckboxActivity.this, ShowAllEventActivity.class ));
    }

    public void deleteEvent(View view) {
        final boolean[] checkboxes = adapter.getCheckBoxState();
        for (int i = 0; i < list.size(); i++) {
            if (checkboxes[i] == true) {
                final int position = i;
                AlertDialog.Builder builder = new AlertDialog.Builder(ShowAllEventWithCheckboxActivity.this);
                builder.setMessage("Are you sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Event event = list.get(position);
                        int e_id = event.getId();
                        int result = dbManagerEvent.deleteEventRecord(e_id);
                        startActivity(new Intent(ShowAllEventWithCheckboxActivity.this, ShowAllEventWithCheckboxActivity.class ));
                        if (result > 0) {
                            Toast.makeText(ShowAllEventWithCheckboxActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            list.remove(event);
                            arrayAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(ShowAllEventWithCheckboxActivity.this, "Somnething Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", null);
                builder.show();
            }
        }
    }
}