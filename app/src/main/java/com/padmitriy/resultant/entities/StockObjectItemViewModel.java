package com.padmitriy.resultant.entities;

import android.databinding.Bindable;

import com.android.databinding.library.baseAdapters.BR;
import com.padmitriy.resultant.mvvm.ItemViewModel;

import java.util.Random;

public class StockObjectItemViewModel extends ItemViewModel<StockObjectResponse.Stock> {

    private StockObjectResponse.Stock stockObject;

    @Override
    public void setItem(StockObjectResponse.Stock item) {
        stockObject = item;
        notifyChange();
    }

    public void onClick() {
        stockObject.setName((new Random().nextBoolean()) ? stockObject.getName().toLowerCase() : stockObject.getName().toUpperCase());
        // TODO: 27.09.18 BR.selected?
        notifyPropertyChanged(BR._all);
    }

    @Bindable
    public String getName() {
        return stockObject.getName();
    }

    @Bindable
    public Double getPercentChange() {
        return stockObject.getPercentChange();
    }

    @Bindable
    public Long getVolume() {
        return stockObject.getVolume();
    }

}
