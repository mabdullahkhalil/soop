package com.something.mabdullahk.soop;import android.content.Intent;import android.graphics.Color;import android.support.v4.view.ViewPager;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.support.v7.widget.GridLayoutManager;import android.support.v7.widget.RecyclerView;import android.text.Html;import android.text.Layout;import android.view.LayoutInflater;import android.view.View;import android.widget.Button;import android.widget.EditText;import android.widget.LinearLayout;import android.widget.TextView;import java.util.ArrayList;import java.util.List;import com.something.mabdullahk.soop.quizzes;import com.something.mabdullahk.soop.student;import com.something.mabdullahk.soop.studentCardAdapter;public class studentCards extends AppCompatActivity {    RecyclerView recyclerView;    private ViewPager viewPager;    private LinearLayout layoutDot;    private TextView[]dotstv;    private int[]layouts;    private Button btnSkip;    private Button btnNext;    private studentCardAdapter studentCardAdapter;    List<student> allChildrenData;    student currentSelectedStudent;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_student_cards);        layoutDot = (LinearLayout) findViewById(R.id.dotLayout1);        viewPager = findViewById(R.id.view_pager_1);        allChildrenData = (List<student>) getIntent().getSerializableExtra("allChildrenData");        layouts = new int[allChildrenData.size()];        System.out.println("the chldrendata"+ allChildrenData);        LinearLayout quizLayout = (LinearLayout) findViewById(R.id.quizlayout);        LinearLayout attendanceLayout = (LinearLayout) findViewById(R.id.attendanceLayout);        LinearLayout announcementLayout = (LinearLayout) findViewById(R.id.announcementLayout);        quizLayout.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(studentCards.this, quizzes.class);                intent.putExtra("studentID",currentSelectedStudent.getId());                startActivity(intent);            }        });        attendanceLayout.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                startActivity(new Intent(studentCards.this, attendanceActivity.class));            }        });        announcementLayout.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent intent = new Intent(studentCards.this, announcementActivity.class);                intent.putExtra("studentID",currentSelectedStudent.getId());                startActivity(intent);//                startActivity(new Intent(studentCards.this, announcementActivity.class));            }        });        for (int i =0 ; i<layouts.length ; i++){            layouts[i] = R.layout.student_card;        }        studentCardAdapter = new studentCardAdapter(layouts,getApplicationContext(),allChildrenData);        viewPager.setAdapter(studentCardAdapter);        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {            @Override            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {            }            @Override            public void onPageSelected(int position) {                currentSelectedStudent = allChildrenData.get(position);                setDotStatus(position);                System.out.println("the current student is "+currentSelectedStudent.getName());            }            @Override            public void onPageScrollStateChanged(int state) {            }        });        setDotStatus(0);        currentSelectedStudent = allChildrenData.get(0);    }    private void setDotStatus(int page){        layoutDot.removeAllViews();        dotstv =new TextView[layouts.length];        for (int i = 0; i < dotstv.length; i++) {            dotstv[i] = new TextView(this);            dotstv[i].setText(Html.fromHtml("&#8226;"));            dotstv[i].setTextSize(30);            dotstv[i].setTextColor(Color.parseColor("#5092D1"));            layoutDot.addView(dotstv[i]);        }        //Set current dot active        if(dotstv.length>0){            dotstv[page].setTextColor(Color.parseColor("#1955A7"));        }    }}