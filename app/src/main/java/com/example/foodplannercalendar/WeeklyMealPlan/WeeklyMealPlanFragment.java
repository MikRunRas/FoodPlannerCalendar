package com.example.foodplannercalendar.WeeklyMealPlan;

import static com.example.foodplannercalendar.calendar.CalendarUtils.daysInWeekArray;
import static com.example.foodplannercalendar.calendar.CalendarUtils.monthYearFromDate;
import static com.example.foodplannercalendar.calendar.CalendarUtils.selectedDate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplannercalendar.R;
import com.example.foodplannercalendar.RecipeFragment;
import com.example.foodplannercalendar.calendar.CalendarAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;


public class WeeklyMealPlanFragment extends Fragment implements CalendarAdapter.OnItemListener{

    private ArrayList<Meal> allMeals = new ArrayList<Meal>();
    private ArrayList<Meal> dailyMeals;
    private MealViewModel viewModel;
    private ImageButton nextWeekButton;
    private ImageButton previousWeekButton;
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView mealListView;
    private FloatingActionButton newMealButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(this).get(MealViewModel.class);
        View root = inflater.inflate(R.layout.fragment_weekly_meal_plan, container, false);
        findViews(root);
        setWeekView();

        viewModel.getAllMeals().observe(getViewLifecycleOwner(), tempMeals -> {
            if(!tempMeals.isEmpty()){
                allMeals.clear();
                for (Meal m : tempMeals){
                    allMeals.add(m);
                }
            }
        });

        nextWeekButton.setOnClickListener(view -> {
            selectedDate = selectedDate.plusWeeks(1);
            setWeekView();
        });

        previousWeekButton.setOnClickListener(view -> {
            selectedDate = selectedDate.minusWeeks(1);
            setWeekView();
        });

        newMealButton.setOnClickListener(view ->{
            Fragment frag = new MealCreatorFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        });

        mealListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Meal m = (Meal) mealListView.getItemAtPosition(position);
                Log.d("Click", m.getStrMeal());
                Fragment frag = new RecipeFragment(m);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return root;
    }


    private void findViews(View root) {
        newMealButton = root.findViewById(R.id.newMealButton);
        calendarRecyclerView = root.findViewById(R.id.calendarRecylcerView);
        monthYearText = root.findViewById(R.id.monthYearTextView);
        selectedDate = LocalDate.now();
        previousWeekButton = root.findViewById(R.id.previousWeekButton);
        nextWeekButton = root.findViewById(R.id.nextWeekButton);
        mealListView = root.findViewById(R.id.mealListView);
    }

    private void setWeekView()
    {
        String str = monthYearFromDate(selectedDate);
        monthYearText.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
        ArrayList<LocalDate> days = daysInWeekArray(selectedDate);
        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setMealAdapter();
    }

    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            selectedDate = date;
            setWeekView();
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        setMealAdapter();
    }

    private void setMealAdapter()
    {
        dailyMeals = Meal.mealsForDate(selectedDate.toString(), allMeals);
        MealAdapter mealAdapter = new MealAdapter(getContext(), dailyMeals);
        mealListView.setAdapter(mealAdapter);
    }
}

