package com.example.cs_midterm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MonstersAdapter extends ArrayAdapter<Monster> {
    public MonstersAdapter(Context context, ArrayList<Monster> monsters) {
        super(context, 0, monsters);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Monster monster = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_monster, parent, false);
        }
        // Lookup view for data population
        TextView textView_name = (TextView) convertView.findViewById(R.id.textView_name);
        // Populate the data into the template view using the data object
        textView_name.setText(monster.name);
        // Return the completed view to render on screen
        return convertView;
    }
}
