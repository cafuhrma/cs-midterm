package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Monster { // implements ProficiencyAPI
    // member fields
    public String index, name, size, type, subtype, alignment, hit_dice, senses, languages, challenge;
    public String[] speed, damage_vulnerabilities, damage_resistances, damage_immunities, condition_immunities;
    public int armor_class, hit_points, xp;
    public int[] abilityScores;
    public Call<List> proficiencies;

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
    public String getSenses() {
        return senses;
    }
    public String getLanguages() {
        return languages;
    }
    public String getChallenge() {
        return challenge;
    }
    public String[] getSpeed() {
        return speed;
    }
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
    public int[] getAbilityScores() {
        return abilityScores;
    }
    // TODO finish incorporating API for proficiencies
//    public Call<List> getProficiencies() {
//        // create Retrofit object for API use
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.dnd5eapi.co/api/monsters/" + index)
//                .build();
//        proficiencies = ProficiencyAPI.getProficiencies();
//        return proficiencies;
//    }
}
