package com.nyinyi.nw.recyclerassignment.dagger;

import android.content.Context;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.data.models.MovieModel;
import com.nyinyi.nw.recyclerassignment.mvp.presenters.PopularMoviePresenter;
import com.nyinyi.nw.recyclerassignment.network.MovieDataAgentImpl;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 1/13/18.
 */

@Module
public class AppModule {

    private MovieApp mApp;

    public AppModule(MovieApp application) {
        mApp = application;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp.getApplicationContext();
    }


    @Provides
    public PopularMoviePresenter provideMovieListPresenter() {
        return new PopularMoviePresenter();
    }


    @Provides
    @Singleton // obj copy ta ku pl shi chin lo singleton use tr
    public MovieDataAgentImpl provideMMNewsDataAgent() {
        return new MovieDataAgentImpl();
    }
    @Provides
    @Singleton
    public MovieModel provideMovieModel(Context context) {
        return new MovieModel();
    }
}
