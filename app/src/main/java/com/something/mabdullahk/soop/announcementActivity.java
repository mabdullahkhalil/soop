package com.something.mabdullahk.soop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class announcementActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<announcement> announcements;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcement);

        recyclerView = findViewById(R.id.announcementsRecyclerView);

        GridLayoutManager announcementsGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Announcements");

        recyclerView.setLayoutManager(announcementsGrid);
        announcements = new ArrayList<>();


        announcements.add(new announcement("Abdullah Khalil","Physics Quiz", "11 January,2019 - 11:59pm", "The mean marks of the quiz were 15. The class performed well overall."));
        announcements.add(new announcement("Hamza Altaf","Urdu Homework", "15 Feburary,2019 - 10:07am", "The homework is due tomorrow morning."));
        announcements.add(new announcement("Qasim Anjum","Artifical Intelligence", "19 Feburary,2019 - 10:07am", "This is the simplest illustration of the basic idea of backprop algorithm. It does not involve matrix nor vectors. It only involves derivatives against scalars. Very good for starters."));


        announcementAdapter myAdapter = new announcementAdapter(this,announcements);
        recyclerView.setAdapter(myAdapter);
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
