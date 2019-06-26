package com.something.mabdullahk.soop.academicCalender;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
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
import com.something.mabdullahk.soop.attendance.attendanceActivity;
import com.something.mabdullahk.soop.attendance.attendanceDates;
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


public class academicCalenderActivity extends AppCompatActivity {
    String studentId;
    SimpleArcDialog mDialog ;
    CustomCalendarView calendarView;
    Calendar currentCalendar;
    TextView attendancePresent;
    TextView attendanceAbsent;
    TextView attendanceLeave;
    academicCalender academicCalenderList;

    int mon;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_academic_calender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Academic Calender");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1,R.color.colorlogo2,R.color.colorlogo3,R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);

        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
        studentId = (String) getIntent().getStringExtra("studentID");
        calendarView = (CustomCalendarView) findViewById(R.id.academic_calendar_view);

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
                Toast.makeText(getApplicationContext(),"hereeee",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onMonthChanged(Date date) {
                mon= date.getMonth();
                getDates();

            }
        });

        getDates();
    }
    private class ColorDecorator implements DayDecorator {

        List<String> dates;
        List<String> type;
        List<String> title;
        List<String> days_to_go;
        List<String> description;
        List<String> dates1;

        ColorDecorator(List<String> start_dates, List<String> type, List<String> title, List<String> days_to_go, List<String> description , List<String> dates1){
            this.dates = start_dates;
            this.type= type;
            this.title = title;
            this.days_to_go =days_to_go;
            this.description =description;
            this.dates1 = dates1;
        }
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void decorate(DayView dayView) {


            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String date = df.format(dayView.getDate());

            if (dates.contains(date)){

                int color = 0;
                final DayView dayView1;
                final int index = dates.indexOf(date);
                switch (type.get(index)){
                    case "exams":
                        color = Color.parseColor("#ff99cc00");
                        break;
                    case "holiday":
                        color = Color.parseColor("#ffff4444");

                        break;
                    case "event":
                        color = Color.parseColor("#ffffbb33");
                        break;
                    case "parent_teacher_meeting":
                        color = Color.parseColor("#6da0f2");
                        break;
                    default:
                        color = Color.parseColor("#FFFFFF");
                        break;


                }

                final int color1 = color;
                final String date1 = date;
                final String type1  = type.get(index).toString();
                dayView.setBackgroundColor(color);
                dayView1 = dayView;
                dayView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dayView1.setBackgroundColor(color1);
                        String Message = title.get(index).toString()+"\n\n"+dates1.get(index).toString() +"\n\n"+description.get(index).toString();
                        showErrorDialog(Message,color1,date1,type1,title.get(index).toString(),days_to_go.get(index).toString());

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



        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + 866 + "/calendar?month=" + 5, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject students = new JSONObject(result);
                    Boolean success = students.getBoolean("success");

                    if (success){
                        JSONArray studentsData = students.getJSONObject("data").getJSONArray("events");
                        List<String> start_date = new ArrayList<>();
                        List<String> end_date = new ArrayList<>();
                        List<String> type = new ArrayList<>();
                        List<String> title = new ArrayList<>();
                        List<String> date = new ArrayList<>();
                        List<String> days_to_go = new ArrayList<>();
                        List<String> description = new ArrayList<>();
                        for(int i=0; i < studentsData.length() ; i++) {
                            JSONObject atDate = studentsData.getJSONObject(i);

                            start_date.add(atDate.getString("start_date"));
                            end_date.add(atDate.getString("end_date"));
                            type.add(atDate.getString("type"));
                            title.add(atDate.getString("title"));
                            days_to_go.add(atDate.getString("days_to_go"));
                            description.add(atDate.getString("description"));
                            date.add(atDate.getString("date"));
                        }


                        academicCalenderList = new academicCalender(start_date,end_date,type,title,date,days_to_go,description);
                        System.out.println(academicCalenderList+ " is the quiz list.");
                        if (start_date.size() > 0){
                            mDialog.dismiss();

                            List<DayDecorator> decorators = new ArrayList<>();
                            decorators.add(new ColorDecorator(start_date,type,title,days_to_go,description,date));
                            calendarView.setDecorators(decorators);

                            System.out.println(decorators.size()+" is the size....");

                        } else {
                            mDialog.dismiss();

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

            }
        },this);

    }
    private void showErrorDialog(String message , int color , String date ,String type ,String title1, String daytogo) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        View view = LayoutInflater.from(academicCalenderActivity.this).inflate(R.layout.custom_layout, null);
//
//        TextView title = (TextView) view.findViewById(R.id.title);
//        ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);
//
//        title.setText(date);
//        imageButton.setImageResource(R.drawable.exam);
//        builder
//                .setMessage(message)
//                .setPositiveButton(android.R.string.ok, null)
//                ;
//        AlertDialog dialog = builder.create();
//        dialog.setView(view);
//        dialog.show();
//       // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(color));
        AlertDialog.Builder builder = new AlertDialog.Builder(academicCalenderActivity.this);

        View view = LayoutInflater.from(academicCalenderActivity.this).inflate(R.layout.custom_layout, null);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView msg = (TextView) view.findViewById(R.id.message);
        TextView day = (TextView) view.findViewById(R.id.daytogo);
        ImageButton imageButton = (ImageButton) view.findViewById(R.id.image);

        title.setText(title1);
        title.setBackgroundColor(color);
        switch (type) {
            case "exams":
                imageButton.setImageResource(R.drawable.exam);
                color = Color.parseColor("#C7E995");
                break;
            case "holiday":
                imageButton.setImageResource(R.drawable.holiday);
                color = Color.parseColor("#ECC3B2");
                break;
            case "event":
                imageButton.setImageResource(R.drawable.event);
                color = Color.parseColor("#EBE49A");
                break;
            case "parent_teacher_meeting":
                imageButton.setImageResource(R.drawable.ptm);
                color = Color.parseColor("#DDE5ED");
                break;
            default:
                color = Color.parseColor("#FFFFFF");
                break;

        }
        msg.setText(message);
        builder.setPositiveButton("ok", null);

        day.setBackgroundColor(color);
        day.setText(daytogo);
        builder.setView(view);
        builder.show();

    }
}
