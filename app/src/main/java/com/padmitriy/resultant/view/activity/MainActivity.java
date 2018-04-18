package com.padmitriy.resultant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.padmitriy.resultant.R;
import com.padmitriy.resultant.ResultantApp;
import com.padmitriy.resultant.entities.StockListObject;
import com.padmitriy.resultant.network.ResultantApi;
import com.padmitriy.resultant.util.StocksRecyclerAdapter;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Inject
    ResultantApi resultantApi;

    @BindView(R.id.stockRecyclerView)
    RecyclerView stockRecyclerView;
    @BindView(R.id.stockProgressBar)
    ProgressBar stockProgressBar;
    private StocksRecyclerAdapter stockRecyclerAdapter;
    private Snackbar snackbar;

    public static Intent makeIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ResultantApp.sAppComponent.inject(this);
        getWindow().setStatusBarColor(getResources().getColor(R.color.blacktext));
        getWindow().getDecorView().setBackground(null);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initViews();
    }

    private void initViews() {

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        stockRecyclerView.setLayoutManager(layoutManager);
        stockRecyclerAdapter = new StocksRecyclerAdapter();
        stockRecyclerView.setAdapter(stockRecyclerAdapter);

        getStockData(false);
    }

    private void getStockData(boolean useDelay) {
        if (networkAvailable()) {

            if (snackbar != null) {
                snackbar.dismiss();
            }

            compositeDisposable.add(resultantApi.getStocks()
                    .subscribeOn(Schedulers.io())
                    .delay(useDelay ? 15 : 0, TimeUnit.SECONDS)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        stockProgressBar.setVisibility(View.GONE);
                        fillStockData(response);
                        getStockData(true);
                    }, throwable -> {
                        throwable.printStackTrace();
                        Toast.makeText(this, "Server error", Toast.LENGTH_LONG).show();
                        Toast.makeText(this, "Reconnecting..", Toast.LENGTH_LONG).show();
                        getStockData(true);
                    }));
        } else {
            snackbar = Snackbar.make(stockRecyclerView, "Enable internet connection", Snackbar.LENGTH_INDEFINITE);
            snackbar.show();
            stockProgressBar.setVisibility(View.GONE);
        }
    }

    private void fillStockData(StockListObject stocks) {
        System.out.println(stocks.getAsOf());
        System.out.println(stocks.getStock().get(0).toString());
        stockRecyclerAdapter.updateRecyclerList(stocks.getStock());
    }

    @OnClick(R.id.refreshButton)
    public void onViewClicked() {
        compositeDisposable.clear();
        Toast.makeText(this, "Refreshing..", Toast.LENGTH_SHORT).show();
        stockProgressBar.setVisibility(View.VISIBLE);
        getStockData(false);
    }
}
