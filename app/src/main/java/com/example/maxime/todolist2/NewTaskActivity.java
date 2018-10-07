package com.example.maxime.todolist2;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.DateFormat;
import java.util.Calendar;

public class NewTaskActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        EditText editTextDate = (EditText) findViewById(R.id.dateID);
        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment datePicker = new DialogDate();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

        EditText editTextTime = (EditText) findViewById(R.id.timeID);
        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment TimePicker = new DialogTime();
                TimePicker.show(getSupportFragmentManager(), "time picker");
            }
        });
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());

        TextView textView = (TextView) findViewById(R.id.dateID);
        textView.setText(currentDateString);
    }


    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.HOUR_OF_DAY, hour);
        c2.set(Calendar.MINUTE, minute);
        String currentTimeString = String.valueOf(timePicker.getCurrentHour()) + "h " + String.valueOf(timePicker.getCurrentMinute()) + "m";

        TextView textView = (TextView) findViewById(R.id.timeID);
        textView.setText(currentTimeString);
    }
}
