CREATE DATABASE  IF NOT EXISTS `integrador_final` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `integrador_final`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: localhost    Database: integrador_final
-- ------------------------------------------------------
-- Server version	8.0.29

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
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `productos` (
  `prd_id` int NOT NULL AUTO_INCREMENT,
  `prd_descripcion` varchar(45) NOT NULL,
  `prd_tip_id` int NOT NULL,
  `prd_stock` int NOT NULL,
  `prd_precio` double NOT NULL,
  `tipo_producto_tip_id` int DEFAULT NULL,
  PRIMARY KEY (`prd_id`),
  KEY `tip_id_idx` (`prd_tip_id`),
  KEY `FK2h32tdjxsp23ev6d38k490ck2` (`tipo_producto_tip_id`),
  CONSTRAINT `FK2h32tdjxsp23ev6d38k490ck2` FOREIGN KEY (`tipo_producto_tip_id`) REFERENCES `tipos_producto` (`tip_id`),
  CONSTRAINT `tip_id` FOREIGN KEY (`prd_tip_id`) REFERENCES `tipos_producto` (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'ProduccionProdTipo1',4,11,11111,NULL),(2,'ProduccionProdTipo2',3,22,22222,NULL),(3,'Otro mas',5,33,33,NULL),(4,'ProduccionProdTipo4',8,44,44444,NULL),(5,'ProduccionProdTipo5',5,55,55555,NULL),(6,'ProduccionProdTipo6',9,66,66666,NULL),(7,'ProduccionProdTipo7',8,77,77777,NULL),(8,'ProduccionProdTipo8',6,88,88888,NULL),(9,'ProduccionProdTipo9',5,99,99999,NULL),(10,'ProduccionProdTipo10',4,101,10110,NULL),(11,'ProduccionProdTipo11',10,11,11111,NULL),(12,'ProduccionProdTipo12',8,121,12112,NULL),(13,'ProduccionProdTipo13',9,131,13113,NULL),(14,'ProduccionProdTipo14',10,141,14114,NULL),(15,'ProduccionProdTipo15',1,151,15115,NULL),(16,'ProduccionProdTipo16',3,161,16116,NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rol`
--

DROP TABLE IF EXISTS `rol`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rol` (
  `rol_id` int NOT NULL AUTO_INCREMENT,
  `rol_descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`rol_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rol`
--

LOCK TABLES `rol` WRITE;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` VALUES (1,'EMPLEADO'),(2,'GERENTE'),(3,'ADMIN');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipos_producto`
--

DROP TABLE IF EXISTS `tipos_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipos_producto` (
  `tip_id` int NOT NULL AUTO_INCREMENT,
  `tip_descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_producto`
--

LOCK TABLES `tipos_producto` WRITE;
/*!40000 ALTER TABLE `tipos_producto` DISABLE KEYS */;
INSERT INTO `tipos_producto` VALUES (1,'ProduccionTipo1'),(2,'ProduccionTipo2'),(3,'ProduccionTipo3'),(4,'ProduccionTipo4'),(5,'ProduccionTipo5'),(6,'ProduccionTipo6'),(7,'ProduccionTipo7'),(8,'ProduccionTipo8'),(9,'ProduccionTipo9'),(10,'ProduccionTipo10');
/*!40000 ALTER TABLE `tipos_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `usr_id` int NOT NULL AUTO_INCREMENT,
  `usr_username` varchar(45) NOT NULL,
  `usr_password` varchar(45) NOT NULL,
  PRIMARY KEY (`usr_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Empleado1','123'),(2,'Gerente1','123'),(3,'Admin1','123');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_usr_id` int NOT NULL,
  `roles_rol_id` int NOT NULL,
  KEY `FKa47odbapk1y3hdi0atndo3762` (`roles_rol_id`),
  KEY `FKsdupdmyd2a6vibuwpb3s8ylgi` (`user_usr_id`),
  CONSTRAINT `FKa47odbapk1y3hdi0atndo3762` FOREIGN KEY (`roles_rol_id`) REFERENCES `rol` (`rol_id`),
  CONSTRAINT `FKsdupdmyd2a6vibuwpb3s8ylgi` FOREIGN KEY (`user_usr_id`) REFERENCES `users` (`usr_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-12-29 23:24:27
