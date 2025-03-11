package com.wp.TmallMarket.service.impl;

import com.wp.TmallMarket.dao.UserRepository;
import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
