package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface LegendaryActionsApi {
    @GET("legendary_actions")
    Call<List<LegendaryAction>> getLegendaryActions();
}
