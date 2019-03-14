package com.something.mabdullahk.soop.quizzesList;

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

import com.something.mabdullahk.soop.HTTPrequest;
import com.something.mabdullahk.soop.R;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        ((quizzesListCardViewHolder)holder).title.setText(quizzesList.get(position).getName());
        ((quizzesListCardViewHolder)holder).subject.setText(quizzesList.get(position).getSubjects());
        ((quizzesListCardViewHolder)holder).passingMarks.setText(quizzesList.get(position).getPassing_marks());
        ((quizzesListCardViewHolder)holder).noOfQUestions.setText(quizzesList.get(position).getTotal_questions());
        ((quizzesListCardViewHolder)holder).difficulty.setText(quizzesList.get(position).getDifficulty_level());


        switch (quizzesList.get(position).getDifficulty_level().toLowerCase()){
            case "normal":
                ((quizzesListCardViewHolder)holder).difficulty.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ff99cc00")));
                break;
            case "medium":
                ((quizzesListCardViewHolder)holder).difficulty.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffffbb33")));
                break;
            case "hard":
                ((quizzesListCardViewHolder)holder).difficulty.setChipBackgroundColor(ColorStateList.valueOf(Color.parseColor("#ffff4444")));
                break;


        }

        ((quizzesListCardViewHolder)holder).quizListCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                Map<String, String> headers = new HashMap<>();
                params.put("sid",quizzesList.get(position).getStudentId());

                HTTPrequest.placeRequest("https://soop-staging.herokuapp.com/api/v1/tests/quizzes/"+quizzesList.get(position).getId()+"/start", "Post", params, headers, new HTTPrequest.VolleyCallback() {
                    @Override
                    public void onSuccess(String result) {
                        System.out.println(result);
                    }

                    @Override
                    public void onFaliure(String faliure) {

                    }
                },mContext);
            }
        });

        }

        @Override
        public int getItemCount() {
                return quizzesList.size();
        }
}


class quizzesListCardViewHolder extends RecyclerView.ViewHolder{

    TextView title;
    TextView subject;
    TextView passingMarks;
    TextView noOfQUestions;
    Chip difficulty;
    LinearLayout quizListCard;




    public quizzesListCardViewHolder(View itemView) {
        super(itemView);


        title = itemView.findViewById(R.id.quizListTitle);
        subject = itemView.findViewById(R.id.quizListSubject);
        passingMarks = itemView.findViewById(R.id.quizListPassignMarks);
        noOfQUestions = itemView.findViewById(R.id.quizListQUestions);
        difficulty = itemView.findViewById(R.id.quizListDifficulty);
        quizListCard = itemView.findViewById(R.id.cardQuizList);


    }
}

