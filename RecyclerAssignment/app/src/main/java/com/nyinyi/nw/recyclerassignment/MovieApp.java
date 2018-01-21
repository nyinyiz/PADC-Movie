package com.nyinyi.nw.recyclerassignment;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nyinyi.nw.recyclerassignment.dagger.AppComponent;
import com.nyinyi.nw.recyclerassignment.dagger.AppModule;
import com.nyinyi.nw.recyclerassignment.dagger.DaggerAppComponent;
import com.nyinyi.nw.recyclerassignment.data.models.MovieModel;

import javax.inject.Inject;

/**
 * Created by User on 11/7/2017.
 */

public class MovieApp extends Application {

    public static final String LOG_TAG = "MovieApp";
    private AppComponent mAppComponent;


    @Inject
    Context mContext;

//    @Inject
    MovieModel mMovieModel;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = initDagger();//dagger init
        mAppComponent.inject(this);//register consumer

        mMovieModel = new MovieModel();
        mMovieModel.getInstance().startLoadingMovie(getApplicationContext());

        Log.d(LOG_TAG,"mContext : "+ mContext);

    }

    private AppComponent initDagger()
    {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getmAppComponent() {
        return mAppComponent;
    }



}
