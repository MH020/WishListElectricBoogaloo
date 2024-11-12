package org.example.wishlistelectricboogaloo.Model;

import java.util.List;

public class Wishlist {
   private int wishlist_id;
   private int profile_id;
   private String name;
   private List<Product> products;

   public Wishlist() {
   }
    public Wishlist(int profile_id) {
   this.profile_id = profile_id;
   }


    public int getProfile_id() {
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
    public void setWishlist_id(int wishlist_id) {
       this.wishlist_id = wishlist_id;
    }
    public int getWishlist_id() {
       return wishlist_id;
    }


}
