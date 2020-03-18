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
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.data.model.Chapters;
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

public class ComedyActivity extends AppCompatActivity implements ChainAdapter.ItemClickListener {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> chainsList;
    private List<Chapters> chapters = new ArrayList<>();
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private FloatingActionButton fab;
    private ChainAdapter chainAdapter;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comedy);
        fab = findViewById(R.id.floatingActionButton);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        chainsList = new ArrayList<>();
        //chainsList.clear();
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        chainsList = loadChains();


        chainAdapter = new ChainAdapter(this, chainsList);
        chainAdapter.setClickListener(this);
        recyclerView.setAdapter(chainAdapter);
        chainAdapter.notifyDataSetChanged();




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getApplicationContext());


            }
        });

    }

    //@Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + chainAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }

    public void createDialog(Context context){
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        final EditText editText = dialogView.findViewById(R.id.edt_comment);
        Button button1 = dialogView.findViewById(R.id.buttonSubmit);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String Title = editText.getText().toString();
               chain.setChainName(Title);
               saveDataToFirebase(chain);
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void saveDataToFirebase(Chain chain){
        DatabaseReference newDb = dbReference.push();
        Map<String, String> saveData = new HashMap<>();
        saveData.put("chainId", chain.getChainID());
        saveData.put("chainAuthor", chain.getChainAuthor());
        saveData.put("chainDesc", chain.getChainDescription());
        saveData.put("chainGenre", chain.getChainGenre());
        saveData.put("chainName", chain.getChainName());
        saveData.put("chainUpvote", chain.getChainUpvotes());
        saveData.put("chainChapterCount", String.valueOf(chain.getChapterCount()));
        newDb.getRef().child("").setValue(saveData);
    }

    private List<Chain> loadChains() {
        chain = new Chain();
        dbReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                chain = dataSnapshot.getValue(Chain.class);
                chainsList.add(chain);

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
        return chainsList;
    }



}
