package com.example.foodplannercalendar.event;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.foodplannercalendar.R;
import com.example.foodplannercalendar.calendar.CalendarUtils;

import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{

    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if(convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);
        }
        TextView eventCellTitleTV = convertView.findViewById(R.id.eventCellTitleTV);
        TextView eventCellTimeTV = convertView.findViewById(R.id.eventCellTimeTV);

        String eventTitle = event.getName();
        String eventTime = event.getTime();
        eventCellTitleTV.setText(eventTitle);
        eventCellTimeTV.setText(eventTime);
        return convertView;
    }
}
