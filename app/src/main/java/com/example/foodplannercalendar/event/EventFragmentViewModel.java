package com.example.foodplannercalendar.event;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.foodplannercalendar.shoppinglist.ShoppingListItem;

import java.util.List;

public class EventFragmentViewModel extends AndroidViewModel {

    private final EventRepository repository;

    public EventFragmentViewModel(Application application){
        super(application);
        repository = EventRepository.getInstance(application);
    }

    public LiveData<List<Event>> getAllEvents() { return repository.getAllEvents(); }

    public void insert(final Event event) { repository.insert(event); }

    public void update(Event event) {repository.update(event); }

    public void delete(final Event event) {repository.delete(event); }

    public void deleteAllEvents() { repository.deleteAllItems(); }
}
