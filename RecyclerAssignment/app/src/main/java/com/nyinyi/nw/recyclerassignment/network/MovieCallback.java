package com.nyinyi.nw.recyclerassignment.network;

import com.nyinyi.nw.recyclerassignment.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by User on 12/9/2017.
 */

public abstract class MovieCallback<T extends MovieResponse> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        MovieResponse movieResponse = response.body();
        if (movieResponse == null)
        {
            RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                    new RestApiEvent.ErrorInvokingAPIEvent(
                            "No data could be loaded for now. Pls try again later.");
            EventBus.getDefault().post(errorEvent);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        RestApiEvent.ErrorInvokingAPIEvent errorEvent = new
                RestApiEvent.ErrorInvokingAPIEvent(t.getMessage());
        EventBus.getDefault().post(errorEvent);
    }
}
