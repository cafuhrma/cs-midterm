package com.example.cs_midterm;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class Proficiencies extends Monster{
    // member fields
    int value;
    Call<List> proficiency;

    // accessors
    public int getValue() {
        return value;
    }
//    public Call<List> getProficiency() {
//        // create Retrofit object for API use
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("https://www.dnd5eapi.co/api/monsters/" + index + "/proficiencies").build();
//        proficiency = ProficiencyAPI.getProficiency();
//        return proficiency;
//    }
}
