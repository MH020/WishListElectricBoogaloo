-- Create Schema
CREATE SCHEMA IF NOT EXISTS LocalwishDB;
SET SCHEMA LocalwishDB;

-- -----------------------------------------------------
-- Table `LocalwishDB`.`Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Profile (
                                       profile_id INT PRIMARY KEY AUTO_INCREMENT,
                                       username VARCHAR(45) NOT NULL UNIQUE,
                                       password VARCHAR(20) NOT NULL,
                                       profile_email VARCHAR(45) NOT NULL UNIQUE,
                                       profile_phone VARCHAR(45) NOT NULL UNIQUE
);

-- -----------------------------------------------------
-- Table `LocalwishDB`.`Market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Market (
                                      market_id INT PRIMARY KEY AUTO_INCREMENT,
                                      market_city VARCHAR(45) NOT NULL UNIQUE
);

-- -----------------------------------------------------
-- Table `LocalwishDB`.`ProfileMarket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ProfileMarket (
                                             profilemarket_id INT PRIMARY KEY AUTO_INCREMENT,
                                             profile_id INT NOT NULL,
                                             market_id INT NOT NULL,
                                             FOREIGN KEY (profile_id) REFERENCES Profile(profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                             FOREIGN KEY (market_id) REFERENCES Market(market_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `LocalwishDB`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Product (
                                       product_id INT PRIMARY KEY AUTO_INCREMENT,
                                       product_name VARCHAR(45) NOT NULL,
                                       product_description VARCHAR(200) NOT NULL,
                                       product_price DECIMAL(10, 2) NOT NULL,
                                       market_id INT NOT NULL,
                                       FOREIGN KEY (market_id) REFERENCES Market(market_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `LocalwishDB`.`Wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Wishlist (
                                        wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
                                        wishlist_name VARCHAR(45) NOT NULL,
                                        profile_id INT NOT NULL,
                                        FOREIGN KEY (profile_id) REFERENCES Profile(profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `LocalwishDB`.`ProductWishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ProductWishlist (
                                               Productwishlist_id INT PRIMARY KEY AUTO_INCREMENT,
                                               product_id INT NOT NULL,
                                               wishlist_id INT NOT NULL,
                                               FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                               FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Insert Test Data
-- -----------------------------------------------------

-- Insert data for `Profile`
INSERT INTO Profile (username, password, profile_email, profile_phone) VALUES
                                                                           ('johndoe', 'password123', 'john.doe@example.com', '123-456-7890'),
                                                                           ('janedoe', 'password456', 'jane.doe@example.com', '234-567-8901'),
                                                                           ('mikejohnson', 'password789', 'mike.johnson@example.com', '345-678-9012');

-- Insert data for `Market`
INSERT INTO Market (market_city) VALUES
                                     ('New York'),
                                     ('Los Angeles'),
                                     ('Chicago');

-- Insert data for `ProfileMarket`
INSERT INTO ProfileMarket (profile_id, market_id) VALUES
                                                      (1, 1),
                                                      (2, 2),
                                                      (3, 3);

-- Insert data for `Product`
INSERT INTO Product (product_name, product_description, product_price, market_id) VALUES
                                                                                      ('Laptop', 'High performance laptop', 999.99, 1),
                                                                                      ('Smartphone', 'Latest model smartphone', 799.99, 2),
                                                                                      ('Tablet', 'Portable tablet with stylus', 499.99, 3);

-- Insert data for `Wishlist`
INSERT INTO Wishlist (wishlist_name, profile_id) VALUES
                                                     ('John''s Electronics Wishlist', 1),
                                                     ('Jane''s Shopping Wishlist', 2),
                                                     ('Mike''s Gadget Wishlist', 3);

-- Insert data for `ProductWishlist`
INSERT INTO ProductWishlist (product_id, wishlist_id) VALUES
                                                          (1, 1),
                                                          (2, 2),
                                                          (3, 3);
