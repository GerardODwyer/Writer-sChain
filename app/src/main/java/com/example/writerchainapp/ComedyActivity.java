package com.example.writerchainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writerchainapp.Adapters.ChainAdapter;
import com.example.writerchainapp.Adapters.ComdeyAdapter;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.data.model.Chapters;
import com.example.writerchainapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ComedyActivity extends AppCompatActivity implements ChainAdapter.ItemClickListener {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> chainsList;
    private List<Chapters> chapters = new ArrayList<>();
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private FloatingActionButton fab;
    private ComdeyAdapter chainAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedy);
        fab = findViewById(R.id.floatingActionButton);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        chain = new Chain();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        chainsList = new ArrayList<>();
        //chainsList.clear();
        recyclerView = findViewById(R.id.recycler_view);
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                chain = dataSnapshot.getValue(Chain.class);
                chainsList.add(chain);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));




                chainAdapter = new ComdeyAdapter(getApplicationContext(), chainsList);
               // chainAdapter.setClickListener(ChainAdapter.ItemClickListener.this);
                recyclerView.setAdapter(chainAdapter);
                chainAdapter.notifyDataSetChanged();


//                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                recyclerView.setLayoutManager(mLayoutManager);
//                recyclerView.setItemAnimator(new DefaultItemAnimator());
//                recyclerView.setAdapter(chainAdapter);


//                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                        mLayoutManager.getOrientation());
//                recyclerView.addItemDecoration(dividerItemDecoration);



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





        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getApplicationContext());


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    //@Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + chainAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    public void createDialog(Context context){
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        final EditText title = dialogView.findViewById(R.id.edt_title);
        final EditText author = dialogView.findViewById(R.id.edt_author);
        final EditText desc = dialogView.findViewById(R.id.edt_desc);
        final EditText dateCreated = dialogView.findViewById(R.id.edt_date_created);
        final EditText genre = dialogView.findViewById(R.id.edt_genre);
        Button button1 = dialogView.findViewById(R.id.buttonSubmit);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String titleName = title.getText().toString();
               String authorName = author.getText().toString();
               String description = desc.getText().toString();
               String date = dateCreated.getText().toString();
               String genreName = genre.getText().toString();

               chain.setChainName(titleName);
               chain.setChainAuthor(authorName);
               chain.setChainDescription(description);
               chain.setDateCreated(date);
               chain.setChainGenre(genreName);
               Utils.saveDataToFirebase(chain, dbReference);
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }



    public List<Chain> loadChains() {
        chain = new Chain();
        return chainsList;
    }



}
