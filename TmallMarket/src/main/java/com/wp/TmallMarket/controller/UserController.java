package com.wp.TmallMarket.controller;

import com.wp.TmallMarket.dao.UserRepository;
import com.wp.TmallMarket.vo.RegisterInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.response.UserResponse;
import com.wp.TmallMarket.service.UserService;
import com.wp.TmallMarket.util.TMallUtils;
import com.wp.TmallMarket.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 功能：登录界面
     *
     * @author 王云龙
     * @date 2025-03-12 19:36
     * @param2 param2
     *
     **/
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
    /**
     * 功能：查询所有的user
     *
     * @author 王云龙
     * @date 2025-03-12 15:18
     *
     **/
    @GetMapping("/users")
    public UserResponse<UserVo> getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        List<UserVo> userVos = new ArrayList<>();
        for(User user : allUsers){
            userVos.add(TMallUtils.User2VoConverter(user));
        }
        return UserResponse.newSuccessResponse(userVos);
    }

    /**
     * 功能：新增user
     *
     * @author 王云龙
     * @date 2025-03-12 15:17
     *
     * @param1 userVo
     *
     **/
    @PostMapping("/adduser")
    public UserResponse<Long> addNewUser(@RequestBody UserVo userVo){
        return UserResponse.newSuccessResponse(List.of(userService.saveUser(userVo)));
    }

    /**
     * 功能：查询所有的user
     *
     * @author 王云龙
     * @date 2025-03-12 15:17
     *
     * @param1 model
     *
     **/
    @GetMapping("/user-list")
    public String showAllUsers(Model model){
        List<User> allUsers = userService.getAllUsers();
        List<UserVo> userVos = new ArrayList<>();
        for(User user : allUsers){
            userVos.add(TMallUtils.User2VoConverter(user));
        }
        model.addAttribute("alluser", userVos);
        return "users";
    }
    /**
     * 功能：根据id删除对应的user
     *
     * @author 王云龙
     * @date 2025-03-12 15:17
     *
     * @param1 id
     *
     **/
    @PatchMapping("deleteUser")
    public UserResponse<UserVo> deleteUsers(@RequestParam Long id){
        userService.deleteUser(id);
        return null;
    }

    @PostMapping("/login")
    private String login(@RequestParam String username, @RequestParam String password){
        String sha256pwd = TMallUtils.sha256(password);
        boolean isExist = userService.validateUser(username, sha256pwd);
        return isExist ? "userMainPage" : "login";
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterInfo registerInfo) {
        User user = new User();
        user.setName(registerInfo.getUsername());
        String sha256pwd = TMallUtils.sha256(registerInfo.getPassword());
        user.setPassword(sha256pwd);
        user.setEmail(registerInfo.getEmail());
        Long l = userService.saveUser(TMallUtils.User2VoConverter(user));
        return "注册成功，用户名："+l + registerInfo.getUsername();
    }
}
