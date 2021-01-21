package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class CreateEncountersFragment extends Fragment {

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

        view.findViewById(R.id.button_backCreate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateEncountersFragment.this)
                        .navigate(R.id.action_createEncountersFragment_to_HomeFragment);
            }
        });

        // switch from create encounters screen to random encounters screen
        view.findViewById(R.id.button_randomList).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CreateEncountersFragment.this)
                        .navigate(R.id.action_createEncountersFragment_to_randomEncountersFragment);
            }
        });

        // switch from create encounters screen to build encounter screen
        view.findViewById(R.id.button_buildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(CreateEncountersFragment.this)
                        .navigate(R.id.action_createEncountersFragment_to_buildEncounterFragment);
            }
        });

        // party level spinner
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.partyLevels));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner levelSpinner = view.findViewById(R.id.spinner_partyLevel);
        levelSpinner.setAdapter(levelAdapter);

        // party size spinner
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.partySize));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sizeSpinner = view.findViewById(R.id.spinner_partySize);
        sizeSpinner.setAdapter(sizeAdapter);

        // difficulty spinner
        ArrayAdapter<String> diffAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficulty));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner diffSpinner = view.findViewById(R.id.spinner_difficulty);
        diffSpinner.setAdapter(diffAdapter);
    }
}