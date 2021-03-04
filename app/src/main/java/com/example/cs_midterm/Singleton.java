package com.example.cs_midterm;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
    private MonstersApi monstersApi;
    private MonsterApi monsterApi;
    private Retrofit retrofit;

    private static Singleton instance = null;

    public ArrayList<Monster> monsterList;
    public Encounter encounter;
    public ArrayList<Encounter> myEncounters;

    private Singleton() {
        monsterList = new ArrayList<>();
        fillList();
        encounter = new Encounter();
        myEncounters = new ArrayList<>();
    }

    public static synchronized Singleton getInstance() {
        if(instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void fillList() {
        // create Retrofit object for API use
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Call the API and populate list of monsters
        monstersApi = retrofit.create(MonstersApi.class);
        monsterApi = retrofit.create(MonsterApi.class);
        getMonsters();
    }

    private void getMonsters() {
        Call<Monsters> call = monstersApi.getMonsters();

        // populate list of monsters from the API
        call.enqueue(new Callback<Monsters>() {
            @Override
            public void onResponse(@NotNull Call<Monsters> call, @NotNull Response<Monsters> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Monsters Response Unsuccessful");
                    return;
                }
                // display each monster's info
                Monsters monsters = response.body();
                // loop through entire monster database
                for (int i = 0; i < monsters.getResults().size(); i++) {
                    Result result = monsters.getResults().get(i);
                    getMonster(result.getIndex());
                }
            }
            @Override
            public void onFailure(@NotNull Call<Monsters> call, @NotNull Throwable t) {
                // error handling
                Log.d("ERROR", "Monsters Failure");
            }
        });
    }
    private void getMonster(String monsterIndex) {
        Call<Monster> call = monsterApi.getMonster(monsterIndex);

        call.enqueue(new Callback<Monster>() {
            @Override
            public void onResponse(@NotNull Call<Monster> call, @NotNull Response<Monster> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Monster Response Unsuccessful");
                    return;
                }
                Monster monster = response.body();
                monsterList.add(monster);
            }
            @Override
            public void onFailure(@NotNull Call<Monster> call, @NotNull Throwable t) {
                // error handling
                Log.d("ERROR", "Monster Failure");
            }
        });
    }
}
