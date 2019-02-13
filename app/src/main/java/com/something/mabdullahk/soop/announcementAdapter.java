package com.something.mabdullahk.soop;

import android.app.Activity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
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

public class announcementAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<announcement> announcementsList;

    announcementAdapter(Activity mContext, List<announcement> announcementsList){
        this.mContext = mContext;
        this.announcementsList=announcementsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("view type::::"+ viewType);
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcement_card, parent, false);
        return new announcementsCardViewHolder(mView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((announcementsCardViewHolder)holder).title.setText(Html.fromHtml("<b>"+announcementsList.get(position).getTeacher()+"</b>"+" posted about "+"<b>"+announcementsList.get(position).getTitle()+"</b>"+"."));
        ((announcementsCardViewHolder)holder).date.setText(announcementsList.get(position).getTime());
        ((announcementsCardViewHolder)holder).description.setText(announcementsList.get(position).getDescription());

    }

    @Override
    public int getItemCount() {
        return announcementsList.size();
    }
}


class announcementsCardViewHolder extends RecyclerView.ViewHolder{
    TextView title;
    TextView date;
    TextView description;


    public announcementsCardViewHolder(View itemView) {
        super(itemView);

        title = itemView.findViewById(R.id.announcementTitle);
        date = itemView.findViewById(R.id.announcementTime);
        description = itemView.findViewById(R.id.announcementDescription);

    }
}