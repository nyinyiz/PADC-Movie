package com.nyinyi.nw.recyclerassignment.mvp.views;

import android.content.Context;

import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;

import java.util.List;

/**
 * Created by user on 1/7/18.
 */

public interface PopularMovieView  {

    void displayMovieList(List<PopularMovieVO> movieVOList);

    void navigateToMovieDetail(PopularMovieVO movie);

    void showLoading();

    Context getContext();
}
