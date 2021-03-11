package com.dosx.javase.service.workflow.mapper;


import com.dosx.javase.service.workflow.entity.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dosx.javase.service.workflow.entity.vo.EntireProject;
import com.dosx.javase.service.workflow.entity.vo.MinProject;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Project实体表 Mapper 接口
 * </p>
 *
 * @author me and my Dokky
 * @since 2021-02-16
 */
public interface ProjectMapper extends BaseMapper<Project> {

    //
    EntireProject getEntireProject(Long id);

    //
    List<MinProject> getAllMinProjects();

    //
    List<MinProject> getAllMinProjectsByUserId(Long id);

    //
    void saveProjectDesc(EntireProject project);

    Integer getProjectProgress(Long id);

    void saveProjectUser(@Param("userId") Long userId, @Param("projectId")Long projectId);

    List<MinProject> getAllMinProjectsByUserIdByTag(@Param("id") Long id, @Param("tag") String tag);
}
