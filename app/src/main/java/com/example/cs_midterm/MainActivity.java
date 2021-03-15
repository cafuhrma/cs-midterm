package com.example.cs_midterm;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadData();
    }

    public void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String jsonEncounters = gson.toJson(Singleton.getInstance().myEncounters);
        String jsonMonsters = gson.toJson(Singleton.getInstance().myMonsters);
        editor.putString("encounter list", jsonEncounters);
        editor.putString("monster list", jsonMonsters);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String jsonEncounters = sharedPreferences.getString("encounter list", null);
        Type type = new TypeToken<ArrayList<Encounter>>() {}.getType();
        Singleton.getInstance().myEncounters = gson.fromJson(jsonEncounters, type);

        if (Singleton.getInstance().myEncounters == null) {
            Singleton.getInstance().myEncounters = new ArrayList<>();
        }

        String jsonMonsters = sharedPreferences.getString("monster list", null);
        Type type2 = new TypeToken<ArrayList<Monster>>() {}.getType();
        Singleton.getInstance().myMonsters = gson.fromJson(jsonMonsters, type2);

        if (Singleton.getInstance().myMonsters == null) {
            Singleton.getInstance().myMonsters = new ArrayList<>();
        }
    }
}