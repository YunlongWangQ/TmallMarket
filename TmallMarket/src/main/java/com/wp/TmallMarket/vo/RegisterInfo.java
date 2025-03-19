package com.wp.TmallMarket.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;
@Data
@Entity
public class RegisterInfo {
    @Id
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer   age;
    private LocalDate birthday;
    private String address;
    private String gender;
}
