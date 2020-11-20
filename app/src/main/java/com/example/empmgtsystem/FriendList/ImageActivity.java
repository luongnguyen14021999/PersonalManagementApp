package com.example.empmgtsystem.FriendList;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.empmgtsystem.R;

public class ImageActivity extends AppCompatActivity {

    ImageView beefpizza,chickenpizza,pepperonipizza,vegetarianpizza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        beefpizza = (ImageView) findViewById(R.id.image_beef);
        chickenpizza = (ImageView) findViewById(R.id.image_chicken);
        pepperonipizza = (ImageView) findViewById(R.id.image_pepperoni);
        vegetarianpizza = (ImageView) findViewById(R.id.image_vegetarian);

        beefpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageActivity.this, AssignImageActivity.class);
                intent.putExtra("image","beefpizza");
                startActivity(intent);
            }
        });

        chickenpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageActivity.this, AssignImageActivity.class);
                intent.putExtra("image","chickenpineapplepizza");
                startActivity(intent);
            }
        });

        pepperonipizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageActivity.this, AssignImageActivity.class);
                intent.putExtra("image","pepperonipizza");
                startActivity(intent);

            }
        });

        vegetarianpizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ImageActivity.this, AssignImageActivity.class);
                intent.putExtra("image","vegetarianpizza");
                startActivity(intent);
            }
        });
    }
}