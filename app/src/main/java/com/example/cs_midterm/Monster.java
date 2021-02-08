package com.example.cs_midterm;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Monster {
    // APIs
    private SensesApi sensesApi;
    private SpeedApi speedApi;
    private ProficienciesApi proficienciesApi;
    private ActionsApi actionsApi;
    private LegendaryActionsApi legendaryActionsApi;
    private DamageVulnerabilityApi damageVulnerabilityApi;
    private DamageResistanceApi damageResistanceApi;
    private DamageImmunityApi damageImmunityApi;
    private ConditionImmunityApi conditionImmunityApi;
    private SpecialAbilityApi specialAbilityApi;

    public Retrofit monsterRetrofit;

    // public member fields
    public String index, name, size, type, subtype, alignment, hit_dice, languages, url;
    public int armor_class, hit_points, strength, dexterity, constitution, intelligence, wisdom, charisma, xp;
    public Number challenge_rating;
    public List<DamageType> damage_vulnerabilities;
    public List<DamageType> damage_resistances;
    public List<DamageType> damage_immunities;
    public List<ConditionType> condition_immunities;
    public List<Proficiencies> proficiencies;
    public List<SpecialAbility> special_abilities;
    public List<Action> actions;
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

    public List<DamageType> getDamage_vulnerabilities() {
        monsterRetrofit = getMonsterRetrofit();
        damageVulnerabilityApi = monsterRetrofit.create(DamageVulnerabilityApi.class);

        Call<List<DamageType>> call = damageVulnerabilityApi.getDamageVulnerabilities();

        call.enqueue(new Callback<List<DamageType>>() {
            @Override
            public void onResponse(Call<List<DamageType>> call, Response<List<DamageType>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Vulnerabilities Response Unsuccessful");
                    return;
                }
                damage_vulnerabilities = response.body();
            }
            @Override
            public void onFailure(Call<List<DamageType>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Vulnerabilities Failure");
            }
        });
        return damage_vulnerabilities;
    }

    public List<DamageType> getDamage_resistances() {
        monsterRetrofit = getMonsterRetrofit();
        damageResistanceApi = monsterRetrofit.create(DamageResistanceApi.class);

        Call<List<DamageType>> call = damageResistanceApi.getDamageResistances();

        call.enqueue(new Callback<List<DamageType>>() {
            @Override
            public void onResponse(Call<List<DamageType>> call, Response<List<DamageType>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Resistances Response Unsuccessful");
                    return;
                }
                damage_resistances = response.body();
            }
            @Override
            public void onFailure(Call<List<DamageType>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Resistances Failure");
            }
        });
        return damage_resistances;
    }

    public List<DamageType> getDamage_immunities() {
        monsterRetrofit = getMonsterRetrofit();
        damageImmunityApi = monsterRetrofit.create(DamageImmunityApi.class);

        Call<List<DamageType>> call = damageImmunityApi.getDamageImmunities();

        call.enqueue(new Callback<List<DamageType>>() {
            @Override
            public void onResponse(Call<List<DamageType>> call, Response<List<DamageType>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Damage Immunities Response Unsuccessful");
                    return;
                }
                damage_immunities = response.body();
            }
            @Override
            public void onFailure(Call<List<DamageType>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Damage Immunities Failure");
            }
        });
        return damage_immunities;
    }

    public List<ConditionType> getCondition_immunities() {
        monsterRetrofit = getMonsterRetrofit();
        conditionImmunityApi = monsterRetrofit.create(ConditionImmunityApi.class);

        Call<List<ConditionType>> call = conditionImmunityApi.getConditionImmunities();

        call.enqueue(new Callback<List<ConditionType>>() {
            @Override
            public void onResponse(Call<List<ConditionType>> call, Response<List<ConditionType>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Condition Immunities Response Unsuccessful");
                    return;
                }
                condition_immunities = response.body();
            }
            @Override
            public void onFailure(Call<List<ConditionType>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Condition Immunities Failure");
            }
        });
        return condition_immunities;
    }

    public List<Proficiencies> getProficiencies() {
        monsterRetrofit = getMonsterRetrofit();
        proficienciesApi = monsterRetrofit.create(ProficienciesApi.class);

        Call<List<Proficiencies>> call = proficienciesApi.getProficiencies();

        call.enqueue(new Callback<List<Proficiencies>>() {
            @Override
            public void onResponse(Call<List<Proficiencies>> call, Response<List<Proficiencies>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Proficiencies Response Unsuccessful");
                    return;
                }
                proficiencies = response.body();
            }
            @Override
            public void onFailure(Call<List<Proficiencies>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Proficiencies Immunities Failure");
            }
        });
        return proficiencies;
    }

    public List<SpecialAbility> getSpecial_abilities() {
        monsterRetrofit = getMonsterRetrofit();
        specialAbilityApi = monsterRetrofit.create(SpecialAbilityApi.class);

        Call<List<SpecialAbility>> call = specialAbilityApi.getSpecialAbilities();

        call.enqueue(new Callback<List<SpecialAbility>>() {
            @Override
            public void onResponse(Call<List<SpecialAbility>> call, Response<List<SpecialAbility>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Special Abilities Response Unsuccessful");
                    return;
                }
                special_abilities = response.body();
            }
            @Override
            public void onFailure(Call<List<SpecialAbility>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Special Abilities Failure");
            }
        });
        return special_abilities;
    }

    public List<Action> getActions() {
        monsterRetrofit = getMonsterRetrofit();
        actionsApi = monsterRetrofit.create(ActionsApi.class);

        Call<List<Action>> call = actionsApi.getActions();

        call.enqueue(new Callback<List<Action>>() {
            @Override
            public void onResponse(Call<List<Action>> call, Response<List<Action>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Actions Response Unsuccessful");
                    return;
                }
                actions = response.body();
            }
            @Override
            public void onFailure(Call<List<Action>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Actions Failure");
            }
        });
        return actions;
    }

    public List<LegendaryAction> getLegendary_actions() {
        monsterRetrofit = getMonsterRetrofit();
        legendaryActionsApi = monsterRetrofit.create(LegendaryActionsApi.class);

        Call<List<LegendaryAction>> call = legendaryActionsApi.getLegendaryActions();

        call.enqueue(new Callback<List<LegendaryAction>>() {
            @Override
            public void onResponse(Call<List<LegendaryAction>> call, Response<List<LegendaryAction>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Legendary Actions Response Unsuccessful");
                    return;
                }
                legendary_actions = response.body();
            }
            @Override
            public void onFailure(Call<List<LegendaryAction>> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Legendary Actions Failure");
            }
        });
        return legendary_actions;
    }

    public Senses getSenses() {
        monsterRetrofit = getMonsterRetrofit();
        sensesApi = monsterRetrofit.create(SensesApi.class);

        Call<Senses> call = sensesApi.getSenses();

        call.enqueue(new Callback<Senses>() {
            @Override
            public void onResponse(Call<Senses> call, Response<Senses> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Senses Response Unsuccessful");
                    return;
                }
                senses = response.body();
            }
            @Override
            public void onFailure(Call<Senses> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Senses Failure");
            }
        });
        return senses;
    }

    public Speed getSpeed() {
        monsterRetrofit = getMonsterRetrofit();
        speedApi = monsterRetrofit.create(SpeedApi.class);

        Call<Speed> call = speedApi.getSpeed();

        call.enqueue(new Callback<Speed>() {
            @Override
            public void onResponse(Call<Speed> call, Response<Speed> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    Log.d("ERROR", "Speed Response Unsuccessful");
                    return;
                }
                speed = response.body();
            }
            @Override
            public void onFailure(Call<Speed> call, Throwable t) {
                // error handling
                Log.d("ERROR", "Speed Failure");
            }
        });
        return speed;
    }

    // helper method for retrofit creation
    public Retrofit getMonsterRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/monsters/" + getIndex())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
