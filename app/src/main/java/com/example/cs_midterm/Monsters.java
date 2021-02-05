package com.example.cs_midterm;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Monsters {
    ResultsApi resultsApi;
    int count;
    ArrayList<Results> results;

    // accessors
    public int getCount() {
        return count;
    }
    public ArrayList<Results> getResults() {
        // create Retrofit object for API use
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        resultsApi = retrofit.create(ResultsApi.class);

        Call<List<Results>> call = resultsApi.getResults();

        // populate list of monsters from the API
        call.enqueue(new Callback<List<Results>>() {
            @Override
            public void onResponse(Call<List<Results>> call, Response<List<Results>> response) {

                if (!response.isSuccessful()) {
                    // error handling

                    return;
                }
                // display each monster's info
                List<Results> resultList = response.body();
                // loop through entire monster database
                for (Results result : resultList) {
                    results.add(result);
                }
            }
            @Override
            public void onFailure(Call<List<Results>> call, Throwable t) {

            }
        });
        return results;
    }
}
