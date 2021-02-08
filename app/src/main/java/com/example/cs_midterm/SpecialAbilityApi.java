package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpecialAbilityApi {
    @GET("special_abilities")
    Call<List<SpecialAbility>> getSpecialAbilities();
}
