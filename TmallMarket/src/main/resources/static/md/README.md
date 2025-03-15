# 数据库配置
## mysql：
### 数据库名:sys
### 用户表名:user
### 建表DDL:
    CREATE TABLE `user` (
    `id` int NOT NULL AUTO_INCREMENT,
    `name` varchar(100) DEFAULT NULL,
    `email` varchar(100) DEFAULT NULL,
    `address` varchar(255) DEFAULT NULL,
    `age` int NOT NULL,
    `gender` varchar(255) DEFAULT NULL,
    `phone` varchar(255) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
### 数据：
    INSERT INTO `user` (`id`,`name`,`email`,`address`,`age`,`gender`,`phone`) VALUES (1,'John Doe','john.doe@example.com',NULL,0,NULL,NULL);
    INSERT INTO `user` (`id`,`name`,`email`,`address`,`age`,`gender`,`phone`) VALUES (2,'Jane Smith','jane.smith@example.com',NULL,0,NULL,NULL);

### 注解：
#### @Data && @Entity
    @Data和@Entity搭配使用，简化了@Getter和@Setter注解以及toString()方法
#### @Table
    @Table可以和数据库中的表进行映射，一般来说会根据Bean的名字去找同名的表，找不到的话该注解可以映射到具体的表，
    schema属性值可以指定在那个schema下，schema = "sys"
    uniqueConstraints = @UniqueConstraint(columnNames = {"id"})可以指定主键



