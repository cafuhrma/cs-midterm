package com.example.cs_midterm;

public class Senses {
    String blindsight, darkvision, tremorsense, truesight;
    int passive_perception;

    //accessors
    public String getBlindsight() {
        return blindsight;
    }

    public String getDarkvision() {
        return darkvision;
    }

    public String getTremorsense() {
        return tremorsense;
    }

    public String getTruesight() {
        return truesight;
    }

    public int getPassive_perception() {
        return passive_perception;
    }

    public String senseString() {
        String senses = "";
        if (getBlindsight() != null) {
            senses += "Blindsight " + getBlindsight();
        }
        if (getDarkvision() != null) {
            senses += " Darkvision " + getDarkvision();
        }
        if (getTremorsense() != null) {
            senses += " Tremorsense " + getTremorsense();
        }
        if (getTruesight() != null) {
            senses += " Truesight " + getTruesight();
        }
        senses += ", Passive Perception " + getPassive_perception();
        return senses;
    }
}
