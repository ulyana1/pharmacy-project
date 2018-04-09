-- MySQL dump 10.16  Distrib 10.1.24-MariaDB, for Linux (x86_64)
--
-- Host: localhost    Database: pharmacy_db
-- ------------------------------------------------------
-- Server version	10.1.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `balance`
--

DROP TABLE IF EXISTS `balance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `balance` (
  `id_balance` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `income` decimal(13,2) NOT NULL,
  `spending` decimal(13,2) NOT NULL,
  `cash_balance` decimal(13,2) NOT NULL,
  `id_cash_flow` int(11) NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  PRIMARY KEY (`id_balance`),
  KEY `id_cash_flow` (`id_cash_flow`),
  KEY `id_pharmacy` (`id_pharmacy`),
  CONSTRAINT `balance_ibfk_1` FOREIGN KEY (`id_cash_flow`) REFERENCES `cash_flow` (`id_cash_flow`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `balance_ibfk_2` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `balance`
--

LOCK TABLES `balance` WRITE;
/*!40000 ALTER TABLE `balance` DISABLE KEYS */;
INSERT INTO `balance` VALUES (1,'2018-04-09',2400.00,1300.00,120300.00,1,8),(2,'2018-04-09',5700.00,200.00,15300.00,1,9),(3,'2018-04-09',1000.00,5000.00,3000.00,1,10),(4,'2018-04-09',2000.00,100.00,5000.00,1,10),(5,'2018-04-09',53000.00,32100.00,500000.00,1,11),(6,'2018-04-02',7500.00,100.00,0.00,1,10),(7,'2018-03-21',1200.00,300.00,0.00,1,8),(8,'2018-03-12',50000.00,30000.00,0.00,1,9),(9,'2018-03-05',12300.00,2000.00,0.00,1,10),(10,'2018-03-29',500.00,100.00,0.00,1,12),(11,'2018-02-15',5030.00,300.00,0.00,1,12),(12,'2018-02-01',35020.00,18000.00,0.00,1,10),(13,'2018-02-28',630.00,100.00,0.00,1,8),(14,'2018-03-05',2500.00,1000.00,0.00,1,9),(15,'2018-03-02',3700.00,2000.00,0.00,1,11),(16,'2018-02-15',5700.00,500.00,0.00,1,8),(17,'2018-02-01',3400.00,100.00,0.00,1,9),(18,'2018-02-01',3400.00,100.00,0.00,1,11);
/*!40000 ALTER TABLE `balance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cash_flow`
--

DROP TABLE IF EXISTS `cash_flow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cash_flow` (
  `id_cash_flow` int(11) NOT NULL AUTO_INCREMENT,
  `purpose_of_cash` varchar(50) NOT NULL,
  `total` decimal(13,2) NOT NULL,
  `id_delivery` int(11) NOT NULL,
  `id_purch` int(11) NOT NULL,
  PRIMARY KEY (`id_cash_flow`),
  KEY `id_delivery` (`id_delivery`),
  KEY `id_purch` (`id_purch`),
  CONSTRAINT `cash_flow_ibfk_1` FOREIGN KEY (`id_delivery`) REFERENCES `delivery` (`id_delivery`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `cash_flow_ibfk_2` FOREIGN KEY (`id_purch`) REFERENCES `purchase` (`id_purch`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cash_flow`
--

LOCK TABLES `cash_flow` WRITE;
/*!40000 ALTER TABLE `cash_flow` DISABLE KEYS */;
INSERT INTO `cash_flow` VALUES (1,'test',0.00,1,1);
/*!40000 ALTER TABLE `cash_flow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery`
--

DROP TABLE IF EXISTS `delivery`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery` (
  `id_delivery` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  PRIMARY KEY (`id_delivery`),
  KEY `id_pharmacy` (`id_pharmacy`),
  CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,'2018-02-01',8),(2,'2018-02-01',9),(3,'2018-02-04',10),(4,'2018-02-05',11),(5,'2018-02-07',12);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `delivery_medicine`
--

DROP TABLE IF EXISTS `delivery_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_medicine` (
  `id_delivery` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `box_quantity` int(11) NOT NULL,
  KEY `id_medicine` (`id_medicine`),
  KEY `id_delivery` (`id_delivery`),
  CONSTRAINT `delivery_medicine_ibfk_1` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `delivery_medicine_ibfk_2` FOREIGN KEY (`id_delivery`) REFERENCES `delivery` (`id_delivery`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_medicine`
--

LOCK TABLES `delivery_medicine` WRITE;
/*!40000 ALTER TABLE `delivery_medicine` DISABLE KEYS */;
INSERT INTO `delivery_medicine` VALUES (1,2,40),(1,3,60),(1,4,30),(1,5,80),(2,6,100),(2,13,75),(3,3,200),(3,5,160),(4,8,200),(5,12,130);
/*!40000 ALTER TABLE `delivery_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor`
--

DROP TABLE IF EXISTS `doctor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `doctor` (
  `id_doctor` int(11) NOT NULL AUTO_INCREMENT,
  `surname` varchar(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `speciality` varchar(50) NOT NULL,
  `years_of_practice` int(11) NOT NULL,
  PRIMARY KEY (`id_doctor`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor`
--

LOCK TABLES `doctor` WRITE;
/*!40000 ALTER TABLE `doctor` DISABLE KEYS */;
INSERT INTO `doctor` VALUES (2,'М\'ясоєдов','Андрій','Хірург',3),(3,'Глазнюк','Сергій','Офтальмолог',7),(4,'Зубнюк','Дарина','Стоматолог',18),(5,'Селівестрова','Катерина','Терапевт',4),(6,'Дорошенко','Марія','Терапевт',7),(7,'Гончар','Анатолій','Гастроентеролог',17);
/*!40000 ALTER TABLE `doctor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `medicine`
--

DROP TABLE IF EXISTS `medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medicine` (
  `id_medicine` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `producer` varchar(255) NOT NULL,
  `box_price` decimal(13,2) NOT NULL,
  `quantity_per_box` int(11) NOT NULL,
  PRIMARY KEY (`id_medicine`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `medicine`
--

LOCK TABLES `medicine` WRITE;
/*!40000 ALTER TABLE `medicine` DISABLE KEYS */;
INSERT INTO `medicine` VALUES (2,'Travomel','Znahar',100.00,25),(3,'Notta','Znahar',100.00,30),(4,'Mezym','Znahar',100.00,30),(5,'Hilac','Znahar',200.00,10),(6,'Penicillin VK','AstraZeneca',500.00,24),(7,'Diazepam','Novartis',100.00,24),(8,'Metformin HCl','Merck',250.00,100),(9,'Digoxin','GlaxoSmithKline',375.00,24),(10,'Lovaza','Pfizer',750.00,24),(11,'Lorazepam','Sanofi',750.00,100),(12,'Ocuvite Forte','JELFA',220.00,20),(13,'Lorazepam','AstraZeneca',375.00,24),(14,'Penicillin VK','Novartis',500.00,30);
/*!40000 ALTER TABLE `medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patient`
--

DROP TABLE IF EXISTS `patient`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient` (
  `id_patient` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `date_of_birth` date NOT NULL,
  `phone` varchar(16) NOT NULL,
  PRIMARY KEY (`id_patient`),
  UNIQUE KEY `phone` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient`
--

LOCK TABLES `patient` WRITE;
/*!40000 ALTER TABLE `patient` DISABLE KEYS */;
INSERT INTO `patient` VALUES (2,'Denzel','Washington','1954-12-28','039-499-1604'),(3,'Leonardo','DiCaprio','1974-11-11','082-270-6766'),(4,'Julia','Stiles','1981-05-28','085-698-4968'),(5,'Tom','Hanks','1956-07-09','047-688-2690'),(6,'Halle','Berry','1966-08-14','040-801-0454'),(7,'Dwayne','Johnson','1975-05-02','022-304-7031'),(8,'Morgan','Freeman','1937-06-01','036-163-4367');
/*!40000 ALTER TABLE `patient` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy`
--

DROP TABLE IF EXISTS `pharmacy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pharmacy` (
  `id_pharmacy` int(11) NOT NULL AUTO_INCREMENT,
  `pharm_title` varchar(50) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id_pharmacy`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy`
--

LOCK TABLES `pharmacy` WRITE;
/*!40000 ALTER TABLE `pharmacy` DISABLE KEYS */;
INSERT INTO `pharmacy` VALUES (8,'Green apteka','Zelena, 12 str.'),(9,'Znahar','Levandivka, 12 str.'),(10,'АНЦ','проспект Бажана, 36'),(11,'Соціальна Аптека','вул. Цвєтаєвої, 32'),(12,'АНЦ','вул. Гришко, 6');
/*!40000 ALTER TABLE `pharmacy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pharmacy_medicine`
--

DROP TABLE IF EXISTS `pharmacy_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pharmacy_medicine` (
  `id_pharmacy` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_price` decimal(13,2) NOT NULL,
  `date_of_change` date NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  PRIMARY KEY (`id_pharmacy`,`id_medicine`,`date_of_change`),
  KEY `id_pharmacy` (`id_pharmacy`),
  KEY `id_medicine` (`id_medicine`),
  CONSTRAINT `pharmacy_medicine_ibfk_1` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `pharmacy_medicine_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pharmacy_medicine`
--

LOCK TABLES `pharmacy_medicine` WRITE;
/*!40000 ALTER TABLE `pharmacy_medicine` DISABLE KEYS */;
INSERT INTO `pharmacy_medicine` VALUES (8,2,16.80,'2018-01-01',10),(8,4,20.00,'2018-01-20',10),(9,6,120.00,'2018-01-21',5);
/*!40000 ALTER TABLE `pharmacy_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescr_medicine`
--

DROP TABLE IF EXISTS `prescr_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescr_medicine` (
  `id_prescr` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  `pack_bought` int(11) DEFAULT NULL,
  KEY `id_prescr` (`id_prescr`),
  KEY `id_medicine` (`id_medicine`),
  CONSTRAINT `prescr_medicine_ibfk_1` FOREIGN KEY (`id_prescr`) REFERENCES `prescription` (`id_prescr`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `prescr_medicine_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescr_medicine`
--

LOCK TABLES `prescr_medicine` WRITE;
/*!40000 ALTER TABLE `prescr_medicine` DISABLE KEYS */;
INSERT INTO `prescr_medicine` VALUES (1,13,3,1),(1,4,2,0),(1,10,7,NULL),(3,8,2,2),(3,11,2,0),(9,6,4,1),(10,7,1,1),(10,11,2,2),(11,9,1,1),(11,14,1,1);
/*!40000 ALTER TABLE `prescr_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prescription`
--

DROP TABLE IF EXISTS `prescription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `prescription` (
  `id_prescr` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `id_doctor` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_prescr`),
  KEY `id_doctor` (`id_doctor`),
  KEY `id_patient` (`id_patient`),
  CONSTRAINT `prescription_ibfk_1` FOREIGN KEY (`id_doctor`) REFERENCES `doctor` (`id_doctor`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `prescription_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prescription`
--

LOCK TABLES `prescription` WRITE;
/*!40000 ALTER TABLE `prescription` DISABLE KEYS */;
INSERT INTO `prescription` VALUES (1,'2018-02-01',2,7),(2,'2018-02-03',3,5),(3,'2018-01-03',4,6),(4,'2017-12-30',5,5),(5,'2018-01-09',3,2),(6,'2018-01-09',7,4),(7,'2018-01-10',6,3),(8,'2018-01-04',3,8),(9,'2018-02-01',4,7),(10,'2018-02-09',2,2),(11,'2018-02-10',5,4);
/*!40000 ALTER TABLE `prescription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purch_medicine`
--

DROP TABLE IF EXISTS `purch_medicine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purch_medicine` (
  `id_purch` int(11) NOT NULL,
  `id_medicine` int(11) NOT NULL,
  `pack_quantity` int(11) NOT NULL,
  KEY `id_purch` (`id_purch`),
  KEY `id_medicine` (`id_medicine`),
  CONSTRAINT `purch_medicine_ibfk_1` FOREIGN KEY (`id_purch`) REFERENCES `purchase` (`id_purch`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `purch_medicine_ibfk_2` FOREIGN KEY (`id_medicine`) REFERENCES `medicine` (`id_medicine`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purch_medicine`
--

LOCK TABLES `purch_medicine` WRITE;
/*!40000 ALTER TABLE `purch_medicine` DISABLE KEYS */;
INSERT INTO `purch_medicine` VALUES (1,7,1),(1,11,1),(2,9,2),(3,14,1);
/*!40000 ALTER TABLE `purch_medicine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `purchase`
--

DROP TABLE IF EXISTS `purchase`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `purchase` (
  `id_purch` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `id_prescr` int(11) NOT NULL,
  `id_pharmacy` int(11) NOT NULL,
  `id_patient` int(11) NOT NULL,
  PRIMARY KEY (`id_purch`),
  KEY `id_prescr` (`id_prescr`),
  KEY `id_pharmacy` (`id_pharmacy`),
  KEY `id_patient` (`id_patient`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`id_prescr`) REFERENCES `prescription` (`id_prescr`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`id_pharmacy`) REFERENCES `pharmacy` (`id_pharmacy`) ON DELETE NO ACTION ON UPDATE CASCADE,
  CONSTRAINT `purchase_ibfk_3` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`id_patient`) ON DELETE NO ACTION ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `purchase`
--

LOCK TABLES `purchase` WRITE;
/*!40000 ALTER TABLE `purchase` DISABLE KEYS */;
INSERT INTO `purchase` VALUES (1,'2018-02-09 00:00:00',10,11,2),(2,'2018-02-10 00:00:00',11,12,4),(3,'2018-02-11 00:00:00',10,9,2);
/*!40000 ALTER TABLE `purchase` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-09 16:27:38
