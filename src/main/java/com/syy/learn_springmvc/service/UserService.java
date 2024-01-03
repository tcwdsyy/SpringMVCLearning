package com.syy.learn_springmvc.service;

import com.syy.learn_springmvc.entity.User;
import com.syy.learn_springmvc.mapper.UserMapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User login(User user) throws SQLException {
        return userMapper.login(user);
    }
}
