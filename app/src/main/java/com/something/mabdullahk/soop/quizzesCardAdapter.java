package com.something.mabdullahk.soop;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by mabdullahk on 31/01/2019.
 */

public class quizzesCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<quizClass> studentQuizzesList;

    quizzesCardAdapter(Activity mContext, List<quizClass> studentQuizzesList){
        this.mContext = mContext;
        this.studentQuizzesList=studentQuizzesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("view type::::"+ viewType);
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.quiz_card, parent, false);
        return new studentQuizCardViewHolder(mView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((studentQuizCardViewHolder)holder).title.setText(studentQuizzesList.get(position).getTitle());
        ((studentQuizCardViewHolder)holder).subject.setText(studentQuizzesList.get(position).getSubject());
        ((studentQuizCardViewHolder)holder).maximum.setText(studentQuizzesList.get(position).getMaximum_marks());
        ((studentQuizCardViewHolder)holder).average.setText(studentQuizzesList.get(position).getAverage());
        ((studentQuizCardViewHolder)holder).marks.setText(studentQuizzesList.get(position).getMarks_obtained());
        ((studentQuizCardViewHolder)holder).title.measure(0,0);
        System.out.println(((studentQuizCardViewHolder)holder).title.getWidth()+" is the widthh......");

    }

    @Override
    public int getItemCount() {
        return studentQuizzesList.size();
    }
}


class studentQuizCardViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView subject;
    TextView maximum;
    TextView average;
    TextView marks;

    public studentQuizCardViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.quizTitle);
        subject = itemView.findViewById(R.id.quizSubject);
        maximum = itemView.findViewById(R.id.quizMax);
        average = itemView.findViewById(R.id.quizAverage);
        marks = itemView.findViewById(R.id.quizMarks);

    }
}