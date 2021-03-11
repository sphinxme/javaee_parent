package com.dosx.javase.service.workflow.service.impl;

import com.dosx.javase.service.workflow.entity.Tag;
import com.dosx.javase.service.workflow.mapper.TagMapper;
import com.dosx.javase.service.workflow.service.TagService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Project可用标签列表
tag使用base64编码存储 服务实现类
 * </p>
 *
 * @author lucky us
 * @since 2021-03-07
 */
@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements TagService {

    @Override
    public void saveProjectToTag(Long projectId, String tagName) {
        baseMapper.saveProjectToTag(projectId, tagName);
    }
}
