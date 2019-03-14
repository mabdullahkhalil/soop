package com.something.mabdullahk.soop.quizzesList;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;

import java.util.Date;
import java.util.List;

public class quizzesListCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<quizzesListClass> quizzesList;

        quizzesListCardAdapter(Activity mContext, List<quizzesListClass> quizzesList){
        this.mContext = mContext;
        this.quizzesList=quizzesList;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                System.out.println("view type::::"+ viewType);
            View mView = android.view.LayoutInflater.from(parent.getContext()).inflate(R.layout.quizzes_list_card, parent, false);
                return new quizzesListCardViewHolder(mView);

                }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {


        }

        @Override
        public int getItemCount() {
                return quizzesList.size();
        }
}


class quizzesListCardViewHolder extends RecyclerView.ViewHolder{

    TextView month;
    TextView paidAt;
    TextView amountPaid;
    TextView amountRemaining;
    TextView status;




    public quizzesListCardViewHolder(View itemView) {
        super(itemView);


//        month = itemView.findViewById(R.id.quizzesListMonth);
//        paidAt = itemView.findViewById(R.id.quizzesListDate);
//        amountPaid = itemView.findViewById(R.id.quizzesListPaid);
//        amountRemaining = itemView.findViewById(R.id.quizzesListRemaining);
//        status = itemView.findViewById(R.id.quizzesListStatus);



    }
}