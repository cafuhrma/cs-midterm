package com.example.cs_midterm;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ViewableEncounterAdapter extends RecyclerView.Adapter<ViewableEncounterAdapter.ViewHolder> {
    private List<Encounter> repos;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public ViewableEncounterAdapter(List<Encounter> repos) {
        this.repos = repos;
        //set initial expanded state to false
        for (int i = 0; i < repos.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ViewableEncounterAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.viewable_encounter, viewGroup, false);
        return new ViewableEncounterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewableEncounterAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.setIsRecyclable(false);

        Encounter encounter = repos.get(position); // Get the data item for this position
        ExpandableMonsterAdapter recyclerAdapter = new ExpandableMonsterAdapter(encounter.getMonsters());
        viewHolder.recyclerView.setAdapter(recyclerAdapter);
        viewHolder.ptHeading.setText("Encounter " + (position+1));

        //check if view is expanded
        final boolean isExpanded = expandState.get(position);
        viewHolder.expandableLayout.setVisibility(isExpanded?View.VISIBLE:View.GONE);

        viewHolder.buttonLayout.setRotation(expandState.get(position) ? 180f : 0f);
        viewHolder.buttonLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                onClickExpand(viewHolder.expandableLayout, viewHolder.buttonLayout,  position);
            }
        });

        viewHolder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().myEncounters.remove(position);
                notifyDataSetChanged();
            }
        });

        viewHolder.ptHeading.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    encounter.setName((String)s);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView ptHeading;
        private RecyclerView recyclerView;
        private Button removeButton;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;

        public ViewHolder(View view) {
            super(view);

            ptHeading = (EditText) view.findViewById(R.id.pt_heading);
            recyclerView = (RecyclerView) view.findViewById(R.id.expandable_recyclerView);
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(layoutManager);
            removeButton = (Button) view.findViewById(R.id.button_removeEncounter);
            buttonLayout = (RelativeLayout) view.findViewById(R.id.button);
            expandableLayout = (LinearLayout) view.findViewById(R.id.expandableLayout);
        }
    }

    private void onClickExpand(final LinearLayout expandableLayout, final RelativeLayout buttonLayout, final  int position) {

        //Simply set View to Gone if not expanded
        //Not necessary but I put simple rotation on button layout
        if (expandableLayout.getVisibility() == View.VISIBLE){
            createRotateAnimator(buttonLayout, 180f, 0f).start();
            expandableLayout.setVisibility(View.GONE);
            expandState.put(position, false);
        }
        else {
            createRotateAnimator(buttonLayout, 0f, 180f).start();
            expandableLayout.setVisibility(View.VISIBLE);
            expandState.put(position, true);
        }
    }

    //Code to rotate button
    private ObjectAnimator createRotateAnimator(final View target, final float from, final float to) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(target, "rotation", from, to);
        animator.setDuration(300);
        animator.setInterpolator(new LinearInterpolator());
        return animator;
    }
}
