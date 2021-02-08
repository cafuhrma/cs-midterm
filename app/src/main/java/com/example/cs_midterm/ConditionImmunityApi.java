package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConditionImmunityApi {
    @GET("condition_immunities")
    Call<List<ConditionType>> getConditionImmunities();
}
