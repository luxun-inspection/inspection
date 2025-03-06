package com.lh.project.mapper;


import com.lh.project.domain.ProjectInfo;

import java.util.List;

public interface ProjectMapper {

    List<ProjectInfo> getProjectList(ProjectInfo projectInfo);

    List<ProjectInfo> getProjectListByIsShow(ProjectInfo projectInfo);

    ProjectInfo getProjectInfoById(Long id);

    int  insertProjectInfo(ProjectInfo projectInfo);

    int updateProjectInfo(ProjectInfo projectInfo);

    int deleteProjectInfo(Long id);

}
