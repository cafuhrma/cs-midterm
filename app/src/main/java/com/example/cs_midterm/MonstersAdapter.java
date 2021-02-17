package com.example.cs_midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;

public class MonstersAdapter extends ArrayAdapter<Monster> {
    private final ArrayList<Monster> originalList;
    private ArrayList<Monster> monsterList;
    private SearchFilter filter;

    public MonstersAdapter(Context context, int textViewResourceId, ArrayList<Monster> monsterList) {
        super(context, 0, textViewResourceId, monsterList);
        this.monsterList = new ArrayList<>();
        this.monsterList.addAll(monsterList);
        this.originalList = new ArrayList<>();
        this.originalList.addAll(monsterList);
    }

    @Override
    public Filter getFilter() {
        if (filter == null){
            filter  = new SearchFilter();
        }
        return filter;
    }

    private static class ViewHolder {
        TextView name;
        TextView cr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_monster, parent, false);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView_name);
            holder.cr = (TextView) convertView.findViewById(R.id.textView_challenge);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        // Get the data item for this position
        Monster monster = getItem(position);
        // Populate the data into the template view using the data object
        holder.name.setText(monster.getName());
        String cr = "CR: " + monster.getChallenge_rating();
        holder.cr.setText(cr);
        // Return the completed view to render on screen
        return convertView;
    }

    // Filter by name (search bar)
    public class SearchFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            constraint = constraint.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if(constraint != null && constraint.toString().length() > 0)
            {
                ArrayList<Monster> filteredItems = new ArrayList<>();

                for(int i = 0, l = originalList.size(); i < l; i++)
                {
                    Monster monster = originalList.get(i);
                    if(monster.getName().toLowerCase().startsWith(constraint.toString()))
                        filteredItems.add(monster);
                }
                result.count = filteredItems.size();
                result.values = filteredItems;
            }
            else
            {
                synchronized(this)
                {
                    result.values = originalList;
                    result.count = originalList.size();
                }
            }
            return result;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint,
                                      FilterResults results) {

            monsterList = (ArrayList<Monster>)results.values;
            notifyDataSetChanged();
            clear();
            for(int i = 0; i < monsterList.size(); i++)
                add(monsterList.get(i));
            notifyDataSetInvalidated();
        }
    }

    // TODO filter by CR/XP
}
