package com.example.writerchainapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.writerchainapp.Constructors.Chapters;
import com.example.writerchainapp.R;

import java.util.ArrayList;
import java.util.List;


public class ChapterAdapter extends RecyclerView.Adapter<ChapterAdapter.ViewHolder> {

    private OnChapterslistener mListener;
    private ArrayList<Chapters> chaptersList;

    LayoutInflater layoutInflater;


    // data is passed into the constructor
    public ChapterAdapter(Context context, ArrayList<Chapters> chaptersList, OnChapterslistener listener) {
        this.layoutInflater = LayoutInflater.from(context);
        this.chaptersList = chaptersList;
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
        Chapters chapters = chaptersList.get(position);
        holder.textChainTitle.setText(chapters.getChapterName());
        holder.textChainAuthor.setText(chapters.getChapterAuthor());
        holder.textChainDate.setText(chapters.getDateCreated());

    }

    // total number of rows
    @Override
    public int getItemCount() {
        return chaptersList.size();
    }


    public void filterList( ArrayList<Chapters> chapterList){
        this.chaptersList = chapterList;
        notifyDataSetChanged();
    }



    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        TextView textChainTitle;
        TextView textChainAuthor;
        TextView textChainDate;
        TextView textGenre;
        OnChapterslistener mChapterslistener;


        ViewHolder(View itemView,  OnChapterslistener chapterlistener) {
            super(itemView);
            textChainTitle = itemView.findViewById(R.id.chain_title);
            textChainAuthor = itemView.findViewById(R.id.chain_author);
            textChainDate = itemView.findViewById(R.id.chain_datecreated);
            textGenre = itemView.findViewById(R.id.textView3);
            textGenre.setVisibility(itemView.GONE);
            this.mChapterslistener = chapterlistener;
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            mChapterslistener.onChaptersClick(getAdapterPosition());
        }
    }

    public interface OnChapterslistener{
        void onChaptersClick(int position);
    }
}
