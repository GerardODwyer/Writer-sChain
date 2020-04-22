package com.example.writerchainapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

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
    private ImageView arrowleft;
    private ImageView arrowright;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private List<Chain> comdeyList;
    private List<Chain> horrorList;
    private List<Chain> loveList;
    private List<Chain> scfiList;
    private List<Chain> medevilList;
    private List<Chain> crimeList;
    private List<Chain> mysteryList;
    private List<Chain> tradgeyList;
    private List<Chain> westrenList;
    private Chain chain;

    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_genre1);


        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        setupVariables();
        chain = new Chain();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        comdeyList = new ArrayList<>();
        horrorList = new ArrayList<>();
        loveList = new ArrayList<>();
        scfiList = new ArrayList<>();
        medevilList = new ArrayList<>();
        crimeList = new ArrayList<>();
        mysteryList = new ArrayList<>();
        tradgeyList = new ArrayList<>();
        westrenList = new ArrayList<>();

        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("TAG", "onChildAdded: Key " + dataSnapshot.getKey());
                Log.d("TAG", "onChildAdded: Value " + dataSnapshot.getValue());
                dataSnapshot.getValue();
                chain = dataSnapshot.getValue(Chain.class);
                switch (chain.getChainGenre().toUpperCase()){
                    case Chain.COMDEY:
                        comdeyList.add(chain);
                        break;
                    case Chain.HORROR:
                        horrorList.add(chain);
                        break;
                    case Chain.LOVE:
                        loveList.add(chain);
                        break;
                    case Chain.SCIFI:
                        scfiList.add(chain);
                    case Chain.MEDEVIL:
                        medevilList.add(chain);
                        break;
                    case Chain.CRIME:
                        crimeList.add(chain);
                        break;
                    case Chain.MYSTERY:
                        mysteryList.add(chain);
                        break;
                    case Chain.TRADGEY:
                        tradgeyList.add(chain);
                        break;
                    case Chain.WESTREN:
                        westrenList.add(chain);
                        break;
                    default:
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


        imageComdey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.COMDEY, (Serializable) comdeyList);
                intent.putExtra("genre", Chain.COMDEY);
                startActivity(intent);
            }
        });

        imageHorror.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.HORROR, (Serializable) horrorList);
                intent.putExtra("genre", Chain.HORROR);
                startActivity(intent);
            }
        });

        imageLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.LOVE, (Serializable) loveList);
                intent.putExtra("genre", Chain.LOVE);
                startActivity(intent);
            }
        });

        imageScifi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.SCIFI, (Serializable) scfiList);
                intent.putExtra("genre", Chain.SCIFI);
                startActivity(intent);
            }
        });

        imageMedevil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.MEDEVIL, (Serializable) medevilList);
                intent.putExtra("genre", Chain.MEDEVIL);
                startActivity(intent);
            }
        });

        imageCrime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.CRIME, (Serializable) crimeList);
                intent.putExtra("genre", Chain.CRIME);
                startActivity(intent);
            }
        });

        imageMystery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.MYSTERY, (Serializable) mysteryList);
                intent.putExtra("genre", Chain.MYSTERY);
                startActivity(intent);
            }
        });

        imageTradgey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.TRADGEY, (Serializable) tradgeyList);
                intent.putExtra("genre", Chain.TRADGEY);
                startActivity(intent);
            }
        });

        imageWestren.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
                intent.putExtra(Chain.WESTREN, (Serializable) westrenList);
                intent.putExtra("genre", Chain.WESTREN);
                startActivity(intent);
            }
        });






        arrowleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, Genre2Activity.class);
                startActivity(intent);
            }
        });

        arrowright.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(GenreActivity.this, Genre2Activity.class);
                startActivity(intent);
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

        arrowright = findViewById(R.id.arrowrightP1);
        arrowleft = findViewById(R.id.arrowleftP1);
    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
//        if(intent.getStringExtra("refreshChains").equals("refreshChains")){
//            intent = new Intent(GenreActivity.this, SingleGenreActivity.class);
//            startActivity(intent);
//        }
//    }

}

