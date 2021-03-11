package com.dosx.javase.service.workflow.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dosx.javase.service.workflow.entity.MinUser;
import com.dosx.javase.service.workflow.entity.Project;

import java.util.List;

public interface MinUserMapper extends BaseMapper<MinUser> {

    MinUser getMinUserById(Long id);

    List<MinUser> getMinUserByProId(Long projectId);

    List<MinUser> searchMinUsersByName(String name);
}
