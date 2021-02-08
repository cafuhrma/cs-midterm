package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DamageApi {
    @GET("damage")
    Call<List<Damage>> getDamage();
}
