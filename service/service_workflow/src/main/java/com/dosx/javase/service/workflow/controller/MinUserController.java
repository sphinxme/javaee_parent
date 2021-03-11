package com.dosx.javase.service.workflow.controller;

import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.workflow.entity.MinUser;
import com.dosx.javase.service.workflow.service.MinUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/workflow/user")
public class MinUserController
{
    @Autowired
    MinUserService minUserService;

    @GetMapping("search")
    UniResponse searchUsers(@RequestParam("name") String name) {
        List<MinUser> userList = minUserService.searchMinUsersByName(name);
        return UniResponse.ok().data("userList", userList);
    }

}
