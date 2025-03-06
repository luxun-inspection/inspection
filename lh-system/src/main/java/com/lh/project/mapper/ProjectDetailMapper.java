package com.lh.project.mapper;

import com.lh.project.domain.ProjectDetail;

import java.util.List;


public interface ProjectDetailMapper {


    List<ProjectDetail> getProjectDetailList(ProjectDetail projectDetail);

    List<ProjectDetail> getProjectDetailListById(ProjectDetail projectDetail);

    ProjectDetail getProjectDetailById(Long id);

    int insertProjectDetail(ProjectDetail projectDetail);

    int updateProjectDetail(ProjectDetail projectDetail);

    int deleteProjectDetail(Long id);
}
