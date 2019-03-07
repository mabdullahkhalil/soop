package com.something.mabdullahk.soop.resources;

import android.app.Activity;
import android.app.DownloadManager;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.something.mabdullahk.soop.R;

import java.util.List;
public class resourcesCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<resourcesClass> resourcesList;

    resourcesCardAdapter(Activity mContext, List<resourcesClass> resourcesList){
        this.mContext = mContext;
        this.resourcesList=resourcesList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("view type::::"+ viewType);
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.resources_card, parent, false);
        return new resourcesCardViewHolder(mView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        ((resourcesCardViewHolder)holder).title.setText(Html.fromHtml("<b>"+resourcesList.get(position).getTeacherName()+"</b>"+" uploaded "+"<b>"+resourcesList.get(position).getTitle()+"</b>"+"."));
        ((resourcesCardViewHolder)holder).subject.setText(resourcesList.get(position).getSubject());
        ((resourcesCardViewHolder)holder).uploadtime.setText(resourcesList.get(position).getUploadTime());
        ((resourcesCardViewHolder)holder).download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"The file is being downloaded.",Toast.LENGTH_SHORT).show();
                DownloadManager downloadManager;
                downloadManager = (DownloadManager)mContext.getSystemService(mContext.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(resourcesList.get(position).getUrl());
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                Long reference = downloadManager.enqueue(request);

            }
        });


    }

    @Override
    public int getItemCount() {
        return resourcesList.size();
    }
}


class resourcesCardViewHolder extends RecyclerView.ViewHolder{

    TextView title;
    TextView uploadtime;
    TextView subject;
    ImageButton download;


    public resourcesCardViewHolder(View itemView) {
        super(itemView);


        title = itemView.findViewById(R.id.resourcesTitle);
        uploadtime = itemView.findViewById(R.id.resourcesDate);
        subject = itemView.findViewById(R.id.resourcesSubject);
        download = itemView.findViewById(R.id.resourcesDownload);




    }
}