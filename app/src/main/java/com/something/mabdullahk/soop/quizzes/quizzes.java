package com.something.mabdullahk.soop.quizzes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class quizzes extends AppCompatActivity {


    RecyclerView recyclerView;
    List<quizClass> quizzesList;
    String studentId;
    LinearLayout quizzesMainView;
    ScrollView quizzesScroll;
    LinearLayout quizzesmsg;
    SimpleArcDialog mDialog ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        recyclerView = findViewById(R.id.quizzesRecyclerView);

        GridLayoutManager quizzesGrid = new GridLayoutManager(quizzes.this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quizzes");
        getSupportActionBar().setLogo(R.drawable.ic_quiz);


        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1,R.color.colorlogo2,R.color.colorlogo3,R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);


        recyclerView.setLayoutManager(quizzesGrid);
        quizzesList = new ArrayList<>();
        studentId = (String) getIntent().getStringExtra("studentID");
        quizzesMainView = (LinearLayout) findViewById(R.id.quizzesMainView);
        quizzesmsg = (LinearLayout) quizzesMainView.findViewById(R.id.quizzesMessage);
        quizzesScroll = (ScrollView) findViewById(R.id.quizzesScroll);



       getquizzes();



    }


    private void getquizzes(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        final List<quizClass> temp_quizzesList = new ArrayList<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/assessments", "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject students = new JSONObject(result);
                    Boolean success = students.getBoolean("success");

                    if (success){
                        JSONArray studentsData = students.getJSONObject("data").getJSONArray("assessments");

                        for(int i=0; i < studentsData.length() ; i++) {
                            JSONObject quiz = studentsData.getJSONObject(i);
                            quizzesList.add(new quizClass(
                                    quiz.getString("id"),
                                    quiz.getString("date"),
                                    quiz.getString("title"),
                                    quiz.getString("subject"),
                                    quiz.getString("average"),
                                    quiz.getString("total_marks"),
                                    quiz.getString("maximum_marks"),
                                    quiz.getString("marks_obtained")));
                        }

                        System.out.println(quizzesList+ " is the quiz list.");
                        if (quizzesList.size() > 0){
                            mDialog.dismiss();
                            quizzesMainView.removeView(quizzesmsg);
                            quizzesCardAdapter myAdapter = new quizzesCardAdapter(quizzes.this,quizzesList);
                            recyclerView.setAdapter(myAdapter);
                        } else {
                            mDialog.dismiss();
                            quizzesMainView.removeView(quizzesScroll);
                            quizzesmsg.setVisibility(View.VISIBLE);
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
                quizzesMainView.removeView(quizzesScroll);
                quizzesmsg.setVisibility(View.VISIBLE);
            }
        },this);

    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
