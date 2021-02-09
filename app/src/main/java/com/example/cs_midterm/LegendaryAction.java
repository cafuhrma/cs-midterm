package com.example.cs_midterm;

import java.util.List;

public class LegendaryAction {
    String name, desc;
    int attack_bonus;
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

    public List<Damage> getDamage() {
        return damage;
    }
}
