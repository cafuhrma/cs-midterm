package com.example.cs_midterm;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ExpandableMonsterAdapter extends RecyclerView.Adapter<ExpandableMonsterAdapter.ViewHolder> implements Filterable {
    private List<Monster> repos;
    private List<Monster> reposFull;
    private SparseBooleanArray expandState = new SparseBooleanArray();
    private Context context;

    public ExpandableMonsterAdapter(List<Monster> repos) {
        this.repos = repos;
        reposFull = new ArrayList<>(repos);
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
        String subHead = monster.getSize() + " " + monster.getType() + " CR:" + monster.getChallenge_rating();
        viewHolder.tvSubheading.setText(subHead);
        viewHolder.name.setText(monster.getName());
        String type = monster.getSize() + " " + monster.getType() + ", " + monster.getAlignment();
        viewHolder.type.setText(type);
        String ac = "Armor Class: " + monster.getArmor_class() + "\nHit Points: " + monster.getHit_points() +
                " (" + monster.getHit_dice() + ")\n" + "Speed: " + monster.getSpeed().speedString();
        viewHolder.acHpSpeed.setText(ac);
        String abilities = "STR: " + monster.getStrength() + " DEX: " + monster.getDexterity() +
                " CON: " + monster.getConstitution() + " INT: " + monster.getIntelligence() +
                " WIS: " + monster.getWisdom() + " CHA: " + monster.getCharisma();
        viewHolder.abilities.setText(abilities);
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
        String skillsSaves = "Saving Throws: " + saves + "\nSkills: " + skills + "\n";
        viewHolder.skillsSaves.setText(skillsSaves);
        if (monster.getDamage_vulnerabilities() != null) {
            String vulnerabilities = "Damage Vulnerabilities: ";
            for (String vulnerability : monster.getDamage_vulnerabilities()) {
                vulnerabilities += vulnerability + ", ";
            }
            viewHolder.vulnerabilities.setText(vulnerabilities);
        }
        else {
            viewHolder.vulnerabilities.setVisibility(View.GONE);
        }
        if (monster.getDamage_resistances() != null) {
            String resistances = "Damage Resistances: ";
            for (String resistance : monster.getDamage_resistances()) {
                resistances += resistance + ", ";
            }
            viewHolder.resistances.setText(resistances);
        }
        else {
            viewHolder.resistances.setVisibility(View.GONE);
        }
        if (monster.getDamage_immunities() != null) {
            String immunities = "Damage Immunities: ";
            for (String immunity : monster.getDamage_immunities()) {
                immunities += immunity + ", ";
            }
            viewHolder.immunities.setText(immunities);
        }
        else {
            viewHolder.immunities.setVisibility(View.GONE);
        }
        if (monster.getCondition_immunities() != null ) {
            String conditionImmunities = "Condition Immunities: ";
            for (ConditionType condition : monster.getCondition_immunities()) {
                conditionImmunities += condition.getName() + ", ";
            }
            viewHolder.conditionImmunities.setText(conditionImmunities);
        }
        else {
            viewHolder.conditionImmunities.setVisibility(View.GONE);
        }
        String senses = "Senses: " + monster.getSenses().senseString();
        viewHolder.senses.setText(senses);
        String language = "Languages: " + monster.getLanguages();
        viewHolder.languages.setText(language);
        String cr = "CR: " + monster.getChallenge_rating() + " (" + monster.getXp() + " XP)";
        viewHolder.cr.setText(cr);
        // Features & Traits
        if (monster.getSpecial_abilities() != null ) {
            String features = "";
            for (SpecialAbility feature : monster.getSpecial_abilities()) {
                features += feature.name + ": " + feature.getDesc() + "\n\n";
            }
            viewHolder.features.setText(features);
        }
        else {
            viewHolder.features.setVisibility(View.GONE);
        }
        // Actions
        String actions = "";
        for (Action action : monster.getActions()) {
            actions += action.getName() + ": " + action.getDesc() + "\n\n";
        }
        viewHolder.actions.setText(actions);
        // Reactions
        if (monster.getReactions() != null) {
            String reactions = "";
            for (Reaction reaction : monster.getReactions()) {
                reactions += reaction.getName() + ": " + reaction.getDesc() + "\n\n";
            }
            viewHolder.reactions.setText(reactions);
        }
        else {
            viewHolder.reactions.setVisibility(View.GONE);
            viewHolder.displayReactions.setVisibility(View.GONE);
            viewHolder.reactionsDivider.setVisibility(View.GONE);
        }
        // Legendary Actions
        if (monster.getLegendary_actions() != null) {
            String legendaryActions = "";
            for (LegendaryAction legendaryAction : monster.getLegendary_actions()) {
                legendaryActions += legendaryAction.getName() + ": " + legendaryAction.getDesc() + "\n";
            }
            viewHolder.legendaryActions.setText(legendaryActions);
        }
        else {
            viewHolder.legendaryActions.setVisibility(View.GONE);
            viewHolder.displayLegendary.setVisibility(View.GONE);
            viewHolder.legendaryDivider.setVisibility(View.GONE);
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
        private TextView vulnerabilities;
        private TextView resistances;
        private TextView immunities;
        private TextView conditionImmunities;
        private TextView senses;
        private TextView languages;
        private TextView cr;
        private TextView features;
        private TextView displayActions;
        private TextView actions;
        private TextView displayReactions;
        private View reactionsDivider;
        private TextView reactions;
        private TextView displayLegendary;
        private View legendaryDivider;
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
            vulnerabilities = view.findViewById(R.id.tv_vulnerabilities);
            resistances = view.findViewById(R.id.tv_resistances);
            immunities = view.findViewById(R.id.tv_immunities);
            conditionImmunities = view.findViewById(R.id.tv_conditionImmunities);
            senses = view.findViewById(R.id.tv_senses);
            languages = view.findViewById(R.id.tv_languages);
            cr = view.findViewById(R.id.tv_cr);
            features = view.findViewById(R.id.tv_features);
            displayActions = view.findViewById(R.id.tv_actionsDisplay);
            actions = view.findViewById(R.id.tv_actions);
            displayReactions = view.findViewById(R.id.tv_reactionsDisplay);
            reactionsDivider = view.findViewById(R.id.view_divider6);
            reactions = view.findViewById(R.id.tv_reactions);
            displayLegendary = view.findViewById(R.id.tv_legendaryDisplay);
            legendaryDivider = view.findViewById(R.id.view_divider7);
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

    @Override
    public Filter getFilter() {
        return reposFilter;
    }

    private Filter reposFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Monster> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(reposFull);
            }
            else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Monster monster : reposFull) {
                    if (monster.getName().toLowerCase().startsWith(filterPattern)) {
                        filteredList.add(monster);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            repos.clear();
            repos.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}
