package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class BuildEncounterFragment extends Fragment {
    private ListView monsterListView;
    private EditText monsterSearch;
    private MonstersAdapter monsterSearchAdapter;
    private Encounter encounter;

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

        // initialize views & variables
        monsterListView = view.findViewById(R.id.listView_monsters);
        monsterSearch = view.findViewById(R.id.editText_monsterSearch);
        encounter = new Encounter();
        monsterSearchAdapter = new MonstersAdapter(getActivity(), R.layout.expandable_monster, Singleton.getInstance().monsterList);

        // back button for returning to create encounters screen
        view.findViewById(R.id.button_backBuildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuildEncounterFragment.this)
                        .navigate(R.id.action_buildEncounterFragment_to_createEncountersFragment);
            }
        });

        monsterListView.setAdapter(monsterSearchAdapter); // Assign adapter to ListView
        monsterListView.setTextFilterEnabled(true); // Enables filtering for the contents of the given ListView

        monsterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Monster monster = (Monster) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),
                        monster.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        monsterSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                monsterSearchAdapter.getFilter().filter(s.toString());
            }
        }); // Check for search query
    }
}