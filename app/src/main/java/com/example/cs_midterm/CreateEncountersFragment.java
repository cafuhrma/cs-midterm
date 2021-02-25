package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateEncountersFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_encounters, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Singleton.getInstance().fillList();
        // Default encounter values
        Singleton.getInstance().encounter.setPartyLevel(3);
        Singleton.getInstance().encounter.setPartySize(4);
        Singleton.getInstance().encounter.setDifficulty("Hard");
        Singleton.getInstance().encounter.setType("Boss");

        // party level spinner
        ArrayAdapter<String> levelAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.partyLevels));
        levelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner levelSpinner = view.findViewById(R.id.spinner_partyLevel);
        levelSpinner.setAdapter(levelAdapter);
        levelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Singleton.getInstance().encounter.setPartyLevel(1);
                        break;
                    case 1:
                        Singleton.getInstance().encounter.setPartyLevel(2);
                        break;
                    case 2:
                        Singleton.getInstance().encounter.setPartyLevel(3);
                        break;
                    case 3:
                        Singleton.getInstance().encounter.setPartyLevel(4);
                        break;
                    case 4:
                        Singleton.getInstance().encounter.setPartyLevel(5);
                        break;
                    case 5:
                        Singleton.getInstance().encounter.setPartyLevel(6);
                        break;
                    case 6:
                        Singleton.getInstance().encounter.setPartyLevel(7);
                        break;
                    case 7:
                        Singleton.getInstance().encounter.setPartyLevel(8);
                        break;
                    case 8:
                        Singleton.getInstance().encounter.setPartyLevel(9);
                        break;
                    case 9:
                        Singleton.getInstance().encounter.setPartyLevel(10);
                        break;
                    case 10:
                        Singleton.getInstance().encounter.setPartyLevel(11);
                        break;
                    case 11:
                        Singleton.getInstance().encounter.setPartyLevel(12);
                        break;
                    case 12:
                        Singleton.getInstance().encounter.setPartyLevel(13);
                        break;
                    case 13:
                        Singleton.getInstance().encounter.setPartyLevel(14);
                        break;
                    case 14:
                        Singleton.getInstance().encounter.setPartyLevel(15);
                        break;
                    case 15:
                        Singleton.getInstance().encounter.setPartyLevel(16);
                        break;
                    case 16:
                        Singleton.getInstance().encounter.setPartyLevel(17);
                        break;
                    case 17:
                        Singleton.getInstance().encounter.setPartyLevel(18);
                        break;
                    case 18:
                        Singleton.getInstance().encounter.setPartyLevel(19);
                        break;
                    case 19:
                        Singleton.getInstance().encounter.setPartyLevel(20);
                        break;
                    case 20:
                        Random rand = new Random();
                        int num = rand.nextInt(20) + 1;
                        Singleton.getInstance().encounter.setPartyLevel(num);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // party size spinner
        ArrayAdapter<String> sizeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.partySize));
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sizeSpinner = view.findViewById(R.id.spinner_partySize);
        sizeSpinner.setAdapter(sizeAdapter);
        sizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Singleton.getInstance().encounter.setPartySize(1);
                        break;
                    case 1:
                        Singleton.getInstance().encounter.setPartySize(2);
                        break;
                    case 2:
                        Singleton.getInstance().encounter.setPartySize(3);
                        break;
                    case 3:
                        Singleton.getInstance().encounter.setPartySize(4);
                        break;
                    case 4:
                        Singleton.getInstance().encounter.setPartySize(5);
                        break;
                    case 5:
                        Singleton.getInstance().encounter.setPartySize(6);
                        break;
                    case 6:
                        Singleton.getInstance().encounter.setPartySize(7);
                        break;
                    case 7:
                        Singleton.getInstance().encounter.setPartySize(8);
                        break;
                    case 8:
                        Singleton.getInstance().encounter.setPartySize(9);
                        break;
                    case 9:
                        Singleton.getInstance().encounter.setPartySize(10);
                        break;
                    case 10:
                        Random rand = new Random();
                        int num = rand.nextInt(10) + 1;
                        Singleton.getInstance().encounter.setPartySize(num);
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // difficulty spinner
        ArrayAdapter<String> diffAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.difficulty));
        diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner diffSpinner = view.findViewById(R.id.spinner_difficulty);
        diffSpinner.setAdapter(diffAdapter);
        diffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Random rand = new Random();
                        int num = rand.nextInt(4);
                        switch (num) {
                            case 0:
                                Singleton.getInstance().encounter.setDifficulty("Easy");
                                break;
                            case 1:
                                Singleton.getInstance().encounter.setDifficulty("Medium");
                                break;
                            case 2:
                                Singleton.getInstance().encounter.setDifficulty("Hard");
                                break;
                            case 3:
                                Singleton.getInstance().encounter.setDifficulty("Deadly");
                                break;
                        }
                        break;
                    case 1:
                        Singleton.getInstance().encounter.setDifficulty("Easy");
                        break;
                    case 2:
                        Singleton.getInstance().encounter.setDifficulty("Medium");
                        break;
                    case 3:
                        Singleton.getInstance().encounter.setDifficulty("Hard");
                        break;
                    case 4:
                        Singleton.getInstance().encounter.setDifficulty("Deadly");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // encounter type spinner
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.encounterType));
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner typeSpinner = view.findViewById(R.id.spinner_encounterType);
        typeSpinner.setAdapter(typeAdapter);
        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Random rand = new Random();
                        int num = rand.nextInt(2);
                        if (num == 0) {
                            Singleton.getInstance().encounter.setType("Boss");
                        }
                        else {
                            Singleton.getInstance().encounter.setType("Horde");
                        }
                        break;
                    case 1:
                        Singleton.getInstance().encounter.setType("Boss");
                        break;
                    case 2:
                        Singleton.getInstance().encounter.setType("Horde");
                        break;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // back button to return to home screen
        view.findViewById(R.id.button_backCreate).setOnClickListener(view1 -> NavHostFragment.findNavController(CreateEncountersFragment.this)
                .navigate(R.id.action_createEncountersFragment_to_HomeFragment));
        // switch from create encounters screen to random encounters screen
        view.findViewById(R.id.button_randomList).setOnClickListener(v -> NavHostFragment.findNavController(CreateEncountersFragment.this)
                .navigate(R.id.action_createEncountersFragment_to_randomEncountersFragment));
        // switch from create encounters screen to build encounter screen
        view.findViewById(R.id.button_buildEncounter).setOnClickListener(v -> NavHostFragment.findNavController(CreateEncountersFragment.this)
                .navigate(R.id.action_createEncountersFragment_to_buildEncounterFragment));
    }
}