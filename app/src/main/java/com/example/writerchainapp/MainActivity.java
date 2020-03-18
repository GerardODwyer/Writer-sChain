package com.example.writerchainapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.data.model.Chapters;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    private ImageView imageBookmark;
    private ImageView imageProfile;
    private ImageView imageGenre;
    private ImageView imageHelp;
    private Button buttonEnter;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> chainsList;
    private List<Chapters> chapters = new ArrayList<>();
    private FirebaseUser user;
    private FirebaseAuth auth;
    private  Chain chain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        chainsList = new ArrayList<>();
//        Chapters chap = new Chapters();
//        chap.setChapterBody("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vel porta tellus. Fusce egestas vulputate sem, ut tincidunt turpis rhoncus nec. Sed mattis, sem quis blandit ornare, sem quam malesuada ante, vel posuere nisi tellus a turpis. Sed a tortor at lacus bibendum convallis. Etiam est lorem, scelerisque ac accumsan nec, faucibus quis ex. Phasellus suscipit luctus sem, vitae aliquet ante semper ac. Vestibulum eu nisi sit amet libero dictum iaculis vel a elit. Aliquam vitae eleifend ex. Etiam vulputate pretium tellus, nec elementum magna imperdiet ac. Nunc eu quam euismod, placerat massa a, lobortis nisl. Praesent dolor nisl, facilisis quis massa at, dignissim rhoncus dolor. Cras et neque bibendum, posuere magna a, tincidunt nunc.");
//        chap.setChapterId(01);
//        chap.setChapterTitle("The Title");
//        chain = new Chain();
//        chain.setChainID("100");
//        chain.setChainAuthor(user.getUid());
//        chain.setChainDescription("Some more text hetre ");
//        chain.setChainGenre(user.getEmail());
//        chain.setChainName("some name");
//        chain.setChainUpvotes("yes");
//        chain.setChapterCount(5);
//        chain.setDateCreated("02/02/02");
//        chain.setChaptersList(chapters);
//        dbReference = database.getReference().child(user.getUid()).child("Chain");
        setupVariables();



//        dbReference.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                Chain chains = dataSnapshot.getValue(Chain.class);
//                chainsList.add(chains);
//
//                for (Chain chan : chainsList) {
//                    Log.d("TAG===>>>", chan.getChainGenre());
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//
//        });

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

    @Override
    protected void onResume() {
        super.onResume();

//        DatabaseReference newDb = dbReference.push();
//        Map<String, String> saveData = new HashMap<>();
//        saveData.put("chainId", chain.getChainID());
//        saveData.put("chainAuthor", chain.getChainAuthor());
//        saveData.put("chainDesc", chain.getChainDescription());
//        saveData.put("chainGenre", chain.getChainGenre());
//        saveData.put("chainName", chain.getChainName());
//        saveData.put("chainUpvote", chain.getChainUpvotes());
//        saveData.put("chainChapterCount", String.valueOf(chain.getChapterCount()));
//
//        newDb.getRef().child("").setValue(saveData);

    }
}
