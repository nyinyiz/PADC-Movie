package com.nyinyi.nw.recyclerassignment.network;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.recyclerassignment.network.response.GetMovieResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by User on 12/7/2017.
 */

public interface MovieAPI {


    @FormUrlEncoded
    @POST("v1/getPopularMovies.php")
    Call<GetMovieResponse> loadPopularMovie(
            @Field("access_token") String accessToken,
            @Field("page") int pageIndex
    );
}
