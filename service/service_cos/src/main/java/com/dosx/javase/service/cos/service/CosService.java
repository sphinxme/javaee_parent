package com.dosx.javase.service.cos.service;


import com.dosx.javase.service.cos.entity.FilePair;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CosService
{
    String uploadFile(MultipartFile file) throws IOException;

    String uploadFileInFolder(MultipartFile file, String folder) throws IOException;

    List<FilePair> getFileListInFolder(String prefix);

    void deleteFile(String key);
}
