package com.dosx.javase.service.acl.service.impl;

import com.dosx.javase.service.acl.entity.Permission;
import com.dosx.javase.service.acl.mapper.PermissionMapper;
import com.dosx.javase.service.acl.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author lucky us
 * @since 2021-02-20
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
