USE Software_Project_NewsCompany;

--
-- Table structure for table `monthly_invoice`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `monthly_invoice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `amount_to_pay` varchar(45) DEFAULT NULL,
  `payment_date` varchar(45) DEFAULT NULL,
  `customer_id` int(45) DEFAULT NULL,
  PRIMARY KEY (`id`)  -- This line defines the primary key
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monthly_invoice`
--
