package com.lh.project.service.impl;


import com.google.zxing.WriterException;
import com.lh.common.config.HuaweiObsfaceService;
import com.lh.common.utils.StringUtils;
import com.lh.project.domain.ProjectDetail;
import com.lh.project.domain.ProjectInfo;
import com.lh.project.mapper.ProjectDetailMapper;
import com.lh.project.mapper.ProjectMapper;
import com.lh.project.service.ProjectService;
import com.lh.weixinweb.controller.req.CreateQRCodeRequest;
import com.lh.weixinweb.service.WxApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class ProjectServiceImpl implements ProjectService {


    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private ProjectDetailMapper projectDetailMapper;

    @Autowired
    private HuaweiObsfaceService huaweiObsfaceService;

    @Autowired
    private WxApiService wxApiService;

    @Override
    public List<ProjectInfo> getProjectList(ProjectInfo projectInfo) {
        return projectMapper.getProjectList(projectInfo);
    }

    @Override
    public List<ProjectInfo> getProjectListByIsShow(ProjectInfo projectInfo) {
        return projectMapper.getProjectListByIsShow(projectInfo);
    }


    @Override
    public int insertProjectInfo(ProjectInfo projectInfo) {
        return projectMapper.insertProjectInfo(projectInfo);
    }

    @Override
    public int updateProjectInfo(ProjectInfo projectInfo) {
        return projectMapper.updateProjectInfo(projectInfo);
    }

    @Override
    public int deleteProjectInfo(Long id) {
        return projectMapper.deleteProjectInfo(id);
    }

    private String toSort(String plate) {
        String replace = plate.replace(",", "");
        // 拆分
        char[] chars = replace.toCharArray();
        // 排序
        Arrays.sort(chars);
        String data = "";
        for (char aChar : chars) {
            data += aChar + ",";
        }
        String newPlate = data.substring(0, data.length() - 1);
        return newPlate;
    }

    @Override
    public List<ProjectDetail> getProjectDetailList(ProjectDetail projectDetail) {
        if (!StringUtils.isEmpty(projectDetail.getPlate())) {
            toSort(projectDetail.getPlate());
        }
        return projectDetailMapper.getProjectDetailList(projectDetail);
    }

    @Override
    public List<ProjectDetail> getProjectDetailListByIdOrProject(ProjectDetail projectDetail) {
        List<ProjectDetail> projectDetailList = projectDetailMapper.getProjectDetailList(projectDetail);
        if (StringUtils.isNotNull(projectDetail.getId())) {
            if (!CollectionUtils.isEmpty(projectDetailList)) {
                ProjectDetail info = projectDetailList.get(0);
                info.setHits(info.getHits() + 1);
                projectDetailMapper.updateProjectDetail(info);
            }
        }
        return projectDetailList;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ProjectDetail getProjectDetailById(Long id) {
        ProjectDetail projectDetailById = projectDetailMapper.getProjectDetailById(id);
        projectDetailById.setHits(projectDetailById.getHits() + 1);
        projectDetailMapper.updateProjectDetail(projectDetailById);
        return projectDetailById;
    }


    @Override
    public int insertProjectDetail(ProjectDetail projectDetail) {
        if (!StringUtils.isEmpty(projectDetail.getPlate())) {
            toSort(projectDetail.getPlate());
        }
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        projectDetail.setId(Long.valueOf(number));
        projectDetail.setIsDel("1");
        try {
            String path = "pages/project/videoDetail?videoId=" + String.valueOf(projectDetail.getId());
            CreateQRCodeRequest request = new CreateQRCodeRequest();
            request.setPath(path);
            byte[] bytes = wxApiService.getQRCode(request);
            InputStream input = new ByteArrayInputStream(bytes);
            String actualName = "qrCode" + System.currentTimeMillis() + ".jpg";
            String fileUrl = huaweiObsfaceService.upload(input, actualName);
            StringBuffer stringBuffer = new StringBuffer("https://");
            stringBuffer.append(fileUrl);
            projectDetail.setQrCode(stringBuffer.toString());
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return projectDetailMapper.insertProjectDetail(projectDetail);
    }


    @Override
    public int updateProjectDetail(ProjectDetail projectDetail) {
        return projectDetailMapper.updateProjectDetail(projectDetail);
    }

    @Override
    public int deleteProjectDetail(Long id) {
        return projectDetailMapper.deleteProjectDetail(id);
    }


}
