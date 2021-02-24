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
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 25;
                        break;
                    case "Medium":
                        mediumThreshold = 50;
                        break;
                    case "Hard":
                        hardThreshold = 75;
                        break;
                    case "Deadly":
                        deadlyThreshold = 100;
                        break;
                }
                break;
            case 2:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 50;
                        break;
                    case "Medium":
                        mediumThreshold = 100;
                        break;
                    case "Hard":
                        hardThreshold = 150;
                        break;
                    case "Deadly":
                        deadlyThreshold = 200;
                        break;
                }
                break;
            case 3:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 75;
                        break;
                    case "Medium":
                        mediumThreshold = 150;
                        break;
                    case "Hard":
                        hardThreshold = 225;
                        break;
                    case "Deadly":
                        deadlyThreshold = 400;
                        break;
                }
                break;
            case 4:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 125;
                        break;
                    case "Medium":
                        mediumThreshold = 250;
                        break;
                    case "Hard":
                        hardThreshold = 375;
                        break;
                    case "Deadly":
                        deadlyThreshold = 500;
                        break;
                }
                break;
            case 5:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 250;
                        break;
                    case "Medium":
                        mediumThreshold = 500;
                        break;
                    case "Hard":
                        hardThreshold = 750;
                        break;
                    case "Deadly":
                        deadlyThreshold = 1100;
                        break;
                }
                break;
            case 6:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 300;
                        break;
                    case "Medium":
                        mediumThreshold = 600;
                        break;
                    case "Hard":
                        hardThreshold = 900;
                        break;
                    case "Deadly":
                        deadlyThreshold = 1400;
                        break;
                }
                break;
            case 7:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 350;
                        break;
                    case "Medium":
                        mediumThreshold = 750;
                        break;
                    case "Hard":
                        hardThreshold = 1100;
                        break;
                    case "Deadly":
                        deadlyThreshold = 1700;
                        break;
                }
                break;
            case 8:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 450;
                        break;
                    case "Medium":
                        mediumThreshold = 900;
                        break;
                    case "Hard":
                        hardThreshold = 1400;
                        break;
                    case "Deadly":
                        deadlyThreshold = 2100;
                        break;
                }
                break;
            case 9:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 550;
                        break;
                    case "Medium":
                        mediumThreshold = 1100;
                        break;
                    case "Hard":
                        hardThreshold = 1600;
                        break;
                    case "Deadly":
                        deadlyThreshold = 2400;
                        break;
                }
                break;
            case 10:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 600;
                        break;
                    case "Medium":
                        mediumThreshold = 1200;
                        break;
                    case "Hard":
                        hardThreshold = 1900;
                        break;
                    case "Deadly":
                        deadlyThreshold = 2800;
                        break;
                }
                break;
            case 11:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 800;
                        break;
                    case "Medium":
                        mediumThreshold = 1600;
                        break;
                    case "Hard":
                        hardThreshold = 2400;
                        break;
                    case "Deadly":
                        deadlyThreshold = 3600;
                        break;
                }
                break;
            case 12:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 1000;
                        break;
                    case "Medium":
                        mediumThreshold = 2000;
                        break;
                    case "Hard":
                        hardThreshold = 3000;
                        break;
                    case "Deadly":
                        deadlyThreshold = 4500;
                        break;
                }
                break;
            case 13:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 1100;
                        break;
                    case "Medium":
                        mediumThreshold = 2200;
                        break;
                    case "Hard":
                        hardThreshold = 3400;
                        break;
                    case "Deadly":
                        deadlyThreshold = 5100;
                        break;
                }
                break;
            case 14:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 1250;
                        break;
                    case "Medium":
                        mediumThreshold = 2500;
                        break;
                    case "Hard":
                        hardThreshold = 3800;
                        break;
                    case "Deadly":
                        deadlyThreshold = 5700;
                        break;
                }
                break;
            case 15:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 1400;
                        break;
                    case "Medium":
                        mediumThreshold = 2800;
                        break;
                    case "Hard":
                        hardThreshold = 4300;
                        break;
                    case "Deadly":
                        deadlyThreshold = 6400;
                        break;
                }
                break;
            case 16:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 1600;
                        break;
                    case "Medium":
                        mediumThreshold = 3200;
                        break;
                    case "Hard":
                        hardThreshold = 4800;
                        break;
                    case "Deadly":
                        deadlyThreshold = 7200;
                        break;
                }
                break;
            case 17:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 2000;
                        break;
                    case "Medium":
                        mediumThreshold = 3900;
                        break;
                    case "Hard":
                        hardThreshold = 5900;
                        break;
                    case "Deadly":
                        deadlyThreshold = 8800;
                        break;
                }
                break;
            case 18:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 2100;
                        break;
                    case "Medium":
                        mediumThreshold = 4200;
                        break;
                    case "Hard":
                        hardThreshold = 6300;
                        break;
                    case "Deadly":
                        deadlyThreshold = 9500;
                        break;
                }
                break;
            case 19:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 2400;
                        break;
                    case "Medium":
                        mediumThreshold = 4900;
                        break;
                    case "Hard":
                        hardThreshold = 7300;
                        break;
                    case "Deadly":
                        deadlyThreshold = 10900;
                        break;
                }
                break;
            case 20:
                switch (difficulty) {
                    case "Easy":
                        easyThreshold = 2800;
                        break;
                    case "Medium":
                        mediumThreshold = 5700;
                        break;
                    case "Hard":
                        hardThreshold = 8500;
                        break;
                    case "Deadly":
                        deadlyThreshold = 12700;
                        break;
                }
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

        // TODO horde encounter generation
        if (type.equals("Horde")) {
            int numMonsters = rand.nextInt(20); // determine random number of monsters (0-20)
            // loop through monster list for available monsters
            for (Monster monster : monsterList) {
                if (monster.getXp() < minThreshold) {
                    filteredList.add(monster);
                }
                // TODO check if xp is within the thresholds of the desired difficulty
                calculateXP();
            }
        }

        // Boss encounter generation
        else if (type.equals("Boss")) {
            minThreshold = 900; // hardcode for testing
            maxThreshold = 1600; // hardcode for testing
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
}
