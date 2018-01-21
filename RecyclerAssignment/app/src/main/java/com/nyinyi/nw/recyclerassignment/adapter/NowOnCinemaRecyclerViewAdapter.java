package com.nyinyi.nw.recyclerassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.recyclerassignment.R;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.delegates.PopularMovieDelegate;
import com.nyinyi.nw.recyclerassignment.viewholder.MovieViewHolder;

/**
 * Created by User on 11/7/2017.
 */

public class NowOnCinemaRecyclerViewAdapter extends BaseRecyclerAdapter<MovieViewHolder, PopularMovieVO> {

    Context context;
    LayoutInflater inflater;

    public PopularMovieDelegate mPopularMovieDelegate;


    public NowOnCinemaRecyclerViewAdapter(Context context, PopularMovieDelegate popularMovieDelegate) {
        super(context);
        this.context = context;
        inflater = LayoutInflater.from(context);
        mPopularMovieDelegate = popularMovieDelegate;


    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_list_movie_card, parent, false);
        MovieViewHolder vh = new MovieViewHolder(view, mPopularMovieDelegate);
        return vh;
    }
}
