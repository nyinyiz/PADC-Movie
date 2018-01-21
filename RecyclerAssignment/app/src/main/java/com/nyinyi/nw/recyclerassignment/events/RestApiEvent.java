package com.nyinyi.nw.recyclerassignment.events;

import android.content.Context;

import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;

import java.util.List;

/**
 * Created by User on 12/7/2017.
 */

public class RestApiEvent {

    public static class ErrorInvokingAPIEvent {
        private String errorMsg;

        public ErrorInvokingAPIEvent(String errorMsg) {
            this.errorMsg = errorMsg;
        }

        public String getErrorMsg() {
            return errorMsg;
        }
    }

    public static class MovieDataLoadEvent {
        private int loadedPageIndex;
        private List<PopularMovieVO> loadMovies;
        private Context context;

        public MovieDataLoadEvent(int loadedPageIndex, List<PopularMovieVO> loadMovies,Context context) {
            this.loadedPageIndex = loadedPageIndex;
            this.loadMovies = loadMovies;
            this.context = context;
        }

        public int getLoadedPageIndex() {
            return loadedPageIndex;
        }

        public List<PopularMovieVO> getLoadMovies() {
            return loadMovies;
        }

        public Context getContext() {
            return context;
        }
    }
}
