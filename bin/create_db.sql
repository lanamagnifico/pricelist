CREATE DATABASE  IF NOT EXISTS `hb_price_list`;
USE `hb_price_list`;

SET FOREIGN_KEY_CHECKS = 0;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;

CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `pricetype`
--

DROP TABLE IF EXISTS `price_type`;

CREATE TABLE `price_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `price`
--

DROP TABLE IF EXISTS `price`;

CREATE TABLE `price` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price_value` int(11) NOT NULL ,
  `item_id` int(11) NOT NULL,
  `price_type_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ITEM_idx` (`item_id`),
  KEY `FK_PRICE_TYPE_idx` (`price_type_id`),
  CONSTRAINT `FK_ITEM` FOREIGN KEY (`item_id`) REFERENCES `item` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_PRICE_TYPE` FOREIGN KEY (`price_type_id`) REFERENCES `price_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `price_reg`
--

DROP TABLE IF EXISTS `price_reg`;

CREATE TABLE `price_reg` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_reg` datetime NOT NULL,
  `price_type_id` int(11) NOT NULL,
  
  PRIMARY KEY (`id`),
  KEY `FK_PRICE_TYPE_ID_idx` (`price_type_id`),

  CONSTRAINT `FK_PRICE_TYPE_02` 
  FOREIGN KEY (`price_type_id`) 
  REFERENCES `price_type` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `price_reg_detail`
--

DROP TABLE IF EXISTS `price_reg_detail`;
CREATE TABLE `price_reg_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `price_reg_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `new_value` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_PRICE_REG_ID_idx` (`price_reg_id`),
  KEY `FK_ITEM_ID_idx` (`item_id`),
  CONSTRAINT `FK_PRICE_REG_02` 
  FOREIGN KEY (`price_reg_id`) 
  REFERENCES `price_reg` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_ITEM_03` 
  FOREIGN KEY (`item_id`) 
  REFERENCES `item` (`id`) 

  ON DELETE NO ACTION ON UPDATE NO ACTION
 ) ENGINE=InnoDB DEFAULT CHARSET=latin1; 
----
---- Table structure for table `item_price_registration`
----
--
--DROP TABLE IF EXISTS `item_price_registration`;
--
--CREATE TABLE `item_price_registration` (
--  `item_id` int(11) NOT NULL,
--  `price_registration_id` int(11) NOT NULL,
--  
--  PRIMARY KEY (`item_id`,`price_registration_id`),
--  
--  KEY `FK_ITEM_idx` (`item_id`),
--  
--  CONSTRAINT `FK_PRICE_REGISTRATION` FOREIGN KEY (`price_registration_id`) 
--  REFERENCES `price_registration` (`id`) 
--  ON DELETE NO ACTION ON UPDATE NO ACTION,
--  
--  CONSTRAINT `FK_ITEM_05` FOREIGN KEY (`item_id`) 
--  REFERENCES `item` (`id`) 
--  ON DELETE NO ACTION ON UPDATE NO ACTION
--) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;