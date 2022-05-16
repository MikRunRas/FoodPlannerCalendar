package com.example.foodplannercalendar.API;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealApi {

    @GET("api/json/v1/1/random.php")
    Call<MealResponse> getRandomMeal();
}
