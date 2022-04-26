package com.example.foodplannercalendar;

import static com.example.foodplannercalendar.CalendarUtils.daysInMonthArray;
import static com.example.foodplannercalendar.CalendarUtils.monthYearFromDate;
import static com.example.foodplannercalendar.CalendarUtils.selectedDate;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private Button nextMonthButton;
    private Button previousMonthButton;
    private Button weeklyViewButton;
    private TextView cellDay;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarRecyclerView = root.findViewById(R.id.calendarRecylcerView);
        monthYearText = root.findViewById(R.id.monthYearTextView);
        selectedDate = LocalDate.now();
        nextMonthButton = root.findViewById(R.id.nextMonthButton);
        previousMonthButton = root.findViewById(R.id.previousMonthButton);
        weeklyViewButton = root.findViewById(R.id.weeklyViewButton);
        cellDay = root.findViewById(R.id.cellDayText);
        setMonthView();

        nextMonthButton.setOnClickListener(view -> {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        });

        previousMonthButton.setOnClickListener(view -> {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
        });

        weeklyViewButton.setOnClickListener(view -> {
            Fragment frag = new WeeklyCalendarViewFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        });
        return root;
    }

    private void setMonthView()
    {
        String str = monthYearFromDate(selectedDate);
        monthYearText.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            selectedDate = date;
            setMonthView();
        }
    }
}