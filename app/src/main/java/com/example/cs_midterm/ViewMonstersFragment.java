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

public class ViewMonstersFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExpandableMonsterAdapter recyclerAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_monsters, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView_myMonsters);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //fetch data and on ExpandableEncounterAdapter
        recyclerAdapter = new ExpandableMonsterAdapter(Singleton.getInstance().myMonsters);
        recyclerView.setAdapter(recyclerAdapter);

        if (Singleton.getInstance().myMonsters.isEmpty()) {
            view.findViewById(R.id.linearLayout_viewMonsters).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        // return to home screen
        view.findViewById(R.id.button_backMonsters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewMonstersFragment.this)
                        .navigate(R.id.action_viewMonstersFragment_to_HomeFragment);
            }
        });

        // switch from view monsters screen to create monster screen
        view.findViewById(R.id.button_createMonster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(ViewMonstersFragment.this)
                        .navigate(R.id.action_viewMonstersFragment_to_createMonsterFragment);
            }
        });
    }
}