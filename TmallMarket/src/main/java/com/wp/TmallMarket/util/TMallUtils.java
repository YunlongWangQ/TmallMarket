package com.wp.TmallMarket.util;

import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.response.UserResponse;
import com.wp.TmallMarket.vo.UserVo;
import org.thymeleaf.util.DateUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
public class TMallUtils {

    public static UserVo User2VoConverter(User user){
        UserVo userVo = new UserVo();
        userVo.setName(user.getName());
        userVo.setEmail(user.getEmail());
        userVo.setAddress(user.getAddress());
        userVo.setAge(user.getAge());
        userVo.setPhone(user.getPhone());
        userVo.setGender(user.getGender());
        userVo.setPassword(user.getPassword());
        return userVo;
    }

    public static User Vo2UserConverter(UserVo userVo) {
        User user = new User();
        user.setAddress(userVo.getAddress());
        user.setEmail(userVo.getEmail());
        user.setName(userVo.getName());
        user.setAge(userVo.getAge());
        return user;
    }

    public static String sha256(String input) {
        try {
            // 获取 SHA-256 算法实例
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            // 计算哈希值
            byte[] messageDigest = md.digest(input.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
	}

    public static Integer getAgeByBirthday(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        // 先计算年份差值
        int age = Period.between(birthDate, currentDate).getYears();

        // 检查是否已过生日
        boolean hasBirthdayOccurred = (currentDate.getMonthValue() > birthDate.getMonthValue())
                || (currentDate.getMonthValue() == birthDate.getMonthValue()
                && currentDate.getDayOfMonth() >= birthDate.getDayOfMonth());

        return hasBirthdayOccurred ? age : age - 1;

    }
}
