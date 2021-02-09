package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MonsterApi {
    @GET("monsters/{index}/")
    Call<Monster> getMonster(@Path("index") String monsterIndex);
}
