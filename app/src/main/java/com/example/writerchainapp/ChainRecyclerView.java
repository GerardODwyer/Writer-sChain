
package com.example.writerchainapp;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.writerchainapp.Adapters.ChainAdapter;
import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.Adapters.ChainAdapter.OnChainlistener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ChainRecyclerView extends AppCompatActivity implements OnChainlistener {

    private List<Chain> chainsList = new ArrayList<>();
    ChainAdapter chainAdapter;
    Chain chain;

    private RecyclerView recyclerView;

    private FirebaseDatabase firebaseDb;
    private DatabaseReference databaseRef;
    private OnChainlistener recyclerClickListener;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chain_recycler_view);

        firebaseDb = FirebaseDatabase.getInstance();
        databaseRef = firebaseDb.getReference("Saved/");


        chainsList.clear();



        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        chainAdapter = new ChainAdapter(this, chainsList, this);
        recyclerView.setAdapter(chainAdapter);


        loadChains();

    }

    @Override
    public void onChainClick(final int position) {
        Toast.makeText(getApplicationContext(), "Position is " + chainsList.get(position), Toast.LENGTH_SHORT).show();
    }


    private List<Chain> loadChains() {
        databaseRef.child("Chains").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                chain = dataSnapshot.getValue(Chain.class);
                chainsList.add(chain);

                //chainAdapter = new ChainAdapter(chainsList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(chainAdapter);


//                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
//                        mLayoutManager.getOrientation());
//                recyclerView.addItemDecoration(dividerItemDecoration);


                chainAdapter.notifyDataSetChanged();
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
