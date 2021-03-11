package com.dosx.javase.service.cos.controller;

import com.alibaba.fastjson.JSONObject;
import com.dosx.javase.common.utils.UniResponse;
import com.dosx.javase.service.cos.entity.FilePair;
import com.dosx.javase.service.cos.service.CosService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/cos/cosfile")
public class CosController
{
    @Autowired
    CosService cosService;

    @PostMapping("uploadtest")
    public UniResponse uploadTest(MultipartFile file) throws IOException
    {
        String url = cosService.uploadFileInFolder(file, "test");
        return UniResponse.ok().data("url", url);
    }

    @PostMapping("upload/avator")
    public UniResponse uploadAvator(MultipartFile avator) throws IOException
    {
        String url = cosService.uploadFileInFolder(avator, "avator");
        return UniResponse.ok().data("url", url);
    }

    @PostMapping("upload/project/{projectId}")
    public UniResponse uploadProjectFile(
            MultipartFile file,
            @PathVariable("projectId") String projectId )
            throws IOException
    {
        String url = cosService.uploadFileInFolder(file, "project/" + projectId);
        return UniResponse.ok().data("url", url);
    }

    @GetMapping("getlist/project/{projectId}")
    public UniResponse getFileList(@PathVariable("projectId") String projectId)
    {
        List<FilePair> fileList = cosService.getFileListInFolder("project/" + projectId + '/');
        return UniResponse.ok().data("filelist", fileList);
    }

    @DeleteMapping("delete/")
    public UniResponse deleteFile(@RequestBody JSONObject file) {
        String key = file.getString("key");
        cosService.deleteFile(key);
        return UniResponse.ok();
    }

}
