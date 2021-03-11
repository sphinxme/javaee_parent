package com.dosx.javase.service.workflow.service;

import com.dosx.javase.service.workflow.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * Project可用标签列表
tag使用base64编码存储 服务类
 * </p>
 *
 * @author lucky us
 * @since 2021-03-07
 */
public interface TagService extends IService<Tag> {
    void saveProjectToTag(Long projectId, String tagName);
}
