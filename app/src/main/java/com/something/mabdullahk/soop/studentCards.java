package com.something.mabdullahk.soop;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class studentCards extends AppCompatActivity {

    RecyclerView recyclerView;
    List<student> new_card = new ArrayList<>();
    private ViewPager viewPager;
    private LinearLayout layoutDot;
    private TextView[]dotstv;
    private int[]layouts;
    private Button btnSkip;
    private Button btnNext;
    private studentCardAdapter studentCardAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_cards);


        layoutDot = (LinearLayout) findViewById(R.id.dotLayout1);
        viewPager = findViewById(R.id.view_pager_1);

        LinearLayout layout = (LinearLayout) findViewById(R.id.quizlayout);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(studentCards.this,quizzes.class));
            }
        });


        student student_1 = new student("Ibrahim Waqas","Class 1 Section A","1","2","3");
        student student_2 = new student("Ibrahim Waqas","Class 1 Section A","1","2","3");
        new_card.add(student_1);
        new_card.add(student_2);
        layouts = new int[]{R.layout.student_card,R.layout.student_card};


        studentCardAdapter = new studentCardAdapter(layouts,getApplicationContext(),new_card);
        viewPager.setAdapter(studentCardAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setDotStatus(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        setDotStatus(0);


    }

    private void setDotStatus(int page){
        layoutDot.removeAllViews();
        dotstv =new TextView[layouts.length];
        for (int i = 0; i < dotstv.length; i++) {
            dotstv[i] = new TextView(this);
            dotstv[i].setText(Html.fromHtml("&#8226;"));
            dotstv[i].setTextSize(30);
            dotstv[i].setTextColor(Color.parseColor("#5092D1"));
            layoutDot.addView(dotstv[i]);
        }
        //Set current dot active
        if(dotstv.length>0){
            dotstv[page].setTextColor(Color.parseColor("#1955A7"));
        }
    }
}
