package com.example.writerchainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Constructors.Users;
import com.example.writerchainapp.ui.login.LoginActivity;
import com.example.writerchainapp.utils.Utils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> genreList;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private TextView chainCount;
    private TextView chapterCount;
    private DataSnapshot dataSnapshot;
    private long longChainCount;
    private String strChainCount;
    private ImageView signout;
    private TextView username;
    private Users users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        signout = findViewById(R.id.image_bookmark);
        chainCount = findViewById(R.id.text_ChainCount);
        chapterCount = findViewById(R.id.text_ChapterCount);
        username = findViewById(R.id.username);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbReference = database.getReference().child("Users").child(user.getUid());
        getChainInfo(dbReference);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }

        });
    }


    public void getChainInfo(DatabaseReference dbReference) {

        dbReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users users = dataSnapshot.getValue(Users.class);
                String chainNumber = String.valueOf(users.getUserChainCount());
                String chapterNumber = String.valueOf(users.getUserChapterCount());
                chainCount.setText(chainNumber);
                chapterCount.setText(chapterNumber);
                username.setText(users.getUserDisplayName());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });
    }

}
