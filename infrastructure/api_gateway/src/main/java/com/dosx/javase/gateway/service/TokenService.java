package com.dosx.javase.gateway.service;


import org.apache.commons.codec.DecoderException;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * TokenService 解析token 与Redis交互 取回相应的权限 并返回权限列表
 * @author lucky us
 */

public interface TokenService
{
    // 由token得到用户id
    /**
     * 解密token 获得id 但不验证是否有效
     * @param token 十六进制已加密字符串
     * @return user_id
     * @exception Exception 就是异常 不想写
     * */
    public Long getIdByToken(String token) throws Exception;


    /**
     * 判断Token是否存在/有效
     * @param token token值
     * @return 存在返回true 不存在返回false
     * */
    public boolean ifTokenValid(String token) throws Exception;


}
