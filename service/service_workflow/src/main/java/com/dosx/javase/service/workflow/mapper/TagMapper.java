package com.dosx.javase.service.workflow.mapper;

import com.dosx.javase.service.workflow.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Project可用标签列表
tag使用base64编码存储 Mapper 接口
 * </p>
 *
 * @author lucky us
 * @since 2021-03-07
 */
public interface TagMapper extends BaseMapper<Tag> {


    List<String> getTagsByProId(Long id);




    void saveProjectToTag(Long projectId, String tagName);
}
