package com.example.foodplannercalendar.calendar;

import static com.example.foodplannercalendar.calendar.CalendarUtils.daysInMonthArray;
import static com.example.foodplannercalendar.calendar.CalendarUtils.monthYearFromDate;
import static com.example.foodplannercalendar.calendar.CalendarUtils.selectedDate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.foodplannercalendar.R;
import com.example.foodplannercalendar.event.Event;
import com.example.foodplannercalendar.event.EventAdapter;
import com.example.foodplannercalendar.event.EventEditFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalDate;
import java.util.ArrayList;

public class CalendarFragment extends Fragment implements CalendarAdapter.OnItemListener {

    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ImageButton nextMonthButton;
    private ImageButton previousMonthButton;
    private FloatingActionButton newEventButton;
    private TextView cellDay;
    private ListView eventListView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calendar, container, false);
        calendarRecyclerView = root.findViewById(R.id.calendarRecylcerView);
        monthYearText = root.findViewById(R.id.monthYearTextView);
        selectedDate = LocalDate.now();
        nextMonthButton = root.findViewById(R.id.nextMonthButton);
        previousMonthButton = root.findViewById(R.id.previousMonthButton);
        newEventButton = root.findViewById(R.id.newEventButton);
        cellDay = root.findViewById(R.id.cellDayText);
        eventListView = root.findViewById(R.id.eventListView);
        setMonthView();

        nextMonthButton.setOnClickListener(view -> {
            selectedDate = selectedDate.plusMonths(1);
            setMonthView();
        });

        previousMonthButton.setOnClickListener(view -> {
            selectedDate = selectedDate.minusMonths(1);
            setMonthView();
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

    private void setMonthView()
    {
        String str = monthYearFromDate(selectedDate);
        monthYearText.setText(str.substring(0, 1).toUpperCase() + str.substring(1));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdapter();
    }

    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            selectedDate = date;
            setMonthView();
        }
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
}