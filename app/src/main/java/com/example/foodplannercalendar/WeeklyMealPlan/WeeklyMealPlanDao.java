package com.example.foodplannercalendar.WeeklyMealPlan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.foodplannercalendar.shoppinglist.ShoppingListItem;

import java.util.List;

@Dao
public interface WeeklyMealPlanDao {

    @Insert
    void insert(WeeklyMealPlanItem weeklyMealPlanItem);

    @Delete
    void delete(WeeklyMealPlanItem weeklyMealPlanItem);

    @Query("DELETE FROM weekly_meals_table")
    void deleteAllItems();

    @Query("SELECT * FROM weekly_meals_table")
    LiveData<List<WeeklyMealPlanItem>> getAllItems();
}
