package com.example.cs_midterm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EncounterViewModel extends ViewModel {
    private final MutableLiveData<Encounter> encounter = new MutableLiveData<>();

    public void setEncounter(Encounter input) {
        encounter.setValue(input);
    }

    public LiveData<Encounter> getEncounter() {
        return encounter;
    }
}
