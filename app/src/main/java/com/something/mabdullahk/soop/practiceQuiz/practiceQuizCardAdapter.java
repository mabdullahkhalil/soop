package com.something.mabdullahk.soop.practiceQuiz;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.chip.Chip;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;
import com.something.mabdullahk.soop.announcements.announcementActivity;
import com.something.mabdullahk.soop.practiceQuiz.exercises;
import com.something.mabdullahk.soop.quizzes.quizzes;
import com.something.mabdullahk.soop.quizzesList.quizzesListActivity;
import com.something.mabdullahk.soop.students.studentCards;

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
        ((practiceQuizCardViewHolder)holder).title.setText(practiceQuizList.get(position).getName());
        ((practiceQuizCardViewHolder)holder).chip.setText(practiceQuizList.get(position).getSubjects());
        ((practiceQuizCardViewHolder)holder).card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, quizzesListActivity.class);
                intent.putExtra("studentID",practiceQuizList.get(position).getStudentId());
                mContext.startActivity(intent);
            }
        });
}




    @Override
    public int getItemCount() {
        return practiceQuizList.size();
    }
}


class practiceQuizCardViewHolder extends RecyclerView.ViewHolder {

    TextView title;
    Chip chip;
    LinearLayout card;


    public practiceQuizCardViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.practiceQuizCardName);
        chip = (Chip) itemView.findViewById(R.id.practiceQuizCardChip);
        card = (LinearLayout) itemView.findViewById(R.id.cardPracticeQuiz);

    }

}
