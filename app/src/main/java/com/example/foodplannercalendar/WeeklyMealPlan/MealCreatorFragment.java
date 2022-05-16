package com.example.foodplannercalendar.WeeklyMealPlan;

import static com.example.foodplannercalendar.calendar.CalendarUtils.selectedDate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplannercalendar.API.MealViewModel;
import com.example.foodplannercalendar.R;

import java.util.Calendar;


public class MealCreatorFragment extends Fragment {

    private Button datePickerButton;
    private DatePickerDialog datePickerDialog;
    private MealViewModel viewModel;
    private ImageView imageView;
    private TextView mealNameTV;
    private Button generateNewMeal;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_meal_creator, container, false);
        findViews(root);
        initDatePicker();

        generateNewMeal.setOnClickListener(view -> {
            viewModel = new ViewModelProvider(this).get(MealViewModel.class);
            viewModel.searchForRandomMeal();
            viewModel.getRandomMeal().observe(getViewLifecycleOwner(), meal -> {
                Glide.with(this).load(meal.getImageUrl()).into(imageView);
                mealNameTV.setText(meal.getName());
            });
        });

        datePickerButton.setOnClickListener(view ->{
            datePickerDialog.show();
        });

        return root;
    }

    private void findViews(View root) {
        mealNameTV = root.findViewById(R.id.mealNameTV);
        datePickerButton = root.findViewById(R.id.datePickerButton);
        generateNewMeal = root.findViewById(R.id.generateNewMeal);
        imageView = root.findViewById(R.id.mealImageView);
        datePickerButton.setText(makeDateString(selectedDate.getDayOfMonth(), selectedDate.getMonthValue(), selectedDate.getYear()).toString());
    }

    private String makeDateString(int day, int month, int year)
    {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month)
    {
        if(month == 1)
        {
            return "JAN";
        }
        if(month == 2)
        {
            return "FEB";
        }
        if(month == 3)
        {
            return "MAR";
        }
        if(month == 4)
        {
            return "APR";
        }
        if(month == 5)
        {
            return "MAY";
        }
        if(month == 6)
        {
            return "JUN";
        }
        if(month == 7)
        {
            return "JUL";
        }
        if(month == 8)
        {
            return "AUG";
        }
        if(month == 9)
        {
            return "SEP";
        }
        if(month == 10)
        {
            return "OCT";
        }
        if(month == 11)
        {
            return "NOV";
        }
        if(month == 12)
        {
            return "DEC";
        }
        return "JAN";
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                datePickerButton.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(getContext(),style, dateSetListener, year, month,day);
    }
}