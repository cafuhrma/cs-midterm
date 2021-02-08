package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpecialAbility extends Monster {
    private DifficultyClassApi difficultyClassApi;
    String name, desc;
    DifficultyClass dc;

    // accessors
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public DifficultyClass getDc() {
        difficultyClassApi = monsterRetrofit.create(DifficultyClassApi.class);

        Call<DifficultyClass> call = difficultyClassApi.getDifficultyClass();

        call.enqueue(new Callback<DifficultyClass>() {
            @Override
            public void onResponse(Call<DifficultyClass> call, Response<DifficultyClass> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    return;
                }
                dc = response.body();
            }
            @Override
            public void onFailure(Call<DifficultyClass> call, Throwable t) {
            }
        });
        return dc;
    }
}
