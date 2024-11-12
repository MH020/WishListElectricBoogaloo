-- -----------------------------------------------------
-- Table `Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Profile (
                                       profile_id INT PRIMARY KEY AUTO_INCREMENT,
                                       username VARCHAR(45) NOT NULL,
                                       password VARCHAR(20) NOT NULL,
                                       profile_email VARCHAR(45) NOT NULL,
                                       profile_phone VARCHAR(45) NOT NULL,
                                       UNIQUE (username),
                                       UNIQUE (profile_email),
                                       UNIQUE (profile_phone)
);

-- -----------------------------------------------------
-- Table `Market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Market (
                                      market_id INT PRIMARY KEY AUTO_INCREMENT,
                                      market_city VARCHAR(45) NOT NULL,
                                      UNIQUE (market_city)
);

-- -----------------------------------------------------
-- Table `ProfileMarket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ProfileMarket (
                                             profile_id INT NOT NULL,
                                             market_id INT NOT NULL,
                                             FOREIGN KEY (profile_id) REFERENCES Profile(profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                             FOREIGN KEY (market_id) REFERENCES Market(market_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Product (
                                       product_id INT PRIMARY KEY AUTO_INCREMENT,
                                       product_name VARCHAR(45) NOT NULL,
                                       product_description VARCHAR(200) NOT NULL,
                                       product_price DECIMAL(10, 2) NOT NULL,  -- Using DECIMAL as DOUBLE has different precision in H2
                                       market_id INT NOT NULL,
                                       FOREIGN KEY (market_id) REFERENCES Market(market_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `Wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Wishlist (
                                        wishlist_id INT PRIMARY KEY AUTO_INCREMENT,
                                        wishlist_name VARCHAR(45) NOT NULL,
                                        profile_id INT NOT NULL,
                                        FOREIGN KEY (profile_id) REFERENCES Profile(profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Table `ProductWishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ProductWishlist (
                                               product_id INT NOT NULL,
                                               wishlist_id INT NOT NULL,
                                               FOREIGN KEY (wishlist_id) REFERENCES Wishlist(wishlist_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
                                               FOREIGN KEY (product_id) REFERENCES Product(product_id) ON DELETE NO ACTION ON UPDATE NO ACTION
);

-- -----------------------------------------------------
-- Test Data
-- -----------------------------------------------------
-- Insert data into Profile table
INSERT INTO Profile (username, password, profile_email, profile_phone)
VALUES
    ('john_doe', 'password123', 'john.doe@example.com', '555-0101'),
    ('test','test', 'jane.smith@example.com', '555-0102'),
    ('alice_jones', 'alicepass', 'alice.jones@example.com', '555-0103');


-- Insert data into Market table
INSERT INTO Market (market_id, market_city)
VALUES
    (1, 'New York'),
    (2, 'Los Angeles'),
    (3, 'Chicago');

-- Insert data into ProfileMarket table
INSERT INTO ProfileMarket (profile_id, market_id)
VALUES
    ( 1, 1),  -- John Doe associated with New York market
    ( 2, 2),  -- Jane Smith associated with Los Angeles market
    ( 3, 3);  -- Alice Jones associated with Chicago market

-- Insert data into Product table
INSERT INTO Product (product_name, product_description, product_price, market_id)
VALUES
    ('Laptop', 'High-performance laptop', 1200.00, 1),  -- Available in New York market
    ('Smartphone', 'Latest model smartphone', 800.00, 2),  -- Available in Los Angeles market
    ('Headphones', 'Noise-canceling headphones', 150.00, 3);  -- Available in Chicago market

-- Insert data into Wishlist table
INSERT INTO Wishlist (wishlist_id, wishlist_name, profile_id)
VALUES
    (1, 'John''s Wishlist', 1),  -- Wishlist for John Doe (escaped single quote)
    (2, 'Jane''s Wishlist', 2),  -- Wishlist for Jane Smith (escaped single quote)
    (3, 'Alice''s Wishlist', 3);  -- Wishlist for Alice Jones (escaped single quote)

-- Insert data into ProductWishlist table
INSERT INTO ProductWishlist (product_id, wishlist_id)
VALUES
    ( 1, 1),  -- Laptop in John's Wishlist
    ( 2, 2),  -- Smartphone in Jane's Wishlist
    ( 3, 3);  -- Headphones in Alice's Wishlist
