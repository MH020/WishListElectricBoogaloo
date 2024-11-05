package org.example.wishlistelectricboogaloo.Repository;

import org.example.wishlistelectricboogaloo.ConnectionManager;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

@Repository
public class WishlistRepository {
    private final Connection conn;

    public WishlistRepository(ConnectionManager connectionManager) {
        this.conn = connectionManager.getConnection();
    }

    //create a wishlist
    /*public createWishlist( String name) {

    String SQlInsertWishlist ="insert into wishlist (name,) values(?)";
    }
    Public GetUserid(){
        String SQLGetUser = "SELECT FROM profile WHERE NAME = ?";
        try (PreparedStatement statement =conn.prepareStatement(Statement.RETURN_GENERATED_KEYS))
    }*/
}
