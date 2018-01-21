
package com.nyinyi.nw.recyclerassignment.data.vos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.recyclerassignment.persistences.MovieDBContract;

public class PopularMovieVO {

    @SerializedName("adult")
    private Boolean mAdult;
    @SerializedName("backdrop_path")
    private String mBackdropPath;
    @SerializedName("genre_ids")
    private List<Long> mGenreIds;
    @SerializedName("id")
    private Long mId;
    @SerializedName("original_language")
    private String mOriginalLanguage;
    @SerializedName("original_title")
    private String mOriginalTitle;
    @SerializedName("overview")
    private String mOverview;
    @SerializedName("popularity")
    private Double mPopularity;
    @SerializedName("poster_path")
    private String mPosterPath;
    @SerializedName("release_date")
    private String mReleaseDate;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("video")
    private Boolean mVideo;
    @SerializedName("vote_average")
    private Double mVoteAverage;
    @SerializedName("vote_count")
    private Long mVoteCount;

    public Boolean getAdult() {
        return mAdult;
    }


    public String getBackdropPath() {
        return mBackdropPath;
    }


    public List<Long> getGenreIds() {
        return mGenreIds;
    }


    public Long getId() {
        return mId;
    }

    public String getOriginalLanguage() {
        return mOriginalLanguage;
    }


    public String getOriginalTitle() {
        return mOriginalTitle;
    }


    public String getOverview() {
        return mOverview;
    }


    public Double getPopularity() {
        return mPopularity;
    }


    public String getPosterPath() {
        return mPosterPath;
    }


    public String getReleaseDate() {
        return mReleaseDate;
    }


    public String getTitle() {
        return mTitle;
    }


    public Boolean getVideo() {
        return mVideo;
    }

    public Double getVoteAverage() {
        return mVoteAverage;
    }

    public Long getVoteCount() {
        return mVoteCount;
    }

    public ContentValues parseToContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_ID,mId);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_VOTE_COUNT,mVoteCount);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_VIDEO,mVideo);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_VOTE_AVERAGE,mVoteAverage);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_TITLE,mTitle);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_POPULARITY,mPopularity);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_POSTER_PATH,mPosterPath);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_ORIGINAL_LANGUAGE,mOriginalLanguage);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_ORIGINAL_TITLE,mOriginalTitle);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_BACKDROP_PATH,mBackdropPath);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_ADULT,mAdult);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_OVERVIEW,mOverview);
        contentValues.put(MovieDBContract.MovieEntry.KEY_MOVIE_RELEASE_DATE,mReleaseDate);

        return contentValues;
    }

    public static PopularMovieVO parseFromCursor(Context context,Cursor data) {
        PopularMovieVO popularMovieVO = new PopularMovieVO();
        popularMovieVO.mId = data.getLong(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_ID));
        popularMovieVO.mVoteCount = data.getLong(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_VOTE_COUNT));
        popularMovieVO.mVideo = Boolean.valueOf(data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_VIDEO)));
        popularMovieVO.mVoteAverage = data.getDouble(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_VOTE_AVERAGE));
        popularMovieVO.mTitle = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_TITLE));
        popularMovieVO.mPopularity = data.getDouble(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_POPULARITY));
        popularMovieVO.mPosterPath = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_POSTER_PATH));
        popularMovieVO.mOriginalLanguage = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_ORIGINAL_LANGUAGE));
        popularMovieVO.mOriginalTitle  = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_ORIGINAL_TITLE));
        popularMovieVO.mBackdropPath = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_BACKDROP_PATH));
        popularMovieVO.mAdult = Boolean.valueOf(data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_ADULT)));
        popularMovieVO.mOverview = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_OVERVIEW));
        popularMovieVO.mReleaseDate = data.getString(data.getColumnIndex(MovieDBContract.MovieEntry.KEY_MOVIE_RELEASE_DATE));

        return popularMovieVO;
    }
}
