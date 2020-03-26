package com.example.writerchainapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.writerchainapp.Constructors.Chain;
import com.example.writerchainapp.R;

import java.util.List;

public class HorrorAdapter extends RecyclerView.Adapter<HorrorAdapter.ViewHolder> {
    List<Chain> chainsList;
    LayoutInflater layoutInflater;
    ChainAdapter.ItemClickListener mClickListener;
    private Context context;

    // data is passed into the constructor
    public HorrorAdapter(Context context, List<Chain> chainsList) {
        this.layoutInflater = LayoutInflater.from(context);
        this.chainsList = chainsList;
    }

    // inflates the row layout from xml when needed
    @Override
    public HorrorAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.chainsrecyclerview_rows, parent, false);

        return new ViewHolder(context, itemView);
    }


    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(HorrorAdapter.ViewHolder holder, int position) {
        Chain chain = chainsList.get(position);
        //holder.text_ChainID.setText(chain.getChainID());
        holder.text_ChainTitle.setText(chain.getChainName());
//        holder.text_ChainAuthor.setText(chain.getChainAuthor());
//        holder.text_ChainDesc.setText(chain.getChainDescription());
//        holder.text_ChainDate.setText(chain.getDateCreated());
//        holder.text_ChainGenre.setText(chain.getChainGenre());
        //holder.text_ChainChapterCount.setText(chain.getChapterCount());
        //holder.text_ChainUpvotes.setText(chain.getChainUpvotes());


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return chainsList.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView text_ChainID;
        TextView text_ChainTitle;
        TextView text_ChainDesc;
        TextView text_ChainAuthor;
        TextView text_ChainDate;
        TextView text_ChainGenre;
        TextView text_ChainChapterCount;
        TextView text_ChainUpvotes;


        ViewHolder(Context ctx,View itemView) {
            super(itemView);
            context = ctx;
//            text_ChainID = itemView.findViewById(R.id.chainID);
            text_ChainTitle = itemView.findViewById(R.id.chain_title);
//            text_ChainAuthor = itemView.findViewById(R.id.chain_author);
//            text_ChainDesc = itemView.findViewById(R.id.chain_desc);
//            text_ChainDate = itemView.findViewById(R.id.chain_datecreated);
//            text_ChainGenre = itemView.findViewById(R.id.chain_genre);
           // text_ChainChapterCount = itemView.findViewById(R.id.chain_chaptercount);
            //text_ChainUpvotes = itemView.findViewById(R.id.chain_upvotes);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    public Chain getItem(int id) {
        return chainsList.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(ChainAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
