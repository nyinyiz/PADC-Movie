package com.nyinyi.nw.recyclerassignment.persistences;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 12/15/2017.
 */

public class MovieDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "movie.db";
    private static final int DB_VERSION = 1;


    String CREATE_MOVIE_TABLE = "CREATE TABLE " + MovieDBContract.MovieEntry.TABLE_NAME
            + "(" + MovieDBContract.MovieEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_ID + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_VOTE_COUNT + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_VIDEO + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_VOTE_AVERAGE + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_TITLE + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_POPULARITY + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_POSTER_PATH + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_ORIGINAL_LANGUAGE + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_ORIGINAL_TITLE + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_BACKDROP_PATH + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_ADULT + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_OVERVIEW + " TEXT,"
            + MovieDBContract.MovieEntry.KEY_MOVIE_RELEASE_DATE + " TEXT,"
            + " UNIQUE ( "
            + MovieDBContract.MovieEntry.KEY_MOVIE_ID
            + " ) ON CONFLICT REPLACE );";

    String CREATE_GENRE_TABLE = "CREATE TABLE " + MovieDBContract.MovieGenreEntry.TABLE_NAME
            + "(" + MovieDBContract.MovieGenreEntry.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + MovieDBContract.MovieGenreEntry.KEY_GENRE_ID + " TEXT,"
            + MovieDBContract.MovieGenreEntry.KEY_MOVIE_ID + " TEXT,"
            + " UNIQUE ( "
            + MovieDBContract.MovieGenreEntry.KEY_GENRE_ID
            + " ) ON CONFLICT REPLACE );";

    public MovieDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_MOVIE_TABLE);
        db.execSQL(CREATE_GENRE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + MovieDBContract.MovieEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MovieDBContract.MovieGenreEntry.TABLE_NAME);
        onCreate(db);
    }
}
