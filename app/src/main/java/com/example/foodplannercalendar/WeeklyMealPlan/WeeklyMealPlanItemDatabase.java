package com.example.foodplannercalendar.WeeklyMealPlan;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.foodplannercalendar.Converters;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItemDao;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItemDatabase;

@Database(entities = {WeeklyMealPlanItem.class}, version = 2)
@TypeConverters({Converters.class})
public abstract class WeeklyMealPlanItemDatabase extends RoomDatabase {

    private static WeeklyMealPlanItemDatabase instance;
    public abstract WeeklyMealPlanDao weeklyMealPlanDao();

    public static synchronized WeeklyMealPlanItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WeeklyMealPlanItemDatabase.class, "weekly_meals_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
