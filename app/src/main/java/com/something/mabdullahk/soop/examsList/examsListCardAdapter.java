package com.something.mabdullahk.soop.examsList;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.design.chip.Chip;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.academicCalender.academicCalenderActivity;
import com.something.mabdullahk.soop.questions.questionsActivity;
import com.something.mabdullahk.soop.examsList.examsListClass;
import com.something.mabdullahk.soop.Datesheet.datesheetListActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class examsListCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<com.something.mabdullahk.soop.examsList.examsListClass> examsList;

        examsListCardAdapter(Activity mContext, List<examsListClass> examsList){
        this.mContext = mContext;
        this.examsList=examsList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.exams_list_card, parent, false);
                return new examsListCardViewHolder(mView);

                }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((examsListCardViewHolder)holder).title.setText(examsList.get(position).getTitle());
        ((examsListCardViewHolder)holder).start_date.setText(examsList.get(position).getStart_date()+" - "+examsList.get(position).getEnd_date());
       // ((examsListCardViewHolder)holder).end_date.setText(examsList.get(position).getEnd_date());



        switch (examsList.get(position).getResult_announced().toLowerCase()){
            case "false":
                ((examsListCardViewHolder)holder).result_announced.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffffbb33")));
                ((examsListCardViewHolder)holder).result_announced.setText("Result Pending");
                break;
            case "true":
                ((examsListCardViewHolder)holder).result_announced.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ff99cc00")));
                ((examsListCardViewHolder)holder).result_announced.setText("Result Announced");
                break;



        }

        ((examsListCardViewHolder)holder).Datesheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        Intent intent = new Intent(mContext, datesheetListActivity.class);
                        intent.putExtra("examID",examsList.get(position).getId());
                        intent.putExtra("studentID",examsList.get(position).getSID());
                        mContext.startActivity(intent);

            }
        });
        ((examsListCardViewHolder)holder).result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setTitle("COMING SOON")
                        .setMessage("\nThe Results are coming Soon")

                        // Specifying a listener allows you to take an action before dismissing the dialog.
                        // The dialog is automatically dismissed when a dialog button is clicked.
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Continue with delete operation
                            }
                        })

                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();

            }
        });

        }

        @Override
        public int getItemCount() {
                return examsList.size();
        }
}


class examsListCardViewHolder extends RecyclerView.ViewHolder{

    TextView title;
    TextView start_date;
    TextView end_date;
    Chip result_announced;
    LinearLayout examListCard;
    TextView Datesheet;
    TextView result;




    public examsListCardViewHolder(View itemView) {
        super(itemView);



        title = itemView.findViewById(R.id.examListTitle);
        start_date = itemView.findViewById(R.id.Date);
        result_announced = itemView.findViewById(R.id.resultAnnounced);
        examListCard = itemView.findViewById(R.id.cardexamList);
        Datesheet = itemView.findViewById(R.id.date_sheet);
        result =itemView.findViewById(R.id.result);

    }
}

