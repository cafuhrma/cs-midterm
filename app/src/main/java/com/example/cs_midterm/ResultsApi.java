package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ResultsApi {
    @GET("results")
    Call<List<Results>> getResults();
}
