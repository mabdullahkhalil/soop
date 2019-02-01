package com.something.mabdullahk.soop;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class attendanceActivity extends AppCompatActivity {



    CustomCalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Attendance");


        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        //Initialize calendar with date
        Calendar currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
            }

            @Override
            public void onMonthChanged(Date date) {

            }
        });

        //call refreshCalendar to update calendar the view
        calendarView.refreshCalendar(currentCalendar);

        //Handling custom calendar events
//        calendarView.setCalendarListener(new CalendarListener() {
//            @Override
//            public void onDateSelected(Date date) {
////                if (!CalendarUtils.isPastDay(date)) {
////                    SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
////                    selectedDateTv.setText("Selected date is " + df.format(date));
////                } else {
////                    selectedDateTv.setText("Selected date is disabled!");
////                }
//            }
//
//            @RequiresApi(api = Build.VERSION_CODES.N)
//            @Override
//            public void onMonthChanged(Date date) {
//                SimpleDateFormat df = new SimpleDateFormat("MM-yyyy");
////                Toast.makeText(CalendarDayDecoratorActivity.this, df.format(date), Toast.LENGTH_SHORT).show();
//            }
//        });


        //adding calendar day decorators
        List<String> putdates = new ArrayList<>();
        putdates.add("01-02-2019");
        putdates.add("01-01-2019");
        putdates.add("03-02-2019");
        putdates.add("02-02-2019");
        List<DayDecorator> decorators = new ArrayList<>();
        decorators.add(new DisabledColorDecorator(putdates));
        calendarView.setDecorators(decorators);
        calendarView.refreshCalendar(currentCalendar);
        System.out.println(decorators.size()+" is the size....");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }
        return super.onOptionsItemSelected(item);
    }

    private class DisabledColorDecorator implements DayDecorator {

        List<String> dates;


        DisabledColorDecorator(List<String> dates){
            this.dates = dates;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void decorate(DayView dayView) {


            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            String date = df.format(dayView.getDate());

            if (dates.contains(date)){
                int color = Color.parseColor("#FAA60E");
                dayView.setBackgroundColor(color);
                final DayView dayView1 = dayView;
                dayView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int color = Color.parseColor("#FAA60E");
                        dayView1.setBackgroundColor(color);
                    }
                });
            }



                System.out.println("date is "+dayView.getDate());

        }



    }

}
