package org.example.wishlistelectricboogaloo.Model;

public class Market {
    private int market_id;
    private String market_city;


    //Constructor
    public Market(int market_id, String market_city) {
        this.market_id = market_id;
        this.market_city = market_city;
    }


    //Getters
    public int getMarket_id() {
        return market_id;
    }

    public String getMarket_city() {
        return market_city;
    }
    //Setters
    public void setMarket_id(int market_id) {
        this.market_id = market_id;
    }

    public void setMarket_city(String market_city) {
        this.market_city = market_city;
    }

}