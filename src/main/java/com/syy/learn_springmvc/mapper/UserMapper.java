package com.syy.learn_springmvc.mapper;

import com.syy.learn_springmvc.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserMapper {
    @Select("SELECT id,username,password FROM user WHERE username = #{username} AND password = #{password}")
    User login(User user) throws SQLException;
}
