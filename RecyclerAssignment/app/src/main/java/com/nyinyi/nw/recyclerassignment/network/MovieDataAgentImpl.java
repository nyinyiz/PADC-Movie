package com.nyinyi.nw.recyclerassignment.network;

import android.content.Context;

import com.google.gson.Gson;
import com.nyinyi.nw.recyclerassignment.events.RestApiEvent;
import com.nyinyi.nw.recyclerassignment.network.response.GetMovieResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by User on 12/7/2017.
 */

public class MovieDataAgentImpl implements MovieDataAgent {

    private static MovieDataAgentImpl objInstance;
    private MovieAPI theAPI;

    public MovieDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://padcmyanmar.com/padc-3/popular-movies/apis/")
                .callFactory(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        theAPI = retrofit.create(MovieAPI.class);
    }

    public static MovieDataAgentImpl getInstance() {
        if (objInstance == null){
            objInstance = new MovieDataAgentImpl();
        }
        return objInstance;
    }


    @Override
    public void loadPopularMovie(String accessToken, int pageIndex, final Context context) {

        /*Call<GetMovieResponse> call = theAPI.loadPopularMovie(accessToken, pageIndex);
        call.enqueue(new MovieCallback<GetMovieResponse>() {
            @Override
            public void onResponse(Call<GetMovieResponse> call, Response<GetMovieResponse> response) {
                super.onResponse(call, response);

                GetMovieResponse getMovieResponse = response.body();

                if (getMovieResponse != null && getMovieResponse.getPopularMovieVOList().size() > 0) {
                    RestApiEvent.MovieDataLoadEvent movieDataLoadedEvent = new
                            RestApiEvent.MovieDataLoadEvent(
                            Integer.parseInt(getMovieResponse.getPage()), getMovieResponse.getPopularMovieVOList(),context);

                    EventBus.getDefault().post(movieDataLoadedEvent);
                }
            }
        });*/

        Call<GetMovieResponse> call = theAPI.loadPopularMovie(accessToken,pageIndex);
        call.enqueue(new Callback<GetMovieResponse>() {
            @Override
            public void onResponse(Call<GetMovieResponse> call, Response<GetMovieResponse> response) {
                GetMovieResponse getMovieResponse = response.body();

                if (getMovieResponse != null && getMovieResponse.getPopularMovieVOList().size() > 0) {
                    RestApiEvent.MovieDataLoadEvent movieDataLoadedEvent = new
                            RestApiEvent.MovieDataLoadEvent(
                            Integer.parseInt(getMovieResponse.getPage()), getMovieResponse.getPopularMovieVOList(),context);

                    EventBus.getDefault().post(movieDataLoadedEvent);
                }
            }

            @Override
            public void onFailure(Call<GetMovieResponse> call, Throwable t) {
                RestApiEvent.ErrorInvokingAPIEvent errorEvent = new
                        RestApiEvent.ErrorInvokingAPIEvent(t.getMessage());
                EventBus.getDefault().post(errorEvent);
            }
        });
    }
}
