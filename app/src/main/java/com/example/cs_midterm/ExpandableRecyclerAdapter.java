package com.example.cs_midterm;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.List;

public class ExpandableRecyclerAdapter extends RecyclerView.Adapter<ExpandableRecyclerAdapter.ViewHolder> {
    private List<Encounter> repos;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public ExpandableRecyclerAdapter(List<Encounter> repos) {
        this.repos = repos;
        //set initial expanded state to false
        for (int i = 0; i < repos.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ExpandableRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expandable_encounter, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ExpandableRecyclerAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.setIsRecyclable(false);

        Encounter encounter = repos.get(position); // Get the data item for this position
        MonstersAdapter monstersAdapter = new MonstersAdapter(context, R.layout.item_monster, encounter.getMonsters());
        viewHolder.lvEncounter.setAdapter(monstersAdapter);
        viewHolder.tvHeading.setText("Encounter " + (position+1));

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

        viewHolder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Singleton.getInstance().myEncounters.add(encounter);
            }
        });
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvHeading;
        private ListView lvEncounter;
        private Button saveButton;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;

        public ViewHolder(View view) {
            super(view);

            tvHeading = (TextView)view.findViewById(R.id.tv_heading);
            lvEncounter = (ListView)view.findViewById(R.id.expandable_listView);
            saveButton = (Button) view.findViewById(R.id.button_saveRandomEncounter);
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
