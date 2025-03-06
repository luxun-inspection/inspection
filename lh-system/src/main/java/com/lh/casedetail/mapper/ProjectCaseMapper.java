package com.lh.casedetail.mapper;

import com.lh.casedetail.domain.ProjectCase;

import java.util.List;

public interface ProjectCaseMapper {

    List<ProjectCase> getProjectCaseList(ProjectCase projectCase);

    ProjectCase getCaseDetailById(Long id);

    List<ProjectCase>  getAllProjectCase(ProjectCase projectCase);

    int  insertProjectCase(ProjectCase projectCase);

    int updateProjectCase(ProjectCase projectCase);

    int deleteProjectCase(Long id);

    int getMaxSort();
}
