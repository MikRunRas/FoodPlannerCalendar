package com.example.foodplannercalendar.WeeklyMealPlan;

import static com.example.foodplannercalendar.calendar.CalendarUtils.selectedDate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.foodplannercalendar.R;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;


public class MealCreatorFragment extends Fragment {

    private Button datePickerButton;
    private DatePickerDialog datePickerDialog;
    private MealViewModel viewModel;
    private ImageView imageView;
    private TextView mealNameTV;
    private Button generateNewMeal;
    private Button saveMealButton;
    private Meal newMeal;

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
                Glide.with(this).load(meal.strMealThumb()).into(imageView);
                mealNameTV.setText(meal.getStrMeal());
                newMeal = new Meal(meal.getIdMeal(), formatLocalDate(datePickerButton.getText().toString()), meal.getStrMeal(), meal.strCategory(), meal.strInstructions(),
                        meal.getStrIngredient1(), meal.getStrIngredient2(), meal.getStrIngredient3(),
                        meal.getStrIngredient4(), meal.getStrIngredient5(), meal.getStrIngredient6(),
                        meal.getStrIngredient7(), meal.getStrIngredient8(), meal.getStrIngredient9(),
                        meal.getStrIngredient10(), meal.getStrIngredient11(), meal.getStrIngredient12(),
                        meal.getStrIngredient13(), meal.getStrIngredient14(), meal.getStrIngredient15(),
                        meal.getStrIngredient16(), meal.getStrIngredient17(), meal.getStrIngredient18(),
                        meal.getStrIngredient19(), meal.getStrIngredient20(), meal.getStrMeasure1(),
                        meal.getStrMeasure2(), meal.getStrMeasure3(), meal.getStrMeasure4(), meal.getStrMeasure5(),
                        meal.getStrMeasure6(),meal.getStrMeasure7(),meal.getStrMeasure8(),meal.getStrMeasure9(),
                        meal.getStrMeasure10(),meal.getStrMeasure11(),meal.getStrMeasure12(),meal.getStrMeasure13(),
                        meal.getStrMeasure14(),meal.getStrMeasure15(),meal.getStrMeasure16(),meal.getStrMeasure17(),
                        meal.getStrMeasure18(),meal.getStrMeasure19(),meal.getStrMeasure20(), meal.strMealThumb());
            });
        });

        datePickerButton.setOnClickListener(view ->{
            datePickerDialog.show();
        });

        saveMealButton.setOnClickListener(view -> {
            if(newMeal == null){
                Toast.makeText(getContext(), "You have no meal to save!", Toast.LENGTH_LONG).show();
                Log.d("TAG", "is null");
            } else {
                Log.d("TAG", newMeal.getAllIngredients());
                viewModel.insert(newMeal);
                Fragment frag = new WeeklyMealPlanFragment();
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
        saveMealButton = root.findViewById(R.id.saveMealButton);
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

    private String formatLocalDate(String text)
    {

        String[] split = text.split(" ");
        String day = split[1];
        String year = split[2];
        String month;

        switch (split[0]){
            case "JAN":
                month = "01";
                break;
            case "FEB":
                month = "02";
                break;
            case "MAR":
                month = "03";
                break;
            case "APR":
                month = "04";
                break;
            case "MAY":
                month = "05";
                break;
            case "JUN":
                month = "06";
                break;
            case "JUL":
                month = "07";
                break;
            case "AUG":
                month = "08";
                break;
            case "SEP":
                month = "09";
                break;
            case "OCT":
                month = "10";
                break;
            case "NOV":
                month = "11";
                break;
            case "DEC":
                month = "12";
                break;
            default:
                month = "01";
        }
        switch (day){
            case "1":
                day = "0"+day;
                break;
            case "2":
                day = "0"+day;
                break;
            case "3":
                day = "0"+day;
                break;
            case "4":
                day = "0"+day;
                break;
            case "5":
                day = "0"+day;
                break;
            case "6":
                day = "0"+day;
                break;
            case "7":
                day = "0"+day;
                break;
            case "8":
                day = "0"+day;
                break;
            case "9":
                day = "0"+day;
                break;
            default:
                day = day;
        }

        String actualDate = year + "-" + month + "-" + day;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return actualDate;
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