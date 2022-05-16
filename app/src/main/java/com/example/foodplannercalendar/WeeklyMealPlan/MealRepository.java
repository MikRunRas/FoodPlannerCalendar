package com.example.foodplannercalendar.WeeklyMealPlan;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.LiveData;

import com.example.foodplannercalendar.API.MealApi;
import com.example.foodplannercalendar.API.MealResponse;
import com.example.foodplannercalendar.API.ServiceGenerator;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class MealRepository {
    private final MealDao mealDao;
    private static MealRepository instance;
    private final LiveData<List<Meal>> allMeals;
    private final MutableLiveData<Meal> randomMeal;
    private ExecutorService executorService;

    private MealRepository(Application application) {
        MealDatabase database = MealDatabase.getInstance(application);
        mealDao = database.mealDao();
        allMeals = mealDao.getAllMeals();
        randomMeal = new MutableLiveData<>();
        executorService = Executors.newFixedThreadPool(1);
    }

    public static synchronized MealRepository getInstance(Application application) {
        if (instance == null) {
            instance = new MealRepository(application);
        }
        return instance;
    }

    public LiveData<Meal> getRandomMeal() { return randomMeal; }

    public void searchForRandomMeal(){
        MealApi mealApi = ServiceGenerator.getMealApi();
        Call<MealResponse> call = mealApi.getRandomMeal();
        call.enqueue(new Callback<MealResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
               if (response.isSuccessful()) {
                   Log.d("TAG", "Success!\n" + response + "\n" + response.body().getMeal());
                    randomMeal.setValue(response.body().getMeal());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(\n" + t);
            }
        });
    }

    public LiveData<List<Meal>> getAllMeals() { return allMeals; }

    public void insert(Meal meal) { executorService.execute(() -> mealDao.insert(meal)); }

    public void update(Meal meal) { executorService.execute(()-> mealDao.update(meal)); }

    public void delete(Meal meal) { executorService.execute(()-> mealDao.delete(meal)); }

    public void deleteAllMeals() {executorService.execute(mealDao::deleteAllItems); }
}
