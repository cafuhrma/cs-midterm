package com.example.cs_midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EncounterAdapter extends ArrayAdapter<Encounter> {
    private ArrayList<Encounter> encounterList;


    public EncounterAdapter( Context context, int textViewResourceId, List<Encounter> encounterList) {
        super(context, textViewResourceId, encounterList);
        this.encounterList = new ArrayList<>();
        this.encounterList.addAll(encounterList);
    }

    private static class ViewHolder {
        TextView index;
        ListView encounterInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EncounterAdapter.ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_encounter, parent, false);

            holder = new EncounterAdapter.ViewHolder();
            holder.index = (TextView) convertView.findViewById(R.id.textView_encounterIndex);
            holder.encounterInfo = (ListView) convertView.findViewById(R.id.listView_encounterInfo);

            convertView.setTag(holder);
        } else {
            holder = (EncounterAdapter.ViewHolder) convertView.getTag();
        }
        Encounter encounter = getItem(position); // Get the data item for this position
        MonstersAdapter monstersAdapter = new MonstersAdapter(getContext(), R.layout.item_monster, encounter.getMonsters());
        holder.encounterInfo.setAdapter(monstersAdapter);
        holder.index.setText("Encounter #" + (position+1));
        return convertView; // Return the completed view to render on screen
    }
}
