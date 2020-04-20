package com.example.writerchainapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.data.model.Chapters;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class ChainInfoActivity extends AppCompatActivity {

    private TextView chainID;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private FirebaseUser user;
    private FirebaseAuth auth;


    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain_info);
        String chainId = getIntent().getStringExtra("chainID");


        chainID = (TextView)findViewById(R.id.text_chainID);
      //  Intent in= getIntent();
      //  Bundle b = in.getExtras();

      //  String test =(String) b.get("chainID");
        chainID.setText(chainId);

      //   dbReference = database.getReference().child(user.getUid()).child("Chain").child(chainId);



    }
}
