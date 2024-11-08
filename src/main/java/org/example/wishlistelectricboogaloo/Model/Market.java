package org.example.wishlistelectricboogaloo.Model;

public class Market {
    private int marketId;
    private String city;

    //Getters
    public int getMarketId() {
        return marketId;
    }

    public String getCity() {
        return city;
    }

    //Setters
    public void setMarketId(int marketId) {
        this.marketId = marketId;
    }

    public void setCity(String city) {
        this.city = city;
    }

    //Constructor
    public Market(int marketId, String city) {
        this.marketId = marketId;
        this.city = city;
    }
}