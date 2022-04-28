package com.example.foodplannercalendar.shoppinglist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ShoppingListItemRepository {

    private final ShoppingListItemDao shoppingListItemDao;
    private static ShoppingListItemRepository instance;
    private final LiveData<List<ShoppingListItem>> allItems;
    private ExecutorService executorService;

    private ShoppingListItemRepository(Application application){
        ShoppingListItemDatabase database = ShoppingListItemDatabase.getInstance(application);
        shoppingListItemDao = database.shoppingListItemDao();
        allItems = shoppingListItemDao.getAllShoppingListItems();
        executorService = Executors.newFixedThreadPool(1);
    }

    public static synchronized ShoppingListItemRepository getInstance(Application application) {
        if (instance == null)
            instance = new ShoppingListItemRepository(application);

        return instance;
    }

    public LiveData<List<ShoppingListItem>> getAllItems() {return allItems; }

    public void insert(ShoppingListItem shoppingListItem) { executorService.execute(() -> shoppingListItemDao.insert(shoppingListItem)); }

    public void update(int ID, boolean isBought) { executorService.execute(()-> shoppingListItemDao.update(ID, isBought)); }

    public void delete(ShoppingListItem shoppingListItem) { executorService.execute(()-> shoppingListItemDao.delete(shoppingListItem)); }

    public void deleteAllItems() {executorService.execute(shoppingListItemDao::deleteAllItems); }

}
