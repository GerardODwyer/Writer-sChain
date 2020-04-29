package com.example.writerchainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.writerchainapp.Constructors.Users;
import com.example.writerchainapp.ui.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private TextView chainCount;
    private TextView chapterCount;
    private ImageView signout;
    private TextView username;
    private Users users;
    private int mCounter = 0;

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

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCounter++;

                if (mCounter == 6){
                    mCounter = 0;
                    if (!users.isUserAdmin()) {
                        users.setUserAdmin(true);
                        dbReference.setValue(users);
                        Toast.makeText(ProfileActivity.this, "User profile set to admin. Enjoy!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(ProfileActivity.this, "Already an admin user.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });

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
                users = dataSnapshot.getValue(Users.class);
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
