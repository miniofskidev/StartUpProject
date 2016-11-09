package com.codefobi.startupproject.retrofit;

import com.codefobi.startupproject.models.Content;
import com.codefobi.startupproject.models.ReadContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tosantechnolocal on 11/8/2016.
 */
public interface  ApiInterface {

    @GET("headContent.json")
    Call<List<Content>> contentCall();

    @GET("content.json")
    Call<List<ReadContent>> readContentCall();

}
