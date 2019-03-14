package com.something.mabdullahk.soop.questions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.practiceQuiz.answersListAdapter;

import java.util.ArrayList;
import java.util.List;

public class questionsActivity extends AppCompatActivity {


    ListView answerss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        answerss = (ListView) findViewById(R.id.answersLayout);

        answerss.setChoiceMode(1);


        List<String> answers= new ArrayList<>();

        answers.add("Quaid -e- Azam \n was the \n founder of pakistan");
        answers.add("Gandhi Ali Jinnah");
        answers.add("Hamza Abdullah");
        answers.add("Soop is the best.");


        answersListAdapter adapter=new answersListAdapter(this, answers);

        answerss.setAdapter(adapter);



    }
}
