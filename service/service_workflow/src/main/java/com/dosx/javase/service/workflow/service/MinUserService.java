package com.dosx.javase.service.workflow.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dosx.javase.service.workflow.entity.MinUser;

import java.util.List;

public interface MinUserService extends IService<MinUser>
{
    List<MinUser> searchMinUsersByName(String name);

}