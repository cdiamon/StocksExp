package com.padmitriy.resultant.entities;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StockObjectResponse {

    @SerializedName("stock")
    private List<Stock> stock = null;
    @SerializedName("as_of")
    private String asOf;

    /**
     * No args constructor for use in serialization
     */
    public StockObjectResponse() {
    }

    /**
     * @param stock
     * @param asOf
     */
    public StockObjectResponse(List<Stock> stock, String asOf) {
        super();
        this.stock = stock;
        this.asOf = asOf;
    }

    @Override
    public String toString() {
        return "StockObjectResponse{" +
                "stock=" + stock +
                ", asOf='" + asOf + '\'' +
                '}';
    }

    public List<Stock> getStock() {
        return stock;
    }

    public void setStock(List<Stock> stock) {
        this.stock = stock;
    }

    public String getAsOf() {
        return asOf;
    }

    public void setAsOf(String asOf) {
        this.asOf = asOf;
    }

    public class Stock {
        @SerializedName("name")
        private String name;
        @SerializedName("price")
        private Price price;
        @SerializedName("percent_change")
        private Double percentChange;
        @SerializedName("volume")
        private Long volume;
        @SerializedName("symbol")
        private String symbol;

        /**
         * No args constructor for use in serialization
         */
        public Stock() {
        }

        /**
         * @param price
         * @param symbol
         * @param percentChange
         * @param name
         * @param volume
         */
        public Stock(String name, Price price, Double percentChange, Long volume, String symbol) {
            super();
            this.name = name;
            this.price = price;
            this.percentChange = percentChange;
            this.volume = volume;
            this.symbol = symbol;
        }

        @Override
        public String toString() {
            return "Stock{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    ", percentChange=" + percentChange +
                    ", volume=" + volume +
                    ", symbol='" + symbol + '\'' +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Price getPrice() {
            return price;
        }

        public void setPrice(Price price) {
            this.price = price;
        }

        public Double getPercentChange() {
            return percentChange;
        }

        public void setPercentChange(Double percentChange) {
            this.percentChange = percentChange;
        }

        public Long getVolume() {
            return volume;
        }

        public void setVolume(Long volume) {
            this.volume = volume;
        }

        public String getSymbol() {
            return symbol;
        }

        public void setSymbol(String symbol) {
            this.symbol = symbol;
        }

        public class Price {
            @SerializedName("currency")
            private String currency;

            @SerializedName("amount")
            private Double amount;

            /**
             * No args constructor for use in serialization
             */
            public Price() {
            }

            /**
             * @param amount
             * @param currency
             */
            public Price(String currency, Double amount) {
                super();
                this.currency = currency;
                this.amount = amount;
            }

            @Override
            public String toString() {
                return "Price{" +
                        "currency='" + currency + '\'' +
                        ", amount=" + amount +
                        '}';
            }

            public String getCurrency() {
                return currency;
            }

            public void setCurrency(String currency) {
                this.currency = currency;
            }

            public Double getAmount() {
                return amount;
            }

            public void setAmount(Double amount) {
                this.amount = amount;
            }

        }

    }

}