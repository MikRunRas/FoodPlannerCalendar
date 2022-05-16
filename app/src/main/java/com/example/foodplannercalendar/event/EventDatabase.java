package com.example.foodplannercalendar.event;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Event.class}, version = 2)
public abstract class EventDatabase extends RoomDatabase {

    private static EventDatabase instance;
    public abstract EventDao eventDao();

    public static synchronized EventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class, "events_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
