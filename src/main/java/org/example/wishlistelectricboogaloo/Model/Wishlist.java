package org.example.wishlistelectricboogaloo.Model;

public class Wishlist {
   private int id;
   private int profileId;
   private String name;

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
    public void setName(String City) {
        this.name = City;
    }
}
