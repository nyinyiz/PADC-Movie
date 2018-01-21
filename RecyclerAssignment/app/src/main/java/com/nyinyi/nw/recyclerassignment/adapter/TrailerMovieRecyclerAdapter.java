package com.nyinyi.nw.recyclerassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.recyclerassignment.R;
import com.nyinyi.nw.recyclerassignment.viewholder.TrailerViewHolder;

/**
 * Created by User on 12/15/2017.
 */

public class TrailerMovieRecyclerAdapter extends RecyclerView.Adapter<TrailerViewHolder> {

    Context context;
    LayoutInflater inflater;

    public TrailerMovieRecyclerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_movie_trailer, parent, false);
        TrailerViewHolder vh = new TrailerViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
