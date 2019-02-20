package com.something.mabdullahk.soop.students;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

        TextView grade = (TextView)v.findViewById(R.id.studentClass) ;
        grade.setText(myStudents.get(position).getGrade());
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}



