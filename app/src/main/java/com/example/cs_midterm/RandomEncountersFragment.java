package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class RandomEncountersFragment extends Fragment {
    private ArrayList<Encounter> encounters;
    private ExpandableEncounterAdapter recyclerAdapter;
    private RecyclerView recyclerView;

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
        recyclerView = view.findViewById(R.id.recyclerView_randomEncounters);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //fetch data and on ExpandableEncounterAdapter
        recyclerAdapter = new ExpandableEncounterAdapter(encounters);

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
                recyclerAdapter.notifyDataSetChanged(); // notify observers
            }
            else { i--; }
        }

        // Assign adapter to the RecyclerView
        recyclerView.setAdapter(recyclerAdapter);

        // Button to generate new random encounters
        view.findViewById(R.id.button_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // populate list of 5 random encounters
                for (Encounter encounter : encounters) {
                    encounter.emptyMonsters();
                    encounter.randomEncounter();
                    recyclerAdapter.notifyDataSetChanged(); // notify observers
                }
            }
        });

        // Button to save saved encounters
        view.findViewById(R.id.button_saveRandom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).saveData(); // save any changes made to myEncounters
            }
        });
    }
}