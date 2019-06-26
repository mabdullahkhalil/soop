package com.something.mabdullahk.soop.examsList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.quizzesList.quizzesListCardAdapter;
import com.something.mabdullahk.soop.quizzesList.quizzesListClass;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class examsListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<com.something.mabdullahk.soop.examsList.examsListClass> examsList;
    SimpleArcDialog mDialog;
    String SId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_list);

        recyclerView = findViewById(R.id.examsListRecyclerView);
        SId = (String) getIntent().getStringExtra("studentID");



        GridLayoutManager quizzesListGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Exams/Results");

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
        examsList = new ArrayList<>();


        getQuizzesList();
    }

    private void getQuizzesList(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/exams?id="+866, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentspayments = new JSONObject(result);
                    Boolean success = studentspayments.getBoolean("success");


                    if (success){
                        JSONArray Data = studentspayments.getJSONObject("data").getJSONArray("exams");


                        for(int i=0; i < Data.length() ; i++) {
                            JSONObject anno = Data.getJSONObject(i);
                            //JSONArray arr = anno.getJSONArray("exams");
                            examsList.add(new examsListClass(  anno.getString("id"),
                                            anno.getString("title"),
                                            anno.getString("start_date"),
                                             anno.getString("end_date"),
                                            anno.getString("result_announced"),
                                            SId

                            ));
                        }


                        if (examsList.size() > 0){
                            mDialog.dismiss();
                            examsListCardAdapter myAdapter = new examsListCardAdapter(examsListActivity.this,examsList);
                            recyclerView.setAdapter(myAdapter);

                        } else {
                            mDialog.dismiss();
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
