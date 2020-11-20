package com.example.empmgtsystem.EventList;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.empmgtsystem.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddEventActivity extends AppCompatActivity {

    EditText edit_EName;
    EditText edit_Date;
    EditText edit_Time;
    EditText edit_Location;
    Calendar myCalendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        initViews();

        myCalendar = Calendar.getInstance();


        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        edit_Date.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(AddEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SimpleDateFormat timeF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = timeF.format(Calendar.getInstance().getTime());
        edit_Time.setText(time);

    }

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edit_Date.setText(sdf.format(myCalendar.getTime()));
    }

    public void initViews() {
        edit_EName = findViewById(R.id.editEName);
        edit_Date = findViewById(R.id.editDate);
        edit_Time = findViewById(R.id.editTime);
        edit_Location = findViewById(R.id.editLocation);
    }

    public void btnAddEvent(View view) {
        String eventName = edit_EName.getText().toString().trim();
        String eventDate = edit_Date.getText().toString().trim();
        String eventTime = edit_Time.getText().toString().trim();
        String eventLocation = edit_Location.getText().toString().trim();

        if(eventName.isEmpty()) {
            edit_EName.setError("Enter Event Name...");
            return;
        }


        if(eventDate.isEmpty()) {
            edit_Date.setError("Enter Date...");
            return;
        }

        if(!eventDate.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$")) {
            edit_Date.setError("Please enter correct format...");
            return;
        }

        if(eventTime.isEmpty()) {
            edit_Time.setError("Enter Time...");
            return;
        }

        if(!eventTime.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
            edit_Time.setError("Please enter correct format...");
            return;
        }

        if(eventLocation.isEmpty()) {
            edit_Location.setError("Enter Location...");
            return;
        }

        DBManagerEvent dbManagerEvent = new DBManagerEvent(this);
        Event event = new Event(eventName,eventDate,eventTime,eventLocation);
        long result = dbManagerEvent.addEventRecord(event);
        if(result != -1) {
            Toast.makeText(this,"Added Sucessfully!",Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        }
    }

    public void btnShowAllEvent(View view) {
        startActivity(new Intent(AddEventActivity.this, ShowAllEventActivity.class ));
    }
}