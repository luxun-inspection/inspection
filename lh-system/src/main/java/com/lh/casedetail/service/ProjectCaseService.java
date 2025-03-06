package com.lh.casedetail.service;

import com.lh.casedetail.domain.ProjectCase;

import java.util.List;

public interface ProjectCaseService {

    List<ProjectCase> getProjectCaseList(ProjectCase projectCae);

    ProjectCase getCaseDetailById(Long id);

    /**
     * 获取所有案例
     */
    List<ProjectCase> getAllProjectCase(ProjectCase projectCae);

    int insertProjectCase(ProjectCase projectCase);

    int updateProjectCase(ProjectCase projectCase);

    int deleteProjectCase(Long id);

    int getMaxSort();

}
