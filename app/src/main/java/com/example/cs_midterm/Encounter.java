package com.example.cs_midterm;

import java.util.ArrayList;

public class Encounter {
    // member fields
    ArrayList<Monster> monsters;
    int totalXP, partySize, partyLevel, xpThreshold;
    String difficulty;

    // class constructor
    public Encounter(int _partySize, int _partyLevel, String _difficulty) {
        monsters = new ArrayList<>();
        partySize = _partySize;
        partyLevel = _partyLevel;
        difficulty = _difficulty;
        totalXP = 0;
    }

    // accessors
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
    public int getTotalXP() {
        return totalXP;
    }
    public int getPartySize() {
        return partySize;
    }
    public int getPartyLevel() {
        return partyLevel;
    }
    public int getXpThreshold() {
        return xpThreshold;
    }
    public String getDifficulty() {
        return difficulty;
    }

    // mutators
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }
    public void setPartySize(int partySize) {
        this.partySize = partySize;
    }
    public void setPartyLevel(int partyLevel) {
        this.partyLevel = partyLevel;
    }
    public void setXpThreshold(int xpThreshold) {
        this.xpThreshold = xpThreshold;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // calculate the total amount of xp for the encounter
    public int calculateXP() {
        double multiplier = 1; // xp multiplier based on # of monsters
        for (int i = 0; i < monsters.size(); i++) {
            totalXP += monsters.get(i).getXp();
        }
        // determine xp multiplier
        if (monsters.size() == 1) {
            if (partySize >= 3 && partySize <= 5) {
                multiplier = 1;
            }
            else if (partySize > 5) {
                multiplier = 1.5;
            }
            else multiplier = 0.5;
        }
        else if (monsters.size() == 2) {
            if (partySize >= 3 && partySize <= 5) {
                multiplier = 1.5;
            }
            else if (partySize > 5) {
                multiplier = 2;
            }
        }
        else if (monsters.size() <= 6) {
            if (partySize >= 3 && partySize <= 5) {
                multiplier = 2;
            }
            else if (partySize > 5) {
                multiplier = 2.5;
            }
            else multiplier = 1.5;
        }
        else if (monsters.size() <= 10) {
            if (partySize >= 3 && partySize <= 5) {
                multiplier = 2.5;
            }
            else if (partySize > 5) {
                multiplier = 3;
            }
            else multiplier = 2;
        }
        else if (monsters.size() <= 14) {
            if (partySize >= 3 && partySize <= 5) {
                multiplier = 3;
            }
            else if (partySize > 5) {
                multiplier = 4;
            }
            else multiplier = 2.5;
        }
        else {
            if (partySize >= 3 && partySize <= 5) {
                multiplier = 4;
            }
            else if (partySize > 5) {
                multiplier = 5;
            }
            else multiplier = 3;
        }
        totalXP *= multiplier;
        return totalXP;
    }

    // determine difficulty level
    public void calculateDifficulty() {
        int playerXP = totalXP / partySize;
        switch (partyLevel) {
            case 1:
                if (playerXP <= 25) { difficulty = "Easy"; xpThreshold = 25; }
                else if (playerXP <= 50) { difficulty = "Medium"; xpThreshold = 50; }
                else if (playerXP <= 75) { difficulty = "Hard"; xpThreshold = 75; }
                else if (playerXP <= 100) { difficulty = "Deadly"; xpThreshold = 100; }
                break;
            case 2:
                if (playerXP <= 50) { difficulty = "Easy"; xpThreshold = 50; }
                else if (playerXP <= 100) { difficulty = "Medium"; xpThreshold = 100; }
                else if (playerXP <= 150) { difficulty = "Hard"; xpThreshold = 150; }
                else if (playerXP <= 200) { difficulty = "Deadly"; xpThreshold = 200; }
                break;
            case 3:
                if (playerXP <= 75) { difficulty = "Easy"; xpThreshold = 75; }
                else if (playerXP <= 150) { difficulty = "Medium"; xpThreshold = 150; }
                else if (playerXP <= 225) { difficulty = "Hard"; xpThreshold = 225; }
                else if (playerXP <= 400) { difficulty = "Deadly"; xpThreshold = 400; }
                break;
            case 4:
                if (playerXP <= 125) { difficulty = "Easy"; xpThreshold = 125; }
                else if (playerXP <= 250) { difficulty = "Medium"; xpThreshold = 250; }
                else if (playerXP <= 375) { difficulty = "Hard"; xpThreshold = 375; }
                else if (playerXP <= 500) { difficulty = "Deadly"; xpThreshold = 500; }
                break;
            case 5:
                if (playerXP <= 250) { difficulty = "Easy"; xpThreshold = 250; }
                else if (playerXP <= 500) { difficulty = "Medium"; xpThreshold = 500; }
                else if (playerXP <= 750) { difficulty = "Hard"; xpThreshold = 750; }
                else if (playerXP <= 1100) { difficulty = "Deadly"; xpThreshold = 1100; }
                break;
            case 6:
                if (playerXP <= 300) { difficulty = "Easy"; xpThreshold = 300; }
                else if (playerXP <= 600) { difficulty = "Medium"; xpThreshold = 600; }
                else if (playerXP <= 900) { difficulty = "Hard"; xpThreshold = 900; }
                else if (playerXP <= 1400) { difficulty = "Deadly"; xpThreshold = 1400; }
                break;
            case 7:
                if (playerXP <= 350) { difficulty = "Easy"; xpThreshold = 350; }
                else if (playerXP <= 750) { difficulty = "Medium"; xpThreshold = 750; }
                else if (playerXP <= 1100) { difficulty = "Hard"; xpThreshold = 1100; }
                else if (playerXP <= 1700) { difficulty = "Deadly"; xpThreshold = 1700; }
                break;
            case 8:
                if (playerXP <= 450) { difficulty = "Easy"; xpThreshold = 450; }
                else if (playerXP <= 900) { difficulty = "Medium"; xpThreshold = 900; }
                else if (playerXP <= 1400) { difficulty = "Hard"; xpThreshold = 1400; }
                else if (playerXP <= 2100) { difficulty = "Deadly"; xpThreshold = 2100; }
                break;
            case 9:
                if (playerXP <= 550) { difficulty = "Easy"; xpThreshold = 550; }
                else if (playerXP <= 1100) { difficulty = "Medium"; xpThreshold = 1100; }
                else if (playerXP <= 1600) { difficulty = "Hard"; xpThreshold = 1600; }
                else if (playerXP <= 2400) { difficulty = "Deadly"; xpThreshold = 2400; }
                break;
            case 10:
                if (playerXP <= 600) { difficulty = "Easy"; xpThreshold = 600; }
                else if (playerXP <= 1200) { difficulty = "Medium"; xpThreshold = 1200; }
                else if (playerXP <= 1900) { difficulty = "Hard"; xpThreshold = 1900; }
                else if (playerXP <= 2800) { difficulty = "Deadly"; xpThreshold = 2800; }
                break;
            case 11:
                if (playerXP <= 800) { difficulty = "Easy"; xpThreshold = 800; }
                else if (playerXP <= 1600) { difficulty = "Medium"; xpThreshold = 1600; }
                else if (playerXP <= 2400) { difficulty = "Hard"; xpThreshold = 2400; }
                else if (playerXP <= 3600) { difficulty = "Deadly"; xpThreshold = 3600; }
                break;
            case 12:
                if (playerXP <= 1000) { difficulty = "Easy"; xpThreshold = 1000; }
                else if (playerXP <= 2000) { difficulty = "Medium"; xpThreshold = 2000; }
                else if (playerXP <= 3000) { difficulty = "Hard"; xpThreshold = 3000; }
                else if (playerXP <= 4500) { difficulty = "Deadly"; xpThreshold = 4500; }
                break;
            case 13:
                if (playerXP <= 1100) { difficulty = "Easy"; xpThreshold = 1100; }
                else if (playerXP <= 2200) { difficulty = "Medium"; xpThreshold = 2200; }
                else if (playerXP <= 3400) { difficulty = "Hard"; xpThreshold = 3400; }
                else if (playerXP <= 5100) { difficulty = "Deadly"; xpThreshold = 5100; }
                break;
            case 14:
                if (playerXP <= 1250) { difficulty = "Easy"; xpThreshold = 1250; }
                else if (playerXP <= 2500) { difficulty = "Medium"; xpThreshold = 2500; }
                else if (playerXP <= 3800) { difficulty = "Hard"; xpThreshold = 3800; }
                else if (playerXP <= 5700) { difficulty = "Deadly"; xpThreshold = 5700; }
                break;
            case 15:
                if (playerXP <= 1400) { difficulty = "Easy"; xpThreshold = 1400; }
                else if (playerXP <= 2800) { difficulty = "Medium"; xpThreshold = 2800; }
                else if (playerXP <= 4300) { difficulty = "Hard"; xpThreshold = 4300; }
                else if (playerXP <= 6400) { difficulty = "Deadly"; xpThreshold = 6400; }
                break;
            case 16:
                if (playerXP <= 1600) { difficulty = "Easy"; xpThreshold = 1600; }
                else if (playerXP <= 3200) { difficulty = "Medium"; xpThreshold = 3200; }
                else if (playerXP <= 4800) { difficulty = "Hard"; xpThreshold = 4800; }
                else if (playerXP <= 7200) { difficulty = "Deadly"; xpThreshold = 7200; }
                break;
            case 17:
                if (playerXP <= 2000) { difficulty = "Easy"; xpThreshold = 2000; }
                else if (playerXP <= 3900) { difficulty = "Medium"; xpThreshold = 3900; }
                else if (playerXP <= 5900) { difficulty = "Hard"; xpThreshold = 5900; }
                else if (playerXP <= 8800) { difficulty = "Deadly"; xpThreshold = 8800; }
                break;
            case 18:
                if (playerXP <= 2100) { difficulty = "Easy"; xpThreshold = 2100; }
                else if (playerXP <= 4200) { difficulty = "Medium"; xpThreshold = 4200; }
                else if (playerXP <= 6300) { difficulty = "Hard"; xpThreshold = 6300; }
                else if (playerXP <= 9500) { difficulty = "Deadly"; xpThreshold = 9500; }
                break;
            case 19:
                if (playerXP <= 2400) { difficulty = "Easy"; xpThreshold = 2400; }
                else if (playerXP <= 4900) { difficulty = "Medium"; xpThreshold = 4900; }
                else if (playerXP <= 7300) { difficulty = "Hard"; xpThreshold = 7300; }
                else if (playerXP <= 10900) { difficulty = "Deadly"; xpThreshold = 10900; }
                break;
            case 20:
                if (playerXP <= 2800) { difficulty = "Easy"; xpThreshold = 2800; }
                else if (playerXP <= 5700) { difficulty = "Medium"; xpThreshold = 5700; }
                else if (playerXP <= 8500) { difficulty = "Hard"; xpThreshold = 8500; }
                else if (playerXP <= 12700) { difficulty = "Deadly"; xpThreshold = 12700; }
                break;
        }
        // calculate the party's xp threshold for the desired difficulty
        xpThreshold *= partySize;
    }

    // add a new monster to the encounter
    public void addMonster(Monster _monster) {
        monsters.add(_monster);
    }

    // remove a monster from the encounter
    public void removeMonster(Monster _monster) {
        monsters.remove(_monster);
    }
}
