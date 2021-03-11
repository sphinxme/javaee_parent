package com.dosx.javase.service.workflow.service;

import com.dosx.javase.service.workflow.entity.Project;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dosx.javase.service.workflow.entity.vo.EntireProject;
import com.dosx.javase.service.workflow.entity.vo.MinProject;

import java.util.List;

/**
 * <p>
 * Project实体表 服务类
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */

public interface ProjectService extends IService<Project> {


    void saveProjectDesc(EntireProject project);


    EntireProject getEntirePro(Long id);


    List<MinProject> getAllMinProjects();


    List<MinProject> getAllMinProjectsByUserId(Long id);

    List<MinProject> getAllMinProjectsByUserIdByTag(Long id, String tag);


    void saveProjectUser(Long projectId, Long userId);

}
