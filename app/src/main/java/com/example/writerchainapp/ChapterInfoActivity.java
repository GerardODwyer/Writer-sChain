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
import android.text.Editable;
import android.text.TextWatcher;
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
import com.example.writerchainapp.Constructors.Users;
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
import java.util.Objects;
import java.util.UUID;


public class ChapterInfoActivity extends AppCompatActivity implements ChapterAdapter.OnChapterslistener {

    private int chapterIncrease = 1;
    private int chapterNo;
    private String chapterSearch;
    private ArrayList<Chapters> chapterList = new ArrayList<>();
    private ArrayList<Chapters> chapterListFull = new ArrayList<>();
    private FloatingActionButton fab;
    private TextView textChainTitle;
    private TextView chapterTitleNumber;
    private TextView textChainDesc;
    private TextView textChainAuthor;
    private TextView textChainDate;
    private TextView textChainGenre;
    private TextView textChapterNumber;
    private FirebaseDatabase database;
    private DatabaseReference chainReference;
    private DatabaseReference usersReference;
    private DatabaseReference chapterReference;
    private FirebaseUser user;
    private FirebaseAuth auth;
    private Chain chain;
    private String chapterID;
//    private Chapters newChapter;
    private EditText search;
    private Users users;

    private String chapterFKID;
//    private String descTest;
    private RecyclerView recyclerView;
    private ChapterAdapter chapterAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_info);
        recyclerView = findViewById(R.id.recycler_view);

        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        search = findViewById(R.id.search_chapters);
        chapterTitleNumber = findViewById(R.id.chapterTitleMain);

        //chapterList.clear();

//        chapters = new Chapters();
        chapterNo = getIntent().getIntExtra("chapterNumber", 0);
        chapterNo = chapterNo + chapterIncrease;
        chapterSearch = String.valueOf(chapterNo);
        search.setHint("Search Chapter " + chapterSearch);

        final String chainId = getIntent().getStringExtra("chainID");
        final String chapterGenre = getIntent().getStringExtra("chapterGenre");

        fab = findViewById(R.id.floatingActionButton2);
        chapterID = getIntent().getStringExtra("chapterID");

        chainReference = database.getReference().child("Chains").child(chainId);
        chapterReference = database.getReference().child("Chains").child(chainId).child("chapters").child(chapterSearch);
        usersReference = database.getReference().child("Users").child(user.getUid());

        if (chapterNo == 1) {
            chapterTitleNumber.setVisibility(View.GONE);
            chainReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    try {
                        chain = dataSnapshot.getValue(Chain.class);
                        textChainTitle.setText(chain.getChainName());
                        textChainAuthor.setText(chain.getChainAuthor());
                        textChainDesc.setText(chain.getChainDescription());
                        textChainDate.setText(chain.getDateCreated());
                    } catch (Exception ignored) {
                        Toast.makeText(ChapterInfoActivity.this, "Deleting...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            chapterTitleNumber.setVisibility(View.VISIBLE);
            int newChapterNumber = Integer.parseInt(chapterSearch);
            newChapterNumber = newChapterNumber - 1;
            chapterTitleNumber.setText("Reading Chapter " + newChapterNumber);
            String newSearch = String.valueOf(newChapterNumber);
            chainReference = database.getReference().child("Chains").child(chainId).child("chapters").child(newSearch);
            chainReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Chapters newChapter = snapshot.getValue(Chapters.class);
                        if (newChapter.getChapterFKID().equals(chapterID)) {
                            try {
                                chapterFKID = newChapter.getChapterFKID();
//                                descTest = newChapter.getChapterDescription();
                                textChainTitle.setText(newChapter.getChapterName());
                                textChainAuthor.setText(newChapter.getChapterAuthor());
                                textChainDesc.setText(newChapter.getChapterDescription());
                                textChainDate.setText(newChapter.getDateCreated());
                            } catch (Exception ignored) {
                                Toast.makeText(ChapterInfoActivity.this, "Deleting...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            chainReference = database.getReference().child("Chains").child(chainId);

        }

        textChainTitle = findViewById(R.id.chain_title);
        textChainAuthor = findViewById(R.id.chain_author);
        textChainDesc = findViewById(R.id.chain_desc);
        textChainDate = findViewById(R.id.chain_datecreated);
        textChainGenre = findViewById(R.id.chain_genre);
        textChapterNumber = findViewById(R.id.ChapterNumber);

        textChapterNumber.setText("Chapter " + chapterSearch);

        chapterReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chapterList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Chapters newChapter = snapshot.getValue(Chapters.class);
                    if (chapterNo == 1) {
                        chapterList.add(newChapter);
                    } else {
                        if (newChapter.getChapterID().equals(chapterFKID)) {
                            chapterList.add(newChapter);
                        }
                    }
                    chapterListFull.add(newChapter);
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
                Chapters chapters = chapterList.get(position);
//                Toast.makeText(ChapterInfoActivity.this, chapters.getChapterID(), Toast.LENGTH_SHORT).show();
//                String pos = String.valueOf(position);
                Intent intent = new Intent(ChapterInfoActivity.this, ChapterInfoActivity.class);
                intent.putExtra("chapterNumber", chapterNo);
                intent.putExtra("chainID", chainId);
                intent.putExtra("chapterID", chapters.getChapterFKID());
                startActivity(intent);
            }

            public void onLongClick(View view, int position) {
                Chapters chapters = chapterList.get(position);
                if (users.isUserAdmin()) {
                    createAdminPopUp(getApplicationContext(), position);
                } else if (users.getUserID().equals(chapters.getChapterAuthorID())) {
                    createEditPopUp(getApplicationContext(), position);
                }
            }
        }));

        usersReference.addValueEventListener(new ValueEventListener() {

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
    protected void onResume() {
        super.onResume();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        chapterAdapter = new ChapterAdapter(getApplicationContext(), chapterList, this);
        recyclerView.setAdapter(chapterAdapter);
        chapterAdapter.notifyDataSetChanged();


        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filterList(s.toString());

            }
        });

    }

    public void createAdminPopUp(final Context context, final int position) {
        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.delete_dialog, null);

        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chapterList.remove(position);
                Utils.saveChaptersToFirebase(chapterList, chainReference, chapterSearch);
                Utils.decreaseUserChapterInfo(usersReference, users);
                chapterAdapter.notifyDataSetChanged();
                finish();
                startActivity(getIntent());
                dialogBuilder.dismiss();
            }
        });

        dialogBuilder.setView(dialogView);
        dialogBuilder.show();
    }

    @SuppressLint("SetTextI18n")
    public void createEditPopUp(final Context context, final int position) {
        final Chapters chapters = chapterList.get(position);

        final AlertDialog dialogBuilder = new AlertDialog.Builder(this).create();
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.chapter_dialog_layout, null);

        final EditText title = dialogView.findViewById(R.id.edt_title);
        final TextView author = dialogView.findViewById(R.id.edt_author);
        final TextView boxTitle = dialogView.findViewById(R.id.dialogTitle);
        final EditText desc = dialogView.findViewById(R.id.edt_desc);
        final TextView dateCreated = dialogView.findViewById(R.id.edt_date_created);
        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        String date_today = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        dateCreated.setText(date_today);
        author.setText(users.getUserDisplayName());
        button1.setText("Edit Chapter");
        boxTitle.setText("Edit The Chapter In The Box Below!");
        title.setText(chapters.getChapterName());
        desc.setText(chapters.getChapterDescription());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleName = title.getText().toString();
                String description = desc.getText().toString();
                String date = dateCreated.getText().toString();
                String genreName = chapters.getChapterGenre();
                chapters.setChapterID(chapters.getChapterID());
                chapters.setChapterAuthor(users.getUserDisplayName());
                chapters.setChapterAuthorID(users.getUserID());
                chapters.setChapterName(titleName);
                chapters.setChapterDescription(description);
                chapters.setDateCreated(date);
                chapters.setChapterGenre(genreName);
                chainReference.child("chapters").child(chapterSearch).child(String.valueOf(position)).setValue(chapters);
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
        final Chapters chapters = new Chapters();
        final EditText title = dialogView.findViewById(R.id.edt_title);
        final TextView author = dialogView.findViewById(R.id.edt_author);
        final EditText desc = dialogView.findViewById(R.id.edt_desc);
        final TextView dateCreated = dialogView.findViewById(R.id.edt_date_created);
        Button button1 = dialogView.findViewById(R.id.buttonDelete);

        String date_today = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault()).format(new Date());

        dateCreated.setText(date_today);
        author.setText(users.getUserDisplayName());

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleName = title.getText().toString();
                String description = desc.getText().toString();
                String date = dateCreated.getText().toString();
                String genreName = chapterGenre;
                String uuid;
                if (chapterNo == 1) {
                    uuid = UUID.randomUUID().toString();
                } else {
                    uuid = chapterFKID;
                }
                String FKID = UUID.randomUUID().toString();
                chapters.setChapterID(uuid);
                chapters.setChapterFKID(FKID);
                chapters.setChapterAuthor(users.getUserDisplayName());
                chapters.setChapterAuthorID(users.getUserID());
                chapters.setChapterName(titleName);
                chapters.setChapterDescription(description);
                chapters.setDateCreated(date);
                chapters.setChapterGenre(genreName);
                chapterListFull.add(chapters);
                Utils.saveChaptersToFirebase(chapterListFull, chainReference, chapterSearch);
                Utils.increaseUserChapterInfo(usersReference, users);
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

    public void filterList(String text) {
        ArrayList<Chapters> chapters = new ArrayList<>();
        for (Chapters chapter : chapterList) {
            if (chapter.getChapterName().toLowerCase().contains(text.toLowerCase())) {
                chapters.add(chapter);
                chapterAdapter.filterList(chapters);
            }
        }
    }
}
