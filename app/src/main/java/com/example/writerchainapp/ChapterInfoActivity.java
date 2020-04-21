package com.example.writerchainapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.writerchainapp.Adapters.ChapterAdapter;
import com.example.writerchainapp.Adapters.RecyclerTouchListener;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Constructors.Chapters;
import com.example.writerchainapp.utils.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;


public class ChapterInfoActivity extends AppCompatActivity implements ChapterAdapter.OnChapterslistener {

    private int chapterIncrease = 1;
    private int chapterNo;
    private String chapterSearch;
    private List<Chapters> chapterList = new ArrayList<>();
    private FloatingActionButton fab;
    private TextView textChainTitle;
    private TextView textChainDesc;
    private TextView textChainAuthor;
    private TextView textChainDate;
    private TextView textChainGenre;
    private TextView textChapterNumber;
    private FirebaseDatabase database;
    private DatabaseReference dbReference;
    private DatabaseReference chapterReference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private Chapters chapters;
    private String position;

    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_info);
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        chapters = new Chapters();
        chapterNo = getIntent().getIntExtra("chapterNumber", 0);
        chapterNo = chapterNo + chapterIncrease;
        chapterSearch = String.valueOf(chapterNo);

        final String chainId = getIntent().getStringExtra("chainID");
        final String chapterGenre = getIntent().getStringExtra("chapterGenre");

        recyclerView = findViewById(R.id.recycler_view);

        fab = findViewById(R.id.floatingActionButton2);
        position = getIntent().getStringExtra("position");

        dbReference = database.getReference().child(user.getUid()).child("Chain").child(chainId);

        if (chapterNo == 1) {
            chapterList.clear();
            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    chain = dataSnapshot.getValue(Chain.class);
                    textChainTitle.setText(chain.getChainName());
                    textChainAuthor.setText(chain.getChainAuthor());
                    textChainDesc.setText(chain.getChainDescription());
                    textChainDate.setText(chain.getDateCreated());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            chapterList.clear();
            int newSearch = chapterNo - 1;
            String newString = String.valueOf(newSearch);
            dbReference = dbReference.child("chapters").child(newString).child(position);
            dbReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Chapters newChapter = dataSnapshot.getValue(Chapters.class);
                    textChainTitle.setText(newChapter.getChapterName());
                    textChainAuthor.setText(newChapter.getChapterAuthor());
                    textChainDesc.setText(newChapter.getChapterDescription());
                    textChainDate.setText(newChapter.getDateCreated());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            dbReference = database.getReference().child(user.getUid()).child("Chain").child(chainId);

        }

        chapterReference = database.getReference().child(user.getUid()).child("Chain").child(chainId).child("chapters").child(chapterSearch);

        textChainTitle = findViewById(R.id.chain_title);
        textChainAuthor = findViewById(R.id.chain_author);
        textChainDesc = findViewById(R.id.chain_desc);
        textChainDate = findViewById(R.id.chain_datecreated);
        textChainGenre = findViewById(R.id.chain_genre);
        textChapterNumber = findViewById(R.id.ChapterNumber);

        textChapterNumber.setText("Chapter " + chapterSearch);

        chapterList.clear();

        chapterReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chapters newChapter = snapshot.getValue(Chapters.class);
                    chapterList.add(newChapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(getApplicationContext(), chapterGenre);
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                chapters = chapterList.get(position);
                String pos = String.valueOf(position);
                Intent intent = new Intent(ChapterInfoActivity.this, ChapterInfoActivity.class);
                intent.putExtra("chapterNumber", chapterNo);
                intent.putExtra("chainID", chainId);
                intent.putExtra("position", pos);
                startActivity(intent);
            }

            public void onLongClick(View view, int position) {
                createPopUp(getApplicationContext(), position);
            }
        }));

    }

    @Override
    protected void onResume() {
        super.onResume();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chapterAdapter = new ChapterAdapter(getApplicationContext(), chapterList, this);
        recyclerView.setAdapter(chapterAdapter);
        chapterAdapter.notifyDataSetChanged();

    }

    public void createPopUp(final Context context, int position) {
        chapters = chapterList.get(position);
        String pos = String.valueOf(position);

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.delete_dialog, null);

        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(), "Tsting", Toast.LENGTH_SHORT).show();

                chapterAdapter.notifyDataSetChanged();
                dialogBuilder.dismiss();

            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    public void createDialog(Context context, final String chapterGenre) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.chapter_dialog_layout, null);

        final EditText title = dialogView.findViewById(R.id.edt_title);
        final EditText author = dialogView.findViewById(R.id.edt_author);
        final EditText desc = dialogView.findViewById(R.id.edt_desc);
        final TextView dateCreated = dialogView.findViewById(R.id.edt_date_created);
        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        String date_today = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        dateCreated.setText(date_today);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleName = title.getText().toString();
                String authorName = author.getText().toString();
                String description = desc.getText().toString();
                String date = dateCreated.getText().toString();
                String genreName = chapterGenre;

                String uuid = UUID.randomUUID().toString();
                chapters.setChapterID(uuid);
                chapters.setChapterAuthor(authorName);
                chapters.setChapterName(titleName);
                chapters.setChapterDescription(description);
                chapters.setDateCreated(date);
                chapters.setChapterGenre(genreName);
                chapterList.add(chapters);
                Utils.saveChaptersToFirebase(chapterList, dbReference, chapterSearch);

                //onBackPressed();
                chapterAdapter.notifyDataSetChanged();
                dialogBuilder.dismiss();

            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @Override
    public void onChaptersClick(int position) {

    }
}
