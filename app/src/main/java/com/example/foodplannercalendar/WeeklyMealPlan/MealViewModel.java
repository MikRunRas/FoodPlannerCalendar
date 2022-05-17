package com.example.foodplannercalendar.WeeklyMealPlan;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodplannercalendar.WeeklyMealPlan.Meal;
import com.example.foodplannercalendar.WeeklyMealPlan.MealRepository;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    MealRepository repository;

    public MealViewModel(Application application) {
        super(application);
        repository = MealRepository.getInstance(application);
    }

    public LiveData<List<Meal>> getAllMeals() { return repository.getAllMeals(); }

    public void insert(final Meal meal) { repository.insert(meal); }

    public void update(Meal meal) {repository.update(meal); }

    public void delete(final Meal meal) {repository.delete(meal); }

    public void deleteAllMeals() { repository.deleteAllMeals(); }

    public LiveData<Meal> getRandomMeal() { return repository.getRandomMeal();}

    public LiveData<Meal> getMealByCategory() { return repository.getMealByCategory();}

    public void searchForRandomMeal() {
        repository.searchForRandomMeal();
    }

    public void searchMealByCategory(String category){
        repository.searchMealByCategory(category);
    }
}
