package com.dosx.javase.service.workflow.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.workflow.entity.Issue;
import com.dosx.javase.service.workflow.entity.MinUser;
import com.dosx.javase.service.workflow.entity.vo.EntireIssue;
import com.dosx.javase.service.workflow.entity.vo.MinIssue;
import com.dosx.javase.service.workflow.service.IssueService;
import com.dosx.javase.service.workflow.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiParam;

/**
 * <p>
 * Issue实体表 前端控制器
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
@RestController
@CrossOrigin
@RequestMapping("/workflow/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/by/project/{id}/detail")
    public UniResponse getIssueDetailByProjectID(
            @ApiParam(name = "id", value = "ProjectID", required = true)
            @PathVariable Long id)
    {
        List<EntireIssue> entireIssuesList = issueService.getEntireIssuesByProId(id);
        return UniResponse.ok().data("issueDetail", entireIssuesList);
    }

    @GetMapping("/by/issue/{id}/detail")
    public UniResponse getIssueDetailByIssueId(
            @ApiParam(name = "id", value = "IssueID", required = true)
            @PathVariable Long id)
    {

        EntireIssue entireIssue = issueService.getEntireIssueByIssueId(id);
        return UniResponse.ok().data("issueDetail", entireIssue);
    }

    @GetMapping("/by/issue/{id}/outline")
    public UniResponse getIssueOutlineByIssueId(
            @ApiParam(name = "id", value = "IssueID", required = true)
            @PathVariable Long id)
    {
        return UniResponse.ok();
    }

    @GetMapping("/by/project/{id}/outline")
    public UniResponse getIssueOutlineByProjectId(
            @ApiParam(name = "id", value = "ProjectID", required = true)
            @PathVariable Long id)
    {

        List<MinIssue> minIssueList = issueService.getMinIssuesByProId(id);
        return UniResponse.ok().data("issue_outline_list", minIssueList);
    }

    @GetMapping("/myissues")
    public UniResponse getAvailableIssues(
            @RequestHeader("access_token") Long accessToken)
    {
        Long userId = accessToken;
        List<MinIssue> availableIssues = issueService.getMinIssuesByUserId(userId);
        return UniResponse.ok().data("issues", availableIssues);
    }

    @GetMapping("/myissuesindate")
    public UniResponse getDateIssues(
            @RequestHeader("access_token") Long accessToken,
            @RequestParam("date") String rawDate
    ) throws ParseException
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-M-d");
        Date date = dateFormater.parse(rawDate);

        Long userId = accessToken;

        List<MinIssue> issues = issueService.getMinIssuesByUserIdAndDate(userId, date);
        return UniResponse.ok().data("issues", issues);
    }

    @PostMapping("/finish/{issueId}")
    public UniResponse finishIssue( @PathVariable Long issueId ) {
        Issue issue = new Issue();
        issue.setId(issueId);
        issue.setStatus(1);
        issueService.updateById(issue);
        return UniResponse.ok();
    }

    @PutMapping("/parent-issue")
    public UniResponse newIssue(@RequestBody JSONObject rawIssue) throws ParseException
    {
        Long projectId = rawIssue.getLong("projectId");
        Issue parentIssue = new Issue();

        parentIssue.setName(rawIssue.getString("issueName"));
        parentIssue.setProjectId(projectId);

        // time
        JSONArray time = rawIssue.getJSONArray("time");
        parentIssue.setStartTime(time.getDate(0));
        parentIssue.setDeadline(time.getDate(1));

        parentIssue.setDescription(rawIssue.getString("desc"));

        // manager
        List<MinUser> manager = rawIssue.getJSONArray("manager").toJavaList(MinUser.class);
        parentIssue.setManager(manager);

        issueService.saveSingleIssue(parentIssue);

        // 添加子事项
        Long parentId = parentIssue.getId();
        JSONArray subIssues = rawIssue.getJSONArray("sub_issues");
        for (int i = 0; i < subIssues.size(); ++i ) {
            // 挨个解析
            Issue subIssue = new Issue();
            subIssue.setName(subIssues.getJSONObject(i).getString("name"));
            subIssue.setDescription(subIssues.getJSONObject(i).getString("desc"));
            subIssue.setManager(
                subIssues
                    .getJSONObject(i)
                    .getJSONArray("manager")
                    .toJavaList(MinUser.class)
            );
            subIssue.setParentId(parentId);
            subIssue.setProjectId(projectId);

            // 上传
            issueService.saveSingleIssue(subIssue);

        }


        //todo
        return UniResponse.ok();
    }


}

