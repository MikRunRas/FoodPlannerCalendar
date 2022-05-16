package com.example.foodplannercalendar.API;

import java.util.List;

public class MealResponse {

    List<Meal> meals;

    public Meal getMeal(){
        return meals.get(0);
    }
}
