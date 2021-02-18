package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

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
        encounterListView = view.findViewById(R.id.listView_randomEncounters);
        encounters = new ArrayList<>();

        // return to create encounters screen
        view.findViewById(R.id.button_backRandomList).setOnClickListener(view1 -> NavHostFragment.findNavController(RandomEncountersFragment.this)
                .navigate(R.id.action_randomEncountersFragment_to_createEncountersFragment));

        // Button to generate new random encounters
        view.findViewById(R.id.button_generate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // populate list of random encounters
                for (int i = 0; i < 5; i++) {
                    EncounterViewModel viewModel = new ViewModelProvider(requireActivity()).get(EncounterViewModel.class);
                    viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> {
                        encounter.randomEncounter();
                        encounters.add(encounter);
                        encounter.getMonsters().clear(); // reset the monsters in the encounter
                    });
                }
                startActivity(getActivity().getIntent());
            }
        });

        encounterAdapter = new EncounterAdapter(getActivity(), R.layout.item_encounter, encounters);
        // Assign adapter to the ListView
        encounterListView.setAdapter(encounterAdapter);
        startActivity(getActivity().getIntent());
    }
}