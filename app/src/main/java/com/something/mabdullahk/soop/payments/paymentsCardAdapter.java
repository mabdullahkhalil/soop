package com.something.mabdullahk.soop.payments;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.something.mabdullahk.soop.R;

import java.util.List;
public class paymentsCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity mContext;
    private List<paymentsClass> paymentsList;

    paymentsCardAdapter(Activity mContext, List<paymentsClass> paymentsList){
        this.mContext = mContext;
        this.paymentsList=paymentsList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        System.out.println("view type::::"+ viewType);
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.payments_card, parent, false);
        return new paymentsCardViewHolder(mView);

    }



    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        ((paymentsCardViewHolder)holder).month.setText(paymentsList.get(position).getMonth());
        ((paymentsCardViewHolder)holder).paidAt.setText(paymentsList.get(position).getPaidAt());
        ((paymentsCardViewHolder)holder).amountPaid.setText(paymentsList.get(position).getAmountPaid());
        ((paymentsCardViewHolder)holder).amountRemaining.setText(paymentsList.get(position).getAmountRemaining());
        ((paymentsCardViewHolder)holder).status.setText(paymentsList.get(position).getStatus());


    }

    @Override
    public int getItemCount() {
        return paymentsList.size();
    }
}


class paymentsCardViewHolder extends RecyclerView.ViewHolder{

    TextView month;
    TextView paidAt;
    TextView amountPaid;
    TextView amountRemaining;
    TextView status;




    public paymentsCardViewHolder(View itemView) {
        super(itemView);


        month = itemView.findViewById(R.id.paymentsMonth);
        paidAt = itemView.findViewById(R.id.paymentsDate);
        amountPaid = itemView.findViewById(R.id.paymentsPaid);
        amountRemaining = itemView.findViewById(R.id.paymentsRemaining);
        status = itemView.findViewById(R.id.paymentsStatus);



    }
}