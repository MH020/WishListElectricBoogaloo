package org.example.wishlistelectricboogaloo.Model;

public class Market {
    private int market_id;
    private String city;

    //Getters
    public int getMarketId() {
        return market_id;
    }

    public String getCity() {
        return city;
    }
    //Setters
    public void setMarketId(int marketId) {
        this.market_id = marketId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //Constructor
    public Market(int marketId, String city) {
        this.market_id = marketId;
        this.city = city;
    }
}