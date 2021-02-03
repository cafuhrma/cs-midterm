package com.example.cs_midterm;

import android.widget.TableLayout;

public class ListViewItem {
    private int type;

    public ListViewItem(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
