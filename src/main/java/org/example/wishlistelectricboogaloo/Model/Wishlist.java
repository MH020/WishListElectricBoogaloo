package org.example.wishlistelectricboogaloo.Model;

import java.util.List;

public class Wishlist {
   private int id;
   private int profileId;
   private String name;
   private List<Product> products;

   public Wishlist() {
   }

   public int getId() {
      return id;

   }
    public void setId(int id) {
        this.id = id;

    }
    public int getProfileId() {
        return profileId;

    }
    public void setProfileId(int profileId) {
       this.profileId = profileId;
    }
    public String getName() {
       return name;
    }

    public void setName(String name) {
       this.name = name;
    }

    public List<Product> getProducts() {
       return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public String getCity() {
       return name;
    }
    public void setName(String City) {
       this.name = City;
    }
}
