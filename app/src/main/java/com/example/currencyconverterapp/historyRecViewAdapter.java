package com.example.currencyconverterapp;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
        holder.txtBaseCurrencyHistory.setText("From: "+conversions.get(position).getBaseCurrencyCode());
        holder.txtTargetCurrencyHistory.setText("To: "+conversions.get(position).getTargetCurrencyCode());
        holder.txtBaseAmountHistory.setText("Base Amount: "+ conversions.get(position).getBaseAmount().toString()+" "+ conversions.get(position).getBaseCurrencyCode().toString());
        holder.txtTargetAmountHistory.setText("Target Amount: "+ conversions.get(position).getBaseAmount().toString()+" "+ conversions.get(position).getTargetCurrencyCode().toString());
        holder.txtExchangeRateHistory.setText("Exchange Rate: "+ String.format("%.4f", conversions.get(position).getChangeRate()));


//
        boolean isExpanded = conversions.get(position).isExpanded();
        TransitionManager.beginDelayedTransition(holder.parent);

        if (isExpanded){
            holder.expandedRelLayout.setVisibility(View.VISIBLE);
            holder.btnDownArrow.setVisibility(View.GONE);
        }else{
            holder.expandedRelLayout.setVisibility(View.GONE);
            holder.btnDownArrow.setVisibility(View.VISIBLE);

        }
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
        private TextView txtBaseCurrencyHistory, txtTargetCurrencyHistory,txtBaseAmountHistory, txtTargetAmountHistory,txtExchangeRateHistory;
        private ImageView btnUpArrow, btnDownArrow;
        private RelativeLayout expandedRelLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            txtBaseCurrencyHistory= itemView.findViewById(R.id.txtBaseCurrencyHistory);
            txtTargetCurrencyHistory= itemView.findViewById(R.id.txtTargetCurrencyHistory);
            txtBaseAmountHistory = itemView.findViewById(R.id.txtBaseAmountHistory);
            txtTargetAmountHistory = itemView.findViewById(R.id.txtTargetAmountHistory);
            txtExchangeRateHistory = itemView.findViewById(R.id.txtExchangeRateHistory);
            btnUpArrow = itemView.findViewById(R.id.btnUpArrow);
            btnDownArrow = itemView.findViewById(R.id.btnDownArrow);
            expandedRelLayout = itemView.findViewById(R.id.expandedRelLayout);

            btnDownArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Conversion conversion = conversions.get(getAdapterPosition());
                    conversion.setExpanded(!conversion.isExpanded());
                    notifyItemChanged(getAdapterPosition());

                }
            });
            btnUpArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Conversion conversion = conversions.get(getAdapterPosition());
                    conversion.setExpanded(!conversion.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}
