package com.something.mabdullahk.soop.announcements;

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
import com.something.mabdullahk.soop.quizzes.quizClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class announcementActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<announcement> announcements;
    SimpleArcDialog mDialog ;
    String studentId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        recyclerView = findViewById(R.id.announcementsRecyclerView);
        studentId = (String) getIntent().getStringExtra("studentID");


        GridLayoutManager announcementsGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Announcements");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1,R.color.colorlogo2,R.color.colorlogo3,R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);

        recyclerView.setLayoutManager(announcementsGrid);
        announcements = new ArrayList<>();

//
//        announcements.add(new announcement("Abdullah Khalil","Physics Quiz", "11 January,2019 - 11:59pm", "The mean marks of the quiz were 15. The class performed well overall."));
//        announcements.add(new announcement("Hamza Altaf","Urdu Homework", "15 Feburary,2019 - 10:07am", "The homework is due tomorrow morning."));
//        announcements.add(new announcement("Qasim Anjum","Artifical Intelligence", "19 Feburary,2019 - 10:07am", "This is the simplest illustration of the basic idea of backprop algorithm. It does not involve matrix nor vectors. It only involves derivatives against scalars. Very good for starters."));


        getAnnouncemnets();

    }

    private void getAnnouncemnets(){

        mDialog.show();
        Map<String, String> params = new HashMap<>();
        Map<String, String> headers = new HashMap<>();
        final List<quizClass> temp_quizzesList = new ArrayList<>();


        HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/parents/children/" + studentId + "/announcements", "Get", params, headers, new HTTPrequest.VolleyCallback() {
            @Override
            public void onSuccess(String result) {

                try {
                    JSONObject studentsAnnouncements = new JSONObject(result);
                    Boolean success = studentsAnnouncements.getBoolean("success");

                    if (success){
                        JSONArray announcementsData = studentsAnnouncements.getJSONObject("data").getJSONArray("announcements");

                        for(int i=0; i < announcementsData.length() ; i++) {
                            JSONObject anno = announcementsData.getJSONObject(i);
                            announcements.add(new announcement(
                                    anno.getString("teacher_name"),
                                    anno.getString("title"),
                                    anno.getString("uploaded_at"),
                                    anno.getString("description")
                            ));
                        }

                        System.out.println(announcements+ " is the quiz list.");
                        if (announcements.size() > 0){
                            mDialog.dismiss();
//                            quizzesMainView.removeView(quizzesmsg);
                            announcementAdapter myAdapter = new announcementAdapter(announcementActivity.this,announcements);
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
                System.out.println("it failed i announcements.java");
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
