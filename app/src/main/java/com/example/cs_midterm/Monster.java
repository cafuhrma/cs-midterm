package com.example.cs_midterm;

import java.util.List;

public class Monster {

    // public member fields
    public String index, name, size, type, subtype, alignment, hit_dice, languages, url;
    public int armor_class, hit_points, strength, dexterity, constitution, intelligence, wisdom, charisma, xp;
    public Number challenge_rating;
    public List<String> damage_vulnerabilities;
    public List<String> damage_resistances;
    public List<String> damage_immunities;
    public List<ConditionType> condition_immunities;
    public List<Proficiencies> proficiencies;
    public List<SpecialAbility> special_abilities;
    public List<Action> actions;
    public List<Reaction> reactions;
    public List<LegendaryAction> legendary_actions;
    public Senses senses;
    public Speed speed;

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

    public String getUrl() {
        return url;
    }

    public int getArmor_class() {
        return armor_class;
    }

    public int getHit_points() {
        return hit_points;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getConstitution() {
        return constitution;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getWisdom() {
        return wisdom;
    }

    public int getCharisma() {
        return charisma;
    }

    public int getXp() {
        return xp;
    }

    public Number getChallenge_rating() {
        return challenge_rating;
    }

    public List<String> getDamage_vulnerabilities() {
        return damage_vulnerabilities;
    }

    public List<String> getDamage_resistances() {
        return damage_resistances;
    }

    public List<String> getDamage_immunities() {
        return damage_immunities;
    }

    public List<ConditionType> getCondition_immunities() {
        return condition_immunities;
    }

    public List<Proficiencies> getProficiencies() {
        return proficiencies;
    }

    public List<SpecialAbility> getSpecial_abilities() {
        return special_abilities;
    }

    public List<Action> getActions() {
        return actions;
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public List<LegendaryAction> getLegendary_actions() {
        return legendary_actions;
    }

    public Senses getSenses() {
        return senses;
    }

    public Speed getSpeed() {
        return speed;
    }
}
