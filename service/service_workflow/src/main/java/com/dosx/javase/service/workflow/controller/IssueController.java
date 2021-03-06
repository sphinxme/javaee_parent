package com.dosx.javase.service.workflow.controller;


import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.workflow.entity.Issue;
import com.dosx.javase.service.workflow.service.IssueService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/workflow/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @GetMapping("/find/issue/{id}/detail")
    public UniResponse findIssueDetailByIssueId(
            @ApiParam(name = "id", value = "IssueID", required = true)
            @PathVariable Long id)
    {

        Issue issue = issueService.getById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.putObject("data");

        return UniResponse.ok().data("issue", issue);
    }

    @GetMapping("/find/issue/{id}/outline")
    public UniResponse findIssueOutlineByIssueId(
            @ApiParam(name = "id", value = "IssueID", required = true)
            @PathVariable Long id)
    {

        return UniResponse.ok();
    }

    @GetMapping("/find/project/{id}/outline")
    public UniResponse findIssueOutlineByProjectId(
            @ApiParam(name = "id", value = "ProjectID", required = true)
            @PathVariable Long id
    ) {
        return UniResponse.ok();
    }
}

