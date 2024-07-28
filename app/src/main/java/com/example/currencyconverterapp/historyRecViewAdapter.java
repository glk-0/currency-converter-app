package com.example.currencyconverterapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class historyRecViewAdapter extends RecyclerView.Adapter<historyRecViewAdapter.ViewHolder>{
    private List<Conversion> conversions = new ArrayList<>();
    private Context mContext;
    public historyRecViewAdapter(Context mContext){
        this.mContext=mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_conversions,parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtBaseCurrency.setText("From: "+conversions.get(position).getBaseCurrencyCode());
        holder.txtTargetCurrency.setText("To: "+conversions.get(position).getTargetCurrencyCode());

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Item Selected", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return conversions.size();
    }

    public void setConversions(List<Conversion> conversions) {
        this.conversions = conversions;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private TextView txtBaseCurrency, txtTargetCurrency;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtBaseCurrency= itemView.findViewById(R.id.txtBaseCurrency);
            txtTargetCurrency= itemView.findViewById(R.id.txtTargetCurrency);
        }
    }
}
