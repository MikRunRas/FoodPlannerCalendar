package com.example.foodplannercalendar.WeeklyMealPlan;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.foodplannercalendar.WeeklyMealPlan.Meal;

import java.util.List;

@Dao
public interface MealDao {

    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);

    @Query("DELETE FROM meals_table")
    void deleteAllItems();

    @Query("SELECT * FROM meals_table")
    LiveData<List<Meal>> getAllMeals();
}
