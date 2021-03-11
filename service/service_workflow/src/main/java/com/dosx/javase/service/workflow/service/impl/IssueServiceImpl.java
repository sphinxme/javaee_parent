package com.dosx.javase.service.workflow.service.impl;

import com.dosx.javase.service.workflow.entity.Issue;
import com.dosx.javase.service.workflow.entity.vo.EntireIssue;
import com.dosx.javase.service.workflow.entity.vo.MinIssue;
import com.dosx.javase.service.workflow.mapper.IssueMapper;
import com.dosx.javase.service.workflow.service.IssueService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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

    @Override
    public List<EntireIssue> getEntireIssuesByProId(Long projectId)
    {
        return baseMapper.getEntireIssuesByProId(projectId);
    }

    @Override
    public EntireIssue getEntireIssueByIssueId(Long id)
    {
        return baseMapper.getEntireIssueByIssueId(id);
    }

    @Override
    public List<MinIssue> getMinIssuesByProId(Long projectId)
    {
        return baseMapper.getMinIssuesByProId(projectId);
    }

    @Override
    public List<MinIssue> getMinIssuesByUserId(Long userId)
    {
        return baseMapper.getMinIssuesByUserId(userId);
    }

    @Override
    public List<MinIssue> getMinIssuesByUserIdAndDate(Long userId, Date date)
    {
        return baseMapper.getMinIssuesByUserIdAndDate(userId, date);
    }

    @Override
    public void saveSingleIssue(Issue issue)
    {
        // 第一步把issue放到issue库中
        baseMapper.insert(issue);

        // 第二部把issue.description 放到issue_desc库中
        baseMapper.insertIssueDesc(issue);

        // 第三步把issue.manager 一个一个放到关系库中
        baseMapper.insertIssueManagers(issue);
    }


}
