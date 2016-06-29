CREATE TABLE `services` (
  `service_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `accessories` varchar(255) DEFAULT NULL,
  `adder` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `contact` varchar(255) DEFAULT NULL,
  `damage` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `employee` varchar(255) DEFAULT NULL,
  `finish_date` date DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `product` varchar(255) DEFAULT NULL,
  `recommendations` varchar(255) DEFAULT NULL,
  `sell_date` date DEFAULT NULL,
  `serial_number` varchar(255) DEFAULT NULL,
  `service` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `warranty` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`service_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `password` varchar(256) NOT NULL,
  `permission` varchar(225) DEFAULT NULL,
  `email` varchar(30) NOT NULL,
  `enabled` varchar(45) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

INSERT INTO `users` VALUES (1,'jblack','John','Black','1234','ROLE_ADMIN','john.black@silverpoint.pl','1'),(2,'awood','Alice','Wood','mrWood','ROLE_USER','alice.wood@silverpoint.pl','1');
