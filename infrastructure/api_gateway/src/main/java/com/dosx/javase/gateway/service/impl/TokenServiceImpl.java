package com.dosx.javase.gateway.service.impl;

import com.dosx.javase.common.utils.TokenUtil;
import com.dosx.javase.gateway.service.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * TokenService 解析token 与Redis交互 取回相应的权限 并返回权限列表
 * @author lucky us
 *
 */

@Service
public class TokenServiceImpl implements TokenService
{
    @Autowired
    StringRedisTemplate redisTemplate;


    /**
     * 解密token 获得id 但不验证是否有效
     *
     * @param token 十六进制已加密字符串
     * @return user_id
     */
    @Override
    public Long getIdByToken(String token) throws Exception
    {
        TokenUtil tokenUtil = TokenUtil.getInstance();
        return Long.parseLong(tokenUtil.decodeToken(token));
    }

    /**
     * 判断Token是否存在/有效
     *
     * @param token token值
     * @return 存在返回true 不存在返回false
     */
    @Override
    public boolean ifTokenValid(String token) throws Exception
    {

        String result;

        do {
            result = redisTemplate.opsForValue().get(token);
        } while (result == null);

        // 如果无此值的话 redis返回(nil)
        if ("nil".equals(result))
        {
            return false;
        }

        // 解析token
        TokenUtil tokenUtil = TokenUtil.getInstance();
        String userId = tokenUtil.decodeToken(token);

        return result.equals(userId);
    }
}
