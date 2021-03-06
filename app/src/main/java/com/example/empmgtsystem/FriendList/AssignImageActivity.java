package com.example.empmgtsystem.FriendList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.Toast;

import com.example.empmgtsystem.HomeActivity;
import com.example.empmgtsystem.R;

import java.util.List;

public class AssignImageActivity extends AppCompatActivity {

    ListView listView;
    List<Friend> list;
    ArrayAdapter<Friend> arrayAdapter;
    DBManagerFriend dbManagerFriend;
    String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assign_image);


        listView = findViewById(R.id.listview);

        dbManagerFriend = new DBManagerFriend(this);

        list = dbManagerFriend.getAllFriendRecord();

        // default adapter
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,list);

        // use your custom layout

        listView.setAdapter(arrayAdapter);

        a = getIntent().getStringExtra("image");


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Friend friend = list.get(position);
                friend.setImage(a);
                Intent intent = new Intent(AssignImageActivity.this, UpdateFriendActivity.class);
                intent.putExtra("FRIEND", friend);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(AssignImageActivity.this);
                builder.setMessage("Are you sure to delete ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        Friend friend = list.get(position);
                        int e_id = friend.getId();
                        int result = dbManagerFriend.deleteFriendRecord(e_id);
                        if (result > 0) {
                            Toast.makeText(AssignImageActivity.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            list.remove(friend);
                            arrayAdapter.notifyDataSetChanged();
                        } else {
                            Toast.makeText(AssignImageActivity.this, "Somnething Wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                builder.setNegativeButton("No", null);
                builder.show();

                return true;
            }
        });

    }

    public void addDetails(View view)  {
        startActivity(new Intent(AssignImageActivity.this, AddFriendActivity.class ));
    }

    public void backToHome(View view) {
        startActivity(new Intent(AssignImageActivity.this, HomeActivity.class ));
    }
}