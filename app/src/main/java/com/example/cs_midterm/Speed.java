package com.example.cs_midterm;

public class Speed {
    String walk, swim, fly, burrow, climb;

    // accessors
    public String getWalk() {
        return walk;
    }

    public String getSwim() {
        return swim;
    }

    public String getFly() {
        return fly;
    }

    public String getBurrow() {
        return burrow;
    }

    public String getClimb() {
        return climb;
    }

    public String speedString() {
        String speeds = "";
        if (walk != null) {
            speeds += walk;
        }
        if (swim != null) {
            speeds += ", swim " + swim;
        }
        if (fly != null) {
            speeds += ", fly " + fly;
        }
        if (burrow != null) {
            speeds += ", burrow " + burrow;
        }
        if (climb != null) {
            speeds += ", climb " + climb;
        }
        return speeds;
    }
}
