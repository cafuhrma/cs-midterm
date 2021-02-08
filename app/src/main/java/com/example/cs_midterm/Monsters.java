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
    ArrayList<Result> results;

    // accessors
    public int getCount() {
        return count;
    }
    public ArrayList<Result> getResults() {
        // create Retrofit object for API use
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/api/monsters/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        resultsApi = retrofit.create(ResultsApi.class);

        Call<List<Result>> call = resultsApi.getResults();

        // populate list of results from the API
        call.enqueue(new Callback<List<Result>>() {
            @Override
            public void onResponse(Call<List<Result>> call, Response<List<Result>> response) {

                if (!response.isSuccessful()) {
                    // error handling
                    return;
                }
                List<Result> resultList = response.body();
                // loop through results
                for (Result result : resultList) {
                    results.add(result);
                }
            }
            @Override
            public void onFailure(Call<List<Result>> call, Throwable t) {

            }
        });
        return results;
    }
}
