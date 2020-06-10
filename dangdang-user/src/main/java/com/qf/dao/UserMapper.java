package com.qf.dao;

import com.qf.pojo.TrAccount;
import com.qf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Created by 54110 on 2020/6/4.
 */
@Mapper
public interface UserMapper {

    void insertUser(User user);

    User findByUserName(@Param("username") String username);

}
