package com.dosx.javase.service.workflow.entity.vo;

import com.dosx.javase.service.workflow.entity.MinUser;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EntireIssue
{
    private Long issueId;
    private Long projectId;
    private String issueName;
    private Date startTime;
    private Date endTime;
    private Integer status;
    private String description;
    private List<MinUser> manager;
    private List<EntireIssue> subIssues;

}
