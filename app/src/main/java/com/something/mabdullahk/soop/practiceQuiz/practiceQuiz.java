package com.something.mabdullahk.soop.practiceQuiz;

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
import com.something.mabdullahk.soop.payments.paymentsActivity;
import com.something.mabdullahk.soop.payments.paymentsCardAdapter;
import com.something.mabdullahk.soop.payments.paymentsClass;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class practiceQuiz extends AppCompatActivity {


    RecyclerView recyclerView;
    List<exercises> exercisess;
    SimpleArcDialog mDialog;
    String studentId;
    LinearLayout practiceQuizMainView;
    ScrollView practiceQuizScroll;
    LinearLayout practiceQuizmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_quiz);
        
        studentId = (String) getIntent().getStringExtra("studentID");
        recyclerView = findViewById(R.id.practiceQuizRecyclerView);
        
        
        GridLayoutManager practiceQuizGrid = new GridLayoutManager(this, 2);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Practice Quizzes");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
        exercisess = new ArrayList<>();

        recyclerView.setLayoutManager(practiceQuizGrid);



        getPracticeQuizzes();
    }

    private void getPracticeQuizzes(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/tests/quizzes?id="+studentId, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentspayments = new JSONObject(result);
                    Boolean success = studentspayments.getBoolean("success");

                    System.out.println(result+" is the resultlttttt.......");
                    if (success){
                        JSONArray exercisesData = studentspayments.getJSONObject("data").getJSONArray("excercises");



                        for(int i=0; i < exercisesData.length() ; i++) {
                            JSONObject anno = exercisesData.getJSONObject(i);
                            JSONArray arr = anno.getJSONArray("subjects");
                            String sub = "Multiple Subjects";
                            if (arr.length() == 1){
                                sub = arr.getString(0);
                            }
                            System.out.println(arr.length()+" is the lenght");
                            exercisess.add(new exercises(
                                    studentId,
                                    anno.getString("name"),
                                    Integer.toString(anno.getInt("id")),
                                    anno.getString("quiz_competition_type"),
                                    sub,
                                    Integer.toString(anno.getInt("rounds")),
                                    Integer.toString(anno.getInt("number_of_quizzes")),
                                    Integer.toString(anno.getInt("number_of_questions"))

                            ));

                        }


//
                        System.out.println(exercisess.size()+ " is the quiz list.");
                        if (exercisess.size() > 0){
                            mDialog.dismiss();
                            practiceQuizCardAdapter myAdapter = new practiceQuizCardAdapter(practiceQuiz.this,exercisess);
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
