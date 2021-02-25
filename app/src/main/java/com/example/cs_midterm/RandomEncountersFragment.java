package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
        encounterListView = view.findViewById(R.id.listView_randomEncounters);
        encounterAdapter = new EncounterAdapter(getActivity(), R.layout.item_encounter, encounters);

        // return to create encounters screen
        view.findViewById(R.id.button_backRandomList).setOnClickListener(view1 -> NavHostFragment.findNavController(RandomEncountersFragment.this)
                .navigate(R.id.action_randomEncountersFragment_to_createEncountersFragment));

        // Create 5 new encounters
        for (int i = 0; i < 5; i++) {
            Encounter temp = new Encounter();
            temp.setPartyLevel(Singleton.getInstance().encounter.getPartyLevel());
            temp.setPartySize(Singleton.getInstance().encounter.getPartySize());
            temp.setDifficulty(Singleton.getInstance().encounter.getDifficulty());
            temp.setType(Singleton.getInstance().encounter.getType());
            temp.setMonsterList(Singleton.getInstance().monsterList);

            temp.randomEncounter();
            Boolean isNew = true;
            // Check for duplicate encounter
            for (Encounter encounter : encounters) {
                for (Monster monster : encounter.getMonsters()) {
                    for (Monster tempMonster : temp.getMonsters()) {
                        if (tempMonster == monster) {
                            isNew = false;
                            break;
                        }
                    }
                    if (isNew == false) { break; }
                }
                if (isNew == false) { break; }
            }
            if (isNew == true) {
                encounters.add(temp);
                encounterAdapter.notifyDataSetChanged(); // notify observers
            }
            else { i--; }
        }

        // Assign adapter to the ListView
        encounterListView.setAdapter(encounterAdapter);
        encounterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Singleton.getInstance().myEncounters.add(encounters.get(position));
            }
        });

        // Button to generate new random encounters
        view.findViewById(R.id.button_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // populate list of 5 random encounters
                for (Encounter encounter : encounters) {
                    encounter.emptyMonsters();
                    encounter.randomEncounter();
                    encounterAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}