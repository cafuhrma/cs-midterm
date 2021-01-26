package com.example.cs_midterm;

import java.util.ArrayList;
import java.util.List;

public class Encounter {
    // member fields
    List<Monster> monsters = new ArrayList<>();
    int totalXP;
    int partySize;
    int partyLevel;
    String difficulty;

    // calculate the total amount of xp for the encounter
    public int calulateXP() {
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
                if (playerXP <= 25) { difficulty = "Easy"; }
                else if (playerXP <= 50) { difficulty = "Medium"; }
                else if (playerXP <= 75) { difficulty = "Hard"; }
                else if (playerXP <= 100) { difficulty = "Deadly"; }
                break;
            case 2:
                if (playerXP <= 50) { difficulty = "Easy"; }
                else if (playerXP <= 100) { difficulty = "Medium"; }
                else if (playerXP <= 150) { difficulty = "Hard"; }
                else if (playerXP <= 200) { difficulty = "Deadly"; }
                break;
            case 3:
                if (playerXP <= 75) { difficulty = "Easy"; }
                else if (playerXP <= 150) { difficulty = "Medium"; }
                else if (playerXP <= 225) { difficulty = "Hard"; }
                else if (playerXP <= 400) { difficulty = "Deadly"; }
                break;
            case 4:
                if (playerXP <= 125) { difficulty = "Easy"; }
                else if (playerXP <= 250) { difficulty = "Medium"; }
                else if (playerXP <= 375) { difficulty = "Hard"; }
                else if (playerXP <= 500) { difficulty = "Deadly"; }
                break;
            case 5:
                if (playerXP <= 250) { difficulty = "Easy"; }
                else if (playerXP <= 500) { difficulty = "Medium"; }
                else if (playerXP <= 750) { difficulty = "Hard"; }
                else if (playerXP <= 1100) { difficulty = "Deadly"; }
                break;
            case 6:
                if (playerXP <= 300) { difficulty = "Easy"; }
                else if (playerXP <= 600) { difficulty = "Medium"; }
                else if (playerXP <= 900) { difficulty = "Hard"; }
                else if (playerXP <= 1400) { difficulty = "Deadly"; }
                break;
            case 7:
                if (playerXP <= 350) { difficulty = "Easy"; }
                else if (playerXP <= 750) { difficulty = "Medium"; }
                else if (playerXP <= 1100) { difficulty = "Hard"; }
                else if (playerXP <= 1700) { difficulty = "Deadly"; }
                break;
            case 8:
                if (playerXP <= 450) { difficulty = "Easy"; }
                else if (playerXP <= 900) { difficulty = "Medium"; }
                else if (playerXP <= 1400) { difficulty = "Hard"; }
                else if (playerXP <= 2100) { difficulty = "Deadly"; }
                break;
            case 9:
                if (playerXP <= 550) { difficulty = "Easy"; }
                else if (playerXP <= 1100) { difficulty = "Medium"; }
                else if (playerXP <= 1600) { difficulty = "Hard"; }
                else if (playerXP <= 2400) { difficulty = "Deadly"; }
                break;
            case 10:
                if (playerXP <= 600) { difficulty = "Easy"; }
                else if (playerXP <= 1200) { difficulty = "Medium"; }
                else if (playerXP <= 1900) { difficulty = "Hard"; }
                else if (playerXP <= 2800) { difficulty = "Deadly"; }
                break;
            case 11:
                if (playerXP <= 800) { difficulty = "Easy"; }
                else if (playerXP <= 1600) { difficulty = "Medium"; }
                else if (playerXP <= 2400) { difficulty = "Hard"; }
                else if (playerXP <= 3600) { difficulty = "Deadly"; }
                break;
            case 12:
                if (playerXP <= 1000) { difficulty = "Easy"; }
                else if (playerXP <= 2000) { difficulty = "Medium"; }
                else if (playerXP <= 3000) { difficulty = "Hard"; }
                else if (playerXP <= 4500) { difficulty = "Deadly"; }
                break;
            case 13:
                if (playerXP <= 1100) { difficulty = "Easy"; }
                else if (playerXP <= 2200) { difficulty = "Medium"; }
                else if (playerXP <= 3400) { difficulty = "Hard"; }
                else if (playerXP <= 5100) { difficulty = "Deadly"; }
                break;
            case 14:
                if (playerXP <= 1250) { difficulty = "Easy"; }
                else if (playerXP <= 2500) { difficulty = "Medium"; }
                else if (playerXP <= 3800) { difficulty = "Hard"; }
                else if (playerXP <= 5700) { difficulty = "Deadly"; }
                break;
            case 15:
                if (playerXP <= 1400) { difficulty = "Easy"; }
                else if (playerXP <= 2800) { difficulty = "Medium"; }
                else if (playerXP <= 4300) { difficulty = "Hard"; }
                else if (playerXP <= 6400) { difficulty = "Deadly"; }
                break;
            case 16:
                if (playerXP <= 1600) { difficulty = "Easy"; }
                else if (playerXP <= 3200) { difficulty = "Medium"; }
                else if (playerXP <= 4800) { difficulty = "Hard"; }
                else if (playerXP <= 7200) { difficulty = "Deadly"; }
                break;
            case 17:
                if (playerXP <= 2000) { difficulty = "Easy"; }
                else if (playerXP <= 3900) { difficulty = "Medium"; }
                else if (playerXP <= 5900) { difficulty = "Hard"; }
                else if (playerXP <= 8800) { difficulty = "Deadly"; }
                break;
            case 18:
                if (playerXP <= 2100) { difficulty = "Easy"; }
                else if (playerXP <= 4200) { difficulty = "Medium"; }
                else if (playerXP <= 6300) { difficulty = "Hard"; }
                else if (playerXP <= 9500) { difficulty = "Deadly"; }
                break;
            case 19:
                if (playerXP <= 2400) { difficulty = "Easy"; }
                else if (playerXP <= 4900) { difficulty = "Medium"; }
                else if (playerXP <= 7300) { difficulty = "Hard"; }
                else if (playerXP <= 10900) { difficulty = "Deadly"; }
                break;
            case 20:
                if (playerXP <= 2800) { difficulty = "Easy"; }
                else if (playerXP <= 5700) { difficulty = "Medium"; }
                else if (playerXP <= 8500) { difficulty = "Hard"; }
                else if (playerXP <= 12700) { difficulty = "Deadly"; }
                break;
        }
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
