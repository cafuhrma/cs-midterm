package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DifficultyClassApi {
    @GET("dc")
    Call<DifficultyClass> getDifficultyClass();
}
