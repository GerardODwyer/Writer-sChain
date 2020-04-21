package com.example.writerchainapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.writerchainapp.Constructors.Chain;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Genre2Activity extends AppCompatActivity {
    private ImageView imageAction;
    private ImageView imageAdventure;
    private ImageView imageDrama;
    private ImageView imageFanfic;
    private ImageView imageFantasy;
    private ImageView imageFairytale;
    private ImageView imageMagicrealism;
    private ImageView imageConspiracy;
    private ImageView imageDetective;
    private ImageView arrowleft;
    private ImageView arrowright;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private List<Chain> ActionList;
    private List<Chain> AdventureList;
    private List<Chain> DramaList;
    private List<Chain> FanficList;
    private List<Chain> FantasyList;
    private List<Chain> FairytaleList;
    private List<Chain> MagicrealismList;
    private List<Chain> ConspiracyList;
    private List<Chain> DetectiveList;
    private Chain chain;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre2);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        setupVariables();
        chain = new Chain();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        ActionList = new ArrayList<>();
        AdventureList = new ArrayList<>();
        DramaList = new ArrayList<>();
        FanficList = new ArrayList<>();
        FantasyList = new ArrayList<>();
        FairytaleList = new ArrayList<>();
        MagicrealismList = new ArrayList<>();
        ConspiracyList = new ArrayList<>();
        DetectiveList = new ArrayList<>();

        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("TAG", "onChildAdded: Key " + dataSnapshot.getKey());
                Log.d("TAG", "onChildAdded: Value " + dataSnapshot.getValue());
                dataSnapshot.getValue();
                chain = dataSnapshot.getValue(Chain.class);
                switch (chain.getChainGenre().toUpperCase()){
                    case Chain.ACTION:
                        ActionList.add(chain);
                        break;
                    case Chain.ADVENTURE:
                        AdventureList.add(chain);
                        break;
                    case Chain.DRAMA:
                        DramaList.add(chain);
                        break;
                    case Chain.FANFIC:
                        FanficList.add(chain);
                        break;
                    case Chain.FANTASY:
                        FantasyList.add(chain);
                        break;
                    case Chain.FAIRYTALE:
                        FairytaleList.add(chain);
                        break;
                    case Chain.MAGICREALISM:
                        MagicrealismList.add(chain);
                        break;
                    case Chain.CONSPIRACY:
                        ConspiracyList.add(chain);
                        break;
                    case Chain.DETECTIVE:
                        DetectiveList.add(chain);
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Genre not found please check and try again", Toast.LENGTH_LONG).show();
                        break;

                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }

        });


        imageAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.ACTION, (Serializable) ActionList);
                intent.putExtra("genre", Chain.ACTION);
                startActivity(intent);
            }
        });

        imageAdventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.ADVENTURE, (Serializable) AdventureList);
                intent.putExtra("genre", Chain.ADVENTURE);
                startActivity(intent);
            }
        });

        imageDrama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.DRAMA, (Serializable) DramaList);
                intent.putExtra("genre", Chain.DRAMA);
                startActivity(intent);
            }
        });

        imageFanfic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.FANFIC, (Serializable) FanficList);
                intent.putExtra("genre", Chain.FANFIC);
                startActivity(intent);
            }
        });

        imageFantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.FANTASY, (Serializable) FantasyList);
                intent.putExtra("genre", Chain.FANTASY);
                startActivity(intent);
            }
        });

        imageFairytale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.FAIRYTALE, (Serializable) FairytaleList);
                intent.putExtra("genre", Chain.FAIRYTALE);
                startActivity(intent);
            }
        });

        imageMagicrealism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.MAGICREALISM, (Serializable) MagicrealismList);
                intent.putExtra("genre", Chain.MAGICREALISM);
                startActivity(intent);
            }
        });

        imageConspiracy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.CONSPIRACY, (Serializable) ConspiracyList);
                intent.putExtra("genre", Chain.CONSPIRACY);
                startActivity(intent);
            }
        });

        imageDetective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.DETECTIVE, (Serializable) DetectiveList);
                intent.putExtra("genre", Chain.DETECTIVE);
                startActivity(intent);
            }
        });


        arrowleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, GenreActivity.class);
                startActivity(intent);
            }
        });

        arrowright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(Genre2Activity.this, GenreActivity.class);
                startActivity(intent);
            }
        });



    }

    public void setupVariables(){
        imageAction = findViewById(R.id.image_action);
        imageAdventure = findViewById(R.id.image_adventure);
        imageDrama = findViewById(R.id.image_drama);
        imageFanfic = findViewById(R.id.image_fanfic);
        imageFantasy = findViewById(R.id.image_fantasy);
        imageFairytale = findViewById(R.id.image_fairytale);
        imageMagicrealism = findViewById(R.id.image_magicrealism);
        imageConspiracy = findViewById(R.id.image_conspiricy);
        imageDetective = findViewById(R.id.image_detective);

        arrowleft = findViewById(R.id.arrowleftP2);
        arrowright = findViewById(R.id.arrowrightP2);

    }
}
