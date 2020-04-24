package com.example.writerchainapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.R;


import java.util.ArrayList;
import java.util.List;



public class ChainAdapter extends RecyclerView.Adapter<ChainAdapter.ViewHolder> {

    private OnChainlistener mListener;
    private List<Chain> chainsList;

    LayoutInflater layoutInflater;


    // data is passed into the constructor
    public ChainAdapter(Context context, List<Chain> chainsList, OnChainlistener listener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.chainsList = chainsList;
        this.mListener = listener;
    }


    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chains_recyclerview_rows, parent, false);

        return new ViewHolder(itemView, mListener);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Chain chain = chainsList.get(position);
        holder.textChainTitle.setText(chain.getChainName());
        holder.textChainAuthor.setText(chain.getChainAuthor());
        holder.textChainDesc.setText(chain.getChainDescription());
        holder.textChainDate.setText(chain.getDateCreated());
        holder.textChainGenre.setText(chain.getChainGenre());
//        holder.text_ChainChapterCount.setText(chain.getChapterCount());
//        holder.text_ChainUpvotes.setText(chain.getChainUpvotes());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return chainsList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textChainTitle;
        TextView textChainDesc;
        TextView textChainAuthor;
        TextView textChainDate;
        TextView textChainGenre;
        TextView textChainChapterCount;
        TextView textChainUpvotes;
        OnChainlistener mChainlistener;


        ViewHolder(View itemView, OnChainlistener chainlistener) {
            super(itemView);
            textChainTitle = itemView.findViewById(R.id.chain_title);
            textChainAuthor = itemView.findViewById(R.id.chain_author);
            textChainDesc = itemView.findViewById(R.id.chain_desc);
            textChainDate = itemView.findViewById(R.id.chain_datecreated);
            textChainGenre = itemView.findViewById(R.id.chain_genre);
//            text_ChainChapterCount = itemView.findViewById(R.id.chain_chaptercount);
//            text_ChainUpvotes = itemView.findViewById(R.id.chain_upvotes);
            this.mChainlistener = chainlistener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mChainlistener.onChainClick(getAdapterPosition());
        }
    }

    public interface OnChainlistener{
        void onChainClick(int position);
    }

    public void filterList( ArrayList<Chain> chainsList){
        this.chainsList = chainsList;
        notifyDataSetChanged();
    }
}
