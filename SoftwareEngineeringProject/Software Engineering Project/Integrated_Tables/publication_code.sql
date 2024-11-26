USE Software_Project_NewsCompany;

--
-- Table structure for table `publication`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `publication` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `publication_name` varchar(45) DEFAULT NULL,
  `publication_date` varchar(45) DEFAULT NULL,
  `stock_amount` int(12) DEFAULT NULL,
  `edition_price` double DEFAULT NULL,
  PRIMARY KEY (`id`)  -- This line defines the primary key
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication`
--
