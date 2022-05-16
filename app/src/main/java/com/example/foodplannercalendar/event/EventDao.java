package com.example.foodplannercalendar.event;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface EventDao {

    @Insert
    void insert(Event event);

    @Update
    void update(Event event);

    @Delete
    void delete(Event event);

    @Query("DELETE FROM events_table")
    void deleteAllItems();

    @Query("SELECT * FROM events_table")
    LiveData<List<Event>> getAllEvents();
}
