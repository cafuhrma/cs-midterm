package com.example.cs_midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EncounterAdapter extends ArrayAdapter<Encounter> {
    private ArrayList<Encounter> encounterList;
    private MonstersAdapter monstersAdapter;


    public EncounterAdapter( Context context, int textViewResourceId, List<Encounter> encounterList) {
        super(context, textViewResourceId, encounterList);
        this.encounterList = new ArrayList<>();
        this.encounterList.addAll(encounterList);
    }

    private static class ViewHolder {
        TextView encounterInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        EncounterAdapter.ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_encounter, parent, false);

            holder = new EncounterAdapter.ViewHolder();
            holder.encounterInfo = (TextView) convertView.findViewById(R.id.textView_encounterInfo);

            convertView.setTag(holder);
        } else {
            holder = (EncounterAdapter.ViewHolder) convertView.getTag();
        }
        Encounter encounter = getItem(position); // Get the data item for this position

        // Put the data into the template view using the data object
        String info = "";
        for (Monster monster : encounter.getMonsters()) {
            info += monster.getName() + "\n";
        }

        holder.encounterInfo.setText(info);
        // Return the completed view to render on screen
        return convertView;
    }
}
