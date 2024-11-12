-- Drop tables if they exist (H2 syntax)
DROP TABLE IF EXISTS ProfileMarket;
DROP TABLE IF EXISTS ProductWishlist;
DROP TABLE IF EXISTS Wishlist;
DROP TABLE IF EXISTS Product;
DROP TABLE IF EXISTS Market;
DROP TABLE IF EXISTS Profile;

-- Create tables
CREATE TABLE Profile (
                         profile_id INT PRIMARY KEY AUTO_INCREMENT,
                         username VARCHAR(45) NOT NULL,
                         password VARCHAR(20) NOT NULL,
                         profile_email VARCHAR(45) NOT NULL,
                         profile_phone VARCHAR(45) NOT NULL,
                         UNIQUE (username),
                         UNIQUE (profile_email),
                         UNIQUE (profile_phone)
);

CREATE TABLE Market (
                        market_id INT PRIMARY KEY AUTO_INCREMENT,
                        market_city VARCHAR(45) NOT NULL,
                        UNIQUE (market_city)
);

CREATE TABLE ProfileMarket (
                               profile_id INT NOT NULL,
                               market_id INT NOT NULL,
                               FOREIGN KEY (profile_id) REFERENCES Profile(profile_id),
                               FOREIGN KEY (market_id) REFERENCES Market(market_id)
);

CREATE TABLE Product (
                         product_id INT PRIMARY KEY AUTO_INCREMENT,
                         product_name VARCHAR(45) NOT NULL,
                         product_description VARCHAR(200) NOT NULL,
                         product_price DOUBLE NOT NULL,
                         market_id INT NOT NULL,
                         FOREIGN KEY (market_id) REFERENCES Market(market_id)
);

CREATE TABLE Wishlist (
                          wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
                          wishlist_name VARCHAR(45) NOT NULL,
                          profile_id INT NOT NULL,
                          FOREIGN KEY (profile_id) REFERENCES Profile(profile_id)
);

CREATE TABLE ProductWishlist (
                                 product_id INT NOT NULL,
                                 wishlist_id INT NOT NULL,
                                 FOREIGN KEY (product_id) REFERENCES Product(product_id),
                                 FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_id)
);

-- Insert test data for Profile
INSERT INTO Profile (username, password, profile_email, profile_phone) VALUES
                                                                           ('john_doe', 'password123', 'john@example.com', '1234567890'),
                                                                           ('98f6bcd4621d373cade4', '98f6bcd4621d373cade4', 'test@mail.com', '12341234');


-- Insert test data for Market
INSERT INTO Market (market_city) VALUES
                                     ('New York'),
                                     ('Los Angeles');

-- Insert test data for ProfileMarket
INSERT INTO ProfileMarket (profile_id, market_id) VALUES
                                                      (1, 1),
                                                      (2, 2);

-- Insert test data for Product
INSERT INTO Product (product_name, product_description, product_price, market_id) VALUES
                                                                                      ('Laptop', 'A high-performance laptop.', 1200.00, 1),
                                                                                      ('Headphones', 'Noise-canceling headphones.', 300.00, 2);

-- Insert test data for Wishlist
INSERT INTO Wishlist (wishlist_name, profile_id) VALUES
                                                     ('Tech Gadgets', 1),
                                                     ('Music Gear', 2);

-- Insert test data for ProductWishlist
INSERT INTO ProductWishlist (product_id, wishlist_id) VALUES
                                                          (1, 1),
                                                          (2, 2);
