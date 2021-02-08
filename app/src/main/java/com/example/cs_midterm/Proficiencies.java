package com.example.cs_midterm;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Proficiencies extends Monster {
    private ProficiencyApi proficiencyApi;
    // member fields
    int value;
    Proficiency proficiency;

    // accessors
    public int getValue() {
        return value;
    }
    public Proficiency getProficiency() {
        proficiencyApi = monsterRetrofit.create(ProficiencyApi.class);

        Call<Proficiency> call = proficiencyApi.getProficiency();

        call.enqueue(new Callback<Proficiency>() {
            @Override
            public void onResponse(Call<Proficiency> call, Response<Proficiency> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Profs Proficiency Response Unsuccessful");
                    return;
                }
                proficiency = response.body();
            }
            @Override
            public void onFailure(Call<Proficiency> call, Throwable t) {
                Log.d("ERROR", "Profs proficiency Failure");
            }
        });
        return proficiency;
    }
}
