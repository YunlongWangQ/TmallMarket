package com.wp.TmallMarket.service.impl;

import com.wp.TmallMarket.dao.UserRepository;
import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.service.UserService;
import com.wp.TmallMarket.util.TMallUtils;
import com.wp.TmallMarket.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired(required=false)
    private UserRepository userRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Long SaveUser(UserVo userVo) {
        List<User> allUsers = userRepository.findByEmail(userVo.getEmail());
        User user = userRepository.save(TMallUtils.Vo2UserConverter(userVo));
        return CollectionUtils.isEmpty(allUsers) ? user.getId() : -1;
    }

    @Override
    public void deleteUser(Long id)
    {
        userRepository.deleteById(id);
    }

    @Override
    public boolean validateUser(String name, String password)
    {
        User user = userRepository.findByNameAndPassword(name, password);
        return user != null;
    }
}
