package com.something.mabdullahk.soop.practiceQuiz;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;

import java.util.List;

public class answersListAdapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> answers;


    public answersListAdapter(Activity context, List<String> answers) {
        super(context, R.layout.answers_list_item, answers);
        // TODO Auto-generated constructor stub
        this.context=context;
        this.answers=answers;

    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.answers_list_item, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.answerListItem);


        titleText.setText(answers.get(position));

        return rowView;

    };
}  
