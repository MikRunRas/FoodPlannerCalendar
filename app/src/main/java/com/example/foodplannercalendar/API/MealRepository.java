package com.example.foodplannercalendar.API;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MealRepository {
    private static MealRepository instance;
    private final MutableLiveData<Meal> randomMeal;

    private MealRepository() { randomMeal = new MutableLiveData<>(); }

    public static synchronized MealRepository getInstance() {
        if (instance == null) {
            instance = new MealRepository();
        }
        return instance;
    }

    public LiveData<Meal> getRandomMeal() { return randomMeal; }

    public void searchForRandomMeal(){
        MealApi mealApi = ServiceGenerator.getMealApi();
        Call<MealResponse> call = mealApi.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
               if (response.isSuccessful()) {
                   Log.d("TAG", "Success!\n" + response + "\n" + response.body().getMeal());
                    randomMeal.setValue(response.body().getMeal());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(\n" + t);
            }
        });
    }
}
