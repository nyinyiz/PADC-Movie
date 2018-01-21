package com.nyinyi.nw.recyclerassignment.mvp.presenters;

import android.content.Context;
import android.database.Cursor;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.data.models.MovieModel;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.delegates.PopularMovieDelegate;
import com.nyinyi.nw.recyclerassignment.mvp.views.PopularMovieView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by user on 1/7/18.
 */

public class PopularMoviePresenter extends BasePresenter<PopularMovieView> implements PopularMovieDelegate {

//    @Inject
    MovieModel mMovieModel;

    public PopularMoviePresenter() {

    }

    @Override
    public void onCreate(PopularMovieView view) {
        super.onCreate(view);
        /*MovieApp movieApp = (MovieApp) view.getContext();
        movieApp.getmAppComponent().inject(this);*/
        mMovieModel = new MovieModel();
    }

    @Override
    public void onStart() {
        List<PopularMovieVO> newsVOList = mMovieModel.getInstance().getmPopularMovie();

        if (!newsVOList.isEmpty())
        {
            mView.displayMovieList(newsVOList);

        }else {
            mView.showLoading();
        }

    }

    public void onMovieStartLoading(Context context)
    {
        mMovieModel.getInstance().startLoadingMovie(context);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onTapMovieItem(PopularMovieVO movie) {
        mView.navigateToMovieDetail(movie);

    }

    public void onDataLoaded(Context context, Cursor data) {

        if (data != null && data.moveToFirst())
        {
            List<PopularMovieVO> movieVOList = new ArrayList<>();
            do {
                PopularMovieVO popularMovieVO = PopularMovieVO.parseFromCursor(context,data);
                movieVOList.add(popularMovieVO);
            }while (data.moveToNext());

            mView.displayMovieList(movieVOList);

        }

    }
}
