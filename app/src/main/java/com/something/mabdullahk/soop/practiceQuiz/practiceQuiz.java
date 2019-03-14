package com.something.mabdullahk.soop.practiceQuiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.leo.simplearcloader.ArcConfiguration;
import com.leo.simplearcloader.SimpleArcDialog;
import com.leo.simplearcloader.SimpleArcLoader;
import com.something.mabdullahk.soop.R;


import java.util.ArrayList;
import java.util.List;

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
        getSupportActionBar().setTitle("practiceQuiz");

        ArcConfiguration configuration = new ArcConfiguration(this);
        configuration.setLoaderStyle(SimpleArcLoader.STYLE.SIMPLE_ARC);
        configuration.setText("Fetching Data..");
        int[] colors = new int[]{R.color.colorlogo1, R.color.colorlogo2, R.color.colorlogo3, R.color.colorlogo4};
        configuration.setColors(colors);
        configuration.setAnimationSpeed(SimpleArcLoader.SPEED_MEDIUM);


        mDialog = new SimpleArcDialog(this);
        mDialog.setConfiguration(configuration);
        exercisess = new ArrayList<>();
//        practiceQuizMainView = (LinearLayout) findViewById(R.id.practiceQuizMainView);
//        practiceQuizmsg = (LinearLayout) findViewById(R.id.practiceQuizMessage);
//        practiceQuizScroll = (ScrollView) findViewById(R.id.practiceQuizScroll);


        recyclerView.setLayoutManager(practiceQuizGrid);




        exercisess.add(new exercises("quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        exercisess.add(new exercises("quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        exercisess.add(new exercises("quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        exercisess.add(new exercises("quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        exercisess.add(new exercises("quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        exercisess.add(new exercises("quiz","quiz","quiz","quiz","quiz","quiz","quiz"));

        practiceQuizCardAdapter myAdapter = new practiceQuizCardAdapter(practiceQuiz.this,exercisess);
        recyclerView.setAdapter(myAdapter);

    }
}
