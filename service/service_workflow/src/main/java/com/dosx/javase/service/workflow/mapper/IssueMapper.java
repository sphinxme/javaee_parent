package com.dosx.javase.service.workflow.mapper;

import com.dosx.javase.service.workflow.entity.Issue;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dosx.javase.service.workflow.entity.vo.EntireIssue;
import com.dosx.javase.service.workflow.entity.vo.MinIssue;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * Issue实体表 Mapper 接口
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
public interface IssueMapper extends BaseMapper<Issue> {

    List<EntireIssue> getEntireIssueRecursion(
            @Param("projectId")Long projectId,
            @Param("parentId") Long parentId
    );

    List<EntireIssue> getEntireIssuesByProId(
            Long projectId
    );

    EntireIssue getEntireIssueByIssueId(
            Long id
    );

    List<MinIssue> getMinIssuesByProId(
            Long projectId
    );

    List<MinIssue> getMinIssuesByUserId(
            Long userId
    );

    List<MinIssue> getMinIssuesByUserIdAndDate(
            @Param("userId") Long userId,
            @Param("date") Date date
    );

    void insertIssueDesc(Issue issue);

    void insertIssueManagers(Issue issue);
}
