package com.example.writerchainapp;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.writerchainapp.recyclerviewsadapter.ChainAdapter;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.data.model.Chapters;
import com.example.writerchainapp.recyclerviewsadapter.ChainAdapter.OnChainlistener;

import com.example.writerchainapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class ComedyActivity extends AppCompatActivity implements OnChainlistener {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> comdeyList;
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
        recyclerView = findViewById(R.id.recycler_view);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        chain = new Chain();
        comdeyList = new ArrayList<>();
        comdeyList = (List<Chain>) getIntent().getExtras().getSerializable(Chain.COMDEY);

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
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chainAdapter = new ChainAdapter(getApplicationContext(), comdeyList, this);
        recyclerView.setAdapter(chainAdapter);
        chainAdapter.notifyDataSetChanged();

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
                onBackPressed();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }



    @Override
    public void onChainClick(final int position) {
        Toast.makeText(getApplicationContext(), "Position is " + comdeyList.get(position).getChainID(), Toast.LENGTH_SHORT).show();
    }
}
