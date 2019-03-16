package com.something.mabdullahk.soop.quizzesList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class quizzesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<quizzesListClass> quizzesList;
    SimpleArcDialog mDialog;
    String studentId;
    String practiceId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_list);

        recyclerView = findViewById(R.id.quizzesListRecyclerView);
        studentId = (String) getIntent().getStringExtra("studentID");
        practiceId = (String) getIntent().getStringExtra("practiceID");


        GridLayoutManager quizzesListGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quizzes");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
//        quizzesListMainView = (LinearLayout) findViewById(R.id.quizzesListMainView);
//        quizzesListmsg = (LinearLayout) findViewById(R.id.quizzesListMessage);
//        quizzesListScroll = (ScrollView) findViewById(R.id.quizzesListScroll);


        recyclerView.setLayoutManager(quizzesListGrid);
        quizzesList = new ArrayList<>();


        getQuizzesList();
    }

    private void getQuizzesList(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/tests/quizzes/"+practiceId+"/quizzes?sid="+studentId, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentspayments = new JSONObject(result);
                    Boolean success = studentspayments.getBoolean("success");

                    System.out.println(result+" is the resultlttttt.......");
                    if (success){
                        JSONArray exercisesData = studentspayments.getJSONObject("data").getJSONArray("quizzes");


                        for(int i=0; i < exercisesData.length() ; i++) {
                            JSONObject anno = exercisesData.getJSONObject(i);
                            JSONArray arr = anno.getJSONArray("subjects");
                            String sub = "Multiple Subjects";
                            if (arr.length() == 1){
                                sub = arr.getString(0);
                            }
                            quizzesList.add(new quizzesListClass(
                                    studentId,
                                    Integer.toString(anno.getInt("id")),
                                    anno.getString("name"),
                                    sub,
                                    Integer.toString(anno.getInt("total_questions")),
                                    Integer.toString(anno.getInt("passing_marks")),
                                    Integer.toString(anno.getInt("mark_per_question")),
                                    anno.getString("difficulty_level"),
                                    anno.getString("round")

                            ));
                        }


//
                        System.out.println(quizzesList.size()+ " is the quiz list.");
                        if (quizzesList.size() > 0){
                            mDialog.dismiss();
                            quizzesListCardAdapter myAdapter = new quizzesListCardAdapter(quizzesListActivity.this,quizzesList);
                            recyclerView.setAdapter(myAdapter);
//                            paymentsMainView.removeView(paymentsmsg);
//                            paymentsMainView.setBackgroundColor(getResources().getColor(R.color.colorgray));
//                            paymentsCardAdapter myAdapter = new paymentsCardAdapter(paymentsActivity.this,payments);
//                            recyclerView.setAdapter(myAdapter);
                        } else {
                            mDialog.dismiss();
//                            paymentsMainView.removeView(paymentsScroll);
//                            paymentsMainView.setBackgroundColor(getResources().getColor(R.color.white));
//                            paymentsmsg.setVisibility(View.VISIBLE);
                        }

                    }
                }catch (JSONException e){
                    mDialog.dismiss();
                    System.out.println("JSON ERROR IN payments.ajva"+e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("it failed i payments.java");
                mDialog.dismiss();
//                paymentsMainView.setBackgroundColor(getResources().getColor(R.color.white));
//                paymentsMainView.removeView(paymentsScroll);
//                paymentsmsg.setVisibility(View.VISIBLE);
            }
        },this);

    }
}
