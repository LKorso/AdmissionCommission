-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: admission_commision
-- ------------------------------------------------------
-- Server version	5.7.13-log

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
-- Table structure for table `applicant_mark`
--

DROP DATABASE IF EXISTS admission_commision;

CREATE DATABASE admission_commision;

USE admission_commision;

DROP TABLE IF EXISTS `applicant_mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `applicant_mark` (
  `applicant_id` int(10) unsigned NOT NULL,
  `subject_id` int(10) unsigned NOT NULL,
  `mark` decimal(5,2) unsigned NOT NULL,
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `FK_Score_Subject_idx` (`subject_id`),
  KEY `FK_Mark_Applicant_idx` (`applicant_id`),
  CONSTRAINT `FK_Mark_Applicant` FOREIGN KEY (`applicant_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Mark_Subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant_mark`
--

LOCK TABLES `applicant_mark` WRITE;
/*!40000 ALTER TABLE `applicant_mark` DISABLE KEYS */;
INSERT INTO `applicant_mark` VALUES (2,1,130.25,1),(2,3,195.54,2),(2,6,194.00,3),(2,13,189.75,4),(3,1,115.80,5),(3,3,187.50,6),(3,6,196.55,7),(3,13,175.54,8),(4,1,110.54,9),(4,3,200.00,10),(4,6,200.00,11),(4,13,174.20,12),(5,1,130.50,13),(5,3,186.34,14),(5,7,198.54,15),(5,13,190.00,16),(8,13,200.00,17),(8,3,150.00,18),(8,1,175.36,30),(8,6,160.45,31),(10,13,200.00,49),(10,8,200.00,50),(10,3,200.00,51),(10,6,200.00,52),(10,7,200.00,53),(5,6,160.00,54),(11,13,175.00,55),(11,1,160.00,56),(11,3,160.00,57),(11,6,160.00,58);
/*!40000 ALTER TABLE `applicant_mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application`
--

DROP TABLE IF EXISTS `application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `filling_date` date NOT NULL,
  `applicant_id` int(10) unsigned NOT NULL,
  `faculty_id` int(10) unsigned NOT NULL,
  `status_id` int(10) unsigned NOT NULL,
  `description` text,
  `priority_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Application_Faculty_idx` (`faculty_id`),
  KEY `FK_ApplicationStatus_Application_idx` (`status_id`),
  KEY `FK_Application_Applicant_idx` (`applicant_id`),
  KEY `FK_Application_Priority_idx` (`priority_id`),
  CONSTRAINT `FK_ApplicationStatus_Application` FOREIGN KEY (`status_id`) REFERENCES `application_status` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Application_Applicant` FOREIGN KEY (`applicant_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `FK_Application_Faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_Application_Priority` FOREIGN KEY (`priority_id`) REFERENCES `priority` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application`
--

LOCK TABLES `application` WRITE;
/*!40000 ALTER TABLE `application` DISABLE KEYS */;
INSERT INTO `application` VALUES (1,'2016-07-14',2,15,2,NULL,1),(2,'2016-07-14',2,7,2,NULL,2),(3,'2016-07-14',3,15,2,NULL,2),(4,'2016-07-14',3,7,2,NULL,1),(5,'2016-07-14',4,7,2,NULL,1),(6,'2016-07-14',4,15,2,NULL,2),(7,'2016-07-14',5,16,2,NULL,1),(23,'2016-07-28',5,19,2,NULL,2),(30,'2016-07-31',8,7,2,NULL,1),(42,'2016-07-31',8,13,2,NULL,2),(44,'2016-08-03',5,13,2,NULL,3),(45,'2016-08-03',11,5,2,NULL,1);
/*!40000 ALTER TABLE `application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_mark`
--

DROP TABLE IF EXISTS `application_mark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_mark` (
  `application_id` int(10) unsigned NOT NULL,
  `mark_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`application_id`,`mark_id`),
  KEY `FK_ApplicationMark_ApplicantMark_idx` (`mark_id`),
  CONSTRAINT `FK_ApplicationMark_ApplicantMark` FOREIGN KEY (`mark_id`) REFERENCES `applicant_mark` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_ApplicationMark_Application` FOREIGN KEY (`application_id`) REFERENCES `application` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_mark`
--

LOCK TABLES `application_mark` WRITE;
/*!40000 ALTER TABLE `application_mark` DISABLE KEYS */;
INSERT INTO `application_mark` VALUES (1,1),(2,1),(1,2),(2,2),(1,3),(2,3),(1,4),(2,4),(3,5),(4,5),(3,6),(4,6),(3,7),(4,7),(3,8),(4,8),(5,9),(6,9),(5,10),(6,10),(5,11),(6,11),(5,12),(6,12),(7,13),(23,13),(44,13),(7,14),(23,14),(44,14),(7,15),(23,15),(7,16),(23,16),(44,16),(30,17),(42,17),(30,18),(42,18),(30,30),(42,30),(30,31),(42,31),(44,54),(45,55),(45,56),(45,57),(45,58);
/*!40000 ALTER TABLE `application_mark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `application_status`
--

DROP TABLE IF EXISTS `application_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `application_status` (
  `id` int(10) unsigned NOT NULL,
  `status` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `application_status`
--

LOCK TABLES `application_status` WRITE;
/*!40000 ALTER TABLE `application_status` DISABLE KEYS */;
INSERT INTO `application_status` VALUES (1,'Unreviewed'),(2,'Passed'),(3,'Accepted'),(4,'Rejected'),(5,'Unaccepted');
/*!40000 ALTER TABLE `application_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `extended_application`
--

DROP TABLE IF EXISTS `extended_application`;
/*!50001 DROP VIEW IF EXISTS `extended_application`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `extended_application` AS SELECT 
 1 AS `user_id`,
 1 AS `last_name`,
 1 AS `first_name`,
 1 AS `date_of_birth`,
 1 AS `sex`,
 1 AS `phone`,
 1 AS `email`,
 1 AS `faculty_name`,
 1 AS `faculty_id`,
 1 AS `status`,
 1 AS `status_id`,
 1 AS `filling_date`,
 1 AS `description`,
 1 AS `application_id`,
 1 AS `priority`,
 1 AS `rating`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `faculty`
--

DROP TABLE IF EXISTS `faculty`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `students_number` int(10) unsigned NOT NULL,
  `short_name` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty`
--

LOCK TABLES `faculty` WRITE;
/*!40000 ALTER TABLE `faculty` DISABLE KEYS */;
INSERT INTO `faculty` VALUES (1,'Institute for Applied System Analysis',60,'IASA'),(2,'Institute of Telecommunication Systems',60,'ITS'),(3,'Faculty of Applied Mathematics',60,'FAM'),(4,'Faculty of Informatics and Computer Science',60,'FICS'),(5,'Faculty of Electronics',60,'FE'),(6,'Faculty of Radio Engineering',60,'FRE'),(7,'Institute of Physics and Technology',2,'IPhT'),(8,'Faculty of Heat and Power Engineering ',60,'FHPE'),(9,'Faculty of Electric Power Engineering and Automatics',60,'FEPEA'),(10,'Institute of Energy Saving and Energy Management',60,'IESEM'),(11,'Institute of Mechanical Engineering',60,'IME'),(12,'Faculty of Instrumentation Engineering',60,'FIE'),(13,'Faculty of Aerospace Systems',60,'FASS'),(14,'Faculty of Welding',60,'FW'),(15,'Faculty of Physical Engineering',2,'FPhE'),(16,'Faculty of Chemical Technology ',60,'FChT'),(17,'Faculty of Chemical Engineering',60,'FChE'),(18,'Faculty of Physics and Mathematics',60,'FPhM'),(19,'Faculty of Biomedical Engineering',60,'FBE'),(20,'Faculty of Biotechnology and Biotechnics',60,'FBB'),(21,'Institute of Publishing and Printing',60,'IPP'),(22,'Faculty of Management and Marketing',60,'FMM'),(23,'Faculty of Sociology and Law ',60,'FSL'),(24,'Faculty of Linguistics',60,'FL');
/*!40000 ALTER TABLE `faculty` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `faculty_subject`
--

DROP TABLE IF EXISTS `faculty_subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `faculty_subject` (
  `faculty_id` int(10) unsigned NOT NULL,
  `subject_id` int(10) unsigned NOT NULL,
  `min_mark` decimal(5,2) unsigned NOT NULL,
  PRIMARY KEY (`faculty_id`,`subject_id`),
  KEY `FK_FacultySubject_Faculty_idx` (`faculty_id`),
  KEY `FK_FacultySubject_Subject_idx` (`subject_id`),
  CONSTRAINT `FK_FacultySubject_Faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_FacultySubject_Subject` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `faculty_subject`
--

LOCK TABLES `faculty_subject` WRITE;
/*!40000 ALTER TABLE `faculty_subject` DISABLE KEYS */;
INSERT INTO `faculty_subject` VALUES (1,1,100.00),(1,3,140.00),(1,6,140.00),(2,1,100.00),(2,3,140.00),(2,6,140.00),(3,1,100.00),(3,3,140.00),(3,6,140.00),(4,1,100.00),(4,3,140.00),(4,6,140.00),(5,1,100.00),(5,3,140.00),(5,6,140.00),(6,1,100.00),(6,3,140.00),(6,6,140.00),(7,1,100.00),(7,3,140.00),(7,6,140.00),(8,1,100.00),(8,3,140.00),(8,6,140.00),(9,1,100.00),(9,3,140.00),(9,6,140.00),(10,1,100.00),(10,3,140.00),(10,7,140.00),(11,1,100.00),(11,3,140.00),(11,6,140.00),(12,1,100.00),(12,3,140.00),(12,6,140.00),(13,1,100.00),(13,3,140.00),(13,6,140.00),(14,1,100.00),(14,3,140.00),(14,6,140.00),(15,1,100.00),(15,3,140.00),(15,7,140.00),(16,1,100.00),(16,3,140.00),(16,7,140.00),(17,1,100.00),(17,3,140.00),(17,7,140.00),(18,1,100.00),(18,3,140.00),(18,6,140.00),(19,1,100.00),(19,3,140.00),(19,7,140.00),(20,1,100.00),(20,4,140.00),(20,7,140.00),(21,1,140.00),(21,2,140.00),(21,3,140.00),(22,1,100.00),(22,3,140.00),(22,5,140.00),(23,1,100.00),(23,2,140.00),(23,8,140.00),(24,1,140.00),(24,2,100.00),(24,8,140.00);
/*!40000 ALTER TABLE `faculty_subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `priority`
--

DROP TABLE IF EXISTS `priority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `priority` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `priority` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `priority`
--

LOCK TABLES `priority` WRITE;
/*!40000 ALTER TABLE `priority` DISABLE KEYS */;
INSERT INTO `priority` VALUES (1,1),(2,2),(3,3);
/*!40000 ALTER TABLE `priority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subject` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'Ukranian'),(2,'History of Ukraine'),(3,'Math'),(4,'Biology'),(5,'Geography'),(6,'Physics'),(7,'Chemistry'),(8,'English'),(9,'Spanish'),(10,'German'),(11,'Russian'),(12,'French'),(13,'Certificate');
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `last_name` varchar(45) NOT NULL,
  `first_name` varchar(45) NOT NULL,
  `date_of_birth` date NOT NULL,
  `sex` enum('M','W') NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `faculty_id` int(10) unsigned DEFAULT NULL,
  `user_type_id` int(10) unsigned NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_User_Faculty_idx` (`faculty_id`),
  KEY `FK_UserType_User_idx` (`user_type_id`),
  CONSTRAINT `FK_UserType_User` FOREIGN KEY (`user_type_id`) REFERENCES `user_type` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_User_Faculty` FOREIGN KEY (`faculty_id`) REFERENCES `faculty` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Skrypnyk','Dmytro','1993-10-23','M','skrypnykdr@gmail.com','80674533856',13,3,'22222222'),(2,'Allen','Barry','1996-10-04','M','flash@starlabs.com','80672200000',NULL,2,'22222222'),(3,'Parker','Peter','1999-08-15','M','spidy@dailybugle.net','80953210065',NULL,2,'33333333'),(4,'Ramone','Francisco ','1998-04-20','M','vibe@starlabs.com','80636874516',NULL,2,'44444444'),(5,'Stacy','Gwendolyn','1998-09-15','W','gwen@oscorp.net','80664654668',NULL,2,'55555555'),(6,'Administrator','Admin','1980-01-23','W','admin@gmail.com','80674509857',NULL,1,'23232323'),(7,'Name','User','1998-05-20','W','user@gmail.com','80674213891',NULL,2,'77777777'),(8,'Applicant','Applicant','1995-09-03','M','root@gmail.com','80506351277',NULL,2,'rootroot'),(10,'Wayn','Bruce','1991-03-01','M','bat@wayn.com','80675489023',NULL,2,'batbatbat'),(11,'Kafka','Franz','1996-05-14','M','kafka@gmail.com','8054963210',NULL,2,'00000000'),(13,'Admin','Adminsky','1986-08-04','W','ad@gmail.com','80677532111',NULL,1,'99999999'),(14,'NewAdmin','Admin','1980-03-20','M','a@gmail.com','80652315000',NULL,1,'66666666');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_type`
--

DROP TABLE IF EXISTS `user_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_type` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `type` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_type`
--

LOCK TABLES `user_type` WRITE;
/*!40000 ALTER TABLE `user_type` DISABLE KEYS */;
INSERT INTO `user_type` VALUES (1,'Administrator'),(2,'Applicant'),(3,'Student');
/*!40000 ALTER TABLE `user_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `extended_application`
--

/*!50001 DROP VIEW IF EXISTS `extended_application`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `extended_application` AS (select `u`.`id` AS `user_id`,`u`.`last_name` AS `last_name`,`u`.`first_name` AS `first_name`,`u`.`date_of_birth` AS `date_of_birth`,`u`.`sex` AS `sex`,`u`.`phone` AS `phone`,`u`.`email` AS `email`,`f`.`short_name` AS `faculty_name`,`f`.`id` AS `faculty_id`,`s`.`status` AS `status`,`s`.`id` AS `status_id`,`a`.`filling_date` AS `filling_date`,`a`.`description` AS `description`,`a`.`id` AS `application_id`,`p`.`priority` AS `priority`,avg(`am`.`mark`) AS `rating` from ((((((`application` `a` join `user` `u` on((`u`.`id` = `a`.`applicant_id`))) join `faculty` `f` on((`f`.`id` = `a`.`faculty_id`))) join `application_status` `s` on((`s`.`id` = `a`.`status_id`))) join `priority` `p` on((`p`.`id` = `a`.`priority_id`))) join `application_mark` on((`application_mark`.`application_id` = `a`.`id`))) join `applicant_mark` `am` on((`am`.`id` = `application_mark`.`mark_id`))) group by `a`.`id`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-08-15 15:43:19
