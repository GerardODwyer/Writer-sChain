package com.example.writerchainapp.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.writerchainapp.Adapters.ChainAdapter;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.R;
import com.example.writerchainapp.data.model.Chapters;
import com.example.writerchainapp.Adapters.ChainAdapter.OnChainlistener;
import com.example.writerchainapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;
import java.util.List;

public class MedevilActivity extends AppCompatActivity implements OnChainlistener {

    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private List<Chain> medevilList;
    private List<Chapters> chapters = new ArrayList<>();
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private FloatingActionButton fab;
    private ChainAdapter chainAdapter;
    private RecyclerView recyclerView;
    private OnChainlistener recyclerClickListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_love);
        fab = findViewById(R.id.floatingActionButton);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        chain = new Chain();
        dbReference = database.getReference().child(user.getUid()).child("Chain");
        recyclerView = findViewById(R.id.recycler_view);
        medevilList = new ArrayList<>();
        medevilList = (List<Chain>) getIntent().getExtras().getSerializable(Chain.MEDEVIL);

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
        chainAdapter = new ChainAdapter(getApplicationContext(), medevilList, this);
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
                String genreName = "Medevil";

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
        Toast.makeText(getApplicationContext(), "Position is " + medevilList.get(position).getChainID(), Toast.LENGTH_SHORT).show();
    }
}
