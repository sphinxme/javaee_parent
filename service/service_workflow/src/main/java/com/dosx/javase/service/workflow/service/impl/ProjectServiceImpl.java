package com.dosx.javase.service.workflow.service.impl;

import com.dosx.javase.service.workflow.entity.Project;
import com.dosx.javase.service.workflow.mapper.ProjectMapper;
import com.dosx.javase.service.workflow.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Project实体表 服务实现类
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
@Service
public class ProjectServiceImpl extends ServiceImpl<ProjectMapper, Project> implements ProjectService {

}
