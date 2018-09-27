package com.padmitriy.resultant.mvvm;

import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

abstract public class RecyclerViewModelAdapter<ITEM_T, VIEW_MODEL_T extends ItemViewModel<ITEM_T>>
        extends RecyclerView.Adapter<RecyclerViewModelAdapter.ItemViewHolder<ITEM_T, VIEW_MODEL_T>> {

    protected final List<ITEM_T> items;

    public RecyclerViewModelAdapter() {
        items = new ArrayList<>();
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder<ITEM_T, VIEW_MODEL_T> holder, int position) {
        holder.setItem(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemViewHolder<T, VT extends ItemViewModel<T>> extends RecyclerView.ViewHolder {

        protected final VT viewModel;
        private final ViewDataBinding binding;


        public ItemViewHolder(View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void setItem(T item) {
            viewModel.setItem(item);
            binding.executePendingBindings();
        }
    }
}
