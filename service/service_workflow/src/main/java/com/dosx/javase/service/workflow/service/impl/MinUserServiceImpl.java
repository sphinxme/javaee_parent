package com.dosx.javase.service.workflow.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dosx.javase.service.workflow.entity.Issue;
import com.dosx.javase.service.workflow.entity.MinUser;
import com.dosx.javase.service.workflow.mapper.IssueMapper;
import com.dosx.javase.service.workflow.mapper.MinUserMapper;
import com.dosx.javase.service.workflow.service.MinUserService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MinUserServiceImpl extends ServiceImpl<MinUserMapper, MinUser> implements MinUserService
{
    @Override
    public List<MinUser> searchMinUsersByName(String name) {
        return baseMapper.searchMinUsersByName(name);
    }
}
