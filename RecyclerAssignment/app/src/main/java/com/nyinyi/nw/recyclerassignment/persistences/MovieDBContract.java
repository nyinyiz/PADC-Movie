package com.nyinyi.nw.recyclerassignment.persistences;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

import com.nyinyi.nw.recyclerassignment.MovieApp;
import com.nyinyi.nw.recyclerassignment.network.MovieAPI;

/**
 * Created by User on 12/15/2017.
 */

public class MovieDBContract {

    public static final String CONTENT_AUTHORITY = MovieApp.class.getPackage().getName();
    private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_MOVIE = "movie";
    public static final String PATH_GENRE = "genre";

    public static final class MovieEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIE).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIE;

        public static final String TABLE_NAME = PATH_MOVIE;

        public static final String KEY_ID = _ID;
        public static final String KEY_MOVIE_ID = "movie_id";
        public static final String KEY_MOVIE_VOTE_COUNT = "vote_count";
        public static final String KEY_MOVIE_VIDEO = "movie_video";
        public static final String KEY_MOVIE_VOTE_AVERAGE = "vote_average";
        public static final String KEY_MOVIE_TITLE = "title";
        public static final String KEY_MOVIE_POPULARITY = "popularity";
        public static final String KEY_MOVIE_POSTER_PATH = "poster_path";
        public static final String KEY_MOVIE_ORIGINAL_LANGUAGE = "original_language";
        public static final String KEY_MOVIE_ORIGINAL_TITLE = "original_title";

        public static final String KEY_MOVIE_BACKDROP_PATH = "backdrop_path";
        public static final String KEY_MOVIE_ADULT = "adult";
        public static final String KEY_MOVIE_OVERVIEW = "overview";
        public static final String KEY_MOVIE_RELEASE_DATE = "release_date";

    }

    public static final class MovieGenreEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_GENRE).build();
        public static final String DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE;
        public static final String ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_GENRE;

        public static final String TABLE_NAME = PATH_GENRE;

        public static final String KEY_ID = _ID;
        public static final String KEY_GENRE_ID = "genre_id";
        public static final String KEY_MOVIE_ID = "movie_id";

    }


}
