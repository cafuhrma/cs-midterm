package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;

public class Monster implements ProficienciesApi { // implements ProficienciesApi
    // public member fields
    public String index, name, size, type, subtype, alignment, hit_dice, languages, url;
    public String[] damage_vulnerabilities, damage_resistances, damage_immunities, condition_immunities;
    public int armor_class, hit_points, strength, dexterity, constitution, intelligence, wisdom, charisma, challenge, xp;
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
    public int getChallenge() {
        return challenge;
    }

    // mutators
    public void setIndex(String index) {
        this.index = index;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }
    public void setAlignment(String alignment) {
        this.alignment = alignment;
    }
    public void setHit_dice(String hit_dice) {
        this.hit_dice = hit_dice;
    }
    public void setLanguages(String languages) {
        this.languages = languages;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setDamage_vulnerabilities(String[] damage_vulnerabilities) {
        this.damage_vulnerabilities = damage_vulnerabilities;
    }
    public void setDamage_resistances(String[] damage_resistances) {
        this.damage_resistances = damage_resistances;
    }
    public void setDamage_immunities(String[] damage_immunities) {
        this.damage_immunities = damage_immunities;
    }
    public void setCondition_immunities(String[] condition_immunities) {
        this.condition_immunities = condition_immunities;
    }
    public void setArmor_class(int armor_class) {
        this.armor_class = armor_class;
    }
    public void setHit_points(int hit_points) {
        this.hit_points = hit_points;
    }
    public void setStrength(int strength) {
        this.strength = strength;
    }
    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }
    public void setConstitution(int constitution) {
        this.constitution = constitution;
    }
    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }
    public void setWisdom(int wisdom) {
        this.wisdom = wisdom;
    }
    public void setCharisma(int charisma) {
        this.charisma = charisma;
    }
    public void setChallenge(int challenge) {
        this.challenge = challenge;
    }
    public void setXp(int xp) {
        this.xp = xp;
    }
    public void setSpeed(Call<List> speed) {
        this.speed = speed;
    }
    public void setProficiencies(Call<List> proficiencies) {
        this.proficiencies = proficiencies;
    }
    public void setSenses(Call<List> senses) {
        this.senses = senses;
    }
    public void setSpecial_abilities(Call<List> special_abilities) {
        this.special_abilities = special_abilities;
    }
    public void setActions(Call<List> actions) {
        this.actions = actions;
    }
    public void setLegendary_actions(Call<List> legendary_actions) {
        this.legendary_actions = legendary_actions;
    }

    // TODO finish incorporating APIs
    public Call<List> getSenses() {

        return senses;
    }
    public Call<List> getProficiencies() {
//        // create Retrofit object for API use
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.dnd5eapi.co/api/monsters/" + index).build();
//        proficiencies = ProficienciesApi.getProficiencies();
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
