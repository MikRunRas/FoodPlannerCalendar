package com.example.foodplannercalendar.event;

import android.util.Log;

import androidx.lifecycle.ViewModelProvider;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Entity(tableName = "events_table")
public class Event
{
    public static ArrayList<Event> eventsForDate(String date, ArrayList<Event> eventsList)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {
            if(event.getDate().equals(date))
            {
                events.add(event);
            }
        }
        return events;
    }

    @PrimaryKey(autoGenerate = true)
    public int ID;
    private String date;
    private String time;
    private String name;

    public Event(String name, String date, String time) {
        this.name = name;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
