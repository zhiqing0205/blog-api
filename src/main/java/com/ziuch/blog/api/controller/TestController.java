package com.ziuch.blog.api.controller;

import com.ziuch.blog.api.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/testApi")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Resource
    private RedisTemplate<String, String> redis;

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

    @GetMapping("/redisSet/{key}/{value}")
    public String redisSet(@PathVariable String key, @PathVariable String value){
        redis.opsForValue().set(key, value, 3600 * 24, TimeUnit.SECONDS);
        LOG.info("key: {}, value: {}", key, value);
        return "success!\nkey: " + key + "\nvalue:" + value;
    }

    @GetMapping("/redisGet/{key}")
    public String redisSet(@PathVariable String key){
        Object object = redis.opsForValue().get(key);
        if(ObjectUtils.isEmpty(object)) {
            LOG.error("key: {} is empty!", key);
            return "key: " + key + " is empty!";
        }
        LOG.info("key: {}, value: {}", key, object);
        return "key: " + key + "\nvalue:" + object.toString();
    }
}
