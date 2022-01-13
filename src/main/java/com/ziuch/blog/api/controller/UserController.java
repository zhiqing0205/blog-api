package com.ziuch.blog.api.controller;

import com.alibaba.fastjson.JSONObject;
import com.ziuch.blog.api.req.UserLoginReq;
import com.ziuch.blog.api.req.UserQueryReq;
import com.ziuch.blog.api.req.UserResetPasswordReq;
import com.ziuch.blog.api.req.UserSaveReq;
import com.ziuch.blog.api.resp.CommonResp;
import com.ziuch.blog.api.resp.PageResp;
import com.ziuch.blog.api.resp.UserLoginResp;
import com.ziuch.blog.api.resp.UserQueryResp;
import com.ziuch.blog.api.service.UserService;
import com.ziuch.blog.api.util.SnowFlake;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
@Api(value = "测试接口", tags = "用户管理相关的接口")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private SnowFlake snowFlake;

    @Resource
    private RedisTemplate<String, String> redis;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/list")

    public CommonResp list(@Valid UserQueryReq req){
        CommonResp<PageResp<UserQueryResp>> resp = new CommonResp<>();
        PageResp<UserQueryResp> list = userService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.save(req);
        return resp;
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id){
        CommonResp resp = new CommonResp<>();
        userService.delete(id);
        return resp;
    }

    @PostMapping("/resetPassword")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        userService.resetPassword(req);
        return resp;
    }

    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody UserLoginReq req){
        req.setPassword(DigestUtils.md5DigestAsHex(req.getPassword().getBytes()));
        CommonResp resp = new CommonResp<>();
        UserLoginResp user = userService.login(req);

        Long token = snowFlake.nextId();
        user.setToken(token.toString());
        redis.opsForValue().set(token.toString(), JSONObject.toJSONString(user), 3600 * 24, TimeUnit.SECONDS);
        LOG.info("{} 生成token：{}，并存放入redis中", user.getLoginName(), user.getToken());

        resp.setContent(user);
        return resp;
    }
}
