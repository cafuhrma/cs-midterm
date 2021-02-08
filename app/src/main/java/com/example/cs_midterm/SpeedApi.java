package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SpeedApi {
    @GET("speed")
    Call<Speed> getSpeed();
}
