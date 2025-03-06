package com.lh.project.service;


import com.lh.project.domain.ProjectDetail;
import com.lh.project.domain.ProjectInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectService {


    /**
     * 获取所有项目
     *
     * @param projectInfo
     * @return
     */
    List<ProjectInfo> getProjectList(ProjectInfo projectInfo);

    /**
     * 获取所有项目根据isShow=0返回
     *
     * @param projectInfo
     * @return
     */
    List<ProjectInfo> getProjectListByIsShow(ProjectInfo projectInfo);

    /**
     * 添加项目
     *
     * @param projectInfo
     * @return
     */
    int insertProjectInfo(ProjectInfo projectInfo);

    /**
     * 修改项目
     *
     * @param projectInfo
     * @return
     */
    int updateProjectInfo(ProjectInfo projectInfo);

    /**
     * 删除项目
     *
     * @param id
     * @return
     */
    int deleteProjectInfo(Long id);


    /**
     * 获取所有项目明细
     *
     * @param projectDetail
     * @return
     */
    List<ProjectDetail> getProjectDetailList(ProjectDetail projectDetail);

    /**
     * 明细详情
     *
     * @param id
     * @return
     */

    ProjectDetail getProjectDetailById(Long id);
    /**
     * 根据项目ID获取所有项目明细
     *
     * @param projectDetail
     * @return
     */
    List<ProjectDetail> getProjectDetailListByIdOrProject(ProjectDetail projectDetail);

    /**
     * 添加项目明细
     *
     * @param projectDetail
     * @return
     */
    int insertProjectDetail(ProjectDetail projectDetail);

    /**
     * 修改项目明细
     *
     * @param projectDetail
     * @return
     */
    int updateProjectDetail(ProjectDetail projectDetail);

    /**
     * 根据Id删除项目明细
     *
     * @param id
     * @return
     */
    int deleteProjectDetail(Long id);

}
