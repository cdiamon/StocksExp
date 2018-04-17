package com.padmitriy.resultant.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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

    public static void launch(Context context) {
        context.startActivity(new Intent(context, MainActivity.class));
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
                        Toast.makeText(this, "Server error", Toast.LENGTH_SHORT).show();
                    }));
        } else {
            Toast.makeText(this, "Enable internet connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void fillStockData(StockListObject stocks) {
        System.out.println(stocks.getAsOf());
        System.out.println(stocks.getStock().get(0).toString());
        stockRecyclerAdapter.updateRecyclerList(stocks.getStock());
    }

    @Override
    protected void onResume() {
        super.onResume();
        getStockData(false);
    }


    @OnClick(R.id.refreshButton)
    public void onViewClicked() {
        compositeDisposable.clear();
        Toast.makeText(this, "Refreshing..", Toast.LENGTH_SHORT).show();
        stockProgressBar.setVisibility(View.VISIBLE);
        getStockData(false);
    }
}
