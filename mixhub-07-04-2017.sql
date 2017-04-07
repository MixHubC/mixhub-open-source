-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: mixhub
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `accounts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` bigint(20) NOT NULL,
  `permission` int(11) NOT NULL DEFAULT '0',
  `register_ip` tinytext NOT NULL,
  `email` mediumtext NOT NULL,
  `username` tinytext NOT NULL,
  `password` tinytext NOT NULL,
  `firstname` tinytext NOT NULL,
  `lastname` tinytext NOT NULL,
  `address_1` tinytext,
  `address_2` tinytext,
  `city` tinytext,
  `state` tinytext,
  `country` tinytext,
  `zip` tinytext,
  `phone` tinytext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
INSERT INTO `accounts` VALUES (1,1491168322595,0,'0:0:0:0:0:0:0:1','e_test','u_test','p_test','Thomas','Burkley',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,1491168322595,1,'0:0:0:0:0:0:0:1','e_test_e','u_test','p_test','Richard','Doobies',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,1491168322595,2,'0:0:0:0:0:0:0:1','e_test_a','u_test','p_test','Johnny','Joints',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,1491189027480,0,'127.0.0.1','','','','Smoked','Pot',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(5,1491194817596,0,'0:0:0:0:0:0:0:1','eee','eee','eee','eee','eee',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,1491214858625,0,'0:0:0:0:0:0:0:1','e','u','p','f','l',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(7,1491214874015,1,'0:0:0:0:0:0:0:1','e2','u','p','f','l',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(8,1491263939241,1,'0:0:0:0:0:0:0:1','a1','a1','p','John','John',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(9,1491263953172,0,'0:0:0:0:0:0:0:1','a2','a2','p','Jimmy','Jim',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(10,1491271967222,0,'0:0:0:0:0:0:0:1','aa','aa','p','aa','aa',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(11,1491272184499,0,'0:0:0:0:0:0:0:1','fwqqwffw','qwfqwffwqw','qwfwqffwq','wqfwqfw','ffqwffwq',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(12,1491277207990,0,'0:0:0:0:0:0:0:1','eg','eg','eg','eg','eg',NULL,NULL,NULL,NULL,NULL,NULL,NULL),(13,1491565887348,0,'0:0:0:0:0:0:0:1','g','g','g','g','g',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `files`
--

DROP TABLE IF EXISTS `files`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `files` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` tinytext NOT NULL,
  `file_path` mediumtext NOT NULL,
  `file_size` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `files`
--

LOCK TABLES `files` WRITE;
/*!40000 ALTER TABLE `files` DISABLE KEYS */;
/*!40000 ALTER TABLE `files` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `job_comments`
--

DROP TABLE IF EXISTS `job_comments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `job_comments` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` bigint(20) NOT NULL,
  `reply_to_id` int(11) DEFAULT NULL,
  `parent_account_id` int(11) NOT NULL,
  `parent_job_id` int(11) NOT NULL,
  `comment` longtext NOT NULL,
  `filepaths` longtext COMMENT 'files identified by their ID and separated by '',''',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `job_comments`
--

LOCK TABLES `job_comments` WRITE;
/*!40000 ALTER TABLE `job_comments` DISABLE KEYS */;
INSERT INTO `job_comments` VALUES (1,1491190463144,NULL,1,1,'gewgweweg','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\NLGJ4XYVIYZA_themixhubonline_LICENSE'),(2,1491201277829,NULL,1,1,'test',NULL),(3,1491201298340,NULL,1,1,'testtest',NULL),(4,1491201356451,NULL,1,1,'file upload test','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\N0T4QWUUDTZB_themixhubonline_LICENSE'),(5,1491213044174,1,2,1,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(6,1491214142296,1,2,1,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(7,1491214914968,NULL,6,2,'Heres my shit song guys make it sound good!','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\EXVLJLV3NXQT_themixhubonline_MixHub2 - Intellij.zip'),(8,1491214979287,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(9,1491215000991,NULL,6,2,'Hey thanks!!!!!',NULL),(10,1491216660717,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(11,1491216664207,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(12,1491216665034,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(13,1491216665852,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(14,1491216666484,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(15,1491216667231,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(16,1491216667956,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(17,1491216669174,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(18,1491216672131,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(19,1491216677548,1,7,2,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(20,1491217002814,NULL,7,2,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(21,1491252907014,NULL,6,2,'Hey I\'d like to request a revision change.',NULL),(22,1491252916739,NULL,6,2,'needs more bass bro',NULL),(23,1491252933440,NULL,7,2,'sure heres the bass bud','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\BWNK7D7SSQG3_themixhubonline_MixHub2 - Intellij.zip'),(24,1491252939168,NULL,7,2,'',NULL),(25,1491253252717,NULL,7,2,'ee',NULL),(26,1491253294063,NULL,7,2,'test',NULL),(27,1491253296649,NULL,7,2,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(28,1491253302150,NULL,6,2,'This is great! I\'m satisfied, Thank you.',NULL),(29,1491263980176,NULL,9,3,'Make the song good','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\1G54A3QOGTBV_themixhubonline_MixHub2 - Intellij.zip'),(30,1491264060182,NULL,8,3,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(31,1491264075016,NULL,8,3,'Hey heres the finished song','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\17Y0FVQUT2HD_themixhubonline_MixHub2 - Intellij.zip'),(32,1491264082369,NULL,8,3,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(33,1491264092003,NULL,9,3,'I want more bass!!!',NULL),(34,1491264093455,NULL,9,3,'Hey I\'d like to request a revision change.',NULL),(35,1491264103099,NULL,8,3,'done','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\13SZWOQXO8KZ_themixhubonline_MixHub2 - Intellij.zip'),(36,1491264104288,NULL,8,3,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(37,1491264110754,NULL,9,3,'Thanks!',NULL),(38,1491264111994,NULL,9,3,'This is great! I\'m satisfied, Thank you.',NULL),(39,1491271981461,NULL,10,4,'aapapa','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\V3GGD64GU960_themixhubonline_MixHub2 - Intellij.zip'),(40,1491277235319,NULL,12,5,'gogogog','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\D3HBJMWZIFBH_themixhubonline_MixHub2 - Intellij.zip'),(41,1491278901853,NULL,1,6,'new one goes here fuck ya','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\LC30OI2MR78P_themixhubonline_MixHub2 - Intellij.zip'),(42,1491279012381,NULL,6,7,'why not','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\YIP4UWAD1I4D_themixhubonline_MixHub2 - Intellij.zip'),(43,1491279031214,-1,7,7,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(44,1491279200782,NULL,6,8,'aint even shit','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\OFXB8G7DTRSF_themixhubonline_MixHub2 - Intellij.zip'),(45,1491280278340,-1,7,8,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(46,1491280279357,-1,7,8,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(47,1491280282785,-1,6,8,'Hey I\'d like to request a revision change.',NULL),(48,1491280286163,-1,7,8,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(49,1491280290479,-1,6,8,'Hey I\'d like to request a revision change.',NULL),(50,1491280294084,-1,7,8,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(51,1491280437780,-1,7,8,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(52,1491280487046,-1,6,8,'Hey I\'d like to request a revision change.',NULL),(53,1491280490662,-1,7,8,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(54,1491289306827,-1,7,8,'hey','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\85ARS24MT7LD_themixhubonline_MixHub2 - Intellij.zip'),(55,1491289554812,-1,7,8,'cool',NULL),(56,1491289561530,-1,7,8,'COOL BRO',NULL),(57,1491289568891,-1,7,8,'&lt;b&gt;COOL AS FUCK&lt;/b&gt;',NULL),(58,1491291583389,-1,7,4,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(59,1491293864034,NULL,6,9,'rehherrheerh','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\WAA4MSUDI7G1_themixhubonline_MixHub2 - Intellij.zip'),(60,1491293867993,-1,6,9,'brebrberbe',NULL),(61,1491293984512,-1,8,9,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(62,1491294237720,-1,8,9,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(63,1491294257341,-1,8,9,'brberbreberber',NULL),(64,1491294261449,-1,8,9,'ewgggggggegwegwgegewegwegw',NULL),(65,1491294267243,-1,6,9,'wegegweggew',NULL),(66,1491294269199,-1,6,9,'wgewegewgewe',NULL),(67,1491294270825,-1,6,9,'wgewgewggew',NULL),(68,1491294272430,-1,6,9,'wgeewew',NULL),(69,1491294275493,-1,8,9,'wegweweg',NULL),(70,1491294278234,-1,8,9,'wegewggewweg',NULL),(71,1491294280137,-1,8,9,'wegegwgwe',NULL),(72,1491294286510,-1,8,6,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(73,1491294288853,-1,8,6,'wegewgew',NULL),(74,1491294291199,-1,8,6,'ewgweewgweg',NULL),(75,1491294295746,-1,8,6,'','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\FLTG8PUGK7AF_themixhubonline_MixHub2 - Intellij.zip'),(76,1491294298480,-1,8,6,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(77,1491294309963,-1,8,9,'egwewgegwegwewg',NULL),(78,1491294311988,-1,8,9,'ewgewg',NULL),(79,1491294314996,-1,6,9,'Hey I\'d like to request a revision change.',NULL),(80,1491294319549,-1,8,9,'egwgewe',NULL),(81,1491294364727,-1,8,9,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(82,1491294368128,-1,6,9,'Hey I\'d like to request a revision change.',NULL),(83,1491294370869,-1,8,9,'I\'ve completed the review, after 3 revisions I am now automatically closing this.',NULL),(84,1491297015267,-1,9,3,'hi',NULL),(85,1491297044742,-1,8,9,'ey',NULL),(86,1491297048393,-1,8,9,'not gud\r\n',NULL),(87,1491297057271,-1,8,9,'test3etst',NULL),(88,1491297858224,-1,8,5,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(89,1491297945378,-1,6,9,'hey',NULL),(90,1491563946186,NULL,1,10,'COMMENT','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\7GM7MXO85IY9_themixhubonline_MixHub2 - Intellij.zip'),(91,1491566028564,-1,2,10,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL),(92,1491566104086,-1,2,10,'I\'ve completed the review, it\'s now up to you to decide if you want anything changed or to close the ticket.',NULL),(93,1491566308211,NULL,13,11,'wegegwewgegwweg','C:\\Users\\John\\Desktop\\Github\\MixHub2 - Intellij\\uploads\\5M9P8YF56WSM_themixhubonline_moneys_idea.txt'),(94,1491566409908,-1,2,11,'Hello, I\'m your mix hub engineer! I am currently reviewing your submitted mix.',NULL);
/*!40000 ALTER TABLE `job_comments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jobs`
--

DROP TABLE IF EXISTS `jobs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jobs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner_id` int(11) NOT NULL,
  `engineer_id` int(11) DEFAULT NULL,
  `title` mediumtext NOT NULL,
  `date` bigint(20) NOT NULL,
  `stage` int(11) NOT NULL DEFAULT '0',
  `anonymous_engineer` int(11) DEFAULT '0',
  `last_activity_date` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jobs`
--

LOCK TABLES `jobs` WRITE;
/*!40000 ALTER TABLE `jobs` DISABLE KEYS */;
INSERT INTO `jobs` VALUES (1,1,7,'ewgwe',1491190463144,1,0,0),(2,6,7,'My Shit Song',1491214914968,99,0,0),(3,9,7,'Test Job',1491263980176,99,0,0),(4,10,7,'aaa',1491271981461,1,1,0),(5,12,8,'eg want good shit',1491277235319,1,1,1491297858164),(6,1,8,'cool man',1491278901853,2,0,1491294298458),(7,6,7,'another shit song',1491279012381,1,0,0),(8,6,7,'anotehr one',1491279200782,98,0,0),(9,6,8,'rhrehhr',1491293864034,98,1,1491294370848),(10,1,2,'TITLE',1491563946186,2,0,1491566104065),(11,13,2,'weggweewg',1491566308211,1,1,1491566409867);
/*!40000 ALTER TABLE `jobs` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-04-07  7:16:19
