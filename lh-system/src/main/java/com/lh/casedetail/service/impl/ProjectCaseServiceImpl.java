package com.lh.casedetail.service.impl;

import com.lh.casedetail.domain.ProjectCase;
import com.lh.casedetail.mapper.ProjectCaseMapper;
import com.lh.casedetail.service.ProjectCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectCaseServiceImpl implements ProjectCaseService {

    @Autowired
    private ProjectCaseMapper projectCaseMapper;

    @Override
    public List<ProjectCase> getProjectCaseList(ProjectCase projectCae) {
        return projectCaseMapper.getProjectCaseList(projectCae);
    }

    @Override
    public ProjectCase getCaseDetailById(Long id) {
        return projectCaseMapper.getCaseDetailById(id);
    }

    @Override
    public List<ProjectCase> getAllProjectCase(ProjectCase projectCae) {
        return projectCaseMapper.getAllProjectCase(projectCae);
    }

    @Override
    public int insertProjectCase(ProjectCase projectCase) {
        return projectCaseMapper.insertProjectCase(projectCase);
    }

    @Override
    public int updateProjectCase(ProjectCase projectCase) {
        return projectCaseMapper.updateProjectCase(projectCase);
    }

    @Override
    public int deleteProjectCase(Long id) {
        return projectCaseMapper.deleteProjectCase(id);
    }

    @Override
    public int getMaxSort() {
        return projectCaseMapper.getMaxSort();
    }
}
