package com.example.foodplannercalendar.API;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class MealViewModel extends ViewModel {

    MealRepository repository;

    public MealViewModel() { repository = MealRepository.getInstance(); }

    public LiveData<Meal> getRandomMeal() { return repository.getRandomMeal();}

    public void searchForRandomMeal() {
        repository.searchForRandomMeal();
    }
}
