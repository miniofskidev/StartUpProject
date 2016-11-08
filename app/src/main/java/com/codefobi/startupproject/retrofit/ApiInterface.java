package com.codefobi.startupproject.retrofit;

import com.codefobi.startupproject.models.Content;
import com.codefobi.startupproject.models.ReadContent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by tosantechnolocal on 11/8/2016.
 */
public abstract class ApiInterface {

    @GET("/amin/content.json")
    abstract Call<List<Content>> contentCall();

    @GET("/amin/readcontent.json")
    abstract Call<List<ReadContent>> readContentCall();

}
