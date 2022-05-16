package com.example.foodplannercalendar.WeeklyMealPlan;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealDatabase extends RoomDatabase {

    private static MealDatabase instance;
    public abstract MealDao mealDao();

    public static synchronized MealDatabase getInstance(Context context) {
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MealDatabase.class, "meals_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
