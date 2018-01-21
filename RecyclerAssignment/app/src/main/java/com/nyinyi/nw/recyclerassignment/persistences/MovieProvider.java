package com.nyinyi.nw.recyclerassignment.persistences;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Movie;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by user on 12/21/17.
 */

public class MovieProvider extends ContentProvider {

    public static final int MOVIE = 100;
    public static final int GENRE = 200;

    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MovieDBHelper movieDBHelper;

    private static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MovieDBContract.CONTENT_AUTHORITY, MovieDBContract.PATH_MOVIE, MOVIE);
        uriMatcher.addURI(MovieDBContract.CONTENT_AUTHORITY, MovieDBContract.PATH_GENRE, GENRE);

        return uriMatcher;
    }

    private String getTableName(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                return MovieDBContract.MovieEntry.TABLE_NAME;
            case GENRE:
                return MovieDBContract.MovieGenreEntry.TABLE_NAME;
            default:
                return null;
        }

    }


    private Uri getContentUri(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                return MovieDBContract.MovieEntry.CONTENT_URI;
            case GENRE:
                return MovieDBContract.MovieGenreEntry.CONTENT_URI;
            default:
                return null;
        }
    }


    @Override
    public boolean onCreate() {
        movieDBHelper = new MovieDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor queryCursor = movieDBHelper.getReadableDatabase().query(getTableName(uri),
                projection,
                selection,
                selectionArgs,
                null,
                null,
                sortOrder);

        if (getContext() != null)
        {
            queryCursor.setNotificationUri(getContext().getContentResolver(),uri);
        }

        return queryCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case MOVIE:
                return MovieDBContract.MovieEntry.DIR_TYPE;
            case GENRE:
                return MovieDBContract.MovieGenreEntry.DIR_TYPE;

        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        long _id = db.insert(tableName, null, values);
        if (_id > 0) {
            Uri tableContentUri = getContentUri(uri);
            Uri insertedUri = ContentUris.withAppendedId(tableContentUri, _id);

            if (getContext() != null) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return insertedUri;
        }
        return null;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        String tableName = getTableName(uri);
        int insertedCount = 0;
        try {
            db.beginTransaction();
            for (ContentValues cv : values) {
                long _id = db.insert(tableName, null, cv);
                if (_id > 0) {
                    insertedCount++;
                }
            }
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
            db.close();
        }

        Context context = getContext();
        if (context != null) {
            context.getContentResolver().notifyChange(uri, null);
        }

        return insertedCount;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        int rowDelteted;
        String tableName = getTableName(uri);
        rowDelteted = db.delete(tableName,selection,selectionArgs);
        Context context = getContext();
        if (context != null && rowDelteted > 0 )
        {
            context.getContentResolver().notifyChange(uri,null);
        }
        return rowDelteted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        int rowUpdated;
        String tableName = getTableName(uri);

        rowUpdated = db.update(tableName, values, selection, selectionArgs);
        Context context = getContext();
        if (context != null && rowUpdated > 0) {
            context.getContentResolver().notifyChange(uri, null);
        }
        return rowUpdated;
    }
}
