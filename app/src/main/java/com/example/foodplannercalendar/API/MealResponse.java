package com.example.foodplannercalendar.API;

import com.example.foodplannercalendar.WeeklyMealPlan.Meal;

import java.util.List;

public class MealResponse {

    List<Meal> meals;

    public Meal getMeal(){
        return meals.get(0);
    }
}
