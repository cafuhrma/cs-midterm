package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DamageTypeApi {
    @GET("damage_type")
    Call<DamageType> getDamageType();
}
