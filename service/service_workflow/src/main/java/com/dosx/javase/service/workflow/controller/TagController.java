package com.dosx.javase.service.workflow.controller;


import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.workflow.entity.Tag;
import com.dosx.javase.service.workflow.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;

import io.swagger.annotations.ApiOperation;

/**
 * <p>
 * Project可用标签列表
tag使用base64编码存储 前端控制器
 * </p>
 *
 * @author lucky us
 * @since 2021-03-07
 */
@RestController
@CrossOrigin
@RequestMapping("/workflow/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @ApiOperation("获取所有Tag")
    @GetMapping("findall")
    public UniResponse findall() {
        List<Tag> tagList = tagService.list(null);
        List<String> tags = new LinkedList<>();
        for (Tag tag : tagList)
        {
            tags.add(tag.getTag());
        }
        return UniResponse.ok().data("tags", tags);
    }
}

