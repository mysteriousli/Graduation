package com.ligy.user.dao;

import com.ligy.pojo.User;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    int deleteById(Integer id);

    int insert(User record);

    User queryByUserNameAndPassWord(User user);

    User queryByUums(String uums);

    User queryById(Integer id);

    int update(User record);
}