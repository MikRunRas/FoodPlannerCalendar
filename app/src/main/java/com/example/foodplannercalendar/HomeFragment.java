package com.example.foodplannercalendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannercalendar.API.MealViewModel;
import com.example.foodplannercalendar.WeeklyMealPlan.WeeklyMealPlanFragment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class HomeFragment extends Fragment {

    private TextView dateTV;
    private ImageView imageView;
    private MealViewModel viewModel;
    private Button weeklyMealPlanButton;
    private TextView mealTV;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        dateTV = root.findViewById(R.id.dateTV);
        imageView = root.findViewById(R.id.mealImage);
        mealTV = root.findViewById(R.id.mealTextView);
        weeklyMealPlanButton = root.findViewById(R.id.weeklyMealPlanButton);
        viewModel = new ViewModelProvider(this).get(MealViewModel.class);
        viewModel.searchForRandomMeal();
        viewModel.getRandomMeal().observe(getViewLifecycleOwner(), meal -> {
            Glide.with(this).load(meal.getImageUrl()).into(imageView);
            mealTV.setText(meal.getName());
        });

        weeklyMealPlanButton.setOnClickListener(view -> {
            Fragment frag = new WeeklyMealPlanFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        });

        dateTV.setText(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()));
        return root;
    }
}