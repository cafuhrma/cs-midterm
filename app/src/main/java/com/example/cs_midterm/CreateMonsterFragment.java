package com.example.cs_midterm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class CreateMonsterFragment extends Fragment {
    // initialize views
    private Monster monster;
    private ListView monsterInfo;
    private ArrayAdapter<String> spinnerAdapter;
    private Spinner infoSpinner;
    private MonsterInfoAdapter infoAdapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_monster, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        monster = new Monster();

        // monster info spinner
        infoSpinner = view.findViewById(R.id.spinner_monsterInfo);
        infoSpinner.setAdapter(new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                getResources().getStringArray(R.array.monsterTabs)));

        monsterInfo = view.findViewById(R.id.listView_monsterInfo);

        //spinner selection events
        infoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
                if (position >= 0 && position < getResources().getStringArray(R.array.monsterTabs).length) {
                    getSelectedCategoryData(position);

                } else {
                    Toast.makeText(getActivity(), "Selected Category Does not Exist",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // save created monster
        view.findViewById(R.id.button_saveMonster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().myMonsters.add(monster);
                ((MainActivity)getActivity()).saveData(); // save changes made to myMonsters
            }
        });

        // return to my monsters screen
        view.findViewById(R.id.button_backCreateMonster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateMonsterFragment.this)
                        .navigate(R.id.action_createMonsterFragment_to_viewMonstersFragment);
            }
        });
    }

    private void getSelectedCategoryData(int categoryID) {
        //instatiate adapter a
        infoAdapter = new MonsterInfoAdapter(getActivity(), R.id.tableLayout, getInfoList(categoryID));
        //set the adapter to GridView
        monsterInfo.setAdapter(infoAdapter);
    }

    private ListViewItem[] getInfoList(int categoryID) {
        final ListViewItem[] info = new ListViewItem[1];
        // initialize the array
        switch (categoryID) {
            case 0:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_BASIC);
                break;
            case 1:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_ATTRIBUTES);
                break;
            case 2:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_SAVES);
                break;
            case 3:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_SKILLS);
                break;
            case 4:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_OTHER);
                break;
            case 5:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_FEATURES);
                break;
            case 6:
                info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_ACTIONS);
        }
        return info;
    }
}