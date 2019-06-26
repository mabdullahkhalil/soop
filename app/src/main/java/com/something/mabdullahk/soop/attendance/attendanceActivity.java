package com.something.mabdullahk.soop.attendance;

import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.imanoweb.calendarview.CalendarListener;
import com.imanoweb.calendarview.CustomCalendarView;
import com.imanoweb.calendarview.DayDecorator;
import com.imanoweb.calendarview.DayView;
import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.quizzes.quizClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class attendanceActivity extends AppCompatActivity {



    CustomCalendarView calendarView;
    SimpleArcDialog mDialog ;
    attendanceDates attendanceDatesList;
    String studentId;
    Calendar currentCalendar;
    TextView attendancePresent;
    TextView attendanceAbsent;
    TextView attendanceLeave;


    int mon=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Attendance");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1,R.color.colorlogo2,R.color.colorlogo3,R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
        studentId = (String) getIntent().getStringExtra("studentID");
        attendancePresent = (TextView) findViewById(R.id.attendancePresent);
        attendanceAbsent = (TextView) findViewById(R.id.attendanceAbsent);
        attendanceLeave = (TextView) findViewById(R.id.attendanceLeave);

//        attendanceDatesList = new ArrayList<>();



        calendarView = (CustomCalendarView) findViewById(R.id.calendar_view);

        //Initialize calendar with date
        currentCalendar = Calendar.getInstance(Locale.getDefault());

        //Show monday as first date of week
        calendarView.setFirstDayOfWeek(Calendar.MONDAY);

        //Show/hide overflow days of a month
        calendarView.setShowOverflowDate(false);
        calendarView.refreshCalendar(currentCalendar);

        calendarView.setCalendarListener(new CalendarListener() {
            @Override
            public void onDateSelected(Date date) {
            }

            @Override
            public void onMonthChanged(Date date) {
                mon= date.getMonth();
                getDates();
                //uaaskjdhgfasghdfasvydgtksgdf
            }
        });



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


        getDates();




//        putdates.add("01-02-2019");
//        putdates.add("01-01-2019");
//        putdates.add("03-02-2019");
//        putdates.add("02-02-2019");

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
        List<String> attended;


        DisabledColorDecorator(List<String> dates, List<String> attended){
            this.dates = dates;
            this.attended = attended;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void decorate(DayView dayView) {


            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(dayView.getDate());

            if (dates.contains(date)){

                int color = 0;
                final DayView dayView1;
                int index = dates.indexOf(date);
               switch (attended.get(index)){
                   case "yes":
                       color = Color.parseColor("#ff99cc00");
                       break;
                   case "no":
                       color = Color.parseColor("#ffff4444");

                       break;
                   case "on_leave":
                       color = Color.parseColor("#ffffbb33");
                       break;
                   default:
                       color = Color.parseColor("#FFFFFF");
                       break;


               }

                final int color1 = color;
                dayView.setBackgroundColor(color);
                dayView1 = dayView;
                dayView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dayView1.setBackgroundColor(color1);
                    }
                });

            } else {
                int color = Color.parseColor("#FFFFFF");
                dayView.setBackgroundColor(color);
                final DayView dayView1 = dayView;
                dayView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int color = Color.parseColor("#FFFFFF");
                        dayView1.setBackgroundColor(color);
                    }
                });

            }

                System.out.println("date is "+dayView.getDate());

        }



    }



    private void getDates(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        final List<quizClass> temp_quizzesList = new ArrayList<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/attendance?query=" + mon, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject students = new JSONObject(result);
                    Boolean success = students.getBoolean("success");

                    if (success){
                        attendancePresent.setText(students.getJSONObject("data").getString("presents"));
                        attendanceAbsent.setText(students.getJSONObject("data").getString("absents"));
                        attendanceLeave.setText(students.getJSONObject("data").getString("leaves"));
                        JSONArray studentsData = students.getJSONObject("data").getJSONArray("attendance");
                        List<String> atDates = new ArrayList<>();
                        List<String> atAttended = new ArrayList<>();

                        for(int i=0; i < studentsData.length() ; i++) {
                            JSONObject atDate = studentsData.getJSONObject(i);

                            atDates.add(atDate.getString("date"));
                            atAttended.add(atDate.getString("attended"));
                        }


                        attendanceDatesList = new attendanceDates(atDates,atAttended);
                        System.out.println(attendanceDatesList+ " is the quiz list.");
                        if (atDates.size() > 0){
                            mDialog.dismiss();

                            List<DayDecorator> decorators = new ArrayList<>();
                            decorators.add(new DisabledColorDecorator(atDates,atAttended));
                            calendarView.setDecorators(decorators);

                            System.out.println(decorators.size()+" is the size....");
//                            quizzesMainView.removeView(quizzesmsg);
//                            quizzesCardAdapter myAdapter = new quizzesCardAdapter(attendanceActivity.this, attendanceDatesList);
//                            recyclerView.setAdapter(myAdapter);
                        } else {
                            mDialog.dismiss();
//                            quizzesMainView.removeView(quizzesScroll);
//                            quizzesmsg.setVisibility(View.VISIBLE);
                        }

                    }
                }catch (JSONException e){
                    System.out.println("JSON ERROR IN QUIZZES.ajva"+e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("it failed i quizzes.java");
                mDialog.dismiss();
//                quizzesMainView.removeView(quizzesScroll);
//                quizzesmsg.setVisibility(View.VISIBLE);
            }
        },this);

    }

}
