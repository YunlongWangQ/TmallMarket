package com.wp.TmallMarket.controller;

import com.wp.TmallMarket.dao.UserRepository;
import com.wp.TmallMarket.entity.Password;
import com.wp.TmallMarket.vo.RegisterInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.response.UserResponse;
import com.wp.TmallMarket.service.UserService;
import com.wp.TmallMarket.util.TMallUtils;
import com.wp.TmallMarket.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 功能：登录界面
     *
     * @author 王云龙
     * @date 2025-03-12 19:36
     *
     **/
    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
    /**
     * 功能：跳转到主页
     *
     * @author 王云龙
     * @date 2025-03-20 10:49
     *
     **/
    @GetMapping("/userMainPage")
    public String showMainPage() {
        return "userMainPage";
    }

    /**
     * 功能：跳转到注册界面
     *
     * @author 王云龙
     * @date 2025-03-20 10:49
     *
     **/
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
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
    /**
     * 功能：登录验证，登陆成功则跳转到用户主界面；否则刷新当前页
     *
     * @author 王云龙
     * @date 2025-03-20 10:51
     * 
     * @param1 username
     * @param2 password
     *
     **/
    @PostMapping("/login")
    private String login(@RequestParam String username, @RequestParam String password){
        String sha256pwd = TMallUtils.sha256(password);
        boolean isExist = userService.validateUser(username, sha256pwd);
        return isExist ? "userMainPage" : "login";
    }
    /**
     * 功能：注册功能
     *
     * @author 王云龙
     * @date 2025-03-20 10:52
     * 
     * @param1 registerInfo
     *
     **/
    @PostMapping("/register")
    @ResponseBody
    public UserResponse register(@RequestBody RegisterInfo registerInfo) {
        User user = new User();
        BeanUtils.copyProperties(registerInfo,user);
        String sha256pwd = TMallUtils.sha256(registerInfo.getPassword());
        user.setPassword(sha256pwd);
        user.setAge(TMallUtils.getAgeByBirthday(registerInfo.getBirthday()));
        Long userId = userService.saveUser(user);
        savePwd(userId,registerInfo.getPassword());
        return !Objects.equals(userId, Long.valueOf(-1L)) ? UserResponse.newSuccessResponse(List.of("注册成功")):UserResponse.newSuccessResponse(List.of("注册失败"));
    }

    /**
     * 功能：保存密码到密码表
     *
     * @author 王云龙
     * @date 2025-03-20 10:52
     * 
     * @param1 userId
     * @param2 original_pwd
     *
     **/
    private void savePwd(Long userId,String original_pwd)
    {
        Password password = new Password();
        password.setOriginal_pwd(original_pwd);
        password.setEncrypted_password(TMallUtils.sha256(original_pwd));
        password.setId(userId);
        userService.savePwd(List.of(password));
    }

}
