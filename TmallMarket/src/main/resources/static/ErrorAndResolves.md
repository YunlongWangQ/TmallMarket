### 1.编译过程中一直报错：找不到符号
    java: 找不到符号
    符号:   方法 getId()
    位置: 类型为com.wp.TmallMarket.entity.User的变量 user
#### 原因：pom文件中配置了如下内容，导致无法找到对应的lombok编译器，User上的@Data注解无法生效
    <exclude>
	    <groupId>org.projectlombok</groupId>
	    <artifactId>lombok</artifactId>
	</exclude>