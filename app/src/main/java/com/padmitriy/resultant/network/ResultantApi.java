package com.padmitriy.resultant.network;

import com.padmitriy.resultant.entities.StockObjectResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by dmitriy on 26.03.18.
 */

public interface ResultantApi {

    /**
     * getStocks
     * <p>
     * Returns a list of available stocks
     */
    @GET("stocks.json")
    Observable<StockObjectResponse> getStocks();

}