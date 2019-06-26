package com.something.mabdullahk.soop.students;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.something.mabdullahk.soop.R;

import java.util.List;

/**
 * Created by mabdullahk on 28/01/2019.
 */

public class studentCardAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private int[]layouts;
    private Context context;
    private List<student> myStudents;
    String s ="";
    char t;

    public studentCardAdapter(int[] layouts, Context context,List<student> myStudents) {
        this.layouts = layouts;
        this.myStudents = myStudents;
        this.context = context;
    }

    @Override
    public int getCount() {
        return layouts.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(layouts[position], container, false);
        TextView name=(TextView)v.findViewById(R.id.studentName);
        name.setText(myStudents.get(position).getName());

        TextView grade = (TextView)v.findViewById(R.id.studentClass);
        TextView picLetter = (TextView)v.findViewById(R.id.title);
        grade.setText(myStudents.get(position).getGrade());

        String s1 = myStudents.get(position).getName();
        String[] tokens = myStudents.get(position).getName().split(" ");
        for (int i =0; i < tokens.length;i++) {
            t = tokens[i].charAt(0);
            s = s+String.valueOf(t);
        }

        picLetter.setText(s);
        container.addView(v);
        s="";
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}



