package com.example.foodplannercalendar;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ShoppinglistFragmentViewModel extends AndroidViewModel {

    private final ShoppingListItemRepository repository;

    public ShoppinglistFragmentViewModel(Application application){
        super(application);
        repository = ShoppingListItemRepository.getInstance(application);
    }

    public LiveData<List<ShoppingListItem>> getAllShoppingListItems() { return repository.getAllItems(); }

    public void insert(final ShoppingListItem shoppingListItem) { repository.insert(shoppingListItem); }

    public void update(int ID, boolean isBought) {repository.update(ID, isBought); }

    public void delete(final ShoppingListItem shoppingListItem) {repository.delete(shoppingListItem); }

    public void deleteAllShoppingListItems() { repository.deleteAllItems(); }
}
