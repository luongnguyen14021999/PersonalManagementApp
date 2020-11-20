package com.example.empmgtsystem.FriendList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.empmgtsystem.R;

public class AddFriendActivity extends AppCompatActivity {

    EditText edit_FName;
    EditText edit_LName;
    AutoCompleteTextView edit_Gentle;
    EditText edit_Age;
    EditText edit_Address;
    TextView nameImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        initViews();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,gentle);
        edit_Gentle.setAdapter(adapter);

        edit_Gentle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_Gentle.showDropDown();
            }
        });

    }

    private static final String[] gentle = new String[]{"Male","Female"};

    public void initViews() {
        edit_FName = findViewById(R.id.editFName);
        edit_LName = findViewById(R.id.editLName);
        edit_Gentle = findViewById(R.id.editGentle);
        edit_Age = findViewById(R.id.editAge);
        edit_Address = findViewById(R.id.editAddress);
        nameImage =  findViewById(R.id.nameImage);
    }

    public void btnAddRecord(View view) {
        String friendFName = edit_FName.getText().toString().trim();
        String friendLName = edit_LName.getText().toString().trim();
        String friendAge = edit_Age.getText().toString().trim();
        String friendGentle = edit_Gentle.getText().toString().trim();
        String friendAddress = edit_Address.getText().toString().trim();
        String friendImage = nameImage.getText().toString().trim();

        if(friendFName.isEmpty()) {
            edit_FName.setError("Enter First Name...");
            return;
        }

        if(!friendFName.matches("[a-z,A-Z]*")) {
            edit_FName.setError("Enter only characters");
        }

        if(friendLName.isEmpty()) {
            edit_LName.setError("Enter Last Name...");
            return;
        }

        if(!friendLName.matches("[a-z,A-Z]*")) {
            edit_LName.setError("Enter only characters");
        }
        if(friendAge.isEmpty()) {
            edit_Age.setError("Enter Age...");
            return;
        }

        if(!friendAge.matches("[0-9]*")) {
            edit_Age.setError("Enter only numbers...");
            return;
        }

        if(friendGentle.isEmpty()) {
            edit_Gentle.setError("Enter Gentle...");
            return;
        }

        if(!friendGentle.matches("^(?:Male|Female)$")) {
            edit_Gentle.setError("Enter Male or Female by clicking in text");
            return;
        }

        if(friendAddress.isEmpty()) {
            edit_Address.setError("Enter Address...");
            return;
        }

        DBManagerFriend dbManagerFriend = new DBManagerFriend(this);
        Friend friend = new Friend(friendFName,friendLName,friendGentle,friendAge,friendAddress,friendImage);
        long result = dbManagerFriend.addFriendRecord(friend);
        if(result != -1) {
            Toast.makeText(this,"Added Sucessfully!",Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        }
    }

    public void btnShowAllRecord(View view) {
       startActivity(new Intent(AddFriendActivity.this, ShowAllFriendActivity.class ));
    }
}