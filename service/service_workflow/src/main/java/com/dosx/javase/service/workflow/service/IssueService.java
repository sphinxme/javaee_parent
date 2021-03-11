package com.dosx.javase.service.workflow.service;

import com.dosx.javase.service.workflow.entity.Issue;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dosx.javase.service.workflow.entity.vo.EntireIssue;
import com.dosx.javase.service.workflow.entity.vo.MinIssue;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Issue实体表 服务类
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */


public interface IssueService extends IService<Issue> {

    List<EntireIssue> getEntireIssuesByProId(Long projectId);

    EntireIssue getEntireIssueByIssueId(Long id);

    List<MinIssue> getMinIssuesByProId(Long projectId);

    List<MinIssue> getMinIssuesByUserId(Long userId);

    List<MinIssue> getMinIssuesByUserIdAndDate(Long userId, Date date);

    void saveSingleIssue(Issue issue);
}
