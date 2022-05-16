package com.example.foodplannercalendar.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static MealApi mealApi; // https://www.themealdb.com/api/json/v1/1/random.php

    public static MealApi getMealApi(){
        if(mealApi == null){
            mealApi = new Retrofit.Builder()
                    .baseUrl("https://www.themealdb.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(MealApi.class);
        }
        return mealApi;
    }
}
