package com.example.currencyconverterapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class historyRecViewAdapter extends RecyclerView.Adapter<historyRecViewAdapter.ViewHolder>{
    private ArrayList<Conversion> conversions = new ArrayList<>();
    public historyRecViewAdapter(){

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_rec_view,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //TODO: code the behavior of the rec view


    }

    @Override
    public int getItemCount() {
        return conversions.size();
    }

    public void setConversions(ArrayList<Conversion> conversions) {
        this.conversions = conversions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
