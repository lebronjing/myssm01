package com.self.IDao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.self.domain.User;

public interface IUserDao {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    
    User findUser(@Param("name")String name,@Param("pwd")String pwd);
    
    List<User> findUserByName(@Param("name")String name);
}