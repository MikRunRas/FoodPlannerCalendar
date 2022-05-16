package com.example.foodplannercalendar.WeeklyMealPlan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.foodplannercalendar.R;

import java.util.List;

public class MealAdapter extends ArrayAdapter<Meal> {
    public MealAdapter(@NonNull Context context, List<Meal> meals) {
        super(context, 0, meals);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Meal meal = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.weekly_meal_plan_cell, parent, false);
        }
        TextView weeklyMealPlanDay = convertView.findViewById(R.id.weeklyMealPlanDay);
        ImageView weeklyMealImage = convertView.findViewById(R.id.weeklyMealImage);
        TextView weeklyMealTitle = convertView.findViewById(R.id.weeklyMealTitle);

        String day = meal.getDate();
        String name = meal.getStrMeal();
        weeklyMealPlanDay.setText(day);
        weeklyMealTitle.setText(name);
        Glide.with(getContext()).load(meal.strMealThumb()).into(weeklyMealImage);

        return convertView;
    }
}
