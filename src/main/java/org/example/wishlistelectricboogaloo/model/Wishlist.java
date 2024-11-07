package org.example.wishlistelectricboogaloo.model;

import java.util.List;

public class Wishlist {
   private int id;
   private int profileId;
   private String name;
   private List<Product> products;

   public Wishlist() {
   }

   public Wishlist(int id, int profileId){
       this.id= id;
       this.profileId = profileId;
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

}
