package com.example.foodplannercalendar;

import static com.example.foodplannercalendar.CalendarUtils.daysInMonthArray;
import static com.example.foodplannercalendar.CalendarUtils.daysInWeekArray;
import static com.example.foodplannercalendar.CalendarUtils.monthYearFromDate;
import static com.example.foodplannercalendar.CalendarUtils.selectedDate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;


public class WeeklyCalendarViewFragment extends Fragment implements CalendarAdapter.OnItemListener{

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private Button nextWeekButton;
    private Button previousWeekButton;
    private Button newEventButton;
    private ListView eventListView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_weekly_calendar_view, container, false);

        calendarRecyclerView = root.findViewById(R.id.calendarRecylcerView);
        monthYearText = root.findViewById(R.id.monthYearTextView);
        nextWeekButton = root.findViewById(R.id.nextWeekButton);
        previousWeekButton = root.findViewById(R.id.previousWeekButton);
        newEventButton = root.findViewById(R.id.newEventButton);
        eventListView = root.findViewById(R.id.eventListView);
        selectedDate = LocalDate.now();
        setWeekView();

        nextWeekButton.setOnClickListener(view -> {
            selectedDate = selectedDate.plusWeeks(1);
            setWeekView();
        });

        previousWeekButton.setOnClickListener(view -> {
            selectedDate = selectedDate.minusWeeks(1);
            setWeekView();
        });

        newEventButton.setOnClickListener(view -> {
            Fragment frag = new EventEditFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        });

        return root;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setEventAdapter();
    }

    private void setEventAdapter()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
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
        setEventAdapter();
    }

    public void onItemClick(int position, LocalDate date)
    {
        selectedDate = date;
        setWeekView();
    }
}