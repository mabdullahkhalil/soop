package com.something.mabdullahk.soop.Datesheet;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.Datesheet.datesheetListActivity;

import java.util.List;

public class datesheetListCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<datesheetListClass> datesheetList;

        datesheetListCardAdapter(Activity mContext, List<datesheetListClass> datesheetList){
        this.mContext = mContext;
        this.datesheetList=datesheetList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.datesheet_list_card, parent, false);
                return new datesheetListCardViewHolder(mView);

                }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((datesheetListCardViewHolder)holder).days_to_go.setText(datesheetList.get(position).getDays_to_go());
        ((datesheetListCardViewHolder)holder).date.setText(datesheetList.get(position).getDate());
        ((datesheetListCardViewHolder)holder).exam.setText(datesheetList.get(position).getSubject());
        ((datesheetListCardViewHolder)holder).time.setText(datesheetList.get(position).getTime());
       // ((datesheetListCardViewHolder)holder).teacher.setText(datesheetList.get(position).getTeacher());

       // ((examsListCardViewHolder)holder).end_date.setText(examsList.get(position).getEnd_date());





        }



        @Override
        public int getItemCount() {
                return datesheetList.size();
        }
}


class datesheetListCardViewHolder extends RecyclerView.ViewHolder{

    TextView days_to_go;
    TextView date;
    TextView exam;
    TextView time;
   // TextView teacher;






    public datesheetListCardViewHolder(View itemView) {
        super(itemView);



        days_to_go = itemView.findViewById(R.id.days_to_go);
        date = itemView.findViewById(R.id.dateD);
        exam = itemView.findViewById(R.id.examD);
        time = itemView.findViewById(R.id.timeD);
       // teacher = itemView.findViewById(R.id.teacherD);



    }
}

