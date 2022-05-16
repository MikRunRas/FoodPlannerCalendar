package com.example.foodplannercalendar.WeeklyMealPlan;

import static java.lang.String.valueOf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannercalendar.R;
import com.example.foodplannercalendar.calendar.WeeklyMealPlanViewHolder;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyMealPlanAdapter extends RecyclerView.Adapter<WeeklyMealPlanViewHolder>{

    private ArrayList<WeeklyMealPlanItem> items;
    private OnItemListener onItemListener;

    public WeeklyMealPlanAdapter(ArrayList<WeeklyMealPlanItem> items) {
        this.items = items;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public WeeklyMealPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.weekly_meal_plan_cell, parent, false);
        return new WeeklyMealPlanViewHolder(view, onItemListener, items);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyMealPlanViewHolder holder, int position)
    {
            final WeeklyMealPlanItem item = items.get(position);
            if(item == null){
                holder.mealDay.setText("whoops");
            }
            else
            {
                holder.mealDay.setText(String.valueOf(item.getDate()));
            }
    }

    public int getItemCount() { return items.size(); }

    public interface OnItemListener
    {
        void onItemClick(int position, WeeklyMealPlanItem item);
    }
}
