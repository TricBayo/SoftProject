USE Software_Project_NewsCompany;

--
-- Table structure for table `delivery_docket`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `delivery_docket` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_date` varchar(45) DEFAULT NULL,
  `track_number` int(12) DEFAULT NULL,
  `delivery_status` int(12) DEFAULT NULL,
  `customer_id` int(12) DEFAULT NULL,
  `delivery_person_id` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`)  -- This line defines the primary key
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `delivery_docket`
--
