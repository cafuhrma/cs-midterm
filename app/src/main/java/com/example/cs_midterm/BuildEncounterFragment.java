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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildEncounterFragment extends Fragment {
    private TextView textViewResult;
    private ListView monsterList;
    private EditText monsterSearch;
    private MonstersApi monstersApi;
    private Encounter encounter;
    private MonstersAdapter monsterSearchAdapter;

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

        // initialize views
        textViewResult = view.findViewById(R.id.textView_result);
        monsterList = view.findViewById(R.id.listView_monsters);
        monsterSearch = view.findViewById(R.id.editText_monsterSearch);
        ArrayList<Monster> monsterArray = new ArrayList<>();

        // back button for returning to create encounters screen
        view.findViewById(R.id.button_backBuildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuildEncounterFragment.this)
                        .navigate(R.id.action_buildEncounterFragment_to_createEncountersFragment);
            }
        });
        // create new encounter object and monster list
        encounter = CreateEncountersFragment.getEncounter();

        // API integration
        // create Retrofit object for API use
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        monstersApi = retrofit.create(MonstersApi.class);
        getMonsters();

        monsterSearchAdapter = new MonstersAdapter(getActivity(), R.layout.item_monster, encounter.getMonsters());
        // Assign adapter to ListView
        monsterList.setAdapter(monsterSearchAdapter);
        //enables filtering for the contents of the given ListView
        monsterList.setTextFilterEnabled(true);

        monsterList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                // When clicked, show a toast with the TextView text
                Monster monster = (Monster) parent.getItemAtPosition(position);
                Toast.makeText(getActivity(),
                        monster.getName(), Toast.LENGTH_SHORT).show();
            }
        });
        // Check for search query
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
        });
    }

    private void getMonsters() {
        Call<Monsters> monstersCall = monstersApi.getMonsters();

        // populate list of monsters from the API
        monstersCall.enqueue(new Callback<Monsters>() {
            @Override
            public void onResponse(Call<Monsters> call, Response<Monsters> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    textViewResult.setText("Code: " + response.code());
                    return;
                }
                // display each monster's info
                Monsters monsters = response.body();
                // loop through entire monster database
                for (Results result : monsters.getResults()) {
                    Monster monster = new Monster();
                    monster.setIndex(result.getIndex());
                    monster.setName(result.getName());
                    monster.setUrl(result.getUrl());
                    encounter.getMonsters().add(monster);
                }
            }
            @Override
            public void onFailure(Call<Monsters> call, Throwable t) {
                // error handling
                textViewResult.setText(t.getMessage());
            }
        });
    }
}