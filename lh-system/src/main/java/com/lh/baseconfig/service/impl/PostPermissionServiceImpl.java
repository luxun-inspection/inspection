package com.lh.baseconfig.service.impl;

import com.lh.baseconfig.domain.Permission;
import com.lh.baseconfig.domain.PostPermission;
import com.lh.baseconfig.mapper.PostPermissionMapper;
import com.lh.baseconfig.service.PostPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostPermissionServiceImpl implements PostPermissionService {

    @Autowired
    private PostPermissionMapper postPermissionMapper;

    @Override
    public List<Permission> getPermissionListByPostId(Long postId) {
        return postPermissionMapper.getPermissionListByPostId(postId);
    }

    @Override
    public int deleteAuthorityByPostId(Long postId) {
        return postPermissionMapper.deleteAuthorityByPostId(postId);
    }


    @Override
    public void dealAuthorityByPostId(Long postId,String ids) {
        if(null!=ids&&!"".equals(ids)){
            String [] idList = ids.split(",");
                //新增权限
            for(int i =0;i<idList.length;i++) {
                PostPermission postPermission = new PostPermission();
                postPermission.setPostId(postId);
                postPermission.setPermissionId(Long.valueOf(idList[i]));
                postPermission.setCreateTime(new Date());
                postPermissionMapper.insertPostPermission(postPermission);
            }
        }
    }
}
