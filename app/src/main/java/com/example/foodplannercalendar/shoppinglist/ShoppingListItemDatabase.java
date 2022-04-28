package com.example.foodplannercalendar.shoppinglist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ShoppingListItem.class}, version = 3)
public abstract class ShoppingListItemDatabase extends RoomDatabase {

    private static ShoppingListItemDatabase instance;
    public abstract ShoppingListItemDao shoppingListItemDao();

    public static synchronized ShoppingListItemDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ShoppingListItemDatabase.class, "shopping_list_items_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
