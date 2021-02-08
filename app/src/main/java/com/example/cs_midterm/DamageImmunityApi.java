package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DamageImmunityApi {
    @GET("damage_immunities")
    Call<List<DamageType>> getDamageImmunities();
}
