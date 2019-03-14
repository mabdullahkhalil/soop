package com.something.mabdullahk.soop.practiceQuiz;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.practiceQuiz.exercises;

import java.util.List;

public class practiceQuizCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<exercises> practiceQuizList;

    practiceQuizCardAdapter(Activity mContext, List<exercises> practiceQuizList){
        this.mContext = mContext;
        this.practiceQuizList=practiceQuizList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("view type::::"+ viewType);
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.practice_quiz_card, parent, false);
        return new com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder(mView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
//        ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).month.setText(practiceQuizList.get(position).getMonth());
//        ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).paidAt.setText(practiceQuizList.get(position).getPaidAt());
//        ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).amountPaid.setText(practiceQuizList.get(position).getAmountPaid());
//        ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).amountRemaining.setText(practiceQuizList.get(position).getAmountRemaining());
//        ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).status.setText(practiceQuizList.get(position).getStatus());
//        String status = practiceQuizList.get(position).getStatus();
//        switch (status){
//            case "Paid":
//                ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).status.setTextColor(Color.parseColor("#32CD32"));
//                break;
//            case "Not Paid":
//                ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).status.setTextColor(Color.parseColor("#FF0000"));
//                break;
//            case "Partially Paid":
//                ((com.something.mabdullahk.soop.practiceQuiz.practiceQuizCardViewHolder)holder).status.setTextColor(Color.parseColor("#FFA500"));
//                break;
//
//        }


    }

    @Override
    public int getItemCount() {
        return practiceQuizList.size();
    }
}


class practiceQuizCardViewHolder extends RecyclerView.ViewHolder {

//    TextView month;
    TextView paidAt;
    TextView amountPaid;
    TextView amountRemaining;
    TextView status;

//
    public practiceQuizCardViewHolder(View itemView) {
        super(itemView);
//
//
//        month = itemView.findViewById(R.id.practiceQuizMonth);
//        paidAt = itemView.findViewById(R.id.practiceQuizDate);
//        amountPaid = itemView.findViewById(R.id.practiceQuizPaid);
//        amountRemaining = itemView.findViewById(R.id.practiceQuizRemaining);
//        status = itemView.findViewById(R.id.practiceQuizStatus);
//
//
    }

}
