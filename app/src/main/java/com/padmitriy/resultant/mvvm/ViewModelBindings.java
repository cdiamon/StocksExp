package com.padmitriy.resultant.mvvm;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

public class ViewModelBindings {

    @BindingAdapter("recyclerViewViewModel")
    public static void setRecyclerViewViewModel(RecyclerView recyclerView,
                                                RecyclerViewViewModel viewModel) {
        viewModel.setupRecyclerView(recyclerView);
    }
}