package com.example.hostelmanagement.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hostelmanagement.R;

import java.util.List;

public class Paymentviewadapter extends RecyclerView.Adapter<Paymentviewadapter.ViewHolder>{
    private Context context;
    private List<Paymentlist> paymentlists;

    public Paymentviewadapter(Context context, List<Paymentlist> paymentlists) {
        this.context = context;
        this.paymentlists = paymentlists;
    }

    // Where to get the single card as viewholder Object
    @NonNull
    @Override
    public Paymentviewadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.paymentview, parent, false);
        return new ViewHolder(vi);
    }

    // What will happen after we create the viewholder object
    @Override
    public void onBindViewHolder(@NonNull Paymentviewadapter.ViewHolder holder, int position) {
        Paymentlist data = paymentlists.get(position);
        holder.Billno.setText(data.getBillno());
        holder.Billtitle.setText(data.getBilltitle());
        holder.Billamount.setText(data.getBillamount());
        holder.Billdate.setText(data.getBilldate());
        holder.Billdue.setText(data.getBilldue());
        holder.Billstatus.setText(data.getBillstatus());
    }

    // How many items?
    @Override
    public int getItemCount() {
        return paymentlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Billno;
        public TextView Billtitle;
        public TextView Billamount;
        public TextView Billdate;
        public TextView Billdue;
        public TextView Billstatus;
        public ViewHolder(View vi) {
            super(vi);
            itemView.setOnClickListener(this);
            Billno = itemView.findViewById(R.id.billd);
            Billtitle = itemView.findViewById(R.id.billtitled);
            Billamount = itemView.findViewById(R.id.billAmd);
            Billdate = itemView.findViewById(R.id.billdated);
            Billdue = itemView.findViewById(R.id.dued);
            Billstatus = itemView.findViewById(R.id.billstatusd);
        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");

        }

    }

}

