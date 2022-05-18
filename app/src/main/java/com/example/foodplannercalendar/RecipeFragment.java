package com.example.foodplannercalendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannercalendar.WeeklyMealPlan.Meal;

public class RecipeFragment extends Fragment {

    private final Meal m;
    TextView mealTitle;
    ImageView mealImage;
    TextView instructions;
    TextView ingredients;

    public RecipeFragment(Meal m) {
        this.m = m;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_recipe, container, false);
        findViews(root);

        mealTitle.setText(m.getStrMeal());
        Glide.with(getContext()).load(m.strMealThumb()).into(mealImage);
        instructions.setText(m.strInstructions());
        String ingredientsString = m.getAllIngredients();
        ingredients.setText(m.getAllIngredients());
        return root;
    }

    private void findViews(View root) {
        ingredients = root.findViewById(R.id.ingredients);
        instructions = root.findViewById(R.id.instructions);
        mealTitle = root.findViewById(R.id.mealTitle);
        mealImage = root.findViewById(R.id.mealImage);
    }
}