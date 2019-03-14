package com.something.mabdullahk.soop.quizzesList;

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
import com.something.mabdullahk.soop.quizzesList.quizzesListClass;

import java.util.ArrayList;
import java.util.List;

public class quizzesListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<quizzesListClass> quizzesList;
    SimpleArcDialog mDialog;
    String studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes_list);

        recyclerView = findViewById(R.id.quizzesListRecyclerView);
        studentId = (String) getIntent().getStringExtra("studentID");


        GridLayoutManager quizzesListGrid = new GridLayoutManager(this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quizzes");

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
        quizzesList = new ArrayList<>();


        quizzesList.add(new quizzesListClass("quiz","quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        quizzesList.add(new quizzesListClass("quiz","quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        quizzesList.add(new quizzesListClass("quiz","quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        quizzesList.add(new quizzesListClass("quiz","quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        quizzesList.add(new quizzesListClass("quiz","quiz","quiz","quiz","quiz","quiz","quiz","quiz"));
        quizzesList.add(new quizzesListClass("quiz","quiz","quiz","quiz","quiz","quiz","quiz","quiz"));


        quizzesListCardAdapter myAdapter = new quizzesListCardAdapter(quizzesListActivity.this,quizzesList);
        recyclerView.setAdapter(myAdapter);






    }
}
