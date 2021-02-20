package com.dosx.javase.service.workflow.service.impl;

import com.dosx.javase.service.workflow.entity.Issue;
import com.dosx.javase.service.workflow.mapper.IssueMapper;
import com.dosx.javase.service.workflow.service.IssueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Issue实体表 服务实现类
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
@Service
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {

}
