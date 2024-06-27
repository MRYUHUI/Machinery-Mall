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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product_category`
--

LOCK TABLES `product_category` WRITE;
/*!40000 ALTER TABLE `product_category` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','81DC9BDB52D04DC20036DBD8313ED055','hui.yu.xiamen.work@gmail.com',NULL,NULL,NULL,2,30,1,'2024-06-25 10:09:47','2024-06-27 08:29:32',0,'管理员'),(2,'123','202CB962AC59075B964B07152D234B70','123','123','123','123',1,3,1,'2024-06-26 09:45:59','2024-06-26 09:45:59',0,'123'),(3,'222','BCBE3365E6AC95EA2C0343A2395834DD','222','222','222','222',1,3,0,'2024-06-26 09:48:45','2024-06-27 15:02:46',0,'222'),(4,'333','310DCBBF4CCE62F762A2AAA148D556BD','333','333','333','333',1,4,1,'2024-06-26 09:52:37','2024-06-26 09:52:37',0,'333'),(5,'444','550A141F12DE6341FBA65B0AD0433500','444','444','444','444',1,3,1,'2024-06-26 10:11:12','2024-06-26 10:11:12',0,'4444'),(6,'user1','BCBE3365E6AC95EA2C0343A2395834DD','user1@example.com','1234567890','Favorite color?','Blue',1,25,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User One'),(7,'user2','BCBE3365E6AC95EA2C0343A2395834DD','user2@example.com','1234567891','Favorite color?','Green',1,30,0,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Two'),(8,'user3','BCBE3365E6AC95EA2C0343A2395834DD','user3@example.com','1234567892','Favorite color?','Red',1,22,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Three'),(9,'user4','BCBE3365E6AC95EA2C0343A2395834DD','user4@example.com','1234567893','Favorite color?','Yellow',1,28,0,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Four'),(10,'user5','BCBE3365E6AC95EA2C0343A2395834DD','user5@example.com','1234567894','Favorite color?','Purple',1,35,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Five'),(11,'user6','BCBE3365E6AC95EA2C0343A2395834DD','user6@example.com','1234567895','Favorite animal?','Cat',1,24,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Six'),(12,'user7','BCBE3365E6AC95EA2C0343A2395834DD','user7@example.com','1234567896','Favorite animal?','Dog',1,27,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Seven'),(13,'user8','BCBE3365E6AC95EA2C0343A2395834DD','user8@example.com','1234567897','Favorite animal?','Elephant',1,31,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Eight'),(14,'user9','BCBE3365E6AC95EA2C0343A2395834DD','user9@example.com','1234567898','Favorite animal?','Horse',1,29,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Nine'),(15,'user10','BCBE3365E6AC95EA2C0343A2395834DD','user10@example.com','1234567899','Favorite animal?','Tiger',1,26,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Ten'),(16,'user11','BCBE3365E6AC95EA2C0343A2395834DD','user11@example.com','1234567800','Favorite animal?','Lion',1,34,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Eleven'),(17,'user12','BCBE3365E6AC95EA2C0343A2395834DD','user12@example.com','1234567801','Favorite animal?','Penguin',1,21,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Twelve');
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

-- Dump completed on 2024-06-27 16:45:12
