package com.nyinyi.nw.recyclerassignment.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.R;
import com.nyinyi.nw.recyclerassignment.adapter.TrailerMovieRecyclerAdapter;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.persistences.MovieDBContract;
import com.nyinyi.nw.recyclerassignment.utils.AppConstants;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 12/15/2017.
 */

public class MovieDetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final String IE_MOVIE_ID = "IE_MOVIE_ID";
    private static final int MOVIE_DETAIL_LOADER_ID = 100;
    

    
    @BindView(R.id.rv_view)
    RecyclerView trailerRecyclerView;
    @BindView(R.id.iv_back_image)
    ImageView ivBackImage;
    @BindView(R.id.iv_movie_poster)
    ImageView ivMoviePoster;
    @BindView(R.id.tv_vote_average)
    TextView tvVoteAverage;
    @BindView(R.id.tv_movie_description)
    TextView tvMovieDesc;
    @BindView(R.id.ratingbar)
    RatingBar ratingBar;
    @BindView(R.id.tv_release)
    TextView tvRelease;
    @BindView(R.id.tv_movie_title)
    TextView tvMovieTitle;
    @BindView(R.id.tv_movie_review)
    TextView tvMovieReview;

    TrailerMovieRecyclerAdapter adapter;
    
    private String mMovieId;

    public static Intent newIntent(Context context,String movieId)
    {
        Intent intent = new Intent(context,MovieDetailActivity.class);
        intent.putExtra(IE_MOVIE_ID,movieId);
        return intent;
        
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this,this);

        mMovieId = getIntent().getStringExtra(IE_MOVIE_ID);

        if (TextUtils.isEmpty(mMovieId))
        {
            throw new UnsupportedOperationException("movieId required for NewsDetailsActivity");
        }else {

            getSupportLoaderManager().initLoader(MOVIE_DETAIL_LOADER_ID,null,this);
        }

        adapter = new TrailerMovieRecyclerAdapter(this);
        setUpTrailerRecycler();
    }

    private void setUpTrailerRecycler() {
        trailerRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        trailerRecyclerView.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this,
                MovieDBContract.MovieEntry.CONTENT_URI,
                null,
                MovieDBContract.MovieEntry.KEY_MOVIE_ID + " = ? ",
                new String[]{mMovieId},
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (data != null && data.moveToFirst())
        {
            PopularMovieVO popularMovieVO = PopularMovieVO.parseFromCursor(getApplicationContext(),data);

            bindData(popularMovieVO);
        }

    }

    String movieReview;

    @SuppressLint("SetTextI18n")
    private void bindData(PopularMovieVO popularMovieVO) {

        movieReview = "Reviews \n" + popularMovieVO.getOverview();

        SpannableString txtSpannable = new SpannableString(movieReview);
        txtSpannable.setSpan(new UnderlineSpan(),0,7,0);
        txtSpannable.setSpan(new StyleSpan(Typeface.BOLD),0,7,0);
        tvMovieReview.setText(txtSpannable);

        tvMovieTitle.setText(popularMovieVO.getOriginalTitle());
        tvRelease.setText("Released : " + popularMovieVO.getReleaseDate());

        Double cal = popularMovieVO.getPopularity() / 100;
        ratingBar.setRating(Double.valueOf(cal).floatValue());
        tvMovieDesc.setText(popularMovieVO.getOverview());
        tvVoteAverage.setText(popularMovieVO.getVoteAverage()+"");

        Glide.with(this)
                .load(AppConstants.MOVIE_IMAGE_PATH + popularMovieVO.getPosterPath())
                .placeholder(R.drawable.movie_poster_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivMoviePoster);

        Glide.with(this)
                .load(AppConstants.MOVIE_IMAGE_PATH + popularMovieVO.getBackdropPath())
                .placeholder(R.drawable.movie_poster_place_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(ivBackImage);



    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
