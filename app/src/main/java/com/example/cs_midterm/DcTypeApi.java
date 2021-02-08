package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DcTypeApi {
    @GET("dc_type")
    Call<DcType> getDcType();
}
