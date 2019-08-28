CREATE DATABASE  IF NOT EXISTS `hb_price_list`;
USE `hb_price_list`;

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

SET FOREIGN_KEY_CHECKS = 1;