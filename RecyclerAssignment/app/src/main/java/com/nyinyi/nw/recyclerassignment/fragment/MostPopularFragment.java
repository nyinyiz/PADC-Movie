package com.nyinyi.nw.recyclerassignment.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.R;
import com.nyinyi.nw.recyclerassignment.activity.MainActivity;
import com.nyinyi.nw.recyclerassignment.activity.MovieDetailActivity;
import com.nyinyi.nw.recyclerassignment.adapter.NowOnCinemaRecyclerViewAdapter;
import com.nyinyi.nw.recyclerassignment.components.EmptyViewPod;
import com.nyinyi.nw.recyclerassignment.components.SmartRecyclerView;
import com.nyinyi.nw.recyclerassignment.components.SmartScrollListener;
import com.nyinyi.nw.recyclerassignment.data.models.MovieModel;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.delegates.PopularMovieDelegate;
import com.nyinyi.nw.recyclerassignment.events.RestApiEvent;
import com.nyinyi.nw.recyclerassignment.mvp.presenters.PopularMoviePresenter;
import com.nyinyi.nw.recyclerassignment.mvp.views.PopularMovieView;
import com.nyinyi.nw.recyclerassignment.network.MovieDataAgentImpl;
import com.nyinyi.nw.recyclerassignment.persistences.MovieDBContract;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 11/7/2017.
 */

@SuppressLint("ValidFragment")
public class MostPopularFragment extends Fragment
        implements LoaderManager.LoaderCallbacks<Cursor>, PopularMovieView {

    private static final int MOVIE_LOADER_ID = 100;

    @BindView(R.id.rv_now_on_cinema)
    SmartRecyclerView rvCinema;
    @BindView(R.id.vp_empty_news)
    EmptyViewPod vpEmptyNews;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    private SmartScrollListener mSmartScrollListener;
    private NowOnCinemaRecyclerViewAdapter adapter;

    Context mContext;
    private PopularMoviePresenter mPresenter;


    @SuppressLint("ValidFragment")
    public MostPopularFragment(Context mContext, PopularMoviePresenter presenter) {
        this.mContext = mContext;
        mPresenter = presenter;

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_now_on_cinema, container, false);
        ButterKnife.bind(this, view);

        mPresenter.onCreateView();

        swipeRefreshLayout.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setRefreshing(true);

        rvCinema.setEmptyView(vpEmptyNews);
        rvCinema.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false));
        adapter = new NowOnCinemaRecyclerViewAdapter(getContext(), mPresenter);
        rvCinema.setAdapter(adapter);

        mSmartScrollListener = new SmartScrollListener(new SmartScrollListener.OnSmartScrollListener() {
            @Override
            public void onListEndReach() {
                Snackbar.make(rvCinema, "This is all the data for NOW.", Snackbar.LENGTH_LONG).show();
            }
        });
        rvCinema.addOnScrollListener(mSmartScrollListener);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(MOVIE_LOADER_ID, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        mPresenter.onStart();
//        mPresenter.onMovieStartLoading(getContext());

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        mPresenter.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMovieDataLoaded(RestApiEvent.MovieDataLoadEvent event) {
       /* adapter.appendNewData(event.getLoadMovies());
        swipeRefreshLayout.setRefreshing(false);*/
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Snackbar.make(rvCinema, event.getErrorMsg(), Snackbar.LENGTH_INDEFINITE).show();
        swipeRefreshLayout.setRefreshing(false);
        Log.d(MovieApp.LOG_TAG, event.getErrorMsg() + " Error Message ");
    }


    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(mContext,
                MovieDBContract.MovieEntry.CONTENT_URI,
                null,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
//        mPresenter.onDataLoaded(getContext(), data);

        if (data != null && data.moveToFirst())
        {
            List<PopularMovieVO> movieVOList = new ArrayList<>();
            do {
                PopularMovieVO popularMovieVO = PopularMovieVO.parseFromCursor(getContext(),data);
                movieVOList.add(popularMovieVO);
            }while (data.moveToNext());

            adapter.setNewData(movieVOList);
            swipeRefreshLayout.setRefreshing(false);

           // mView.displayMovieList(movieVOList);

        }

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void displayMovieList(List<PopularMovieVO> movieVOList) {
        adapter.setNewData(movieVOList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void navigateToMovieDetail(PopularMovieVO movie) {
       /* Intent intent = MovieDetailActivity.newIntent(getContext(),movie.getId().toString());
        startActivity(intent);*/

       mPresenter.onTapMovieItem(movie);

    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }
}
