package com.dosx.javase.service.acl.controller;


import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.acl.entity.User;
import com.dosx.javase.service.acl.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * User实体表 前端控制器
 * </p>
 *
 * @author lucky us
 * @since 2021-02-20
 */
@Api("用户中心")
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public UniResponse login(@RequestBody User user)
    {
        // 先取出user的账密 取不出来抛异常
        // 然后用username 取出数据库那边的user
        // 加密pwd 然后和数据库里取出来的做对比
        // 如果取不出来抛异常
        // 如果不一样抛异常
        // 如果一样 登陆成功 颁发token并返回 todo

        return UniResponse.ok().data("token", "this is a token");
    }



}