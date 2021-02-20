package com.dosx.javase.service.workflow.controller;


import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.workflow.entity.Project;
import com.dosx.javase.service.workflow.service.ProjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * <p>
 * Project实体表 前端控制器
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
@Api("Project管理")
@RestController
@RequestMapping("/workflow/project")
public class ProjectController {

    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @ApiOperation("查询所有Project")
    @GetMapping("findall")
    public UniResponse findAllProject() {

        return UniResponse.ok().data("projects", projectService.list(null));
    }

    @ApiOperation("删除对应ID的Project")
    @DeleteMapping("{id}")
    public UniResponse removeProject(
            @ApiParam(name = "id", value = "ProjectID", required = true)
            @PathVariable Long id)
    {
        if (!projectService.removeById(id))
        {
            return UniResponse.error();
        }
        return UniResponse.ok();
    }

    @ApiOperation("添加Project")
    @PostMapping("addProject")
    public UniResponse addProject(@RequestBody Project project) throws Exception
    {
        Project project0 = new Project(project.getMasterId(), project.getName());
        boolean flag;
        try
        {
            flag = projectService.save(project0);
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("添加失败");
        }

        if (!flag)
        {
            throw new Exception("添加失败");
        }

        return UniResponse.ok();
    }
}