package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class BuildEncounterFragment extends Fragment {
    private RecyclerView recyclerView;
    private ExpandableMonsterAdapter recyclerAdapter;
    private Encounter encounter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_build_encounter, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialize views & variables
        encounter = new Encounter();
        recyclerView = view.findViewById(R.id.recyclerView_buildEncounters);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        //fetch data and on ExpandableEncounterAdapter
        recyclerAdapter = new ExpandableMonsterAdapter(Singleton.getInstance().monsterList);
        recyclerView.setAdapter(recyclerAdapter);

        // EditText for encounter name
        EditText encounterName = view.findViewById(R.id.pt_encounterName);
        encounterName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() != 0) {
                    encounter.setName(s.toString());
                }
            }
        });

        // back button for returning to create encounters screen
        view.findViewById(R.id.button_backBuildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuildEncounterFragment.this)
                        .navigate(R.id.action_buildEncounterFragment_to_createEncountersFragment);
            }
        });

        // save button
        view.findViewById(R.id.button_saveEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                encounter.setMonsters(recyclerAdapter.getReposAdded());
                Singleton.getInstance().myEncounters.add(encounter);
                ((MainActivity)getActivity()).saveData(); // save any changes made to myEncounters
                showToast(v);
            }
        });
    }

    // search filter
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                recyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void showToast(View v) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) v.findViewById(R.id.toast_root));

        TextView toastText = layout.findViewById(R.id.toast_text);
        ImageView toastImage = layout.findViewById(R.id.toast_image);

        toastText.setText("Encounter Saved");
        toastImage.setImageResource(R.drawable.ic_baseline_save_24);

        Toast toast = new Toast(getContext());
        toast.setGravity(Gravity.CENTER, 0, -10);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }
}