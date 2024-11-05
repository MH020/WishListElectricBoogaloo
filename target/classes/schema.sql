-- -----------------------------------------------------
-- Schema LocalWishDB
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `LocalWishDB`;
USE `LocalWishDB`;

-- -----------------------------------------------------
-- Table `Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Profile` (
                                         `profile_id` INT NOT NULL AUTO_INCREMENT,
                                         `username` VARCHAR(45) NOT NULL,
    `password` VARCHAR(20) NOT NULL,
    `Email` VARCHAR(45) NOT NULL,
    `phone` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`profile_id`),
    UNIQUE (`username`),
    UNIQUE (`Email`),
    UNIQUE (`phone`)
    );

-- -----------------------------------------------------
-- Table `Market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Market` (
                                        `market_id` INT NOT NULL AUTO_INCREMENT,
                                        `city` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`market_id`),
    UNIQUE (`city`)
    );

-- -----------------------------------------------------
-- Table `Joined_Profile_Market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Joined_Profile_Market` (
                                                       `profile_id` INT NOT NULL,
                                                       `market_id` INT NOT NULL,
                                                       FOREIGN KEY (`profile_id`)
    REFERENCES `Profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`market_id`)
    REFERENCES `Market` (`market_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Separate index creation for `Joined_Profile_Market`
CREATE INDEX IF NOT EXISTS `profile_id_idx` ON `Joined_Profile_Market` (`profile_id`);
CREATE INDEX IF NOT EXISTS `market_id_idx` ON `Joined_Profile_Market` (`market_id`);

-- -----------------------------------------------------
-- Table `Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Product` (
                                         `product_id` INT NOT NULL AUTO_INCREMENT,
                                         `product_name` VARCHAR(45) NOT NULL,
    `description` VARCHAR(200) NOT NULL,
    `price` DOUBLE NOT NULL,
    `market_id` INT NOT NULL,
    PRIMARY KEY (`product_id`),
    FOREIGN KEY (`market_id`)
    REFERENCES `Market` (`market_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Separate index creation for `Product`
CREATE INDEX IF NOT EXISTS `market_id_idx` ON `Product` (`market_id`);

-- -----------------------------------------------------
-- Table `Wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Wishlist` (
                                          `wishlist_id` INT NOT NULL AUTO_INCREMENT,
                                          `name` VARCHAR(45) NOT NULL,
    PRIMARY KEY (`wishlist_id`)
    );

-- -----------------------------------------------------
-- Table `Joined_Wishlist_Products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Joined_Wishlist_Products` (
                                                          `wishlist_id` INT NOT NULL,
                                                          `product_id` INT NOT NULL,
                                                          FOREIGN KEY (`wishlist_id`)
    REFERENCES `Wishlist` (`wishlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`product_id`)
    REFERENCES `Product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Separate index creation for `Joined_Wishlist_Products`
CREATE INDEX IF NOT EXISTS `wishlist_id_idx` ON `Joined_Wishlist_Products` (`wishlist_id`);
CREATE INDEX IF NOT EXISTS `product_id_idx` ON `Joined_Wishlist_Products` (`product_id`);

-- -----------------------------------------------------
-- Table `Joined_Profile_Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Joined_Profile_Product` (
                                                        `profile_id` INT NOT NULL,
                                                        `product_id` INT NOT NULL,
                                                        FOREIGN KEY (`profile_id`)
    REFERENCES `Profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`product_id`)
    REFERENCES `Product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Separate index creation for `Joined_Profile_Product`
CREATE INDEX IF NOT EXISTS `profile_id_idx` ON `Joined_Profile_Product` (`profile_id`);
CREATE INDEX IF NOT EXISTS `product_id_idx` ON `Joined_Profile_Product` (`product_id`);

-- -----------------------------------------------------
-- Table `Joined_Profile_Wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Joined_Profile_Wishlist` (
                                                         `profile_id` INT NOT NULL,
                                                         `wishlist_id` INT NOT NULL,
                                                         FOREIGN KEY (`profile_id`)
    REFERENCES `Profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`wishlist_id`)
    REFERENCES `Wishlist` (`wishlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
    );

-- Separate index creation for `Joined_Profile_Wishlist`
CREATE INDEX IF NOT EXISTS `profile_id_idx` ON `Joined_Profile_Wishlist` (`profile_id`);
CREATE INDEX IF NOT EXISTS `wishlist_id_idx` ON `Joined_Profile_Wishlist` (`wishlist_id`);
-- -----------------------------------------------------
-- Insert test data into Profile
-- -----------------------------------------------------
INSERT INTO `Profile` (`username`, `password`, `Email`, `phone`) VALUES
                                                                     ('john_doe', 'password123', 'john.doe@example.com', '123-456-7890'),
                                                                     ('jane_smith', 'pass456', 'jane.smith@example.com', '234-567-8901'),
                                                                     ('alice_wonder', 'alicePass', 'alice.wonder@example.com', '345-678-9012');

-- -----------------------------------------------------
-- Insert test data into Market
-- -----------------------------------------------------
INSERT INTO `Market` (`city`) VALUES
                                  ('New York'),
                                  ('Los Angeles'),
                                  ('Chicago');

-- -----------------------------------------------------
-- Insert test data into Joined_Profile_Market
-- -----------------------------------------------------
INSERT INTO `Joined_Profile_Market` (`profile_id`, `market_id`) VALUES
                                                                    (1, 1),  -- john_doe in New York
                                                                    (2, 2),  -- jane_smith in Los Angeles
                                                                    (3, 3);  -- alice_wonder in Chicago

-- -----------------------------------------------------
-- Insert test data into Product
-- -----------------------------------------------------
INSERT INTO `Product` (`product_name`, `description`, `price`, `market_id`) VALUES
                                                                                ('Laptop', 'High-performance laptop', 999.99, 1),
                                                                                ('Headphones', 'Noise-cancelling headphones', 199.99, 2),
                                                                                ('Smartphone', 'Latest model smartphone', 799.99, 3);

-- -----------------------------------------------------
-- Insert test data into Wishlist
-- -----------------------------------------------------
INSERT INTO `Wishlist` (`name`) VALUES
                                    ('Tech Wishlist'),
                                    ('Travel Wishlist'),
                                    ('Gadget Wishlist');

-- -----------------------------------------------------
-- Insert test data into Joined_Wishlist_Products
-- -----------------------------------------------------
INSERT INTO `Joined_Wishlist_Products` (`wishlist_id`, `product_id`) VALUES
                                                                         (1, 1),  -- Tech Wishlist with Laptop
                                                                         (1, 2),  -- Tech Wishlist with Headphones
                                                                         (2, 3);  -- Travel Wishlist with Smartphone

-- -----------------------------------------------------
-- Insert test data into Joined_Profile_Product
-- -----------------------------------------------------
INSERT INTO `Joined_Profile_Product` (`profile_id`, `product_id`) VALUES
                                                                      (1, 1),  -- john_doe owns Laptop
                                                                      (2, 2),  -- jane_smith owns Headphones
                                                                      (3, 3);  -- alice_wonder owns Smartphone

-- -----------------------------------------------------
-- Insert test data into Joined_Profile_Wishlist
-- -----------------------------------------------------
INSERT INTO `Joined_Profile_Wishlist` (`profile_id`, `wishlist_id`) VALUES
                                                                        (1, 1),  -- john_doe's Tech Wishlist
                                                                        (2, 2),  -- jane_smith's Travel Wishlist
                                                                        (3, 3);  -- alice_wonder's Gadget Wishlist

