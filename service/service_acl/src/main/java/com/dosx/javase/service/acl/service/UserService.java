package com.dosx.javase.service.acl.service;

import com.dosx.javase.service.acl.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * User实体表 服务类
 * </p>
 *
 * @author lucky us
 * @since 2021-02-20
 */
public interface UserService extends IService<User> {

    /**
     * 通过username寻找user
     * @param username 用户名
     * @return User
     */
    public User findPasswordByUsername(String username);

    /**
     * 把Token:user_id 插入redis
     * @return 成功数量
     * @param token token
     * @param userId user_id*/
    public int insertTokenToRedis(String token, String userId);
}
