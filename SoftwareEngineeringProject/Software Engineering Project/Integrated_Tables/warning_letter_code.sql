USE Software_Project_NewsCompany;

--
-- Table structure for table `warning_letter`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `warning_letter` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customer_id` int(12) DEFAULT NULL,
  `darea_id` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)  -- This line defines the primary key
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `warning_letter`
--