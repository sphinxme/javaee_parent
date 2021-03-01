package com.dosx.javase.service.acl.controller;


import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.acl.entity.User;
import com.dosx.javase.service.acl.service.UserService;
import com.dosx.javase.service.acl.utils.PasswordUtil;
import com.dosx.javase.common.utils.TokenUtil;

import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.NoSuchPaddingException;

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

    @Autowired
    private UserService userService;

    TokenUtil tokenUtil = TokenUtil.getInstance();

    public UserController()
            throws
            NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            DecoderException
    {
    }

    @ApiOperation("登录")
    @PostMapping("/login")
    public UniResponse login(@RequestBody User user) throws Exception
    {
        // 先取出user的账密 取不出来抛异常
        String username = user.getUsername();
        String password = user.getPwd();
        if ( password == null || username == null)
        {
            throw new Exception("用户名有问题");
        }
        // 然后用username 取出数据库那边的user
        User realUser = userService.findPasswordByUsername(username);

        // 如果取不出来抛异常
        if(realUser == null)
        {
            throw new Exception("用户名不对");
        }

        String realPassword = realUser.getPwd();

        // 加密pwd 然后和数据库里取出来的做对比
        if (!PasswordUtil.encryptPwd(password).equals(realPassword))
        {
            // 如果不一样抛异常
            throw new Exception("密码不对");
        }

        // 如果一样 登陆成功 颁发token
        // 放入redis 设置时间
        String token = tokenUtil.encodeToken(realUser.getId().toString());
        userService.insertTokenToRedis(token, realUser.getId().toString());
        return UniResponse.ok().data("token", token);
    }

    @ApiOperation("注册新用户")
    @PostMapping("/signup")
    public UniResponse signUp(@RequestBody User user) {
        // todo 注册
        return UniResponse.ok().message("注册成功");
    }



}