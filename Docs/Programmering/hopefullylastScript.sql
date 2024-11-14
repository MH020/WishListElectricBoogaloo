-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Localwishdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Localwishdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Localwishdb` DEFAULT CHARACTER SET utf8 ;
USE `Localwishdb` ;

-- -----------------------------------------------------
-- Table `Localwishdb`.`Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Localwishdb`.`Profile` (
  `profile_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `profile_email` VARCHAR(45) NOT NULL,
  `profile_phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`profile_id`),
  UNIQUE INDEX `brugernavn_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `Email_UNIQUE` (`profile_email` ASC) VISIBLE,
  UNIQUE INDEX `phone_UNIQUE` (`profile_phone` ASC) VISIBLE,
  UNIQUE INDEX `profile_id_UNIQUE` (`profile_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Localwishdb`.`Market`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Localwishdb`.`Market` (
  `market_id` INT NOT NULL AUTO_INCREMENT,
  `market_city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`market_id`),
  UNIQUE INDEX `by_UNIQUE` (`market_city` ASC) VISIBLE,
  UNIQUE INDEX `market_id_UNIQUE` (`market_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Localwishdb`.`ProfileMarket`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Localwishdb`.`ProfileMarket` (
  `profile_id` INT NOT NULL,
  `market_id` INT NOT NULL,
  INDEX `bruger id _idx` (`profile_id` ASC) VISIBLE,
  INDEX `market id _idx` (`market_id` ASC) VISIBLE,
  CONSTRAINT `profile_idOnMarket`
    FOREIGN KEY (`profile_id`)
    REFERENCES `Localwishdb`.`Profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `market_idOnMarket`
    FOREIGN KEY (`market_id`)
    REFERENCES `Localwishdb`.`Market` (`market_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Localwishdb`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Localwishdb`.`Product` (
  `product_id` INT NOT NULL AUTO_INCREMENT,
  `product_name` VARCHAR(45) NOT NULL,
  `product_description` VARCHAR(200) NOT NULL,
  `product_price` DOUBLE NOT NULL,
  `market_id` INT NOT NULL,
  PRIMARY KEY (`product_id`),
  INDEX `market id _idx` (`market_id` ASC) VISIBLE,
  UNIQUE INDEX `product_id_UNIQUE` (`product_id` ASC) VISIBLE,
  CONSTRAINT `market_id_onProduct`
    FOREIGN KEY (`market_id`)
    REFERENCES `Localwishdb`.`Market` (`market_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Localwishdb`.`Wishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Localwishdb`.`Wishlist` (
  `wishlist_id` INT NOT NULL AUTO_INCREMENT,
  `wishlist_name` VARCHAR(45) NOT NULL,
  `profile_id` INT NOT NULL,
  PRIMARY KEY (`wishlist_id`),
  INDEX `profile_id_idx` (`profile_id` ASC) VISIBLE,
  UNIQUE INDEX `wishlist_id_UNIQUE` (`wishlist_id` ASC) VISIBLE,
  CONSTRAINT `profile_idOnWish`
    FOREIGN KEY (`profile_id`)
    REFERENCES `Localwishdb`.`Profile` (`profile_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Localwishdb`.`ProductWishlist`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Localwishdb`.`ProductWishlist` (
  `product_id` INT NOT NULL,
  `wishlist_id` INT NOT NULL,
  INDEX `wishlist id _idx` (`wishlist_id` ASC) VISIBLE,
  INDEX `product id _idx` (`product_id` ASC) VISIBLE,
  CONSTRAINT `wishlist_idOnWish`
    FOREIGN KEY (`wishlist_id`)
    REFERENCES `Localwishdb`.`Wishlist` (`wishlist_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `product_idOnWish`
    FOREIGN KEY (`product_id`)
    REFERENCES `Localwishdb`.`Product` (`product_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
