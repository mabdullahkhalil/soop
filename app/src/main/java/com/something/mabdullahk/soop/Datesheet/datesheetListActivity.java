package com.something.mabdullahk.soop.Datesheet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

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

public class datesheetListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<datesheetListClass> datesheetList;
    SimpleArcDialog mDialog;
    String examId;
    String studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datesheet_list);

        recyclerView = findViewById(R.id.datesheetListRecyclerView);
        studentId= (String) getIntent().getStringExtra("studentID");
        examId = (String) getIntent().getStringExtra("examID");

        studentId = "866";

        GridLayoutManager quizzesListGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Datesheet");

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
        datesheetList = new ArrayList<>();


        getDatesheetList();
    }

    private void getDatesheetList(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/exams/"+studentId+"/datesheet?exam_id="+examId, "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentsdata = new JSONObject(result);
                    Boolean success = studentsdata.getBoolean("success");


                    if (success){
                        JSONArray Data = studentsdata.getJSONObject("data").getJSONArray("exams");


                        for(int i=0; i < Data.length() ; i++) {
                            JSONObject anno = Data.getJSONObject(i);
                            //JSONArray arr = anno.getJSONArray("exams");
                            datesheetList.add(new datesheetListClass( anno.getString("subject_id"),
                                            anno.getString("subject"),
                                             anno.getString("date"),
                                            anno.getString("time"),
                                            anno.getString("teacher"),
                                    anno.getString("days_to_go")

                            ));
                        }


                        if (datesheetList.size() > 0){
                            mDialog.dismiss();
                            datesheetListCardAdapter myAdapter = new datesheetListCardAdapter(datesheetListActivity.this,datesheetList);
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
