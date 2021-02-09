package com.example.cs_midterm;

import java.util.List;

public class Action {
    String name, desc;
    int attack_bonus;
    DifficultyClass dc;
    List<Damage> damage;

    // accessors
    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getAttack_bonus() {
        return attack_bonus;
    }

    public DifficultyClass getDc() {
        return dc;
    }

    public List<Damage> getDamage() {
        return damage;
    }
}
