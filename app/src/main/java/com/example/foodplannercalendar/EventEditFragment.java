package com.example.foodplannercalendar;

import static com.example.foodplannercalendar.CalendarUtils.selectedDate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.time.LocalTime;

public class EventEditFragment extends Fragment {

    private EditText eventNameET;
    private TextView eventDateTV;
    private TextView eventTimeTV;
    private Button saveEventButton;

    private LocalTime time;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_create_event, container, false);

        eventNameET = root.findViewById(R.id.eventNameET);
        eventDateTV = root.findViewById(R.id.eventDateTV);
        eventTimeTV = root.findViewById(R.id.eventTimeTV);
        saveEventButton = root.findViewById(R.id.saveEventButton);
        time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(selectedDate).toString());
        eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));

        saveEventButton.setOnClickListener(view ->{
            String eventName = eventNameET.getText().toString();
            Event newEvent = new Event(eventName, selectedDate, time);
            Event.eventsList.add(newEvent);
            Fragment frag = new WeeklyCalendarViewFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.nav_host_fragment, frag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.addToBackStack(null);
            ft.commit();
        });

        return root;
    }
}