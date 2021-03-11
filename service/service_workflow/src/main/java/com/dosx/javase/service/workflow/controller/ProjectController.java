package com.dosx.javase.service.workflow.controller;

import com.alibaba.fastjson.JSONObject;
import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.workflow.entity.Project;
import com.dosx.javase.service.workflow.entity.Tag;
import com.dosx.javase.service.workflow.entity.vo.EntireProject;
import com.dosx.javase.service.workflow.entity.vo.MinProject;
import com.dosx.javase.service.workflow.service.ProjectService;
import com.dosx.javase.service.workflow.service.TagService;


import org.apache.commons.codec.DecoderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

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
@CrossOrigin
@RequestMapping("/workflow/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TagService tagService;

    @ApiOperation("查询所有Project")
    @GetMapping("findall")
    public UniResponse findAllProject() {
        List<MinProject> minProjectList = projectService.getAllMinProjects();
        return UniResponse.ok().data("projects", minProjectList);
    }

    @ApiOperation("找到对应用户的所有的Project")
    @GetMapping("findmy/{userId}/")
    public UniResponse findMyProjects(
            @ApiParam(name = "id", value = "UserID", required = true)
            @PathVariable Long userId
    ) {
        List<MinProject> projectList = projectService.getAllMinProjectsByUserId(userId);
        return UniResponse.ok().data("projects", projectList);
    }

    @ApiOperation("找到对应用户的所有的Project")
    @GetMapping("findall/tag/{tag}")
    public UniResponse findMyProjectsbyTag(
            @PathVariable String tag
    ) {
        List<MinProject> projectList = projectService.getAllMinProjects();
        List<MinProject> selectedList = new ArrayList<>();
        for (MinProject project : projectList) {
            if (project.getTags().contains(tag))
            {
                selectedList.add(project);
            }
        }

        return UniResponse.ok().data("projects", selectedList);
    }

    @ApiOperation("删除对应ID的Project")
    @DeleteMapping("{id}")
    public UniResponse removeProject(
            @ApiParam(name = "id", value = "ProjectID", required = true)
            @PathVariable Long id)
    {
        // fixme
        if (!projectService.removeById(id))
        {
            return UniResponse.error();
        }
        return UniResponse.ok();
    }

    @ApiOperation("获取project全部信息")
    @GetMapping("getFullyPro/{id}")
    public UniResponse getEntirePro(@PathVariable Long id) {
        EntireProject entireProject = projectService.getEntirePro(id);
        return UniResponse.ok().data("projectData", entireProject);
    }

    @ApiOperation("新增Project")
    @PutMapping("newProject")
    public UniResponse newProject(@RequestBody EntireProject rawProject,
                                  @RequestHeader("access_token") Long accessToken)
            throws NoSuchPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            DecoderException,
            BadPaddingException,
            IllegalBlockSizeException
    {

        Long userId = accessToken;
        Project project  = new Project();
        project.setMasterId(userId);
        project.setName(rawProject.getName());
        projectService.save(project);

        List<String> tags = rawProject.getTags();
        for (String tagName : tags)
        {
            tagService.save(new Tag(tagName));
        }

        rawProject.setId(project.getId());

        for(String tag: tags)
        {
            tagService.saveProjectToTag(project.getId(), tag);
        }

        projectService.saveProjectDesc(rawProject);
        projectService.saveProjectUser(project.getId(), userId);

        return UniResponse.ok().message("添加成功").data("projectId", project.getId());
    }
}