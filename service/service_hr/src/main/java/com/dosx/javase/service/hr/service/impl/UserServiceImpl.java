package com.dosx.javase.service.hr.service.impl;

import com.dosx.javase.service.hr.entity.User;
import com.dosx.javase.service.hr.mapper.UserMapper;
import com.dosx.javase.service.hr.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * User实体表 服务实现类
 * </p>
 *
 * @author lucky us
 * @since 2021-03-01
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
