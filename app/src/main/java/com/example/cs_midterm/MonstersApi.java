package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MonstersApi {
    @GET("monsters")
    Call<Monsters> getMonsters();
}
