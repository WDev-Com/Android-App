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

public class Adminacconadapter extends RecyclerView.Adapter<Adminacconadapter.ViewHolder>{
    private Context context;
    private List<ADAlist> adAlists;

    public Adminacconadapter(Context context, List<ADAlist> adAlists) {
        this.context = context;
        this.adAlists = adAlists;
    }
    @NonNull
    @Override
    public Adminacconadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vi = LayoutInflater.from(parent.getContext()).inflate(R.layout.adannon, parent, false);
        return new Adminacconadapter.ViewHolder(vi);
    }

    @Override
    public void onBindViewHolder(@NonNull Adminacconadapter.ViewHolder holder, int position) {
        ADAlist data = adAlists.get(position);
        holder.annocid.setText(data.getannocid());
        holder.datea.setText(data.getdate());
        holder.notea.setText(data.getnote());
    }

    @Override
    public int getItemCount() {
        return adAlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView annocid;
        public TextView datea;
        public TextView notea;
        public ViewHolder(View vi) {
            super(vi);
            itemView.setOnClickListener(this);
            annocid = itemView.findViewById(R.id.anniddd);
            datea = itemView.findViewById(R.id.anndateDD);
            notea = itemView.findViewById(R.id.annodd);
        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");

        }

    }
}
