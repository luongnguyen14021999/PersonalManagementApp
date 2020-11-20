package com.example.empmgtsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.empmgtsystem.EventList.ShowAllEventActivity;
import com.example.empmgtsystem.FriendList.ShowAllFriendActivity;
import com.example.empmgtsystem.Gallery.ImageGalleryActivity;
import com.example.empmgtsystem.TaskList.ShowAllTaskActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void screenAddDetails (View view) {
        startActivity(new Intent(HomeActivity.this, ShowAllFriendActivity.class));
    }

    public void screenAddTasks (View view) {
        startActivity(new Intent(HomeActivity.this, ShowAllTaskActivity.class));
    }

    public void screenGallery (View view) {
        startActivity(new Intent(HomeActivity.this, ImageGalleryActivity.class));
    }

    public void screenEvents (View view) {
        startActivity(new Intent(HomeActivity.this, ShowAllEventActivity.class));
    }
}