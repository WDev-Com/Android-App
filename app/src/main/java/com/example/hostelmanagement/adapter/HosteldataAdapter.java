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

import com.example.hostelmanagement.R;

import java.util.ArrayList;
import java.util.List;

public class HosteldataAdapter extends RecyclerView.Adapter<HosteldataAdapter.ViewHolder> implements Filterable {
    private Context context;
    private List<HostelList> hostelLists;
    private List<HostelList> hostelListsfull;

    public HosteldataAdapter(Context context, List<HostelList> hostelLists) {
        this.context = context;
        this.hostelLists = hostelLists;
        hostelListsfull = new ArrayList<>(hostelLists);
    }

    @NonNull
    @Override
    public HosteldataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.hosteldata, parent, false);
        return new HosteldataAdapter.ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull HosteldataAdapter.ViewHolder holder, int position) {
        HostelList datass = hostelLists.get(position);
        holder.Rollno.setText(datass.getRollno());
        holder.Roomno.setText(datass.getRoomno());
        holder.Bedno.setText(datass.getBedno());
        holder.Tableno.setText(datass.getTableno());

    }

    @Override
    public int getItemCount() {
        return hostelLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        public TextView Rollno;
        public TextView Roomno;
        public TextView Bedno;
        public TextView Tableno;

        public ViewHolder(View vi) {
            super(vi);
            itemView.setOnClickListener(this);
            Rollno = itemView.findViewById(R.id.hAmd);
            Roomno = itemView.findViewById(R.id.hbhed);
            Bedno = itemView.findViewById(R.id.hdued);
            Tableno = itemView.findViewById(R.id.hbihd);

        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");

        }
        }

    @Override
    public Filter getFilter() {
        return exampleFilter3;
    }

    private Filter exampleFilter3 = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<HostelList> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(hostelListsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (HostelList item : hostelListsfull) {
                    if (item.getRollno().toLowerCase().contains(filterPattern)) {
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
            hostelLists.clear();
            hostelLists.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };


}
