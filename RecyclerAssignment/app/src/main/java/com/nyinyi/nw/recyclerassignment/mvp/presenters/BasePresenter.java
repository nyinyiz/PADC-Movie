package com.nyinyi.nw.recyclerassignment.mvp.presenters;

import java.security.PublicKey;

/**
 * Created by user on 1/7/18.
 */

public abstract class BasePresenter<T> {

    T mView;

    public void onCreate(T view) {
        mView = view;
    }

    public void onCreateView()
    {

    }
    public abstract void onStart();

    public void onResume() {

    }

    public void onPause() {

    }

    public abstract void onStop();

    public void onDestroy() {

    }


}
