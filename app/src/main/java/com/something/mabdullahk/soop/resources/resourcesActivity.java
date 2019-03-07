package com.something.mabdullahk.soop.resources;

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
import com.something.mabdullahk.soop.resources.resourcesActivity;
import com.something.mabdullahk.soop.resources.resourcesCardAdapter;
import com.something.mabdullahk.soop.resources.resourcesClass;
import com.something.mabdullahk.soop.resources.resourcesClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class resourcesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<resourcesClass> resources;
    SimpleArcDialog mDialog;
    String studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources);


        recyclerView = findViewById(R.id.resourcesRecyclerView);
        studentId = (String) getIntent().getStringExtra("studentID");


        GridLayoutManager resourcesGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Resources");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);

        recyclerView.setLayoutManager(resourcesGrid);
        resources = new ArrayList<>();

        getresources();
    }



    private void getresources(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/resources", "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONObject studentsresources = new JSONObject(result);
                    Boolean success = studentsresources.getBoolean("success");

                    if (success){
                        JSONArray resourcesData = studentsresources.getJSONObject("data").getJSONArray("resources");

                        for(int i=0; i < resourcesData.length() ; i++) {
                            JSONObject anno = resourcesData.getJSONObject(i);
                            resources.add(new resourcesClass(
                                    anno.getString("teacher_name"),
                                    anno.getString("title"),
                                    anno.getString("subject_name"),
                                    anno.getString("url"),
                                    anno.getString("uploaded_at")
                            ));
                        }

                        System.out.println(resources+ " is the quiz list.");
                        if (resources.size() > 0){
                            mDialog.dismiss();
                            resourcesCardAdapter myAdapter = new resourcesCardAdapter(resourcesActivity.this,resources);
                            recyclerView.setAdapter(myAdapter);
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
                System.out.println("it failed i resources.java");
                mDialog.dismiss();
//                quizzesMainView.removeView(quizzesScroll);
//                quizzesmsg.setVisibility(View.VISIBLE);
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
