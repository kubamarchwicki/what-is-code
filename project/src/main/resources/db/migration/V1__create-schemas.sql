CREATE TABLE `translation_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cnt` int(11) DEFAULT NULL,
  `polish_word` varchar(255) DEFAULT NULL,
  `timestamp` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;