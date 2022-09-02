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

import com.example.hostelmanagement.AdminAnnoucement;
import com.example.hostelmanagement.R;
import com.example.hostelmanagement.StudentPayment;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {

        private Context context;
        private List<Studentdatalist> studentdatalists;
        private List<Studentdatalist> studentdatalistsfull;

        public RecyclerViewAdapter(Context context, List<Studentdatalist> studentdatalists) {
            this.context = context;
            this.studentdatalists= studentdatalists;
            studentdatalistsfull = new ArrayList<>(studentdatalists);
        }

    // Where to get the single card as viewholder Object
        @NonNull
        @Override
        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rowofdata, parent, false);
            return new ViewHolder(view);
        }

        // What will happen after we create the viewholder object
        @Override
        public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
            Studentdatalist data = studentdatalists.get(position);

            holder.name.setText(data.getName());
            holder.rollno.setText(data.getRollno());
            holder.adharno.setText(data.getAdharno());
            holder.address.setText(data.getAddress());
            holder.contact.setText(data.getContact());
            holder.email.setText(data.getEmail());
            holder.gender.setText(data.getGender());
            holder.date.setText(data.getDate());
        }

        // How many items?
        @Override
        public int getItemCount() {
            return studentdatalists.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
            public TextView name;
            public TextView rollno;
            public TextView adharno;
            public TextView address;
            public TextView contact;
            public TextView email;
            public TextView gender;
            public TextView date;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                itemView.setOnClickListener(this);

                name = itemView.findViewById(R.id.named);
                rollno = itemView.findViewById(R.id.rolld);
                adharno = itemView.findViewById(R.id.adhard);
                address = itemView.findViewById(R.id.addressd);
                contact = itemView.findViewById(R.id.phod);
                email = itemView.findViewById(R.id.emaild);
                gender = itemView.findViewById(R.id.genderd);
                date = itemView.findViewById(R.id.Dated);
            }

            @Override
            public void onClick(View view) {
                Log.d("ClickFromViewHolder", "Clicked");

            }
        }


    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Studentdatalist> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(studentdatalistsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Studentdatalist item : studentdatalistsfull) {
                    if (item.getName().toLowerCase().contains(filterPattern)) {
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
            studentdatalists.clear();
            studentdatalists.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    }

