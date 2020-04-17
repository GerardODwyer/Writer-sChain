package com.example.writerchainapp;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.data.model.Chapters;
import com.example.writerchainapp.utils.Help;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import com.google.firebase.database.annotations.Nullable;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ImageView imageBookmark;
    private ImageView imageProfile;
    private ImageView imageGenre;
    private ImageView imageHelp;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;

    private List<Chapters> chapters = new ArrayList<>();
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        setupVariables();
        chain = new Chain();






        imageGenre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GenreActivity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        imageHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Help.class);
                MainActivity.this.startActivity(intent);

            }
        });


    }

    public void setupVariables(){
        imageBookmark = findViewById(R.id.image_bookmark);
        imageProfile = findViewById(R.id.image_profile);
        imageGenre = findViewById(R.id.image_genre);
        imageHelp = findViewById(R.id.image_help);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
