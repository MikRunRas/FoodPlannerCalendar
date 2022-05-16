package com.example.foodplannercalendar.event;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplannercalendar.shoppinglist.ShoppingListItem;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EventRepository {
    private final EventDao eventDao;
    private static EventRepository instance;
    private final LiveData<List<Event>> allEvents;
    private ExecutorService executorService;

    private EventRepository(Application application){
        EventDatabase database = EventDatabase.getInstance(application);
        eventDao = database.eventDao();
        allEvents = eventDao.getAllEvents();
        executorService = Executors.newFixedThreadPool(1);
    }

    public static synchronized EventRepository getInstance(Application application) {
        if(instance == null)
            instance = new EventRepository(application);
        return instance;
    }

    public LiveData<List<Event>> getAllEvents() {return allEvents; }

    public void insert(Event event) {executorService.execute(() -> eventDao.insert(event)); }

    public void update(Event event) { executorService.execute(()-> eventDao.update(event)); }

    public void delete(Event event) { executorService.execute(()-> eventDao.delete(event)); }

    public void deleteAllItems() {executorService.execute(eventDao::deleteAllItems); }
}
