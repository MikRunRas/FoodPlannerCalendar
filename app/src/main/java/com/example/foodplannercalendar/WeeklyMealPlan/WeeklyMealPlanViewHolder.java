package com.example.foodplannercalendar.calendar;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplannercalendar.R;
import com.example.foodplannercalendar.WeeklyMealPlan.WeeklyMealPlanAdapter;
import com.example.foodplannercalendar.WeeklyMealPlan.WeeklyMealPlanItem;
import com.example.foodplannercalendar.event.Event;

import java.time.LocalDate;
import java.util.ArrayList;

public class WeeklyMealPlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private final ArrayList<WeeklyMealPlanItem> items;
    public final View parentView;
    public final TextView mealDay;
    public final ImageView eventNotifier;
    private final WeeklyMealPlanAdapter.OnItemListener onItemListener;

    public WeeklyMealPlanViewHolder(@NonNull View itemView, WeeklyMealPlanAdapter.OnItemListener onItemListener, ArrayList<WeeklyMealPlanItem> items)
    {
        super(itemView);
        parentView = itemView.findViewById(R.id.parentView2);
        mealDay = itemView.findViewById(R.id.weeklyMealPlanDay);
        eventNotifier = itemView.findViewById(R.id.eventNotifier);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
        this.items = items;
    }

    @Override
    public void onClick(View view)
    {
        onItemListener.onItemClick(getAbsoluteAdapterPosition(), items.get(getAdapterPosition()));
    }
}