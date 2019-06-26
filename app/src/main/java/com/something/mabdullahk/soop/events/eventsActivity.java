package com.something.mabdullahk.soop.events;

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

public class eventsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<eventsClass> events;
    SimpleArcDialog mDialog;
    String studentId;
    LinearLayout eventsMainView;
    ScrollView eventsScroll;
    LinearLayout eventsmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        recyclerView = findViewById(R.id.eventsRecyclerView);
        studentId = (String) getIntent().getStringExtra("studentID");


        GridLayoutManager eventsGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Events");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
        eventsMainView = (LinearLayout) findViewById(R.id.eventsMainView);
        eventsmsg = (LinearLayout) findViewById(R.id.eventsMessage);
        eventsScroll = (ScrollView) findViewById(R.id.eventsScroll);

        recyclerView.setLayoutManager(eventsGrid);
        events = new ArrayList<>();


        getEvents();

    }

    private void getEvents(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/events", "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentsevents = new JSONObject(result);
                    Boolean success = studentsevents.getBoolean("success");

                    if (success){
                        JSONArray eventsData = studentsevents.getJSONObject("data").getJSONArray("events");
//                        (String title, String time, String toGo, String type, String description)
                        for(int i=0; i < eventsData.length() ; i++) {
                            JSONObject anno = eventsData.getJSONObject(i);
                            events.add(new eventsClass(
                                    anno.getString("title"),
                                    anno.getString("date"),
                                    anno.getString("days_to_go"),
                                    anno.getString("type"),
                                    anno.getString("description")
                            ));
                        }

                        System.out.println(events+ " is the quiz list.");
                        if (events.size() > 0){
                            mDialog.dismiss();
                            eventsMainView.removeView(eventsmsg);
                            eventsMainView.setBackgroundColor(getResources().getColor(R.color.colorgray));
                            eventsCardAdapter myAdapter = new eventsCardAdapter(eventsActivity.this,events);
                            recyclerView.setAdapter(myAdapter);
                        } else {
                            mDialog.dismiss();
                            eventsMainView.removeView(eventsScroll);
                            eventsMainView.setBackgroundColor(getResources().getColor(R.color.white));
                            eventsmsg.setVisibility(View.VISIBLE);
                        }

                    }
                }catch (JSONException e){
                    System.out.println("JSON ERROR IN QUIZZES.ajva"+e);
                }
            }

            @Override
            public void onFaliure(String faliure) {
                System.out.println("it failed i events.java");
                mDialog.dismiss();
                eventsMainView.setBackgroundColor(getResources().getColor(R.color.white));
                eventsMainView.removeView(eventsScroll);
                eventsmsg.setVisibility(View.VISIBLE);
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