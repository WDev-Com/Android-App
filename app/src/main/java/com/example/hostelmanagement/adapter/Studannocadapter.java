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

public class Studannocadapter extends RecyclerView.Adapter<Studannocadapter.ViewHolder>{
    private Context context;
    private List<STAlist> stAlists;

    public Studannocadapter(Context context, List<STAlist> stAlists) {
        this.context = context;
        this.stAlists = stAlists;
    }
    @NonNull
    @Override
    public Studannocadapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vc = LayoutInflater.from(parent.getContext()).inflate(R.layout.staccone, parent, false);
        return new Studannocadapter.ViewHolder(vc);
    }

    @Override
    public void onBindViewHolder(@NonNull Studannocadapter.ViewHolder holder, int position) {
        STAlist datas = stAlists.get(position);
        holder.datea.setText(datas.getdate());
        holder.notea.setText(datas.getnote());
    }

    @Override
    public int getItemCount() {
        return stAlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView datea;
        public TextView notea;
        public ViewHolder(View vi) {
            super(vi);
            itemView.setOnClickListener(this);
            datea = itemView.findViewById(R.id.anndateDDs);
            notea = itemView.findViewById(R.id.annodds);
        }

        @Override
        public void onClick(View view) {
            Log.d("ClickFromViewHolder", "Clicked");

        }

    }
}
