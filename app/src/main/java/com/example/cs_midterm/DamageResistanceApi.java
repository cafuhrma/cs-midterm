package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DamageResistanceApi {
    @GET("damage_resistances")
    Call<List<DamageType>> getDamageResistances();
}
