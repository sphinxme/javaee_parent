package com.dosx.javase.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.dosx.javase.service.acl.entity.User;
import com.dosx.javase.service.acl.mapper.UserMapper;
import com.dosx.javase.service.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User实体表 服务实现类
 * </p>
 *
 * @author lucky us
 * @since 2021-02-20
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
//    @Autowired
//    public void setRedisTemplate(RedisTemplate redisTemplate)
//    {
//        System.out.println("***redisTemplate已注入UserService***");
//        this.redisTemplate = redisTemplate;
//    }

    @Override
    public User findPasswordByUsername(String username)
    {
        QueryWrapper<User> userWrapper = new QueryWrapper<>();
        userWrapper.eq("username", username).select("id","pwd");
        return baseMapper.selectOne(userWrapper);
    }

    @Override
    public int insertTokenToRedis(String token, String userId)
    {
        stringRedisTemplate.opsForValue().set(token, userId);
        return 1;
    }
}
