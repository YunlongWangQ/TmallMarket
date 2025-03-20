package com.wp.TmallMarket.service.impl;

import com.wp.TmallMarket.dao.PassWordRepository;
import com.wp.TmallMarket.dao.UserRepository;
import com.wp.TmallMarket.entity.Password;
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

    @Autowired
    private PassWordRepository pwdRepository;
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Long saveUser(User user) {
        User save = userRepository.save(user);
        return user.getId();
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

    @Override
    public void savePwd(List<Password> passwords)
    {
        pwdRepository.saveAll(passwords);
    }
}
