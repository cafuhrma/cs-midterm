package com.example.cs_midterm;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ViewEncountersFragment extends Fragment {
    private RecyclerView recyclerView;
    private ViewableEncounterAdapter recyclerAdapter;

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

        recyclerView = view.findViewById(R.id.recyclerView_myEncounters);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //fetch data and on ExpandableEncounterAdapter
        recyclerAdapter = new ViewableEncounterAdapter(Singleton.getInstance().myEncounters);
        recyclerView.setAdapter(recyclerAdapter);

        if (Singleton.getInstance().myEncounters.isEmpty()) {
            view.findViewById(R.id.linearLayout_viewEncounters).setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }

        // back button to return to home screen
        view.findViewById(R.id.button_backEncounters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ViewEncountersFragment.this)
                        .navigate(R.id.action_ViewEncountersFragment_to_HomeFragment);
            }
        });

        // save any changes made to myEncounters
        view.findViewById(R.id.button_saveViewEncounters).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).saveData(); // save any changes made to myEncounters
                recyclerAdapter.notifyDataSetChanged(); // stop displaying the removed encounters
            }
        });
    }
}