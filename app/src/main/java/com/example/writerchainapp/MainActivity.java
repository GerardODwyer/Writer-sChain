package com.example.writerchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageBookmark;
    private ImageView imageProfile;
    private ImageView imageGenre;
    private ImageView imageHelp;
    private Button buttonEnter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupVariables();

        imageGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChainRecyclerView.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }

    public void setupVariables(){
        imageBookmark = findViewById(R.id.image_bookmark);
        imageProfile = findViewById(R.id.image_profile);
        imageGenre = findViewById(R.id.image_genre);
        imageHelp = findViewById(R.id.image_help);
        buttonEnter = findViewById(R.id.button_enter);
    }
}
