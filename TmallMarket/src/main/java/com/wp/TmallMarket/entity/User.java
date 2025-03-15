package com.wp.TmallMarket.entity;

import jakarta.persistence.*;
import lombok.Data;

import static jakarta.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = User.TableName)
public class User {

    public static final  String TableName        = "user";
    /** 用户id */
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    /** 用户名 */
    private String name;
    /** 邮箱 */
    private String email;
    /** 地址 */
    private String address;
    /** 年龄 */
    private Integer age;
    /** 性别 */
    private String gender;
    /** 手机号码 */
    private String phone;
    /** 登录密码 */
    private String password;
}
