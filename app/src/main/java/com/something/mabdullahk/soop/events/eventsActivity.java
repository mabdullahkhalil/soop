package com.something.mabdullahk.soop.events;

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

        recyclerView.setLayoutManager(eventsGrid);
        events = new ArrayList<>();


        events.add(new eventsClass("Trip to SOS", "13 Jan, 2019", "2 days to go", "social_activity", "A trip is planned to SOS village for social working skills of the children."));
        events.add(new eventsClass("October meeting", "9 Jan, 2019", "Passed", "parent_teacher_meeting", "It is mandatory for all the parents to attend the ptm as December tests performance of their chidlren would be discussed."));
        events.add(new eventsClass("Midterm Exam 2019", "10 Jan, 2019", "9 days to go", "exams", "Let the games begin of the october exams."));
        events.add(new eventsClass("Pakistan Revolution Holiday", "23 March, 2019", "2 months to go", "holiday", "CHildren be patriotic and understand the meaning behind this holiday. it plays an important role in the history of pakistan by quaid-e-Azam Muhammad Ali Jinnah."));
//        case "holiday":
//        return R.color.colorlogo1;
//        case "exams":
//        return R.color.colorlogo2;
//        case "function":
//        return R.color.colorlogo1;
//        case "social_activity":
//        return R.color.colorlogo3;
//        case "parent_teacher_meeting":

        eventsCardAdapter myAdapter = new eventsCardAdapter(eventsActivity.this, events);
        recyclerView.setAdapter(myAdapter);


//        getAnnouncemnets();

    }

//    private void getAnnouncemnets(){
//
//        mDialog.show();
//        Map<String, String> params = new HashMap<>();
//        Map<String, String> headers = new HashMap<>();
//
//
//        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/events", "Get", params, headers, new HTTPrequest.VolleyCallback() {
//            @Override
//            public void onSuccess(String result) {
//
//                try {
//                    JSONObject studentsevents = new JSONObject(result);
//                    Boolean success = studentsevents.getBoolean("success");
//
//                    if (success){
//                        JSONArray eventsData = studentsevents.getJSONObject("data").getJSONArray("events");
//
//                        for(int i=0; i < eventsData.length() ; i++) {
//                            JSONObject anno = eventsData.getJSONObject(i);
//                            events.add(new announcement(
//                                    anno.getString("teacher_name"),
//                                    anno.getString("title"),
//                                    anno.getString("uploaded_at"),
//                                    anno.getString("description")
//                            ));
//                        }
//
//                        System.out.println(events+ " is the quiz list.");
//                        if (events.size() > 0){
//                            mDialog.dismiss();
////                            quizzesMainView.removeView(quizzesmsg);
//                            announcementAdapter myAdapter = new announcementAdapter(announcementActivity.this,events);
//                            recyclerView.setAdapter(myAdapter);
//                        } else {
//                            mDialog.dismiss();
////                            quizzesMainView.removeView(quizzesScroll);
////                            quizzesmsg.setVisibility(View.VISIBLE);
//                        }
//
//                    }
//                }catch (JSONException e){
//                    System.out.println("JSON ERROR IN QUIZZES.ajva"+e);
//                }
//            }
//
//            @Override
//            public void onFaliure(String faliure) {
//                System.out.println("it failed i events.java");
//                mDialog.dismiss();
////                quizzesMainView.removeView(quizzesScroll);
////                quizzesmsg.setVisibility(View.VISIBLE);
//            }
//        },this);
//
//    }

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