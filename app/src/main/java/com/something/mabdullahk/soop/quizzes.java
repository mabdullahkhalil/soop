package com.something.mabdullahk.soop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class quizzes extends AppCompatActivity {


    RecyclerView recyclerView;
    List<quizClass> quizzes1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizzes);

        recyclerView = findViewById(R.id.quizzesRecyclerView);

        GridLayoutManager foodMenuGrid = new GridLayoutManager(quizzes.this, 1);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Quizzes");
        getSupportActionBar().setLogo(R.drawable.ic_quiz);


        recyclerView.setLayoutManager(foodMenuGrid);
        quizzes1 = new ArrayList<>();


        quizzes1.add(new quizClass("Exam Term 1","Physics","23","100","43","50","50"));
        quizzes1.add(new quizClass("Exam Term 1","Physics","23","100","43","50","50"));
        quizzes1.add(new quizClass("Exam Term 1","Physics","23","100","43","50","50"));


        quizzesCardAdapter myAdapter = new quizzesCardAdapter(quizzes.this,quizzes1);
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
