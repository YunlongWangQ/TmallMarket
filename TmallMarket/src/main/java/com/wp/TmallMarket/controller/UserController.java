package com.wp.TmallMarket.controller;

import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.response.UserResponse;
import com.wp.TmallMarket.service.UserService;
import com.wp.TmallMarket.util.TMallUtils;
import com.wp.TmallMarket.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public UserResponse<UserVo> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        List<UserVo> userVos = new ArrayList<>();
        for(User user : allUsers){
            userVos.add(TMallUtils.User2VoConverter(user));
        }
        return UserResponse.newSuccessResponse(userVos);
    }

    @PostMapping("adduser")
    public UserResponse<Long> addNewUser(@RequestBody UserVo userVo){
        return UserResponse.newSuccessResponse(List.of(userService.SaveUser(userVo)));
    }
}
