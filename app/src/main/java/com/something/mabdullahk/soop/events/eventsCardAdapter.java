package com.something.mabdullahk.soop.events;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;

import java.util.List;
public class eventsCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<eventsClass> eventsList;

    eventsCardAdapter(Activity mContext, List<eventsClass> eventsList){
        this.mContext = mContext;
        this.eventsList=eventsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("view type::::"+ viewType);
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.events_cards, parent, false);
        return new eventsCardViewHolder(mView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((eventsCardViewHolder)holder).title.setText(eventsList.get(position).getTitle());
        ((eventsCardViewHolder)holder).date.setText(eventsList.get(position).getTime());
        ((eventsCardViewHolder)holder).description.setText(eventsList.get(position).getDescription());
        ((eventsCardViewHolder)holder).togo.setText(eventsList.get(position).getToGo());
        ((eventsCardViewHolder)holder).cardImage.setImageResource(eventsList.get(position).findImage());
        ((eventsCardViewHolder)holder).middleBar.setBackgroundColor(mContext.getResources().getColor(eventsList.get(position).findColor()));
        ((eventsCardViewHolder)holder).sideBar.setBackgroundColor(mContext.getResources().getColor(eventsList.get(position).findColor()));


    }

    @Override
    public int getItemCount() {
        return eventsList.size();
    }
}


class eventsCardViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView date;
    TextView description;
    TextView togo;
    ImageView cardImage;
    View middleBar;
    LinearLayout sideBar;


    public eventsCardViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.eventTitle);
        date = itemView.findViewById(R.id.eventDate);
        description = itemView.findViewById(R.id.eventDescription);
        togo = itemView.findViewById(R.id.eventTogoDays);
        cardImage = itemView.findViewById(R.id.eventPicture);
        middleBar = itemView.findViewById(R.id.eventsMiddleLine);
        sideBar = itemView.findViewById(R.id.eventSideBar);


    }
}