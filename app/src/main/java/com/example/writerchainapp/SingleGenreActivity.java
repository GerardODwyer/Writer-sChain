package com.example.writerchainapp;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.writerchainapp.Adapters.ChainAdapter;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Adapters.ChainAdapter.OnChainlistener;

import com.example.writerchainapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.writerchainapp.Adapters.RecyclerTouchListener;

public class SingleGenreActivity extends AppCompatActivity implements OnChainlistener {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> genreList;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private FloatingActionButton fab;
    private FloatingActionButton fabRefresh;
    private String genreTitle;

    private ChainAdapter chainAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain_titles_recycler);
        fab = findViewById(R.id.floatingActionButton);
        fabRefresh = findViewById(R.id.floatingActionButton2);
        recyclerView = findViewById(R.id.recycler_view);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        chain = new Chain();
        genreList = new ArrayList<>();

        genreTitle = getIntent().getStringExtra("genre");

        genreList = (List<Chain>) getIntent().getExtras().getSerializable(genreTitle);




        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getApplicationContext());
            }
        });

        fabRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                chain = genreList.get(position);
                String chainID = chain.getChainID();

                Intent intent = new Intent(SingleGenreActivity.this, ChapterInfoActivity.class);
                intent.putExtra("chainID", chainID);
                intent.putExtra("chapterNumber", 0);
                intent.putExtra("chapterGenre", genreTitle);
                startActivity(intent);
            }

            public void onLongClick(View view, int position) {
            }
        }));

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
        chainAdapter = new ChainAdapter(getApplicationContext(), genreList, this);
        recyclerView.setAdapter(chainAdapter);
        chainAdapter.notifyDataSetChanged();

    }

//    public void refreshChains() {
//
//        Intent intent = new Intent(SingleGenreActivity.this, GenreActivity.class);
//        intent.putExtra("refreshChains","refreshChains");
//        startActivity(intent);
//    }


    public void createDialog(Context context) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        final EditText title = dialogView.findViewById(R.id.edt_title);
        final EditText author = dialogView.findViewById(R.id.edt_author);
        final EditText desc = dialogView.findViewById(R.id.edt_desc);
        final TextView dateCreated = dialogView.findViewById(R.id.edt_date_created);
        final TextView genre = dialogView.findViewById(R.id.edt_genre);
        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        String date_today = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        genre.setText(genreTitle);
        dateCreated.setText(date_today);

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

                //onBackPressed();
                chainAdapter.notifyDataSetChanged();
                dialogBuilder.dismiss();

                finish();

            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }


    @Override
    public void onChainClick(final int position) {
    }

}
