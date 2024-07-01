-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mechmall
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cart` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `product_id` int NOT NULL,
  `quantity` int NOT NULL,
  `created` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_product_id` (`product_id`),
  CONSTRAINT `fk_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE,
  CONSTRAINT `fk_user` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_items`
--

DROP TABLE IF EXISTS `order_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_items` (
  `id` int NOT NULL AUTO_INCREMENT,
  `uid` int NOT NULL,
  `order_id` int NOT NULL,
  `goods_id` int NOT NULL,
  `goods_name` varchar(100) NOT NULL,
  `icon_url` varchar(500) NOT NULL,
  `price` decimal(20,2) NOT NULL,
  `quantity` int NOT NULL,
  `total_price` decimal(20,2) NOT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_goods_id` (`goods_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
/*!40000 ALTER TABLE `order_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_no` bigint NOT NULL,
  `uid` int NOT NULL,
  `addr_id` int NOT NULL,
  `amount` decimal(20,2) NOT NULL,
  `type` int NOT NULL,
  `freight` int NOT NULL,
  `status` int NOT NULL,
  `payment_time` datetime DEFAULT NULL,
  `delivery_time` datetime DEFAULT NULL,
  `finish_time` datetime DEFAULT NULL,
  `close_time` datetime DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_uid` (`uid`),
  KEY `idx_addr_id` (`addr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (2,563,102,202,200.50,2,15,0,'2024-06-27 12:00:00',NULL,NULL,NULL,'2024-06-29 08:29:10','2024-06-29 13:38:09'),(3,567890,103,203,300.25,1,20,2,'2024-06-26 10:30:00','2024-07-03 14:00:00','2024-07-04 11:00:00','2024-07-04 12:00:00','2024-06-29 08:29:10','2024-06-29 08:29:10'),(4,246801,104,204,180.00,1,12,1,'2024-06-25 08:45:00','2024-07-02 11:30:00','2024-07-03 09:00:00',NULL,'2024-06-29 08:29:10','2024-06-29 08:29:10'),(5,135792,105,205,250.80,2,18,0,'2024-06-24 15:00:00',NULL,NULL,NULL,'2024-06-29 08:29:10','2024-06-29 08:29:10'),(6,112233,106,206,350.00,1,25,2,'2024-06-23 16:20:00','2024-06-30 13:45:00','2024-07-01 10:00:00','2024-07-01 11:00:00','2024-06-29 08:29:10','2024-06-29 08:29:10'),(7,445566,107,207,280.50,1,15,1,'2024-06-22 11:00:00','2024-06-29 09:30:00','2024-06-29 11:15:00',NULL,'2024-06-29 08:29:10','2024-06-29 08:29:10'),(8,778899,108,208,190.90,2,10,0,'2024-06-21 09:30:00',NULL,NULL,NULL,'2024-06-29 08:29:10','2024-06-29 08:29:10'),(12,789012,102,202,200.50,2,15,0,'2024-06-27 12:00:00',NULL,NULL,NULL,'2024-06-29 08:29:21','2024-06-29 08:29:21'),(13,567890,103,203,300.25,1,20,2,'2024-06-26 10:30:00','2024-07-03 14:00:00','2024-07-04 11:00:00','2024-07-04 12:00:00','2024-06-29 08:29:21','2024-06-29 08:29:21'),(14,246801,104,204,180.00,1,12,1,'2024-06-25 08:45:00','2024-07-02 11:30:00','2024-07-03 09:00:00',NULL,'2024-06-29 08:29:21','2024-06-29 08:29:21'),(15,135792,105,205,250.80,2,18,0,'2024-06-24 15:00:00',NULL,NULL,NULL,'2024-06-29 08:29:21','2024-06-29 08:29:21'),(16,112233,106,206,350.00,1,25,2,'2024-06-23 16:20:00','2024-06-30 13:45:00','2024-07-01 10:00:00','2024-07-01 11:00:00','2024-06-29 08:29:21','2024-06-29 08:29:21'),(17,445566,107,207,280.50,1,15,1,'2024-06-22 11:00:00','2024-06-29 09:30:00','2024-06-29 11:15:00',NULL,'2024-06-29 08:29:21','2024-06-29 08:29:21'),(18,778899,108,208,190.90,2,10,0,'2024-06-21 09:30:00',NULL,NULL,NULL,'2024-06-29 08:29:21','2024-06-29 08:29:21'),(22,789012,102,202,200.50,2,15,0,'2024-06-27 12:00:00',NULL,NULL,NULL,'2024-06-29 10:04:26','2024-06-29 10:04:26'),(23,567890,103,203,300.25,1,20,2,'2024-06-26 10:30:00','2024-07-03 14:00:00','2024-07-04 11:00:00','2024-07-04 12:00:00','2024-06-29 10:04:26','2024-06-29 10:04:26'),(24,246801,104,204,180.00,1,12,1,'2024-06-25 08:45:00','2024-07-02 11:30:00','2024-07-03 09:00:00',NULL,'2024-06-29 10:04:26','2024-06-29 10:04:26'),(25,135792,105,205,250.80,2,18,0,'2024-06-24 15:00:00',NULL,NULL,NULL,'2024-06-29 10:04:26','2024-06-29 10:04:26'),(26,112233,106,206,350.00,1,25,2,'2024-06-23 16:20:00','2024-06-30 13:45:00','2024-07-01 10:00:00','2024-07-01 11:00:00','2024-06-29 10:04:26','2024-06-29 10:04:26'),(27,445566,107,207,280.50,1,15,1,'2024-06-22 11:00:00','2024-06-29 09:30:00','2024-06-29 11:15:00',NULL,'2024-06-29 10:04:26','2024-06-29 10:04:26'),(28,778899,108,208,190.90,2,10,0,'2024-06-21 09:30:00',NULL,NULL,NULL,'2024-06-29 10:04:26','2024-06-29 10:04:26'),(32,789012,102,202,200.50,2,15,0,'2024-06-27 12:00:00',NULL,NULL,NULL,'2024-06-29 10:04:52','2024-06-29 10:04:52'),(33,567890,103,203,300.25,1,20,2,'2024-06-26 10:30:00','2024-07-03 14:00:00','2024-07-04 11:00:00','2024-07-04 12:00:00','2024-06-29 10:04:52','2024-06-29 10:04:52'),(34,246801,104,204,180.00,1,12,1,'2024-06-25 08:45:00','2024-07-02 11:30:00','2024-07-03 09:00:00',NULL,'2024-06-29 10:04:52','2024-06-29 10:04:52'),(35,135792,105,205,250.80,2,18,0,'2024-06-24 15:00:00',NULL,NULL,NULL,'2024-06-29 10:04:52','2024-06-29 10:04:52'),(36,112233,106,206,350.00,1,25,2,'2024-06-23 16:20:00','2024-06-30 13:45:00','2024-07-01 10:00:00','2024-07-01 11:00:00','2024-06-29 10:04:52','2024-06-29 10:04:52'),(37,445566,107,207,280.50,1,15,1,'2024-06-22 11:00:00','2024-06-29 09:30:00','2024-06-29 11:15:00',NULL,'2024-06-29 10:04:52','2024-06-29 10:04:52'),(38,778899,108,208,190.90,2,10,0,'2024-06-21 09:30:00',NULL,NULL,NULL,'2024-06-29 10:04:52','2024-06-29 10:04:52');
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product_category`
--

DROP TABLE IF EXISTS `product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int NOT NULL,
  `name` varchar(50) NOT NULL,
  `sort_order` int DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `level` int DEFAULT NULL,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`parent_id`),
  CONSTRAINT `product_category_chk_1` CHECK ((`status` in (0,1))),
  CONSTRAINT `product_category_chk_2` CHECK ((`level` >= 0))
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
INSERT INTO `product_category` VALUES (1,0,'配件商城',1,1,1,'2024-06-28 12:00:00','2024-06-30 21:28:32'),(19,1,'type2',1,1,2,'2023-04-21 10:15:30','2024-06-30 21:28:32'),(28,1,'type1',2,1,2,'2023-04-21 10:15:30','2024-06-30 21:28:32'),(29,28,'type21',1,1,3,'2023-04-21 10:15:30','2024-06-30 21:28:32'),(35,29,'type211',1,1,4,'2023-04-21 10:15:30','2024-06-30 21:28:32'),(36,29,'type212',2,1,4,'2023-02-14 11:42:23','2024-06-30 21:28:32');
/*!40000 ALTER TABLE `product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `products`
--

DROP TABLE IF EXISTS `products`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `product_id` int DEFAULT NULL,
  `parts_id` int DEFAULT NULL,
  `icon_url` varchar(500) DEFAULT NULL,
  `sub_images` text,
  `detail` text,
  `spec_param` text,
  `price` decimal(20,2) NOT NULL,
  `stock` int NOT NULL,
  `status` int NOT NULL DEFAULT '1',
  `is_hot` int NOT NULL DEFAULT '2',
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id` (`product_id`),
  UNIQUE KEY `parts_id` (`parts_id`),
  CONSTRAINT `products_chk_1` CHECK ((`status` in (1,2,3))),
  CONSTRAINT `products_chk_2` CHECK ((`is_hot` in (1,2)))
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (1,'锂基脂 00#',1,1,NULL,NULL,NULL,NULL,215.91,30,2,1,'2024-06-29 00:01:22','2024-07-01 00:09:26'),(2,'道达尔液压油200L',2,2,NULL,NULL,NULL,NULL,215.89,50,1,1,'2024-06-29 09:20:59','2024-06-30 21:42:08'),(3,'商品1',3,3,NULL,NULL,NULL,NULL,10.99,100,1,2,'2024-06-29 09:25:28','2024-07-01 00:09:34'),(4,'商品2',4,4,NULL,NULL,NULL,NULL,15.50,200,1,2,'2024-06-29 09:25:28','2024-07-01 00:09:35'),(5,'商品3',5,5,NULL,NULL,NULL,NULL,20.75,150,1,2,'2024-06-29 09:25:28','2024-07-01 00:21:41'),(6,'商品4',6,6,NULL,NULL,NULL,NULL,25.00,80,1,1,'2024-06-29 09:25:28','2024-06-30 21:42:11'),(7,'商品5',7,7,NULL,NULL,NULL,NULL,30.10,60,1,1,'2024-06-29 09:25:28','2024-06-30 21:42:12'),(8,'商品6',8,8,NULL,NULL,NULL,NULL,99.99,40,1,1,'2024-06-29 09:25:28','2024-06-30 21:42:13'),(9,'商品7',9,9,NULL,NULL,NULL,NULL,100.87,30,1,1,'2024-06-29 09:25:28','2024-06-30 21:42:15'),(10,'商品8',10,10,NULL,NULL,NULL,NULL,45.75,20,1,2,'2024-06-29 09:25:28','2024-06-29 10:12:00'),(11,'商品9',12,11,NULL,NULL,NULL,NULL,50.99,10,1,2,'2024-06-29 09:25:28','2024-06-29 10:12:00'),(14,'商品123',99,NULL,NULL,NULL,NULL,'',789.11,982,1,2,'2024-07-01 08:52:49','2024-07-01 08:52:49');
/*!40000 ALTER TABLE `products` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shipping_address`
--

DROP TABLE IF EXISTS `shipping_address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shipping_address` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `mobile` varchar(20) NOT NULL,
  `province` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `district` varchar(20) DEFAULT NULL,
  `addr` varchar(300) DEFAULT NULL,
  `zip` varchar(6) NOT NULL,
  `dfault` tinyint(1) DEFAULT NULL,
  `isDel` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `shipping_address_chk_1` CHECK ((`dfault` in (0,1))),
  CONSTRAINT `shipping_address_chk_2` CHECK ((`isDel` in (0,1)))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
/*!40000 ALTER TABLE `shipping_address` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `question` varchar(50) DEFAULT NULL,
  `asw` varchar(50) DEFAULT NULL,
  `role` int NOT NULL,
  `age` int NOT NULL,
  `sex` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `del` tinyint(1) NOT NULL DEFAULT '0',
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_account` (`account`),
  UNIQUE KEY `uniq_email` (`email`),
  CONSTRAINT `users_chk_1` CHECK (((`age` >= 0) and (`age` <= 120))),
  CONSTRAINT `users_chk_2` CHECK ((`role` in (1,2))),
  CONSTRAINT `users_chk_3` CHECK ((`sex` in (0,1)))
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','202CB962AC59075B964B07152D234B70','hui.yu.xiamen.work@gmail.com',NULL,NULL,NULL,2,30,1,'2024-06-25 10:09:47','2024-06-27 18:38:22',0,'管理员'),(2,'123','202CB962AC59075B964B07152D234B70','123','123','123','123',1,3,1,'2024-06-26 09:45:59','2024-06-26 09:45:59',0,'123'),(3,'222','BCBE3365E6AC95EA2C0343A2395834DD','222','222','222','222',1,3,0,'2024-06-26 09:48:45','2024-06-27 15:02:46',0,'222'),(4,'333','310DCBBF4CCE62F762A2AAA148D556BD','333','333','333','333',1,4,1,'2024-06-26 09:52:37','2024-06-26 09:52:37',0,'333'),(5,'444','550A141F12DE6341FBA65B0AD0433500','444','444','444','444',1,3,1,'2024-06-26 10:11:12','2024-06-26 10:11:12',0,'4444'),(6,'user1','BCBE3365E6AC95EA2C0343A2395834DD','user1@example.com','1234567890','Favorite color?','Blue',1,25,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User One'),(7,'user2','BCBE3365E6AC95EA2C0343A2395834DD','user2@example.com','1234567891','Favorite color?','Green',1,30,0,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Two'),(8,'user3','BCBE3365E6AC95EA2C0343A2395834DD','user3@example.com','1234567892','Favorite color?','Red',1,22,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Three'),(9,'user4','BCBE3365E6AC95EA2C0343A2395834DD','user4@example.com','1234567893','Favorite color?','Yellow',1,28,0,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Four'),(10,'user5','BCBE3365E6AC95EA2C0343A2395834DD','user5@example.com','1234567894','Favorite color?','Purple',1,35,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Five'),(11,'user6','BCBE3365E6AC95EA2C0343A2395834DD','user6@example.com','1234567895','Favorite animal?','Cat',1,24,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Six'),(12,'user7','BCBE3365E6AC95EA2C0343A2395834DD','user7@example.com','1234567896','Favorite animal?','Dog',1,27,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Seven'),(13,'user8','BCBE3365E6AC95EA2C0343A2395834DD','user8@example.com','1234567897','Favorite animal?','Elephant',1,31,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Eight'),(14,'user9','BCBE3365E6AC95EA2C0343A2395834DD','user9@example.com','1234567898','Favorite animal?','Horse',1,29,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Nine'),(15,'user10','BCBE3365E6AC95EA2C0343A2395834DD','user10@example.com','1234567899','Favorite animal?','Tiger',1,26,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Ten'),(16,'user11','BCBE3365E6AC95EA2C0343A2395834DD','user11@example.com','1234567800','Favorite animal?','Lion',1,34,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Eleven'),(17,'user12','BCBE3365E6AC95EA2C0343A2395834DD','user12@example.com','1234567801','Favorite animal?','Penguin',1,21,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Twelve'),(21,'user13','BCBE3365E6AC95EA2C0343A2395834DD','user13@example.com','1234567802','最喜欢的颜色？','蓝色',1,22,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十三'),(22,'user14','BCBE3365E6AC95EA2C0343A2395834DD','user14@example.com','1234567803','你父亲的姓名？','张三',1,23,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十四'),(23,'user15','BCBE3365E6AC95EA2C0343A2395834DD','user15@example.com','1234567804','你母亲的姓名？','李四',1,24,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十五'),(24,'user16','BCBE3365E6AC95EA2C0343A2395834DD','user16@example.com','1234567805','你的生日？','1990-01-01',1,25,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十六'),(25,'user17','BCBE3365E6AC95EA2C0343A2395834DD','user17@example.com','1234567806','你的学号？','20230001',1,26,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十七'),(26,'user18','BCBE3365E6AC95EA2C0343A2395834DD','user18@example.com','1234567807','你的座右铭？','努力就会成功',1,27,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十八'),(27,'user19','BCBE3365E6AC95EA2C0343A2395834DD','user19@example.com','1234567808','你的爱好？','游泳',1,28,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十九'),(28,'user20','BCBE3365E6AC95EA2C0343A2395834DD','user20@example.com','1234567809','你的梦想？','成为科学家',1,29,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二十'),(29,'user21','BCBE3365E6AC95EA2C0343A2395834DD','user21@example.com','1234567810','你的愿望？','世界和平',1,30,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二一'),(30,'user22','BCBE3365E6AC95EA2C0343A2395834DD','user22@example.com','1234567811','你的理想？','自由平等',1,31,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二二'),(31,'user23','BCBE3365E6AC95EA2C0343A2395834DD','user23@example.com','1234567812','你的希望？','家庭幸福',1,32,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二三'),(32,'user24','BCBE3365E6AC95EA2C0343A2395834DD','user24@example.com','1234567813','你的信念？','坚持就是胜利',1,33,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二四'),(33,'user25','BCBE3365E6AC95EA2C0343A2395834DD','user25@example.com','1234567814','你的座右铭？','生活如歌',1,34,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二五'),(34,'user26','BCBE3365E6AC95EA2C0343A2395834DD','user26@example.com','1234567815','你的座右铭？','心有所向',1,35,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二六'),(35,'user27','BCBE3365E6AC95EA2C0343A2395834DD','user27@example.com','1234567816','你的座右铭？','不怕艰难',1,36,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二七'),(36,'user28','BCBE3365E6AC95EA2C0343A2395834DD','user28@example.com','1234567817','你的座右铭？','永不言弃',1,37,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二八'),(37,'user29','BCBE3365E6AC95EA2C0343A2395834DD','user29@example.com','1234567818','你的座右铭？','不畏浮云遮望眼',1,38,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二九'),(58,'account1','BCBE3365E6AC95EA2C0343A2395834DD','account1@example.com','1234567890','喜欢的颜色？','蓝色',1,25,1,'2024-06-27 16:32:53','2024-06-27 16:32:53',0,'张三'),(59,'account10','BCBE3365E6AC95EA2C0343A2395834DD','account10@example.com','1234567899','喜欢的动物？','老虎',1,26,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'李四'),(60,'account11','BCBE3365E6AC95EA2C0343A2395834DD','account11@example.com','1234567800','喜欢的动物？','狮子',1,34,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'王五'),(61,'account12','BCBE3365E6AC95EA2C0343A2395834DD','account12@example.com','1234567801','喜欢的动物？','企鹅',1,21,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'赵六'),(62,'account21','BCBE3365E6AC95EA2C0343A2395834DD','account21@example.com','1234567810','喜欢的颜色？','红色',1,28,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'钱七'),(63,'account31','BCBE3365E6AC95EA2C0343A2395834DD','account31@example.com','1234567820','喜欢的颜色？','绿色',1,32,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'孙八'),(64,'account41','BCBE3365E6AC95EA2C0343A2395834DD','account41@example.com','1234567830','喜欢的动物？','猫',1,29,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'李九'),(65,'account51','BCBE3365E6AC95EA2C0343A2395834DD','account51@example.com','1234567840','喜欢的动物？','狗',1,31,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'周十'),(66,'account61','BCBE3365E6AC95EA2C0343A2395834DD','account61@example.com','1234567850','喜欢的动物？','兔子',1,27,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'吴十一'),(67,'account71','BCBE3365E6AC95EA2C0343A2395834DD','account71@example.com','1234567860','喜欢的颜色？','紫色',1,30,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'郑十二'),(68,'aaa','47BCE5C74F589F4867DBD57E9CA9F808','aaa','aaa','aaa','aaa',1,2,0,'2024-06-30 12:20:40','2024-06-30 12:20:40',0,'aaa'),(69,'vvv','4786F3282F04DE5B5C7317C490C6D922','vvv','vvv','vvv','vvv',1,4,0,'2024-06-30 12:50:14','2024-06-30 12:50:14',0,'vvv');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mechmall'
--

--
-- Dumping routines for database 'mechmall'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-01 10:25:49
