package com.example.writerchainapp;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.writerchainapp.Adapters.ChainAdapter;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Adapters.ChainAdapter.OnChainlistener;

import com.example.writerchainapp.Constructors.Users;
import com.example.writerchainapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.example.writerchainapp.Adapters.RecyclerTouchListener;
import com.google.firebase.database.ValueEventListener;

public class SingleGenreActivity extends AppCompatActivity implements OnChainlistener {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private DatabaseReference userReference;
    private List<Chain> genreList;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private FloatingActionButton fab;
    private FloatingActionButton fabRefresh;
    private String genreTitle;
    private Users users;

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
        dbReference = database.getReference().child("Chains");
        userReference = database.getReference().child("Users").child(user.getUid());
        chain = new Chain();
        genreList = new ArrayList<>();

        genreTitle = getIntent().getStringExtra("genre");

        genreList = (List<Chain>) getIntent().getExtras().getSerializable(genreTitle);

        if (genreList != null) {
            Collections.sort(genreList);
            Collections.reverse(genreList);
        }

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
//                refreshChains();
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


        userReference.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users = dataSnapshot.getValue(Users.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
        chainAdapter = new ChainAdapter(getApplicationContext(), genreList, this);
        recyclerView.setAdapter(chainAdapter);
        chainAdapter.notifyDataSetChanged();

    }

//    public void refreshChains() {
//        finish();
//        Intent intent = new Intent(getApplicationContext(), SingleGenreActivity.class);
//        intent.putExtra(genreTitle, (Serializable) genreList);
//        intent.putExtra("genre", genreTitle);
//        startActivity(intent);
//    }


    public void createDialog(Context context) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_layout, null);

        final EditText title = dialogView.findViewById(R.id.edt_title);
        final TextView author = dialogView.findViewById(R.id.edt_author);
        final EditText desc = dialogView.findViewById(R.id.edt_desc);
        final TextView dateCreated = dialogView.findViewById(R.id.edt_date_created);
        final TextView genre = dialogView.findViewById(R.id.edt_genre);
        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        String date_today = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        genre.setText(genreTitle);
        dateCreated.setText(date_today);
        author.setText(users.getUserDisplayName());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleName = title.getText().toString();
                String description = desc.getText().toString();
                String date = dateCreated.getText().toString();
                String genreName = genre.getText().toString();

                chain.setChainName(titleName);
                chain.setChainAuthor(users.getUserDisplayName());
                chain.setChainAuthorID(users.getUserID());
                chain.setChainDescription(description);
                chain.setDateCreated(date);
                chain.setChainGenre(genreName);
                Utils.saveDataToFirebase(chain, dbReference);
                Utils.increaseUserChainInfo(userReference, users);
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

    public void  filterList(String text){
        ArrayList<Chain> genres = new ArrayList<>();
        for (Chain genre : genreList){
            if (genre.getChainName().toLowerCase().contains(text.toLowerCase())){
                genres.add(genre);
                chainAdapter.filterList(genres);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_genre, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setMaxWidth(android.R.attr.width);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // TODO
                return true;
            }

            @Override
            public boolean onQueryTextChange(final String query) {
                filterList(query);
                return true;
            }
        });

        return true;
    }
}
