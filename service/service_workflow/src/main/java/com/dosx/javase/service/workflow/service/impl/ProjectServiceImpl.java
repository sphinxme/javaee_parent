package com.dosx.javase.service.workflow.service.impl;

import com.dosx.javase.service.workflow.entity.Project;
import com.dosx.javase.service.workflow.entity.vo.EntireProject;
import com.dosx.javase.service.workflow.entity.vo.MinProject;
import com.dosx.javase.service.workflow.mapper.ProjectMapper;
import com.dosx.javase.service.workflow.service.ProjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public void saveProjectDesc(EntireProject project) {
        baseMapper.saveProjectDesc(project);
    }


    @Override
    public EntireProject getEntirePro(Long id) {

        return baseMapper.getEntireProject(id);
    }


    @Override
    public List<MinProject> getAllMinProjects() {
        return baseMapper.getAllMinProjects();
    }


    @Override
    public List<MinProject> getAllMinProjectsByUserId(Long id) {
        return baseMapper.getAllMinProjectsByUserId(id);
    }

    @Override
    public List<MinProject> getAllMinProjectsByUserIdByTag(Long id, String tag) {
        return baseMapper.getAllMinProjectsByUserIdByTag(id, tag);
    }

    @Override
    public void saveProjectUser(Long projectId, Long userId) {
        baseMapper.saveProjectUser(userId, projectId);
    }
}
