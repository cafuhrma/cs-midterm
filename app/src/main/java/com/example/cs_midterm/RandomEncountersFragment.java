package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RandomEncountersFragment extends Fragment {
    private ArrayList<Encounter> encounters;
    private EncounterAdapter encounterAdapter;
    private ListView encounterListView;

    private EncounterViewModel viewModel;

    private MonstersApi monstersApi;
    private MonsterApi monsterApi;
    private ArrayList<Monster> monsterList;
    private Retrofit retrofit;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_random_encounters, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        encounters = new ArrayList<>();
        monsterList = new ArrayList<>();
        encounterListView = view.findViewById(R.id.listView_randomEncounters);
        encounterAdapter = new EncounterAdapter(getActivity(), R.layout.item_encounter, encounters);

        // return to create encounters screen
        view.findViewById(R.id.button_backRandomList).setOnClickListener(view1 -> NavHostFragment.findNavController(RandomEncountersFragment.this)
                .navigate(R.id.action_randomEncountersFragment_to_createEncountersFragment));

        // TODO remove API
        // API integration
        // create Retrofit object for API use
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Call the API and populate list of monsters
        monstersApi = retrofit.create(MonstersApi.class);
        monsterApi = retrofit.create(MonsterApi.class);
        getMonsters();

        // Create 5 new encounters
        for (int i = 0; i < 5; i++) {
            Encounter temp = new Encounter();
            temp.setMonsterList(monsterList);

            // values for testing
            temp.setType("Boss");
            temp.setDifficulty("Hard");
            temp.setPartySize(4);
            temp.setPartyLevel(3);

            encounters.add(temp);
            encounterAdapter.notifyDataSetChanged(); // notify observers
        }

        // Assign adapter to the ListView
        encounterListView.setAdapter(encounterAdapter);

        // Button to generate new random encounters
        view.findViewById(R.id.button_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // populate list of 5 random encounters
                for (Encounter encounter : encounters) {
                    encounter.randomEncounter();
                    encounterAdapter.notifyDataSetChanged();
                }
            }
        });
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
                // TODO notify the observers that the data has been changed
            }
            @Override
            public void onFailure(@NotNull Call<Monster> call, @NotNull Throwable t) {
                // error handling
                Log.d("ERROR", "Monster Failure");
            }
        });
    }
}