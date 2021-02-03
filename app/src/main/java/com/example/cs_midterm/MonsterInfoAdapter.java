package com.example.cs_midterm;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TableLayout;

public class MonsterInfoAdapter extends ArrayAdapter {
    public static final int ITEM_BASIC = 0;
    public static final int ITEM_ATTRIBUTES = 1;
    public static final int ITEM_SAVES = 2;
    public static final int ITEM_SKILLS = 3;
    public static final int ITEM_OTHER = 4;
    public static final int ITEM_FEATURES = 5;
    public static final int ITEM_ACTIONS = 6;

    private ListViewItem[] objects;

    @Override
    public int getViewTypeCount() {
        return 7;
    }

    @Override
    public int getItemViewType(int position) {
        return objects[position].getType();
    }

    public MonsterInfoAdapter(Context context, int resource, ListViewItem[] objects) {
        super(context, resource, objects);
        this.objects = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder = null;
        ListViewItem listViewItem = objects[position];
        int listViewItemType = getItemViewType(position);


        if (convertView == null) {
            if (listViewItemType == ITEM_BASIC) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_basic_info, null);
            } else if (listViewItemType == ITEM_ATTRIBUTES) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_attributes, null);
            } else if (listViewItemType == ITEM_SAVES) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_saves, null);
            } else if (listViewItemType == ITEM_SKILLS) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_skills, null);
            } else if (listViewItemType == ITEM_OTHER) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_other_info, null);
            } else if (listViewItemType == ITEM_FEATURES) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_features, null);
            } else {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_actions, null);
            }

            TableLayout table = convertView.findViewById(R.id.tableLayout);
            viewHolder = new ViewHolder(table);

            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    // prevent memory leaks
    private class ViewHolder {
        TableLayout table;
        public ViewHolder(TableLayout table) {
            this.table = table;
        }

        public TableLayout getTable() {
            return table;
        }

        public void setTable(TableLayout table) {
            this.table = table;
        }
    }
}
