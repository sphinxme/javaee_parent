package com.dosx.javase.service.hr.controller;


import com.dosx.javase.common.utils.UniResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author lucky us
 * @since 2021-03-01
 */
@RestController
@RequestMapping("/hr/department")
public class DepartmentController {

    @GetMapping("/test")
    public UniResponse test() {
        return UniResponse.ok().message("testing");
    }
}

