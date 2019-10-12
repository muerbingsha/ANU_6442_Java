package com.anu.samplecalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        final Calendar mCalendar = Calendar.getInstance();
        final Button time = findViewById(R.id.time);
        final Button date = findViewById(R.id.date);




        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //
                int hour = mCalendar.get(Calendar.HOUR);
                int minute = mCalendar.get(Calendar.MINUTE);
                int am = mCalendar.get(Calendar.AM);


                new TimePickerDialog(TestActivity.this, new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker timePicker, int _hour, int _minute) {
                        mCalendar.set(Calendar.HOUR, _hour);
                        mCalendar.set(Calendar.MINUTE, _minute);
                    }
                }, hour, minute, false)
                .show();

                time.setText(hour +" : " + minute + " : " + am);
            }
        });



        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int year_ = mCalendar.get(Calendar.YEAR);
                int month_ = mCalendar.get(Calendar.MONTH);
                int date_ = mCalendar.get(Calendar.DAY_OF_MONTH);

                new DatePickerDialog(TestActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int _year, int  _month, int _date) {
                        mCalendar.set(Calendar.YEAR, _year);
                        mCalendar.set(Calendar.MONTH, _month);
                        mCalendar.set(Calendar.DAY_OF_MONTH, _date);
                    }
                }, year_, month_, date_).show();

                date.setText(year_ +" : " + month_ +" : "+ date_);
            }
        });


    }
}
