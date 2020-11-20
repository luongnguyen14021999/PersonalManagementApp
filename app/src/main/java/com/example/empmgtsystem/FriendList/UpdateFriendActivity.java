package com.example.empmgtsystem.FriendList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.empmgtsystem.R;


public class UpdateFriendActivity extends AppCompatActivity {

    EditText edit_FName;
    EditText edit_LName;
    AutoCompleteTextView edit_Gentle;
    EditText edit_Age;
    EditText edit_Address;
    int id;
    ImageView imageView;
    DBManagerFriend dbManagerFriend;
    TextView nameImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_friend);

        initViews();

        edit_Gentle.setThreshold(1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,gentle);
        edit_Gentle.setAdapter(adapter);

        edit_Gentle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edit_Gentle.showDropDown();
            }
        });

        dbManagerFriend = new DBManagerFriend(this);
        Friend friend = (Friend) getIntent().getExtras().getSerializable("FRIEND");


        id = friend.getId();
        edit_FName.setText(friend.getFname());
        edit_LName.setText(friend.getLname());
        edit_Gentle.setText(friend.getGentle());
        edit_Age.setText(friend.getAge());
        edit_Address.setText(friend.getAddress());
        nameImage.setText(friend.getImage());
        if(friend.getImage() != null) {
            if(friend.getImage().equals("beefpizza")) {
                imageView.setImageResource(R.drawable.beefpizza);
                nameImage.setText("beefpizza");
            } else if(friend.getImage().equals("chickenpineapplepizza")) {
                imageView.setImageResource(R.drawable.chickenpineapplepizza);
                nameImage.setText("chickenpineapplepizza");
            } else if (friend.getImage().equals("pepperonipizza")) {
                imageView.setImageResource(R.drawable.pepperonipizza);
                nameImage.setText("pepperonipizza");
            } else if(friend.getImage().equals("vegetarianpizza")) {
                imageView.setImageResource(R.drawable.vegetarianpizza);
                nameImage.setText("vegetarianpizza");
            }
        }
    }

    private static final String[] gentle = new String[]{"Male","Female"};

    public void initViews() {
        edit_FName = findViewById(R.id.editFName);
        edit_LName = findViewById(R.id.editLName);
        edit_Gentle = findViewById(R.id.editGentle);
        edit_Age = findViewById(R.id.editAge);
        edit_Address = findViewById(R.id.editAddress);
        imageView = findViewById(R.id.imageView);
        nameImage = findViewById(R.id.nameImage);
    }

    public void btnUpdateRecord(View view) {
       String fname = edit_FName.getText().toString();
       String lname = edit_LName.getText().toString();
       String age = edit_Age.getText().toString();
       String gentle = edit_Gentle.getText().toString();
       String address = edit_Address.getText().toString();
       String image = nameImage.getText().toString();

        if(fname.isEmpty()) {
            edit_FName.setError("Enter First Name...");
            return;
        }

        if(!fname.matches("[a-z,A-Z]*")) {
            edit_FName.setError("Enter only characters");
        }

        if(lname.isEmpty()) {
            edit_LName.setError("Enter Last Name...");
            return;
        }

        if(!lname.matches("[a-z,A-Z]*")) {
            edit_LName.setError("Enter only characters");
        }
        if(age.isEmpty()) {
            edit_Age.setError("Enter Age...");
            return;
        }

        if(!age.matches("[0-9]*")) {
            edit_Age.setError("Enter only numbers...");
            return;
        }

        if(gentle.isEmpty()) {
            edit_Gentle.setError("Enter Gentle...");
            return;
        }

        if(!gentle.matches("^(?:Male|Female)$")) {
            edit_Gentle.setError("Enter Male or Female by clicking in text");
            return;
        }

        if(address.isEmpty()) {
            edit_Address.setError("Enter Address...");
            return;
        }

       Friend friend = new Friend(id,fname,lname,gentle,age,address,image);

       int result = dbManagerFriend.updateFriendInfo(friend);

        if(result > 0) {
            Toast.makeText(this,"Updated Sucessfully!",Toast.LENGTH_SHORT).show();
        }  else {
            Toast.makeText(this,"Something Wrong!",Toast.LENGTH_SHORT).show();
        }

        startActivity(new Intent(UpdateFriendActivity.this, ShowAllFriendActivity.class ));
    }

    public void showMap(View view) {
        String addr =  edit_Address.getText().toString();
        Intent intent = new Intent(UpdateFriendActivity.this,MapsActivity.class);
        intent.putExtra("myaddress",addr);
        startActivity(intent);
    }
}