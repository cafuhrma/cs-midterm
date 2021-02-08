package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DifficultyClass extends Monster {
    private DcTypeApi dcTypeApi;
    DcType dc_type;
    int dc_value;
    String success_type;

    // accessors
    public DcType getDc_type() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        dcTypeApi = retrofit.create(DcTypeApi.class);

        Call<DcType> call = dcTypeApi.getDcType();

        call.enqueue(new Callback<DcType>() {
            @Override
            public void onResponse(Call<DcType> call, Response<DcType> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    return;
                }
                dc_type = response.body();
            }
            @Override
            public void onFailure(Call<DcType> call, Throwable t) {
            }
        });
        return dc_type;
    }

    public int getDc_value() {
        return dc_value;
    }

    public String getSuccess_type() {
        return success_type;
    }
}
