package com.wp.TmallMarket.service;

import com.wp.TmallMarket.dao.UserRepository;
import com.wp.TmallMarket.entity.User;
import com.wp.TmallMarket.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public List<User> getAllUsers() ;

    Long SaveUser(UserVo userVo);
}
