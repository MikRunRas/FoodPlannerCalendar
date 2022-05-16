package com.example.foodplannercalendar.WeeklyMealPlan;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.foodplannercalendar.shoppinglist.ShoppingListItem;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItemDao;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItemDatabase;
import com.example.foodplannercalendar.shoppinglist.ShoppingListItemRepository;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WeeklyMealPlanItemRepository {

    private final WeeklyMealPlanDao weeklyMealPlanDao;
    private static WeeklyMealPlanItemRepository instance;
    private final LiveData<List<WeeklyMealPlanItem>> allItems;
    private ExecutorService executorService;

    private WeeklyMealPlanItemRepository(Application application){
        WeeklyMealPlanItemDatabase database = WeeklyMealPlanItemDatabase.getInstance(application);
        weeklyMealPlanDao = database.weeklyMealPlanDao();
        allItems = weeklyMealPlanDao.getAllItems();
        executorService = Executors.newFixedThreadPool(1);
    }

    public static synchronized WeeklyMealPlanItemRepository getInstance(Application application) {
        if (instance == null)
            instance = new WeeklyMealPlanItemRepository(application);
        return instance;
    }

    public LiveData<List<WeeklyMealPlanItem>> getAllItems() {return allItems; }

    public void insert(WeeklyMealPlanItem weeklyMealPlanItem) { executorService.execute(() -> weeklyMealPlanDao.insert(weeklyMealPlanItem)); }

    public void delete(WeeklyMealPlanItem weeklyMealPlanItem) { executorService.execute(()-> weeklyMealPlanDao.delete(weeklyMealPlanItem)); }

    public void deleteAllItems() {executorService.execute(weeklyMealPlanDao::deleteAllItems); }

}
