package com.something.mabdullahk.soop.announcements;

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
    LinearLayout announcementsMainView;
    ScrollView announcementsScroll;
    LinearLayout announcementsmsg;

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
        announcementsMainView = (LinearLayout) findViewById(R.id.announcementsMainView);
        announcementsmsg = (LinearLayout) findViewById(R.id.announcementsMessage);
        announcementsScroll = (ScrollView) findViewById(R.id.announcementsScroll);

        recyclerView.setLayoutManager(announcementsGrid);
        announcements = new ArrayList<>();

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
                            announcementsMainView.removeView(announcementsmsg);
                            announcementsMainView.setBackgroundColor(getResources().getColor(R.color.colorgray));
                            announcementAdapter myAdapter = new announcementAdapter(announcementActivity.this,announcements);
                            recyclerView.setAdapter(myAdapter);
                        } else {
                            mDialog.dismiss();
                            announcementsMainView.removeView(announcementsScroll);
                            announcementsMainView.setBackgroundColor(getResources().getColor(R.color.white));
                            announcementsmsg.setVisibility(View.VISIBLE);
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
                announcementsMainView.setBackgroundColor(getResources().getColor(R.color.white));
                announcementsMainView.removeView(announcementsScroll);
                announcementsmsg.setVisibility(View.VISIBLE);
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
