package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class BuildEncounterFragment extends Fragment {

    private ArrayAdapter monsterSearchAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_build_encounter, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.button_backBuildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuildEncounterFragment.this)
                        .navigate(R.id.action_buildEncounterFragment_to_createEncountersFragment);
            }
        });

        // monster name search filter
        EditText monsterSearch = view.findViewById(R.id.editText_monsterSearch);
        ListView monsterList = view.findViewById(R.id.listView_monsters);
        ArrayList<String> monsterNames = new ArrayList<>();
        monsterNames.add("Aboleth");
        monsterNames.add("Goblin");
        monsterNames.add("Mimic");
        monsterNames.add("Orc");

        monsterSearchAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,
                monsterNames);
        monsterList.setAdapter(monsterSearchAdapter);
        monsterSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                (BuildEncounterFragment.this).monsterSearchAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}