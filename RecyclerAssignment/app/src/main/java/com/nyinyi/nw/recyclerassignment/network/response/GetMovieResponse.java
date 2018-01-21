package com.nyinyi.nw.recyclerassignment.network.response;

import com.google.gson.annotations.SerializedName;
import com.nyinyi.nw.recyclerassignment.data.vos.PopularMovieVO;
import com.nyinyi.nw.recyclerassignment.network.MovieResponse;

import java.util.List;

/**
 * Created by User on 12/7/2017.
 */

public class GetMovieResponse extends MovieResponse {

    @SerializedName("code")
    private int code;
    @SerializedName("message")
    private String success;
    @SerializedName("apiVersion")
    private String apiVersion;
    @SerializedName("page")
    private String page;
    @SerializedName("popular-movies")
    List<PopularMovieVO> popularMovieVOList;

    public int getCode() {
        return code;
    }

    public String getSuccess() {
        return success;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public String getPage() {
        return page;
    }

    public List<PopularMovieVO> getPopularMovieVOList() {
        return popularMovieVOList;
    }
}
