package com.example.foodplannercalendar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class HomeFragment extends Fragment {

    private TextView dateTV;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        dateTV = root.findViewById(R.id.dateTV);

        dateTV.setText(DateTimeFormatter.ofPattern("dd-MM-yyyy").format(LocalDateTime.now()));
        return root;
    }
}