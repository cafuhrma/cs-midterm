package com.example.cs_midterm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EncounterViewModel extends ViewModel {
    private final MutableLiveData<Encounter> encounter = new MutableLiveData<>();

    public void setEncounter(Encounter _encounter) {
        encounter.setValue(_encounter);
    }

    public LiveData<Encounter> getEncounter() {
        return encounter;
    }
}
