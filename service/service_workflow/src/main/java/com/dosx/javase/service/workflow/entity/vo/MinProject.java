package com.dosx.javase.service.workflow.entity.vo;

import com.dosx.javase.service.workflow.entity.MinUser;
import com.dosx.javase.service.workflow.entity.Project;

import java.util.List;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MinProject {

    Long id;
    String name;
    List<String> tags;
    MinUser manager;
    Integer progress;
    String imgPath;
    Boolean display = true;

    public MinProject() {}
    public MinProject(Project project) {
        id = project.getId();
        name = project.getName();
        tags = project.getTags();
        manager = project.getManager();
        progress = getProgress();
    }

}
