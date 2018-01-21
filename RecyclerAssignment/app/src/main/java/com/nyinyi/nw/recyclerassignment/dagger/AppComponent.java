package com.nyinyi.nw.recyclerassignment.dagger;

import android.graphics.Movie;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.activity.MainActivity;
import com.nyinyi.nw.recyclerassignment.data.models.MovieModel;
import com.nyinyi.nw.recyclerassignment.mvp.presenters.PopularMoviePresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by user on 1/13/18.
 */

@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {

    void inject(MovieApp app);

    void inject(PopularMoviePresenter movieListPresenter);

    void inject(MainActivity mainActivity);

//    void inject(MovieModel movieModel);


}
