# 数据库配置
## mysql：
### 数据库名:sys
### 用户表名:user
### 建表DDL:
#### CREATE TABLE `user` (
#### `id` int NOT NULL AUTO_INCREMENT,
#### `name` varchar(100) DEFAULT NULL,
#### `email` varchar(100) DEFAULT NULL,
#### `address` varchar(255) DEFAULT NULL,
#### `age` int NOT NULL,
#### `gender` varchar(255) DEFAULT NULL,
#### `phone` varchar(255) DEFAULT NULL,
#### PRIMARY KEY (`id`)
#### ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
### 数据：
###### INSERT INTO `user` (`id`,`name`,`email`,`address`,`age`,`gender`,`phone`) VALUES (1,'John Doe','john.doe@example.com',NULL,0,NULL,NULL);
###### INSERT INTO `user` (`id`,`name`,`email`,`address`,`age`,`gender`,`phone`) VALUES (2,'Jane Smith','jane.smith@example.com',NULL,0,NULL,NULL);



