package com.example.empmgtsystem.EventList;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.empmgtsystem.R;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;

public class UpdateEventActivity extends AppCompatActivity {

    EditText edit_EName;
    EditText edit_Date;
    EditText edit_Time;
    EditText edit_Location;
    int id;
    DBManagerEvent dbManagerEvent;
    Calendar myCalendar;
    TextView status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_event);

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
                new DatePickerDialog(UpdateEventActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        SimpleDateFormat timeF = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = timeF.format(Calendar.getInstance().getTime());

        dbManagerEvent = new DBManagerEvent(this);
        Event event = (Event) getIntent().getExtras().getSerializable("EVENT");

        try {
            Date currentDate = new Date();
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");
            Date eventDate = formatDate.parse(event.getDate());
            Date eventTime =  formatTime.parse(event.getTime());
            String currentTime = formatTime.format(myCalendar.getTime());
            Date currenTime = formatTime.parse(currentTime);
            if(formatDate.format(currentDate).equals(formatDate.format(eventDate))){
                if(currenTime.after(eventTime)) {
                    status.setText("Past Event!");
                    Toast.makeText(this,"Past Event!",Toast.LENGTH_SHORT).show();
                } else {
                    status.setText("Current Event!");
                    Toast.makeText(this,"Current Event!",Toast.LENGTH_SHORT).show();
                }
            } else {
                if(System.currentTimeMillis() > eventDate.getTime()) {
                    status.setText("Past Event!");
                    Toast.makeText(this,"Past Event!",Toast.LENGTH_LONG).show();
                } else {
                    status.setText("Current Event!");
                    Toast.makeText(this,"Current Event!",Toast.LENGTH_LONG).show();
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }



        id = event.getId();
        edit_EName.setText(event.getEventName());
        edit_Date.setText(event.getDate());
        edit_Time.setText(event.getTime());
        edit_Location.setText(event.getLocation());

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
        status = findViewById(R.id.eventStatus);
    }

    public void btnUpdateEvent(View view) {
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

        if(eventLocation.isEmpty()) {
            edit_Location.setError("Enter Location...");
            return;
        }

        if(!eventTime.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")) {
            edit_Time.setError("Please enter correct format...");
            return;
        }

        Event event = new Event(id,eventName,eventDate,eventTime,eventLocation);

        int result = dbManagerEvent.updateEventInfo(event);

        if(result > 0) {
            Toast.makeText(this,"Updated Sucessfully!",Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(UpdateEventActivity.this, ShowAllEventActivity.class ));
    }
}