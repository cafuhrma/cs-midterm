package com.example.cs_midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.util.List;

public class ViewEncountersFragment extends Fragment {
    ListView encountersList;
    EncounterAdapter encounterAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_encounters, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        encountersList = view.findViewById(R.id.listView_encounters);
        encounterAdapter = new EncounterAdapter(getActivity(), R.layout.item_encounter, Singleton.getInstance().myEncounters);
        encountersList.setAdapter(encounterAdapter);

        // back button to return to home screen
        view.findViewById(R.id.button_backEncounters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewEncountersFragment.this)
                        .navigate(R.id.action_ViewEncountersFragment_to_HomeFragment);
            }
        });
    }
}