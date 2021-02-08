package com.example.cs_midterm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Action extends Monster {
    private DifficultyClassApi difficultyClassApi;
    private DamageApi damageApi;
    String name, desc;
    int attack_bonus;
    DifficultyClass dc;
    List<Damage> damage;

    // accessors
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getAttack_bonus() {
        return attack_bonus;
    }

    public DifficultyClass getDc() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        difficultyClassApi = retrofit.create(DifficultyClassApi.class);

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

    public List<Damage> getDamage() {
        damageApi = monsterRetrofit.create(DamageApi.class);

        Call<List<Damage>> call = damageApi.getDamage();

        call.enqueue(new Callback<List<Damage>>() {
            @Override
            public void onResponse(Call<List<Damage>> call, Response<List<Damage>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    return;
                }
                damage = response.body();
            }
            @Override
            public void onFailure(Call<List<Damage>> call, Throwable t) {
            }
        });
        return damage;
    }
}
