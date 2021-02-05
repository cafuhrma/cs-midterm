package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Monster implements ProficienciesAPI { // implements ProficienciesAPI
    // public member fields
    public String index, name, size, type, subtype, alignment, hit_dice, languages, challenge, url;
    public String[] damage_vulnerabilities, damage_resistances, damage_immunities, condition_immunities;
    public int armor_class, hit_points, strength, dexterity, constitution, intelligence, wisdom, charisma, xp;
    public Call<List> speed, proficiencies, senses, special_abilities, actions, legendary_actions;

    // accessors
    public String getIndex() {
        return index;
    }
    public String getName() {
        return name;
    }
    public String getSize() {
        return size;
    }
    public String getType() {
        return type;
    }
    public String getSubtype() {
        return subtype;
    }
    public String getAlignment() {
        return alignment;
    }
    public String getHit_dice() {
        return hit_dice;
    }
    public String getLanguages() {
        return languages;
    }
    public String getChallenge() {
        return challenge;
    }
    public String getUrl() { return url; }
    public String[] getDamage_vulnerabilities() {
        return damage_vulnerabilities;
    }
    public String[] getDamage_resistances() {
        return damage_resistances;
    }
    public String[] getDamage_immunities() {
        return damage_immunities;
    }
    public String[] getCondition_immunities() {
        return condition_immunities;
    }
    public int getArmor_class() {
        return armor_class;
    }
    public int getHit_points() {
        return hit_points;
    }
    public int getXp() {
        return xp;
    }
    public int getStrength() { return strength; }
    public int getDexterity() { return dexterity; }
    public int getConstitution() { return constitution; }
    public int getIntelligence() { return intelligence; }
    public int getWisdom() { return wisdom; }
    public int getCharisma() { return charisma; }

    // TODO finish incorporating APIs

    public Call<List> getSenses() {

        return senses;
    }
    public Call<List> getProficiencies() {
//        // create Retrofit object for API use
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.dnd5eapi.co/api/monsters/" + index).build();
//        proficiencies = ProficienciesAPI.getProficiencies();
        return proficiencies;
    }
    public Call<List> getSpeed() {

        return speed;
    }
    public Call<List> getSpecial_abilities() {

        return special_abilities;
    }
    public Call<List> getActions() {

        return actions;
    }
    public Call<List> getLegendary_actions() {

        return legendary_actions;
    }
}
