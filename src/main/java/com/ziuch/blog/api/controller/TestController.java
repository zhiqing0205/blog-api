package com.ziuch.blog.api.controller;

import com.ziuch.blog.api.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/testApi")
public class TestController {

    @Value("${test.hello:defaultValue}")
    private String helloWorld;

    @Resource
    private UserService userService;

    @GetMapping("/hello")
    public String hello(){
        return "Hello world " + helloWorld;
    }

    @PostMapping("/hello/post")
    public String helloPost(String name){
        return "hello world, post: " + name;
    }

//    @GetMapping("/user/list")
//    public List<User> list() {
//        return userService.list();
//    }
}
