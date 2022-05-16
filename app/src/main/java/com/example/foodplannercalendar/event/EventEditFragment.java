package com.example.foodplannercalendar.event;

import static com.example.foodplannercalendar.calendar.CalendarUtils.selectedDate;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.foodplannercalendar.R;
import com.example.foodplannercalendar.calendar.CalendarFragment;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

public class EventEditFragment extends Fragment {

    private EditText eventNameET;
    private TextView eventDateTV;
    private TextView eventTimeTV;
    private DatePickerDialog datePickerDialog;
    private Button saveEventButton;
    private Button datePickerButton;
    private Button selectTimeButton;
    private EventFragmentViewModel viewModel;
    int hour, minute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(EventFragmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_create_event, container, false);

        findViews(root);
        initDatePicker();

        saveEventButton.setOnClickListener(view ->{
            if(TextUtils.isEmpty(eventNameET.getText().toString())){
                eventNameET.setError("Event name cannot be empty");
                eventNameET.requestFocus();
            } else if (selectTimeButton.getText() == "Select Time"){
                Toast.makeText(getContext(), "Time cannot be empty", Toast.LENGTH_LONG).show();
            }
            else {
                Event newEvent = new Event(eventNameET.getText().toString(), formatLocalDate(datePickerButton.getText().toString()), selectTimeButton.getText().toString());
                viewModel.insert(newEvent);
                Fragment frag = new CalendarFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.nav_host_fragment, frag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        selectTimeButton.setOnClickListener(view ->{
            TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    hour = selectedHour;
                    minute = selectedMinute;
                    selectTimeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
                }
            };
            int style = AlertDialog.THEME_HOLO_DARK;
            TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), style, onTimeSetListener, hour, minute, true);

            timePickerDialog.setTitle("Select Time");
            timePickerDialog.show();
        });

        datePickerButton.setOnClickListener(view ->{
            datePickerDialog.show();
        });

        return root;
    }

    private void findViews(View root) {
        eventNameET = root.findViewById(R.id.eventNameET);
        eventDateTV = root.findViewById(R.id.eventDateTV);
        saveEventButton = root.findViewById(R.id.saveEventButton);
        datePickerButton = root.findViewById(R.id.datePickerButton);
        selectTimeButton = root.findViewById(R.id.selectTimeButton);
        datePickerButton.setText(makeDateString(selectedDate.getDayOfMonth(), selectedDate.getMonthValue(), selectedDate.getYear()).toString());
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
}