package com.example.cs_midterm;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Damage extends Monster {
    private DamageTypeApi damageTypeApi;
    String damage_dice;
    int damage_bonus;
    DamageType damage_type;

    // accessors
    public String getDamage_dice() {
        return damage_dice;
    }

    public int getDamage_bonus() {
        return damage_bonus;
    }

    public DamageType getDamage_type() {
        damageTypeApi = monsterRetrofit.create(DamageTypeApi.class);

        Call<DamageType> call = damageTypeApi.getDamageType();

        call.enqueue(new Callback<DamageType>() {
            @Override
            public void onResponse(Call<DamageType> call, Response<DamageType> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    return;
                }
                damage_type = response.body();
            }
            @Override
            public void onFailure(Call<DamageType> call, Throwable t) {
            }
        });
        return damage_type;
    }
}
