package com.ligy.user.service;

import com.ligy.pojo.User;
import com.ligy.user.dao.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Author lgy
 */
@Service
public class UserService {
    @Resource
    private UserMapper userMapper;

    public String login(User user){
        User oldUser = userMapper.queryByUserNameAndPassWord(user);
        if (oldUser != null) {
            oldUser.setUums(UUID.randomUUID().toString());
            userMapper.update(oldUser);
            return oldUser.getUums();
        }
        return "账号或密码错误！";
    }

    public User uums(String uums){
        return userMapper.queryByUums(uums);
    }

    public User queryById(Integer id){
        return userMapper.queryById(id);
    }
}
