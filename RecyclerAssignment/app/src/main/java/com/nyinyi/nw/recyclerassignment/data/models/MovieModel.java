package com.nyinyi.nw.recyclerassignment.data.models;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.dagger.AppComponent;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.events.RestApiEvent;
import com.nyinyi.nw.recyclerassignment.network.MovieDataAgent;
import com.nyinyi.nw.recyclerassignment.network.MovieDataAgentImpl;
import com.nyinyi.nw.recyclerassignment.persistences.MovieDBContract;
import com.nyinyi.nw.recyclerassignment.utils.AppConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by User on 12/8/2017.
 */

public class MovieModel {

    private static MovieModel objInstance;

    private List<PopularMovieVO> mPopularMovie;
    private int mmPageIndex = 1;

    @Inject
    MovieDataAgent movieDataAgent;

    public MovieModel() {
        EventBus.getDefault().register(this);
        mPopularMovie = new ArrayList<>();

        /*AppComponent appComponent = (AppComponent) context.getApplicationContext();
//        MovieApp movieApp = (MovieApp) context.getApplicationContext();
        appComponent.inject(this);*/

    }

    public static MovieModel getInstance() {
        if (objInstance == null) {
            objInstance = new MovieModel();
        }
        return objInstance;
    }

    public List<PopularMovieVO> getmPopularMovie() {
        return mPopularMovie;
    }

    public void startLoadingMovie(Context context) {
        MovieDataAgentImpl.getInstance().loadPopularMovie(AppConstants.ACCESS_TOKEN, mmPageIndex,context);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMovieDataLoaded(RestApiEvent.MovieDataLoadEvent event) {
        mPopularMovie.addAll(event.getLoadMovies());
        mmPageIndex = event.getLoadedPageIndex() + 1;

        //TODO LOGIC TO SAVE THE DATA IN PERSISTENCE LAYER
        ContentValues[] movieCVs = new ContentValues[event.getLoadMovies().size()];

        for (int index = 0; index < movieCVs.length; index++) {
            movieCVs[index] = event.getLoadMovies().get(index).parseToContentValues();
        }

        int insertRow = event.getContext().getContentResolver().bulkInsert(MovieDBContract.MovieEntry.CONTENT_URI,movieCVs);
        Log.d(MovieApp.LOG_TAG,"Inserted Row : "+ insertRow);
    }

}
