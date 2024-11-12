package org.example.wishlistelectricboogaloo.Model;

import java.util.List;

public class Wishlist {
   private int id;
   private int profile_id;
   private String name;
   private List<Product> products;

   public Wishlist() {
   }
    public Wishlist(int profile_id) {
   this.profile_id = profile_id;
   }


    public int getProfileId() {
        return profile_id;

    }
    public void setProfileId(int profile_id) {
       this.profile_id = profile_id;
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
