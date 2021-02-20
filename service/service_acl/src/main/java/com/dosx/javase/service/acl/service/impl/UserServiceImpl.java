package com.dosx.javase.service.acl.service.impl;

import com.dosx.javase.service.acl.entity.User;
import com.dosx.javase.service.acl.mapper.UserMapper;
import com.dosx.javase.service.acl.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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

}
