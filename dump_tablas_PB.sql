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
  PRIMARY KEY (`prd_id`),
  KEY `tip_id_idx` (`prd_tip_id`),
  CONSTRAINT `tip_id` FOREIGN KEY (`prd_tip_id`) REFERENCES `tipos_producto` (`tip_id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (11,'enesima prueba',7,22,57.915),(13,'es nueva prueba',5,12,112),(14,'Leche',11,212,333),(15,'lrrtrtqq',10,23,333),(16,'Primera prueba APi',14,44,33),(18,'Otra prueba APi',13,42,31),(19,'Otra prueba APi',15,42,31),(21,'SEguimos testeando',17,42,31),(25,'Otro test',4,-1,0),(26,'Otro test',4,-1,0),(29,'Otro test de domingo',4,11,33);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipos_producto`
--

LOCK TABLES `tipos_producto` WRITE;
/*!40000 ALTER TABLE `tipos_producto` DISABLE KEYS */;
INSERT INTO `tipos_producto` VALUES (4,'Comidas calientes'),(5,'Prueba nueva del doming'),(6,'Seguimos probando'),(7,'nueva descripcion'),(10,'lechuga'),(11,'prueba final de producto'),(12,'PRueba servicio'),(13,'Prueba Post exitosa'),(14,'Otra prueba mas'),(15,'La nueva prueba'),(17,'Another prueba'),(19,'Prueba de sabado');
/*!40000 ALTER TABLE `tipos_producto` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-30 22:42:21
