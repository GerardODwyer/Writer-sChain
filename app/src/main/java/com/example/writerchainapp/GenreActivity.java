package com.example.writerchainapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GenreActivity extends AppCompatActivity {
    private ImageView imageComdey;
    private ImageView imageHorror;
    private ImageView imageLove;
    private ImageView imageScifi;
    private ImageView imageMedevil;
    private ImageView imageCrime;
    private ImageView imageMystery;
    private ImageView imageTradgey;
    private ImageView imageWestren;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.genre);
        setupVariables();


        imageComdey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Comdey Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageHorror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Horror Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Love Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageScifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Scifi Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageMedevil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Medevil Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Crime Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageMystery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Mystery Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageTradgey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Tradgey Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        imageWestren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(GenreActivity.this, "Westren Clicked", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void setupVariables(){
        imageComdey = findViewById(R.id.image_comedy);
        imageHorror = findViewById(R.id.image_horror);
        imageLove = findViewById(R.id.image_love);
        imageScifi = findViewById(R.id.image_scifi);
        imageMedevil = findViewById(R.id.image_medieval);
        imageCrime = findViewById(R.id.image_ci);
        imageMystery = findViewById(R.id.image_mystery);
        imageTradgey = findViewById(R.id.image_tragedy);
        imageWestren = findViewById(R.id.image_western);

    }
}
