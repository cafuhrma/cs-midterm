package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEncountersFragment extends Fragment {
    private int partyLevel = 2; // hardcoded for testing
    private int partySize = 3; // hardcoded for testing
    private String difficulty = "Hard"; // hardcoded for testing
    private final String encounterType = "Boss"; // hardcoded for testing

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
        return inflater.inflate(R.layout.fragment_create_encounters, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EncounterViewModel viewModel = new ViewModelProvider(requireActivity()).get(EncounterViewModel.class);

        // API integration
        monsterList = new ArrayList<>();
        // create Retrofit object for API use
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Call the API and populate list of monsters
        monstersApi = retrofit.create(MonstersApi.class);
        monsterApi = retrofit.create(MonsterApi.class);
        getMonsters();
        viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> encounter.setMonsterList(monsterList));

        // party level spinner
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.partyLevels));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner levelSpinner = view.findViewById(R.id.spinner_partyLevel);
        levelSpinner.setAdapter(levelAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < getResources().getStringArray(R.array.partyLevels).length) {
                    partyLevel = position;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> encounter.setPartyLevel(partyLevel));
        // party size spinner
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.partySize));
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sizeSpinner = view.findViewById(R.id.spinner_partySize);
        sizeSpinner.setAdapter(sizeAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < getResources().getStringArray(R.array.partySize).length) {
                    partySize = position;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> encounter.setPartySize(partySize));
        // difficulty spinner
        ArrayAdapter<String> diffAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficulty));
        diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner diffSpinner = view.findViewById(R.id.spinner_difficulty);
        diffSpinner.setAdapter(diffAdapter);
        diffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position >= 0 && position < getResources().getStringArray(R.array.difficulty).length) {
                    if (position == 1) {
                        difficulty = "EASY";
                    }
                    else if (position == 2) {
                        difficulty = "MEDIUM";
                    }
                    else if (position == 3) {
                        difficulty = "HARD";
                    }
                    else if (position == 4) {
                        difficulty = "DEADLY";
                    }
                    else {
                        difficulty = "MEDIUM";
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> encounter.setDifficulty(difficulty));
        // encounter type spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.encounterType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner typeSpinner = view.findViewById(R.id.spinner_encounterType);
        typeSpinner.setAdapter(typeAdapter);
        // TODO add click listener for encounter type spinner
        viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> encounter.setType(encounterType));

        // back button to return to home screen
        view.findViewById(R.id.button_backCreate).setOnClickListener(view1 -> NavHostFragment.findNavController(CreateEncountersFragment.this)
                .navigate(R.id.action_createEncountersFragment_to_HomeFragment));
        // switch from create encounters screen to random encounters screen
        view.findViewById(R.id.button_randomList).setOnClickListener(v -> NavHostFragment.findNavController(CreateEncountersFragment.this)
                .navigate(R.id.action_createEncountersFragment_to_randomEncountersFragment));
        // switch from create encounters screen to build encounter screen
        view.findViewById(R.id.button_buildEncounter).setOnClickListener(v -> NavHostFragment.findNavController(CreateEncountersFragment.this)
                .navigate(R.id.action_createEncountersFragment_to_buildEncounterFragment));
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