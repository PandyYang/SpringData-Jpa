# SpringData-Jpa  
## 数据库创建语句   
```
CREATE TABLE `customer` (
  `cust_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cust_address` varchar(255) DEFAULT NULL,
  `cust_industry` varchar(255) DEFAULT NULL,
  `cust_level` varchar(255) DEFAULT NULL,
  `cust_name` varchar(255) DEFAULT NULL,
  `cust_phone` varchar(255) DEFAULT NULL,
  `cust_source` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
```
