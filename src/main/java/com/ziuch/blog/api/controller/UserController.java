package com.ziuch.blog.api.controller;

import com.ziuch.blog.api.req.UserLoginReq;
import com.ziuch.blog.api.req.UserQueryReq;
import com.ziuch.blog.api.req.UserResetPasswordReq;
import com.ziuch.blog.api.req.UserSaveReq;
import com.ziuch.blog.api.resp.CommonResp;
import com.ziuch.blog.api.resp.PageResp;
import com.ziuch.blog.api.resp.UserLoginResp;
import com.ziuch.blog.api.resp.UserQueryResp;
import com.ziuch.blog.api.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@Api(value = "测试接口", tags = "用户管理相关的接口")
public class UserController {

    @Resource
    private UserService userService;

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
        CommonResp<UserLoginResp> resp = new CommonResp<>();
        UserLoginResp user = userService.login(req);
        resp.setContent(user);
        return resp;
    }
}
