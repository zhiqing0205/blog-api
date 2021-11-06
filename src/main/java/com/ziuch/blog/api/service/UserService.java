package com.ziuch.blog.api.service;

import com.ziuch.blog.api.domain.User;
import com.ziuch.blog.api.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public List<User> list(){
        return userMapper.list();
    }
}
