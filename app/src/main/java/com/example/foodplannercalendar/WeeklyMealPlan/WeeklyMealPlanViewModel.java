package com.example.foodplannercalendar.WeeklyMealPlan;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodplannercalendar.shoppinglist.ShoppingListItem;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItemRepository;

import java.util.List;

public class WeeklyMealPlanViewModel extends AndroidViewModel {

    private final WeeklyMealPlanItemRepository repository;

    public WeeklyMealPlanViewModel(Application application){
        super(application);
        repository = WeeklyMealPlanItemRepository.getInstance(application);
    }

    public LiveData<List<WeeklyMealPlanItem>> getAllItems() { return repository.getAllItems(); }

    public void insert(final WeeklyMealPlanItem weeklyMealPlanItem) { repository.insert(weeklyMealPlanItem); }

    public void delete(final WeeklyMealPlanItem weeklyMealPlanItem) {repository.delete(weeklyMealPlanItem); }

    public void deleteAllItems() { repository.deleteAllItems(); }
}
