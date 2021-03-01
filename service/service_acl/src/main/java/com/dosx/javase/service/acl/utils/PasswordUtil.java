package com.dosx.javase.service.acl.utils;

import org.springframework.util.DigestUtils;


/**
 * @author lucky us
 */
public class PasswordUtil
{
    public static String encryptPwd(String rawPassword) {
        // 以后补上加密算法
        return DigestUtils.md5DigestAsHex(rawPassword.getBytes());
    }

}
