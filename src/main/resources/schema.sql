-- -----------------------------------------------------
-- Table Profile
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Profile (
                                       profile_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       username VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(20) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    phone VARCHAR(45) NOT NULL UNIQUE
    );

-- -----------------------------------------------------
-- Table Market
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Market (
                                      market_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                      city VARCHAR(45) NOT NULL UNIQUE
    );

-- -----------------------------------------------------
-- Table Joined_Profile_Market
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Joined_Profile_Market (
                                                     profile_id INT NOT NULL,
                                                     market_id INT NOT NULL,
                                                     FOREIGN KEY (profile_id) REFERENCES Profile (profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (market_id) REFERENCES Market (market_id) ON DELETE NO ACTION ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table Product
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Product (
                                       product_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                       product_name VARCHAR(45) NOT NULL,
    description VARCHAR(200) NOT NULL,
    price DOUBLE NOT NULL,
    market_id INT NOT NULL,
    FOREIGN KEY (market_id) REFERENCES Market (market_id) ON DELETE NO ACTION ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table Wishlist
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Wishlist (
                                        wishlist_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(45) NOT NULL,
    profile_id INT NOT NULL,
    FOREIGN KEY (profile_id) REFERENCES Profile (profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table Joined_Wishlist_Products
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Joined_Wishlist_Products (
                                                        wishlist_id INT NOT NULL,
                                                        product_id INT NOT NULL,
                                                        FOREIGN KEY (wishlist_id) REFERENCES Wishlist (wishlist_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (product_id) REFERENCES Product (product_id) ON DELETE NO ACTION ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Table Joined_Profile_Product
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Joined_Profile_Product (
                                                      profile_id INT NOT NULL,
                                                      product_id INT NOT NULL,
                                                      FOREIGN KEY (profile_id) REFERENCES Profile (profile_id) ON DELETE NO ACTION ON UPDATE NO ACTION,
    FOREIGN KEY (product_id) REFERENCES Product (product_id) ON DELETE NO ACTION ON UPDATE NO ACTION
    );

-- -----------------------------------------------------
-- Test Data
-- -----------------------------------------------------
-- Insert into Profile
INSERT INTO Profile (username, password, email, phone) VALUES ('johndoe', 'password123', 'johndoe@example.com', '1234567890');
INSERT INTO Profile (username, password, email, phone) VALUES ('janedoe', 'securepass', 'janedoe@example.com', '0987654321');

-- Insert into Market
INSERT INTO Market (city) VALUES ('New York');
INSERT INTO Market (city) VALUES ('Los Angeles');

-- Insert into Product
INSERT INTO Product (product_name, description, price, market_id) VALUES ('Electric Guitar', 'A high-quality electric guitar', 299.99, 1);
INSERT INTO Product (product_name, description, price, market_id) VALUES ('Vinyl Record', 'Classic rock vinyl', 19.99, 1);
INSERT INTO Product (product_name, description, price, market_id) VALUES ('Polaroid Camera', 'Instant camera', 99.99, 2);

-- Insert into Wishlist
INSERT INTO Wishlist (name, profile_id) VALUES ('John''s Wishlist', 1);
INSERT INTO Wishlist (name, profile_id) VALUES ('Jane''s Wishlist', 2);

-- Insert into Joined_Wishlist_Products
INSERT INTO Joined_Wishlist_Products (wishlist_id, product_id) VALUES (1, 1);
INSERT INTO Joined_Wishlist_Products (wishlist_id, product_id) VALUES (1, 2);
INSERT INTO Joined_Wishlist_Products (wishlist_id, product_id) VALUES (2, 3);

-- Insert into Joined_Profile_Market
INSERT INTO Joined_Profile_Market (profile_id, market_id) VALUES (1, 1);
INSERT INTO Joined_Profile_Market (profile_id, market_id) VALUES (2, 2);

-- Insert into Joined_Profile_Product
INSERT INTO Joined_Profile_Product (profile_id, product_id) VALUES (1, 1);
INSERT INTO Joined_Profile_Product (profile_id, product_id) VALUES (2, 3);
