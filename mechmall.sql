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
-- Table structure for table `carousel`
--

DROP TABLE IF EXISTS `carousel`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carousel` (
  `id` int NOT NULL AUTO_INCREMENT,
  `img_url` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carousel`
--

LOCK TABLES `carousel` WRITE;
/*!40000 ALTER TABLE `carousel` DISABLE KEYS */;
INSERT INTO `carousel` VALUES (1,'/static/img/carousel/accordion-1.jpg','Accordion 1'),(2,'/static/img/carousel/accordion-2.jpg','Accordion 2'),(3,'/static/img/carousel/accordion-3.jpg','Accordion 3'),(4,'/static/img/carousel/accordion-4.jpg','Accordion 4'),(5,'/static/img/carousel/accordion-5.jpg','Accordion 5'),(6,'/static/img/carousel/accordion-6.jpg','Accordion 6');
/*!40000 ALTER TABLE `carousel` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (7,3,55,1,'2024-07-06 09:23:21','2024-07-06 09:23:21'),(10,3,57,1,'2024-07-06 09:31:13','2024-07-06 09:31:13'),(11,3,58,1,'2024-07-06 10:49:58','2024-07-06 10:49:58');
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
) ENGINE=InnoDB AUTO_INCREMENT=45 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_items`
--

LOCK TABLES `order_items` WRITE;
/*!40000 ALTER TABLE `order_items` DISABLE KEYS */;
INSERT INTO `order_items` VALUES (20,3,64,57,'轴承座','/static/img/goods/17201395086172.jpg',345.67,1,345.67,'2024-07-05 22:52:43','2024-07-05 22:52:43'),(22,3,66,57,'轴承座','/static/img/goods/17201395086172.jpg',345.67,1,345.67,'2024-07-05 22:52:43','2024-07-05 22:52:43'),(23,3,67,57,'轴承座','/static/img/goods/17201395086172.jpg',345.67,1,345.67,'2024-07-05 22:52:44','2024-07-05 22:52:44'),(43,3,87,57,'轴承座','/static/img/goods/17201395086172.jpg',345.67,1,345.67,'2024-07-06 09:31:12','2024-07-06 09:31:12'),(44,3,88,58,'传动轴','/static/img/goods/17201395115323.jpg',456.78,3,1370.34,'2024-07-06 10:49:57','2024-07-06 10:49:57');
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
  `order_no` varchar(255) NOT NULL,
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
  UNIQUE KEY `order_no_UNIQUE` (`order_no`),
  KEY `idx_uid` (`uid`),
  KEY `idx_addr_id` (`addr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (64,'1720191163630337278',3,23,0.00,1,0,2,'2024-07-05 14:53:14',NULL,NULL,NULL,'2024-07-05 22:52:43','2024-07-05 22:53:13'),(66,'1720191163910665236',3,23,0.00,1,0,2,'2024-07-05 14:55:45',NULL,NULL,NULL,'2024-07-05 22:52:43','2024-07-05 22:55:44'),(67,'1720191164051852216',3,23,0.00,1,0,2,'2024-07-05 15:05:14',NULL,NULL,NULL,'2024-07-05 22:52:44','2024-07-05 23:05:14'),(87,'1720229472292804814',3,23,345.67,1,0,1,NULL,NULL,NULL,NULL,'2024-07-06 09:31:12','2024-07-06 09:31:12'),(88,'1720234197779967694',3,23,1370.34,1,0,1,NULL,NULL,NULL,NULL,'2024-07-06 10:49:57','2024-07-06 10:49:57');
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
  UNIQUE KEY `name_UNIQUE` (`name`),
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
INSERT INTO `product_category` VALUES (1,0,'数控设备',1,1,1,'2024-07-04 09:28:57','2024-07-04 09:28:57'),(2,0,'加工中心',2,1,1,'2024-07-04 09:28:57','2024-07-04 09:28:57'),(3,0,'切割设备',3,1,1,'2024-07-04 09:28:57','2024-07-06 09:26:34'),(4,1,'数控车床',1,1,2,'2024-07-04 09:29:04','2024-07-04 09:29:04'),(5,2,'立式加工中心',2,1,2,'2024-07-04 09:29:04','2024-07-04 09:29:04'),(6,3,'激光切割机',3,1,2,'2024-07-04 09:29:04','2024-07-06 09:26:34'),(7,4,'高精度数控车床',1,1,3,'2024-07-04 09:33:48','2024-07-04 09:33:48'),(8,5,'高级立式加工中心',2,1,3,'2024-07-04 09:33:48','2024-07-04 09:33:48'),(9,5,'高速钻攻中心',3,1,3,'2024-07-04 09:33:48','2024-07-04 09:33:48'),(10,5,'卧式镗铣床',4,1,3,'2024-07-04 09:33:48','2024-07-04 09:33:48'),(11,6,'高精度激光切割机',1,1,3,'2024-07-04 09:33:48','2024-07-06 09:26:34'),(12,5,'三维五轴联动加工中心',6,1,3,'2024-07-04 09:33:48','2024-07-04 09:33:48'),(13,6,'液压折弯机',2,1,3,'2024-07-04 09:33:48','2024-07-06 09:26:34'),(14,6,'冲床',3,1,3,'2024-07-04 09:33:48','2024-07-06 09:26:34');
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
  CONSTRAINT `products_chk_1` CHECK ((`status` in (1,2,3))),
  CONSTRAINT `products_chk_2` CHECK ((`is_hot` in (1,2)))
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `products`
--

LOCK TABLES `products` WRITE;
/*!40000 ALTER TABLE `products` DISABLE KEYS */;
INSERT INTO `products` VALUES (55,'精密齿轮',3,11,'/static/img/goods/1720234263211A路人.png',NULL,'精密加工的高质量齿轮，性能优异。',NULL,123.45,10,3,1,'2024-07-03 15:20:23','2024-07-06 10:51:03'),(56,'液压泵',2,10,'/static/img/goods/17201395043201.jpg',NULL,'高效稳定的液压泵，适用于多种机械。',NULL,234.56,16,3,1,'2024-07-03 15:20:23','2024-07-06 09:24:09'),(57,'轴承座',2,10,'/static/img/goods/17201395086172.jpg',NULL,'坚固耐用的轴承座，支持重载运转。',NULL,345.67,30,3,1,'2024-07-03 15:20:23','2024-07-06 09:31:12'),(58,'传动轴',2,14,'/static/img/goods/17201395115323.jpg',NULL,'精确传动的传动轴，耐磨损，长寿命。',NULL,456.78,37,3,1,'2024-07-03 15:20:23','2024-07-06 10:49:57'),(59,'气动阀',1,11,'/static/img/goods/17201395138654.jpg',NULL,'可靠的气动阀门，操作简便，性能稳定。',NULL,567.89,50,3,1,'2024-07-03 15:20:23','2024-07-06 09:24:31'),(60,'切割机',2,10,'/static/img/goods/17201395158505.jpg',NULL,'高精度的切割机，效率高，安全可靠。',NULL,678.90,60,3,1,'2024-07-03 15:20:23','2024-07-05 08:31:55'),(61,'铣床',1,7,'/static/img/goods/17201395201438.jpg',NULL,'多功能铣床，适合各类精密加工。',NULL,789.01,70,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:00'),(62,'数控机床',2,15,'/static/img/goods/17201395227809.jpg',NULL,'高性能数控机床，自动化程度高。',NULL,890.12,80,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:02'),(63,'输送带',2,11,'/static/img/goods/172013952483310.jpg',NULL,'耐用的输送带，适用于多种物料输送。',NULL,901.23,90,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:04'),(64,'高压管道',2,8,'/static/img/goods/172013952866311.jpg',NULL,'高压管道，耐高温高压，适用广泛。',NULL,101.34,100,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:08'),(65,'工业风扇',1,7,'/static/img/goods/172013953304112.jpg',NULL,'强劲的工业风扇，高效散热，低噪音。',NULL,112.45,110,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:13'),(66,'精密齿轮',2,9,'/static/img/goods/172013953702013.jpg',NULL,'精密加工的高质量齿轮，性能优异。',NULL,223.56,120,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:17'),(67,'液压泵',2,11,'/static/img/goods/172013953980414.jpg',NULL,'高效稳定的液压泵，适用于多种机械。',NULL,334.67,130,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:19'),(68,'轴承座',2,12,'/static/img/goods/172013954278915.jpg',NULL,'坚固耐用的轴承座，支持重载运转。',NULL,445.78,140,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:22'),(69,'传动轴',1,10,'/static/img/goods/172013954566816.jpg',NULL,'精确传动的传动轴，耐磨损，长寿命。',NULL,556.89,150,3,1,'2024-07-03 15:20:23','2024-07-05 08:32:25'),(70,'气动阀',1,8,'/static/img/goods/172013954883317.jpg',NULL,'可靠的气动阀门，操作简便，性能稳定。',NULL,667.90,160,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:28'),(71,'切割机',1,12,'/static/img/goods/172013955341918.jpg',NULL,'高精度的切割机，效率高，安全可靠。',NULL,778.01,170,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:33'),(72,'铣床',3,8,'/static/img/goods/172013955850919.jpg',NULL,'多功能铣床，适合各类精密加工。',NULL,889.12,180,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:38'),(73,'数控机床',2,12,'/static/img/goods/172013956118120.jpg',NULL,'高性能数控机床，自动化程度高。',NULL,990.23,190,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:41'),(74,'输送带',1,8,'/static/img/goods/172013956544321.jpg',NULL,'耐用的输送带，适用于多种物料输送。',NULL,1011.34,200,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:45'),(75,'高压管道',1,15,'/static/img/goods/172013957005622.jpg',NULL,'高压管道，耐高温高压，适用广泛。',NULL,1122.45,210,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:50'),(76,'工业风扇',2,9,'/static/img/goods/172013957404023.jpg',NULL,'强劲的工业风扇，高效散热，低噪音。',NULL,1233.56,220,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:54'),(77,'精密齿轮',1,10,'/static/img/goods/172013957848824.jpg',NULL,'精密加工的高质量齿轮，性能优异。',NULL,1344.67,230,3,2,'2024-07-03 15:20:23','2024-07-05 08:32:58'),(78,'液压泵',3,9,'/static/img/goods/172013994585125.jpg',NULL,'高效稳定的液压泵，适用于多种机械。',NULL,1455.78,240,3,2,'2024-07-03 15:20:23','2024-07-05 08:39:05'),(79,'轴承座',3,7,'/static/img/goods/172013994860026.jpg',NULL,'坚固耐用的轴承座，支持重载运转。',NULL,1566.89,250,3,2,'2024-07-03 15:20:23','2024-07-05 08:39:08'),(80,'传动轴',3,9,'/static/img/goods/172013999538227.jpg',NULL,'精确传动的传动轴，耐磨损，长寿命。',NULL,1677.90,260,3,2,'2024-07-03 15:20:23','2024-07-05 08:39:55'),(81,'气动阀',2,14,'/static/img/goods/172013999803228.jpg',NULL,'可靠的气动阀门，操作简便，性能稳定。',NULL,1788.01,270,3,2,'2024-07-03 15:20:23','2024-07-05 08:39:58'),(82,'切割机',3,12,'/static/img/goods/172014000046929.jpg',NULL,'高精度的切割机，效率高，安全可靠。',NULL,1899.12,280,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:00'),(83,'铣床',2,9,'/static/img/goods/172014000305930.jpg',NULL,'多功能铣床，适合各类精密加工。',NULL,1900.23,290,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:03'),(84,'数控机床',2,7,'/static/img/goods/1720140024507Centuries.png',NULL,'高性能数控机床，自动化程度高。',NULL,2011.34,300,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:24'),(85,'输送带',2,12,'/static/img/goods/1720140030716Hybrid_Theory.png',NULL,'耐用的输送带，适用于多种物料输送。',NULL,2122.45,310,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:30'),(86,'高压管道',3,14,'/static/img/goods/1720140033351Linkin_Park.jpg',NULL,'高压管道，耐高温高压，适用广泛。',NULL,2233.56,320,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:33'),(87,'工业风扇',1,8,'/static/img/goods/1720140036301LIVING_THINGS.png',NULL,'强劲的工业风扇，高效散热，低噪音。',NULL,2344.67,330,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:36'),(88,'精密齿轮',3,9,'/static/img/goods/1720140038229maroon5.png',NULL,'精密加工的高质量齿轮，性能优异。',NULL,2455.78,340,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:38'),(89,'液压泵',3,8,'/static/img/goods/1720140040522merry_c.png',NULL,'高效稳定的液压泵，适用于多种机械。',NULL,2566.89,350,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:40'),(90,'轴承座',3,8,'/static/img/goods/1720140042904Meteora_20th.png',NULL,'坚固耐用的轴承座，支持重载运转。',NULL,2677.90,360,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:42'),(91,'传动轴',1,11,'/static/img/goods/1720140047920meteora.png',NULL,'精确传动的传动轴，耐磨损，长寿命。',NULL,2788.01,370,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:47'),(92,'气动阀',2,12,'/static/img/goods/1720140050442Minutes_to_Midnight.png',NULL,'可靠的气动阀门，操作简便，性能稳定。',NULL,2899.12,380,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:50'),(93,'切割机',1,15,'/static/img/goods/1720140054481One_More_Light.png',NULL,'高精度的切割机，效率高，安全可靠。',NULL,2900.23,390,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:54'),(94,'铣床',2,15,'/static/img/goods/1720140059190坂本龙一.png',NULL,'多功能铣床，适合各类精密加工。',NULL,3011.34,400,3,2,'2024-07-03 15:20:23','2024-07-05 08:40:59');
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
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shipping_address`
--

LOCK TABLES `shipping_address` WRITE;
/*!40000 ALTER TABLE `shipping_address` DISABLE KEYS */;
INSERT INTO `shipping_address` VALUES (1,123,'张三','010-12345678','13800138000','北京市','北京市','朝阳区','望京街道1号','100000',1,0,'2024-07-04 10:39:52','2024-07-05 00:50:51'),(2,123,'李四','010-23456789','13900139000','上海市','上海市','浦东新区','陆家嘴街道2号','200000',0,0,'2024-07-04 10:39:52','2024-07-05 00:50:51'),(3,123,'王五','021-34567890','13600136000','广东省','广州市','天河区','珠江新城3号','300000',0,0,'2024-07-04 10:39:52','2024-07-04 23:20:13'),(4,123,'赵六','2314412321312','13700137000','四川省','成都市','锦江区','春熙路4号','400000',0,0,'2024-07-04 10:39:52','2024-07-04 19:35:46'),(5,123,'小明','022-45678901','13500135000','湖北省','武汉市','江汉区','解放大道5号','500000',0,0,'2024-07-04 10:39:52','2024-07-04 19:35:46'),(6,123,'小红','023-56789012','13400134000','河北省','石家庄市','长安区','中山路6号','600000',0,0,'2024-07-04 10:39:52','2024-07-04 19:35:48'),(16,123,'刘一霆','9988','3123','天津市','市辖区','河西区','123','3333',0,0,'2024-07-04 15:50:33','2024-07-04 23:20:16'),(17,123,'3213','4123','32112','天津市','市辖区','河东区','213','2131',0,0,'2024-07-04 15:51:30','2024-07-04 23:20:14'),(19,123,'123445523','213213124','2131312','内蒙古自治区','乌海市','海勃湾区','12312','1111',0,0,'2024-07-05 09:15:46','2024-07-05 10:13:55'),(23,3,'唐行行','12345678911','12345678911','河南省','郑州市','中原区','郑州精神病院狡辩室','361006',1,0,'2024-07-05 13:42:14','2024-07-05 14:00:32'),(24,3,'刘一霆','111451445','45646461515','河南省','周口市','西华县','河南大学','315464',0,0,'2024-07-05 13:44:25','2024-07-05 14:00:32'),(25,3,'张振学','12465735646','12465735646','山东省','德州市','德城区','1号楼','36000',NULL,0,'2024-07-05 13:48:30','2024-07-05 13:48:30'),(26,3,'庞一凡','324543124','154211321231','山东省','济宁市','嘉祥县','2号楼','361006',NULL,0,'2024-07-05 13:49:42','2024-07-05 13:49:42'),(27,3,'张晖','123456789','123456789','江西省','萍乡市','湘东区','3号楼','30124',NULL,0,'2024-07-05 13:51:24','2024-07-05 13:51:24');
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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','202CB962AC59075B964B07152D234B70','hui.yu.xiamen.work@gmail.com','123',NULL,NULL,2,30,1,'2024-06-25 10:09:47','2024-07-02 11:49:23',0,'管理员'),(2,'123','202CB962AC59075B964B07152D234B70','123','1233','123','123',1,3,0,'2024-06-26 09:45:59','2024-07-06 10:50:38',0,'123'),(3,'222','BCBE3365E6AC95EA2C0343A2395834DD','222f','2222','222','222',1,3,0,'2024-06-26 09:48:45','2024-07-06 10:49:53',0,'222'),(4,'333','310DCBBF4CCE62F762A2AAA148D556BD','333','333','333','333',1,4,1,'2024-06-26 09:52:37','2024-06-26 09:52:37',0,'333'),(5,'444','550A141F12DE6341FBA65B0AD0433500','444','444','444','444',1,3,1,'2024-06-26 10:11:12','2024-06-26 10:11:12',0,'4444'),(6,'user1','BCBE3365E6AC95EA2C0343A2395834DD','user1@example.com','1234567890','Favorite color?','Blue',1,25,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User One'),(7,'user2','BCBE3365E6AC95EA2C0343A2395834DD','user2@example.com','1234567891','Favorite color?','Green',1,30,0,'2024-06-27 08:32:53','2024-07-02 10:42:32',0,'User Two'),(8,'user3','BCBE3365E6AC95EA2C0343A2395834DD','user3@example.com','1234567892','Favorite color?','Red',1,22,1,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Three'),(9,'user4','BCBE3365E6AC95EA2C0343A2395834DD','user4@example.com','1234567893','Favorite color?','Yellow',1,28,0,'2024-06-27 08:32:53','2024-06-27 08:32:53',0,'User Four'),(11,'user6','BCBE3365E6AC95EA2C0343A2395834DD','user6@example.com','1234567895','Favorite animal?','Cat',1,24,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Six'),(12,'user7','BCBE3365E6AC95EA2C0343A2395834DD','user7@example.com','1234567896','Favorite animal?','Dog',1,27,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Seven'),(13,'user8','BCBE3365E6AC95EA2C0343A2395834DD','user8@example.com','1234567897','Favorite animal?','Elephant',1,31,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Eight'),(14,'user9','BCBE3365E6AC95EA2C0343A2395834DD','user9@example.com','1234567898','Favorite animal?','Horse',1,29,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Nine'),(15,'user10','BCBE3365E6AC95EA2C0343A2395834DD','user10@example.com','1234567899','Favorite animal?','Tiger',1,26,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Ten'),(16,'user11','BCBE3365E6AC95EA2C0343A2395834DD','user11@example.com','1234567800','Favorite animal?','Lion',1,34,1,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Eleven'),(17,'user12','BCBE3365E6AC95EA2C0343A2395834DD','user12@example.com','1234567801','Favorite animal?','Penguin',1,21,0,'2024-06-27 08:33:35','2024-06-27 08:33:35',0,'User Twelve'),(21,'user13','BCBE3365E6AC95EA2C0343A2395834DD','user13@example.com','1234567802','最喜欢的颜色？','蓝色',1,22,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十三'),(22,'user14','BCBE3365E6AC95EA2C0343A2395834DD','user14@example.com','1234567803','你父亲的姓名？','张三',1,23,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十四'),(23,'user15','BCBE3365E6AC95EA2C0343A2395834DD','user15@example.com','1234567804','你母亲的姓名？','李四',1,24,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十五'),(24,'user16','BCBE3365E6AC95EA2C0343A2395834DD','user16@example.com','1234567805','你的生日？','1990-01-01',1,25,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十六'),(25,'user17','BCBE3365E6AC95EA2C0343A2395834DD','user17@example.com','1234567806','你的学号？','20230001',1,26,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十七'),(26,'user18','BCBE3365E6AC95EA2C0343A2395834DD','user18@example.com','1234567807','你的座右铭？','努力就会成功',1,27,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十八'),(27,'user19','BCBE3365E6AC95EA2C0343A2395834DD','user19@example.com','1234567808','你的爱好？','游泳',1,28,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户十九'),(28,'user20','BCBE3365E6AC95EA2C0343A2395834DD','user20@example.com','1234567809','你的梦想？','成为科学家',1,29,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二十'),(29,'user21','BCBE3365E6AC95EA2C0343A2395834DD','user21@example.com','1234567810','你的愿望？','世界和平',1,30,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二一'),(30,'user22','BCBE3365E6AC95EA2C0343A2395834DD','user22@example.com','1234567811','你的理想？','自由平等',1,31,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二二'),(31,'user23','BCBE3365E6AC95EA2C0343A2395834DD','user23@example.com','1234567812','你的希望？','家庭幸福',1,32,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二三'),(32,'user24','BCBE3365E6AC95EA2C0343A2395834DD','user24@example.com','1234567813','你的信念？','坚持就是胜利',1,33,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二四'),(33,'user25','BCBE3365E6AC95EA2C0343A2395834DD','user25@example.com','1234567814','你的座右铭？','生活如歌',1,34,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二五'),(34,'user26','BCBE3365E6AC95EA2C0343A2395834DD','user26@example.com','1234567815','你的座右铭？','心有所向',1,35,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二六'),(35,'user27','BCBE3365E6AC95EA2C0343A2395834DD','user27@example.com','1234567816','你的座右铭？','不怕艰难',1,36,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二七'),(36,'user28','BCBE3365E6AC95EA2C0343A2395834DD','user28@example.com','1234567817','你的座右铭？','永不言弃',1,37,1,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二八'),(37,'user29','BCBE3365E6AC95EA2C0343A2395834DD','user29@example.com','1234567818','你的座右铭？','不畏浮云遮望眼',1,38,0,'2024-06-27 23:05:57','2024-06-27 23:05:57',0,'用户二九'),(58,'account1','BCBE3365E6AC95EA2C0343A2395834DD','account1@example.com','1234567890','喜欢的颜色？','蓝色',1,25,1,'2024-06-27 16:32:53','2024-06-27 16:32:53',0,'张三'),(59,'account10','BCBE3365E6AC95EA2C0343A2395834DD','account10@example.com','1234567899','喜欢的动物？','老虎',1,26,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'李四'),(60,'account11','BCBE3365E6AC95EA2C0343A2395834DD','account11@example.com','1234567800','喜欢的动物？','狮子',1,34,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'王五'),(61,'account12','BCBE3365E6AC95EA2C0343A2395834DD','account12@example.com','1234567801','喜欢的动物？','企鹅',1,21,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'赵六'),(62,'account21','BCBE3365E6AC95EA2C0343A2395834DD','account21@example.com','1234567810','喜欢的颜色？','红色',1,28,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'钱七'),(63,'account31','BCBE3365E6AC95EA2C0343A2395834DD','account31@example.com','1234567820','喜欢的颜色？','绿色',1,32,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'孙八'),(64,'account41','BCBE3365E6AC95EA2C0343A2395834DD','account41@example.com','1234567830','喜欢的动物？','猫',1,29,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'李九'),(65,'account51','BCBE3365E6AC95EA2C0343A2395834DD','account51@example.com','1234567840','喜欢的动物？','狗',1,31,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'周十'),(66,'account61','BCBE3365E6AC95EA2C0343A2395834DD','account61@example.com','1234567850','喜欢的动物？','兔子',1,27,1,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'吴十一'),(67,'account71','BCBE3365E6AC95EA2C0343A2395834DD','account71@example.com','1234567860','喜欢的颜色？','紫色',1,30,0,'2024-06-27 16:33:35','2024-06-27 16:33:35',0,'郑十二'),(70,'ccc','9DF62E693988EB4E1E1444ECE0578579','cccc','ccc','ccc','ccc',1,4,1,'2024-07-01 20:30:15','2024-07-06 09:35:37',0,'ccc');
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

-- Dump completed on 2024-07-06 11:04:58
