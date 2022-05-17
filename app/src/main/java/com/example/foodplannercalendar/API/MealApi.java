package com.example.foodplannercalendar.API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MealApi {

    @GET("api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();

    @GET("api/json/v1/1/filter.php?c={category}")
    Call<MealResponse> getMealByCategory(@Query("category") String category);
}
