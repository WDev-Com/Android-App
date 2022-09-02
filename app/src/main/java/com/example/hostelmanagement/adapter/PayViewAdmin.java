package com.example.hostelmanagement.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hostelmanagement.PaymentDatabase;
import com.example.hostelmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class PayViewAdmin extends RecyclerView.Adapter<PayViewAdmin.ViewHolder> implements Filterable {
    private Context context;
    private List<PaymentlistAD> paymentlistAD;
    private List<PaymentlistAD> paymentlistADfull;

    public PayViewAdmin(Context context, List<PaymentlistAD> paymentlistAD) {
        this.context = context;
        this.paymentlistAD = paymentlistAD;
        paymentlistADfull = new ArrayList<>(paymentlistAD);
    }

    @NonNull
    @Override
    public PayViewAdmin.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.payviewadmin, parent, false);
        return new PayViewAdmin.ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull PayViewAdmin.ViewHolder holder, int position) {
        PaymentlistAD datas = paymentlistAD.get(position);
        holder.Rollno.setText(datas.getaRollno());
        holder.Billno.setText(datas.getaBillno());
        holder.Billtitle.setText(datas.getaBilltitle());
        holder.Billamount.setText(datas.getaBillamount());
        holder.Billdate.setText(datas.getaBilldate());
        holder.Billdue.setText(datas.getaBilldue());
        holder.Billstatus.setText(datas.getaBillstatus());
    }

    @Override
    public int getItemCount() {
        return paymentlistAD.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView Rollno;
        public TextView Billno;
        public TextView Billtitle;
        public TextView Billamount;
        public TextView Billdate;
        public TextView Billdue;
        public TextView Billstatus;
        public ViewHolder(View vi) {
            super(vi);
            itemView.setOnClickListener(this);
            Rollno = itemView.findViewById(R.id.roldd);
            Billno = itemView.findViewById(R.id.billld);
            Billtitle = itemView.findViewById(R.id.abilltitled);
            Billamount = itemView.findViewById(R.id.abillAmd);
            Billdate = itemView.findViewById(R.id.abilldated);
            Billdue = itemView.findViewById(R.id.adued);
            Billstatus = itemView.findViewById(R.id.abillstatusd);
        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");

        }
    }
    @Override
    public Filter getFilter() {
        return exampleFilter2;
    }

    private Filter exampleFilter2 = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<PaymentlistAD> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(paymentlistADfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (PaymentlistAD item : paymentlistADfull) {
                    if (item.getaRollno().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            paymentlistAD.clear();
            paymentlistAD.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}
