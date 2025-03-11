package com.wp.TmallMarket.util;

import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.response.UserResponse;
import com.wp.TmallMarket.vo.UserVo;

public class TMallUtils {

    public static UserVo User2VoConverter(User user){
        UserVo userVo = new UserVo();
        userVo.setName(user.getName());
        userVo.setEmail(user.getEmail());
        userVo.setAddress(user.getAddress());
        return userVo;
    }

    public static User Vo2UserConverter(UserVo userVo) {
        User user = new User();
        user.setAddress(userVo.getAddress());
        user.setEmail(userVo.getEmail());
        user.setName(userVo.getName());
        return user;
    }
}
