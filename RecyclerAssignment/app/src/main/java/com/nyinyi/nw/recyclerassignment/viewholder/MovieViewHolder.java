package com.nyinyi.nw.recyclerassignment.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nyinyi.nw.recyclerassignment.R;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.delegates.PopularMovieDelegate;
import com.nyinyi.nw.recyclerassignment.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 11/7/2017.
 */

public class MovieViewHolder extends BaseViewHolder<PopularMovieVO> {

    @BindView(R.id.iv_movie_poster)
    ImageView moviePoster;
    @BindView(R.id.tv_movie_title)
    TextView movieTitle;
    @BindView(R.id.tv_vote_average)
    TextView voteAverage;
    @BindView(R.id.ratingbar)
    RatingBar ratingBar;

    private PopularMovieDelegate mDelegate;

    private PopularMovieVO mPopularVO;

    public MovieViewHolder(View itemView, PopularMovieDelegate popularMovieDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mDelegate = popularMovieDelegate;

    }

    @Override
    public void setmData(PopularMovieVO data) {
        mPopularVO = data;
    }

    @Override
    public void bind(Context context) {
        Glide.with(context)
                .load(AppConstants.MOVIE_IMAGE_PATH + mPopularVO.getPosterPath())
                .placeholder(R.drawable.movie_poster_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(moviePoster);
        movieTitle.setText(mPopularVO.getTitle());
        voteAverage.setText(mPopularVO.getVoteAverage() + "");
        Double cal = mPopularVO.getPopularity() / 100;
        ratingBar.setRating(Double.valueOf(cal).floatValue());

    }


    @Override
    public void onClick(View view) {
        mDelegate.onTapMovieItem(mPopularVO);
    }
}
