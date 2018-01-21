package com.nyinyi.nw.recyclerassignment.network;

import android.content.Context;

/**
 * Created by User on 12/7/2017.
 */

public interface MovieDataAgent {
    void loadPopularMovie(String accessToken , int pageIndex, Context context);
}
