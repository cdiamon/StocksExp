package com.padmitriy.resultant.mvvm;

abstract public class ItemViewModel<ITEM_T> extends ViewModel {

    public abstract void setItem(ITEM_T item);

    protected ItemViewModel() {
        super(null);
    }
}
