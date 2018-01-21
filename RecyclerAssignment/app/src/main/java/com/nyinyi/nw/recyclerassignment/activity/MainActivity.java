package com.nyinyi.nw.recyclerassignment.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.R;
import com.nyinyi.nw.recyclerassignment.adapter.ViewPagerAdapter;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.delegates.PopularMovieDelegate;
import com.nyinyi.nw.recyclerassignment.fragment.MostPopularFragment;
import com.nyinyi.nw.recyclerassignment.mvp.presenters.PopularMoviePresenter;
import com.nyinyi.nw.recyclerassignment.mvp.views.PopularMovieView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener,PopularMovieView {

    @BindView(R.id.tabs)
    TabLayout tab;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;


    @Inject
    PopularMoviePresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this, this);
        setSupportActionBar(toolbar);

        MovieApp movieApp = (MovieApp) getApplicationContext();
        movieApp.getmAppComponent().inject(this);

        mPresenter.onCreate(this);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        setUpViewPager(viewPager);
        viewPager.setOffscreenPageLimit(3);
        tab.setupWithViewPager(viewPager);


    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MostPopularFragment(this, mPresenter), "NOW ON CINEMA");
        adapter.addFragment(new MostPopularFragment(this, mPresenter), "UPCOMING");
        adapter.addFragment(new MostPopularFragment(this, mPresenter), "MOST POPULAR");
        viewPager.setAdapter(adapter);
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

   /* @Override
    public void onTapMovieItem(PopularMovieVO movie) {
        Intent intent = MovieDetailActivity.newIntent(getApplicationContext(),movie.getId().toString());
        startActivity(intent);

    }*/

    @Override
    public void displayMovieList(List<PopularMovieVO> movieVOList) {

    }

    @Override
    public void navigateToMovieDetail(PopularMovieVO movie) {

        Intent intent = MovieDetailActivity.newIntent(getApplicationContext(),movie.getId().toString());
        startActivity(intent);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public Context getContext() {
        return null;
    }
}
