package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RandomEncountersFragment extends CreateEncountersFragment {
    Encounter randomEncounter;
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

        // return to create encounters screen
        view.findViewById(R.id.button_backRandomList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(RandomEncountersFragment.this)
                        .navigate(R.id.action_randomEncountersFragment_to_createEncountersFragment);
            }
        });

        // initialize encounter
        randomEncounter = getEncounter();
    }
}