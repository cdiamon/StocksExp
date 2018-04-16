package com.padmitriy.resultant.util;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.padmitriy.resultant.R;
import com.padmitriy.resultant.entities.StockListObject;

import java.util.List;

public class StocksRecyclerAdapter extends RecyclerView.Adapter<StocksRecyclerAdapter.HistoryViewHolder> {

    private final String TAG = getClass().getSimpleName();
    private List<StockListObject.Stock> items;

    public StocksRecyclerAdapter() {
    }

    public StocksRecyclerAdapter(List<StockListObject.Stock> items) {
        this.items = items;
    }

    public void updateRecyclerList(List<StockListObject.Stock> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HistoryViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_stocks, parent, false));
    }

    static class HistoryViewHolder extends RecyclerView.ViewHolder {

        View itemView;
        TextView stockName;
        TextView stockPrice;
        TextView stockAmount;

        HistoryViewHolder(View v) {
            super(v);
            this.itemView = v;

            stockName = v.findViewById(R.id.stockName);
            stockPrice = v.findViewById(R.id.stockPrice);
            stockAmount = v.findViewById(R.id.stockAmount);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull StocksRecyclerAdapter.HistoryViewHolder holder, int position) {
        StockListObject.Stock stockItem = items.get(position);

        holder.stockName.setText(stockItem.getName());
        holder.stockPrice.setText("price: " + String.valueOf(stockItem.getVolume()));
        holder.stockAmount.setText("amount: " + String.valueOf(stockItem.getPrice().getAmount()));
    }

    @Override
    public int getItemCount() {
        if (items != null) {
            return items.size();
        } else return 0;
    }
}