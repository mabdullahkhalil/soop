package com.something.mabdullahk.soop.quizResults;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.LoginPage;
import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.students.student;
import com.something.mabdullahk.soop.students.studentCards;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class quizResults extends AppCompatActivity {

    PieChart pieChart;
    int correctAns;
    int incorrectAns;
    Button done;
    List<student> childrenList;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_results);

        done = findViewById(R.id.resultsButton);
        preferences = getSharedPreferences("soop", MODE_PRIVATE);
        editor = preferences.edit();

        correctAns = Integer.valueOf(getIntent().getStringExtra("correctAns"));
        incorrectAns = Integer.valueOf(getIntent().getStringExtra("incorrectAns"));
        childrenList = new ArrayList<>();


        pieChart = findViewById(R.id.pieChart);

//        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        ArrayList<PieEntry> values = new ArrayList<>();


        values.add(new PieEntry(correctAns,"Correct"));
        values.add(new PieEntry(incorrectAns,"InCorrect"));


        pieChart.animateY(1000, Easing.EaseInOutCubic);

        PieDataSet pieDataSet = new PieDataSet(values,"Marks");
        pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieDataSet.setValueTextSize(16f);

        PieData data = new PieData((pieDataSet));
        data.setValueTextSize(12f);

        pieChart.setData(data);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        String rollnumber = preferences.getString("rollnumber", null);


        System.out.println("rollnumber ======> "+rollnumber);

//        params.put("id","46285948");

        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/sessions?id="+rollnumber, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject students = new JSONObject(result);
                    Boolean success = students.getBoolean("success");


//                    System.out.println(studentsData);

                    if (success){
                        JSONObject studentsData = students.getJSONObject("data");

                        JSONArray childrenData= studentsData.getJSONArray("children");


                        for(int i=0; i < childrenData.length() ; i++){
                            JSONObject children = childrenData.getJSONObject(i);
                            childrenList.add(new student(children.getString("name"), children.getString("roll_number"), children.getString("id"), children.getString("class_name"),children.getString("attendance"), children.getString("quizzes"), children.getString("notes"), children.getString("announcements")));
                        }

                        System.out.println("student list ..."+childrenList);
                        Intent intent = new Intent(quizResults.this,
                                studentCards.class);
                        intent.putExtra("allChildrenData", (Serializable) childrenList);
                        startActivity(intent);
                    }

                    if (!success) {
//                        rollNumber.setText("");
//                        rollNumber.setError("Enter correct roll number to continue.");

                    }

                }catch(JSONException e){
                    System.out.println("you had"+e);
                }


                System.out.println(result);

            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("failed bro");
            }
        },this);

    }
}
