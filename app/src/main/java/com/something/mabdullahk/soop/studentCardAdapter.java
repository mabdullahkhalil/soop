package com.something.mabdullahk.soop;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
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
        container.addView(v);
        return v;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View)object;
        container.removeView(v);
    }
}




class StudentCardViewHolder extends RecyclerView.ViewHolder{
    TextView studentName;
    TextView studentClass;


    public StudentCardViewHolder(View itemView) {
        super(itemView);

        studentName = itemView.findViewById(R.id.studentName);
        studentClass = itemView.findViewById(R.id.studentClass);

    }
}

