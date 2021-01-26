package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class BuildEncounterFragment extends Fragment {
    // TODO
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

        view.findViewById(R.id.button_backBuildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuildEncounterFragment.this)
                        .navigate(R.id.action_buildEncounterFragment_to_createEncountersFragment);
            }
        });
        // create new encounter object
        Encounter encounter;
        // TODO finsih API integration
//        // create Retrofit object for API use
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.dnd5eapi.co/api/")
//                .build();
//        Call<List> monsterCall = MonsterAPI.getMonsters();
//        // display list of monsters
//        ArrayList<Monster> monsters;
//        monsterCall.enqueue(new Callback<List>() {
//            @Override
//            public void onResponse(Call<List> call, Response<List> response) {
//
//                if (response.isSuccessful()) {
//                    List monsters = response.body();
//                    Log.d("onResponse: ", monsters.get(3).getName().toString());
//                } else {
//                    Log.d("onResponse: ", "respnse...Failed");
//                    return;
//                }
//            }
//            @Override
//            public void onFailure(Call<List> call, Throwable t) {
//                Log.d("OnFailure:", "Errror!");
//            }
//        });
//        Log.d("Bruh","You lookin good!");
        // monster name search filter
        ArrayList<Monster> monsters = new ArrayList<>();
        // hard coded monsters for testing
        Monster goblin = new Monster();
        goblin.index = "goblin";
        goblin.name = "Goblin";
        goblin.challenge = "1/4";
        goblin.xp = 50;
        Monster bandit = new Monster();
        bandit.index = "bandit";
        bandit.name = "Bandit";
        bandit.challenge = "1/8";
        bandit.xp = 25;
        monsters.add(goblin);
        monsters.add(bandit);
        monsterSearchAdapter = new MonstersAdapter(getActivity(), R.layout.item_monster, monsters);
        ListView monsterList = view.findViewById(R.id.listView_monsters);
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
        EditText monsterSearch = view.findViewById(R.id.editText_monsterSearch);
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
}