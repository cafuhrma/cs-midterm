package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuildEncounterFragment extends Fragment {
    private TextView textViewResult;
    private ListView monsterListView;
    private EditText monsterSearch;
    private MonstersAdapter monsterSearchAdapter;
    private ArrayList<Monster> monsterList;
    private Encounter encounter;

    private MonstersApi monstersApi;
    private MonsterApi monsterApi;
    private Retrofit retrofit;

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
        textViewResult = view.findViewById(R.id.textView_result);
        monsterListView = view.findViewById(R.id.listView_monsters);
        monsterSearch = view.findViewById(R.id.editText_monsterSearch);
        encounter = new Encounter();
        monsterList = new ArrayList<>();

//        EncounterViewModel viewModel = new ViewModelProvider(requireActivity()).get(EncounterViewModel.class);
//        viewModel.getEncounter().observe(getViewLifecycleOwner(), encounter -> {
//            monsterList.addAll(encounter.getMonsterList());
//        });

        // back button for returning to create encounters screen
        view.findViewById(R.id.button_backBuildEncounter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(BuildEncounterFragment.this)
                        .navigate(R.id.action_buildEncounterFragment_to_createEncountersFragment);
            }
        });

        // API integration
        monsterList = new ArrayList<>();
        // create Retrofit object for API use
        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Call the API and populate list of monsters
        monstersApi = retrofit.create(MonstersApi.class);
        monsterApi = retrofit.create(MonsterApi.class);
        getMonsters();

        monsterSearchAdapter = new MonstersAdapter(getActivity(), R.layout.item_monster, monsterList);
        // Assign adapter to ListView
        monsterListView.setAdapter(monsterSearchAdapter);
        //enables filtering for the contents of the given ListView
        monsterListView.setTextFilterEnabled(true);

        monsterListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
                startActivity(getActivity().getIntent());
            }
            @Override
            public void afterTextChanged(Editable s) {
                startActivity(getActivity().getIntent());
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                monsterSearchAdapter.getFilter().filter(s.toString());
                startActivity(getActivity().getIntent());
            }
        });
        startActivity(getActivity().getIntent());
    }

    private void getMonsters() {
        Call<Monsters> call = monstersApi.getMonsters();

        // populate list of monsters from the API
        call.enqueue(new Callback<Monsters>() {
            @Override
            public void onResponse(@NotNull Call<Monsters> call, @NotNull Response<Monsters> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    textViewResult.setText("Code: " + response.code());
                    Log.d("ERROR", "Monsters Response Unsuccessful");
                    return;
                }
                // display each monster's info
                Monsters monsters = response.body();
                // loop through entire monster database
                for (int i = 0; i < monsters.getResults().size(); i++) {
                    Result result = monsters.getResults().get(i);
                    getMonster(result.getIndex());
                }
            }
            @Override
            public void onFailure(@NotNull Call<Monsters> call, @NotNull Throwable t) {
                // error handling
                textViewResult.setText(t.getMessage());
                Log.d("ERROR", "Monsters Failure");
            }
        });
    }
    private void getMonster(String monsterIndex) {
        Call<Monster> call = monsterApi.getMonster(monsterIndex);

        call.enqueue(new Callback<Monster>() {
            @Override
            public void onResponse(@NotNull Call<Monster> call, @NotNull Response<Monster> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    textViewResult.setText("Code: " + response.code());
                    Log.d("ERROR", "Monster Response Unsuccessful");
                    return;
                }
                Monster monster = response.body();
                monsterList.add(monster);
            }
            @Override
            public void onFailure(@NotNull Call<Monster> call, @NotNull Throwable t) {
                // error handling
                textViewResult.setText(t.getMessage());
                Log.d("ERROR", "Monster Failure");
            }
        });
    }
}