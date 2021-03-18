package com.example.cs_midterm;

import java.util.ArrayList;
import java.util.Random;

public class Encounter {
    // member fields
    private ArrayList<Monster> monsters; // monsters used in the encounter
    private ArrayList<Monster> monsterList; // entire list of monsters to choose from
    private int totalXP, partySize, partyLevel;
    private int easyThreshold, mediumThreshold, hardThreshold, deadlyThreshold;
    private String difficulty;
    private String type; // type of encounter

    // class constructor
    public Encounter() {
        monsters = new ArrayList<>();
        monsterList = new ArrayList<>();
    }

    // accessors
    public ArrayList<Monster> getMonsters() {
        return monsters;
    }
    public ArrayList<Monster> getMonsterList() {
        return monsterList;
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
    public int getEasyThreshold() {
        return easyThreshold;
    }
    public int getMediumThreshold() {
        return mediumThreshold;
    }
    public int getHardThreshold() {
        return hardThreshold;
    }
    public int getDeadlyThreshold() {
        return deadlyThreshold;
    }
    public String getType() {
        return type;
    }
    public String getDifficulty() {
        return difficulty;
    }

    // mutators
    public void setMonsters(ArrayList<Monster> monsters) {
        this.monsters = monsters;
    }
    public void setMonsterList(ArrayList<Monster> monsterList) {
        this.monsterList = monsterList;
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
    public void setEasyThreshold(int easyThreshold) {
        this.easyThreshold = easyThreshold;
    }
    public void setMediumThreshold(int mediumThreshold) {
        this.mediumThreshold = mediumThreshold;
}
    public void setHardThreshold(int hardThreshold) {
        this.hardThreshold = hardThreshold;
    }
    public void setDeadlyThreshold(int deadlyThreshold) {
        this.deadlyThreshold = deadlyThreshold;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    // calculate the total amount of xp for the encounter
    public int calculateXP() {
        double multiplier = 1; // xp multiplier based on # of monsters
        // Add together each monster's xp value
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

    // determine the xp threshold for each player
    public void calculateThreshold() {
        switch (partyLevel) {
            case 1:
                easyThreshold = 25;
                mediumThreshold = 50;
                hardThreshold = 75;
                deadlyThreshold = 100;
                break;
            case 2:
                easyThreshold = 50;
                mediumThreshold = 100;
                hardThreshold = 150;
                deadlyThreshold = 200;
                break;
            case 3:
                easyThreshold = 75;
                mediumThreshold = 150;
                hardThreshold = 225;
                deadlyThreshold = 400;
                break;
            case 4:
                easyThreshold = 125;
                mediumThreshold = 250;
                hardThreshold = 375;
                deadlyThreshold = 500;
                break;
            case 5:
                easyThreshold = 250;
                mediumThreshold = 500;
                hardThreshold = 750;
                deadlyThreshold = 1100;
                break;
            case 6:
                easyThreshold = 300;
                mediumThreshold = 600;
                hardThreshold = 900;
                deadlyThreshold = 1400;
                break;
            case 7:
                easyThreshold = 350;
                mediumThreshold = 750;
                hardThreshold = 1100;
                deadlyThreshold = 1700;
                break;
            case 8:
                easyThreshold = 450;
                mediumThreshold = 900;
                hardThreshold = 1400;
                deadlyThreshold = 2100;
                break;
            case 9:
                easyThreshold = 550;
                mediumThreshold = 1100;
                hardThreshold = 1600;
                deadlyThreshold = 2400;
                break;
            case 10:
                easyThreshold = 600;
                mediumThreshold = 1200;
                hardThreshold = 1900;
                deadlyThreshold = 2800;
                break;
            case 11:
                easyThreshold = 800;
                mediumThreshold = 1600;
                hardThreshold = 2400;
                deadlyThreshold = 3600;
                break;
            case 12:
                easyThreshold = 1000;
                mediumThreshold = 2000;
                hardThreshold = 3000;
                deadlyThreshold = 4500;
                break;
            case 13:
                easyThreshold = 1100;
                mediumThreshold = 2200;
                hardThreshold = 3400;
                deadlyThreshold = 5100;
                break;
            case 14:
                easyThreshold = 1250;
                mediumThreshold = 2500;
                hardThreshold = 3800;
                deadlyThreshold = 5700;
                break;
            case 15:
                easyThreshold = 1400;
                mediumThreshold = 2800;
                hardThreshold = 4300;
                deadlyThreshold = 6400;
                break;
            case 16:
                easyThreshold = 1600;
                mediumThreshold = 3200;
                hardThreshold = 4800;
                deadlyThreshold = 7200;
                break;
            case 17:
                easyThreshold = 2000;
                mediumThreshold = 3900;
                hardThreshold = 5900;
                deadlyThreshold = 8800;
                break;
            case 18:
                easyThreshold = 2100;
                mediumThreshold = 4200;
                hardThreshold = 6300;
                deadlyThreshold = 9500;
                break;
            case 19:
                easyThreshold = 2400;
                mediumThreshold = 4900;
                hardThreshold = 7300;
                deadlyThreshold = 10900;
                break;
            case 20:
                easyThreshold = 2800;
                mediumThreshold = 5700;
                hardThreshold = 8500;
                deadlyThreshold = 12700;
                break;
        }
        // calculate the party's xp threshold for the desired difficulty
        easyThreshold *= partySize;
        mediumThreshold *= partySize;
        hardThreshold *= partySize;
        deadlyThreshold *= partySize;
    }

    // generate a random encounter
    public void randomEncounter() {
        calculateThreshold();
        int minThreshold, maxThreshold;
        ArrayList<Monster> filteredList = new ArrayList<>(); // filtered list of monsters
        Random rand = new Random();

        switch (difficulty) {
            case "Easy":
                minThreshold = easyThreshold;
                maxThreshold = mediumThreshold;
                break;
            case "Medium":
                minThreshold = mediumThreshold;
                maxThreshold = hardThreshold;
                break;
            case "Hard":
                minThreshold = hardThreshold;
                maxThreshold = deadlyThreshold;
                break;
            default:
                minThreshold = deadlyThreshold;
                maxThreshold = deadlyThreshold * 2;
                break;
        }

        // Horde encounter generation
        if (type.equals("Horde")) {
            int numMonsters = getRandomNumber(3, 10); // determine random number of monsters (3-20)
            int typesOfMonsters = rand.nextInt(3) + 1;
            int minMonsterXP = minThreshold / numMonsters;
            int maxMonsterXP = maxThreshold / numMonsters;
            // loop through monster list for available monsters
            for (Monster monster : monsterList) {
                if (monster.getXp() >= minMonsterXP && monster.getXp() <= maxMonsterXP) {
                    filteredList.add(monster);
                }
            }
            // populate the encounter
            for (int i = 0; i < typesOfMonsters; i++) {
                int num = rand.nextInt(filteredList.size());
                for (int j = 0; j < numMonsters/typesOfMonsters; j++) {
                    monsters.add(filteredList.get(num));
                }
                filteredList.remove(num); // remove the recently added monster from the list
            }
            filteredList.clear();
        }

        // Boss encounter generation
        else if (type.equals("Boss")) {
            // loop through monster list for available monsters
            for (Monster monster : monsterList) {
                if (monster.getXp() >=  minThreshold && monster.getXp() < maxThreshold) {
                    filteredList.add(monster);
                }
            }
            // add a randomly selected monster from the filtered list to the encounter
            int num = rand.nextInt(filteredList.size());
            monsters.add(filteredList.get(num));
            filteredList.clear();
        }
        calculateXP(); // Update XP for the encounter
    }

    // empty the list of monsters in the encounter
    public void emptyMonsters() {
        monsters.clear();
    }
    // add a new monster to the encounter
    public void addMonster(Monster _monster) {
        monsters.add(_monster);
    }
    // remove a monster from the encounter
    public void removeMonster(Monster _monster) {
        monsters.remove(_monster);
    }
    // generate a random number in a given range
    public int getRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }
}
