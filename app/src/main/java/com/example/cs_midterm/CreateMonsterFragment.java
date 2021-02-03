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
    ListView monsterInfo;
    ArrayAdapter<String> spinnerAdapter;
    Spinner infoSpinner;
    MonsterInfoAdapter infoAdapter;

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

        view.findViewById(R.id.button_backCreateMonster).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CreateMonsterFragment.this)
                        .navigate(R.id.action_createMonsterFragment_to_viewMonstersFragment);
            }
        });

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
//                    if (infoSpinner.getPrompt() == "BASIC INFO") {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_BASIC);
//                    } else if (infoSpinner.getPrompt() == "ATTRIBUTES") {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_ATTRIBUTES);
//                    } else if (infoSpinner.getPrompt() == "SAVES") {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_SAVES);
//                    } else if (infoSpinner.getPrompt() == "SKILLS") {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_SKILLS);
//                    } else if (infoSpinner.getPrompt() == "OTHER INFO") {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_OTHER);
//                    } else if (infoSpinner.getPrompt() == "FEATURES") {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_FEATURES);
//                    } else {
//                        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_ACTIONS);
//                    }
                } else {
                    Toast.makeText(getActivity(), "Selected Category Does not Exist",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
//        infoAdapter = new MonsterInfoAdapter(getActivity(), R.id.tableLayout, getInfoList());
//        monsterInfo.setAdapter(infoAdapter);
    }
    // TODO finish setting up the category filter
    // resource: https://camposha.info/android-filter-listview-using-a-spinner-dropdown/
    private void getSelectedCategoryData(int categoryID) {
        //arraylist to hold selected cosmic bodies
        ArrayList<ListViewItem> categories = new ArrayList<>();
        if(categoryID == 0)
        {
            infoAdapter = new MonsterInfoAdapter(getActivity(), R.id.tableLayout, getInfoList());
        }else {
            //filter by id
            for (ListViewItem item : getInfoList()) {
                if (item.getType() == categoryID) {
                    categories.add(item);
                }
            }
            //instatiate adapter a
            infoAdapter = new MonsterInfoAdapter(getActivity(), R.id.tableLayout, getInfoList());
        }
        //set the adapter to GridView
        monsterInfo.setAdapter(infoAdapter);
    }
    private ListViewItem[] getInfoList() {
        final ListViewItem[] info = new ListViewItem[7];
        // initialize the array
        info[0] = new ListViewItem(MonsterInfoAdapter.ITEM_BASIC);
        info[1] = new ListViewItem(MonsterInfoAdapter.ITEM_ATTRIBUTES);
        info[2] = new ListViewItem(MonsterInfoAdapter.ITEM_SAVES);
        info[3] = new ListViewItem(MonsterInfoAdapter.ITEM_SKILLS);
        info[4] = new ListViewItem(MonsterInfoAdapter.ITEM_OTHER);
        info[5] = new ListViewItem(MonsterInfoAdapter.ITEM_FEATURES);
        info[6] = new ListViewItem(MonsterInfoAdapter.ITEM_ACTIONS);

        return info;
    }
}