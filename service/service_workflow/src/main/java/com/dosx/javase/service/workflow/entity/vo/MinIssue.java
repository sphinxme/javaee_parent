package com.dosx.javase.service.workflow.entity.vo;

import java.util.Date;

import lombok.Data;

@Data
public class MinIssue
{
    private Long issueId;
    private String issueName;
    private Date endTime;
    private Integer status;
    private MinProject project;
}
