package com.declan.digivice.digiatlasv3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DigimonAdapter extends RecyclerView.Adapter<DigimonAdapter.ViewHolder> {

    public interface OnItemClick {
        void onClick(DigimonEntry entry);
    }

    private final List<DigimonEntry> digimonList;
    private final OnItemClick onItemClick;

    public DigimonAdapter(List<DigimonEntry> digimonList, OnItemClick onItemClick) {
        this.digimonList = digimonList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_digimon, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DigimonEntry entry = digimonList.get(position);
        holder.name.setText(entry.name);
        holder.stage.setText(entry.stage);

        holder.itemView.setOnClickListener(v -> onItemClick.onClick(entry));
    }

    @Override
    public int getItemCount() {
        return digimonList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView stage;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            stage = itemView.findViewById(R.id.stage);
        }
    }
}
