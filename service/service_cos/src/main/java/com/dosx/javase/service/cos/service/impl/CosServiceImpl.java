package com.dosx.javase.service.cos.service.impl;

import com.dosx.javase.service.cos.entity.FilePair;
import com.dosx.javase.service.cos.service.CosService;
import com.dosx.javase.service.cos.utils.CosPropertiesUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.COSObjectSummary;
import com.qcloud.cos.model.ListObjectsRequest;
import com.qcloud.cos.model.ObjectListing;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CosServiceImpl implements CosService
{
    private final COSClient cosClient;

    public CosServiceImpl() {

        COSCredentials cred = new BasicCOSCredentials(
                CosPropertiesUtils.secretId,
                CosPropertiesUtils.secretKey
        );

        Region region = new Region(
                CosPropertiesUtils.region
        );

        ClientConfig clientConfig = new ClientConfig(region);

        cosClient = new COSClient(cred, clientConfig);
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException
    {
        return rawUploadFile(file, file.getOriginalFilename());
    }

    @Override
    public String uploadFileInFolder(MultipartFile file, String folder) throws IOException
    {
        System.out.println("=========================file.getName=================================");
        System.out.println(file.getOriginalFilename());
        return rawUploadFile(file, folder + '/' + file.getOriginalFilename());
    }

    @Override
    public List<FilePair> getFileListInFolder(String prefix)
    {

        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
// 设置bucket名称
        listObjectsRequest.setBucketName(CosPropertiesUtils.bucket);
// prefix表示列出的object的key以prefix开始
        listObjectsRequest.setPrefix(prefix);
// deliter表示分隔符, 设置为/表示列出当前目录下的object, 设置为空表示列出所有的object
        listObjectsRequest.setDelimiter("/");
// 设置最大遍历出多少个对象, 一次listobject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        ObjectListing objectListing = null;

        try {
            objectListing = cosClient.listObjects(listObjectsRequest);
        } catch (CosClientException e) {
            e.printStackTrace();
            return null;
        }

        List<COSObjectSummary> list = objectListing.getObjectSummaries();
        List<FilePair> fileList = new ArrayList<>();

        for (COSObjectSummary cosObjectSummary : list) {
            String key = cosObjectSummary.getKey();
            FilePair filePair = new FilePair();
            filePair.setFilename(key.substring(prefix.length()));
            filePair.setUrl(getFileUrl(key));
            fileList.add(filePair);
        }

        return fileList;
    }

    @Override
    public void deleteFile(String key)
    {
        cosClient.deleteObject(CosPropertiesUtils.bucket, key);
    }

    public String rawUploadFile(MultipartFile file, String key) throws IOException
    {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        PutObjectRequest request = new PutObjectRequest(
                CosPropertiesUtils.bucket,
                key,
                file.getInputStream(),
                objectMetadata
        );

        PutObjectResult result = cosClient.putObject(request);

        return getFileUrl(key);
    }

    public static String getFileUrl(String key) {
        return "https://" +
                CosPropertiesUtils.bucket +
                ".cos." + CosPropertiesUtils.region +
                ".myqcloud.com/" + key;
    }

}
