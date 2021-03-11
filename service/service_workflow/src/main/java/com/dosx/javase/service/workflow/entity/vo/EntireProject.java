package com.dosx.javase.service.workflow.entity.vo;

import com.dosx.javase.service.workflow.entity.MinUser;
import com.dosx.javase.service.workflow.entity.Project;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class EntireProject
{

    private Long id;
    private String name;
    private String desc;
    private Integer progress;
    private List<String> tags;
    private MinUser manager;
    private List<MinUser> participants;

    public EntireProject() {}

    public EntireProject(Project project) {
        id = project.getId();
        name = project.getName();
        desc = project.getDesc();
        tags = project.getTags();
        manager = (project.getManager());
    }
}
