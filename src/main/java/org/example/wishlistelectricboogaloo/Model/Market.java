package org.example.wishlistelectricboogaloo.Model;

public class Market {
    private int market_id;
    private String market_city;

    //Getters
    public int getMarketId() {
        return market_id;
    }

    public String getCity() {
        return market_city;
    }
    //Setters
    public void setMarketId(int marketId) {
        this.market_id = marketId;
    }

    public void setCity(String city) {
        this.market_city = city;
    }

    //Constructor
    public Market(int marketId, String city) {
        this.market_id = marketId;
        this.market_city = city;
    }
}