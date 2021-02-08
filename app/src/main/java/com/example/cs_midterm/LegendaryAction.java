package com.example.cs_midterm;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LegendaryAction {
    private DamageApi damageApi;
    String name, desc;
    int attack_bonus;
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

    public List<Damage> getDamage() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        damageApi = retrofit.create(DamageApi.class);

        Call<List<Damage>> call = damageApi.getDamage();

        call.enqueue(new Callback<List<Damage>>() {
            @Override
            public void onResponse(Call<List<Damage>> call, Response<List<Damage>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "LA Damage Response Unsuccessful");
                    return;
                }
                damage = response.body();
            }
            @Override
            public void onFailure(Call<List<Damage>> call, Throwable t) {
                Log.d("ERROR", "LA Damage Failure");
            }
        });
        return damage;
    }
}
