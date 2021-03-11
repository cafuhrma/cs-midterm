package com.example.cs_midterm;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExpandableMonsterAdapter extends RecyclerView.Adapter<ExpandableMonsterAdapter.ViewHolder> {
    private List<Monster> repos;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public ExpandableMonsterAdapter(List<Monster> repos) {
        this.repos = repos;
        //set initial expanded state to false
        for (int i = 0; i < repos.size(); i++) {
            expandState.append(i, false);
        }
    }

    @Override
    public ExpandableMonsterAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.expandable_monster, viewGroup, false);
        return new ExpandableMonsterAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ExpandableMonsterAdapter.ViewHolder viewHolder, final int position) {

        viewHolder.setIsRecyclable(false);

        Monster monster = repos.get(position); // Get the data item for this position

        viewHolder.tvHeading.setText(monster.getName());
        viewHolder.tvSubheading.setText(monster.getSize() + " " + monster.getType()
                + " CR:" + monster.getChallenge_rating());
        viewHolder.name.setText(monster.getName());
        viewHolder.type.setText(monster.getSize() + " " + monster.getType() + ", " + monster.getAlignment());
        viewHolder.acHpSpeed.setText("Armor Class: " + monster.getArmor_class() +
                "\nHit Points: " + monster.getHit_points() + " (" + monster.getHit_dice() + ")\n" +
                "Speed: " + monster.getSpeed().speedString());
        viewHolder.abilities.setText("STR: " + monster.getStrength() + " DEX: " + monster.getDexterity() +
                " CON: " + monster.getConstitution() + " INT: " + monster.getIntelligence() +
                " WIS: " + monster.getWisdom() + " CHA: " + monster.getCharisma());
        String saves = "";
        String skills = "";
        for (Proficiencies proficiency : monster.getProficiencies()) {
            if (proficiency.proficiency.getName().startsWith("Saving")) {
                saves += proficiency.proficiencyString() + " ";
            }
            else {
                skills += proficiency.proficiencyString() + " ";
            }
        }
        viewHolder.skillsSaves.setText("Saving Throws: " + saves + "\nSkills: " + skills + "\n");
        // TODO: add senses and immunities/resistances/vulnerabilities
        viewHolder.languages.setText("Languages: " + monster.getLanguages());
        viewHolder.cr.setText("CR: " + monster.getChallenge_rating() + " (" + monster.getXp() + " XP)");
        // Features & Traits
        if (monster.getSpecial_abilities() != null) {
            String features = "\nFEATURES\n";
            for (SpecialAbility feature : monster.getSpecial_abilities()) {
                features += feature.name + ": " + feature.getDesc() + "\n\n";
            }
            viewHolder.features.setText(features);
        }
        else {
            viewHolder.features.setVisibility(View.GONE);
        }
        // Actions
        String actions = "\nACTIONS\n";
        for (Action action : monster.getActions()) {
            actions += action.getName() + ": " + action.getDesc() + "\n\n";
        }
        viewHolder.actions.setText(actions);
        // Reactions
        if (monster.getReactions() != null) {
            String reactions = "\nREACTIONS\n";
            for (Reaction reaction : monster.getReactions()) {
                reactions += reaction.getName() + ": " + reaction.getDesc() + "\n\n";
            }
            viewHolder.reactions.setText(reactions);
        }
        else {
            viewHolder.reactions.setVisibility(View.GONE);
        }
        // Legendary Actions
        if (monster.getLegendary_actions() != null) {
            String legendaryActions = "\nLEGENDARY ACTIONS\n";
            for (LegendaryAction legendaryAction : monster.getLegendary_actions()) {
                legendaryActions += legendaryAction.getName() + ": " + legendaryAction.getDesc() + "\n";
            }
            viewHolder.legendaryActions.setText(legendaryActions);
        }
        else {
            viewHolder.legendaryActions.setVisibility(View.GONE);
        }

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
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvHeading;
        private TextView tvSubheading;
        public RelativeLayout buttonLayout;
        public LinearLayout expandableLayout;
        private TextView name;
        private TextView type;
        private TextView acHpSpeed;
        private TextView abilities;
        private TextView skillsSaves;
        private TextView languages;
        private TextView cr;
        private TextView features;
        private TextView actions;
        private TextView reactions;
        private TextView legendaryActions;

        public ViewHolder(View view) {
            super(view);

            tvHeading = view.findViewById(R.id.tv_heading);
            tvSubheading = view.findViewById(R.id.tv_subheading);
            buttonLayout = view.findViewById(R.id.button);
            expandableLayout = view.findViewById(R.id.expandableLayout);
            name = view.findViewById(R.id.tv_name);
            type = view.findViewById(R.id.tv_type);
            acHpSpeed = view.findViewById(R.id.tv_acHp);
            abilities = view.findViewById(R.id.tv_abilities);
            skillsSaves = view.findViewById(R.id.tv_skillsSaves);
            languages = view.findViewById(R.id.tv_languages);
            cr = view.findViewById(R.id.tv_cr);
            features = view.findViewById(R.id.tv_features);
            actions = view.findViewById(R.id.tv_actions);
            reactions = view.findViewById(R.id.tv_reactions);
            legendaryActions = view.findViewById(R.id.tv_legendaryActions);
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
