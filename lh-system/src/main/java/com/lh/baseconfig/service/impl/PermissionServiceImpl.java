package com.lh.baseconfig.service.impl;

import com.lh.baseconfig.domain.Permission;
import com.lh.baseconfig.mapper.PermissionMapper;
import com.lh.baseconfig.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> getPermissionListByPostId(Long postId) {
        return permissionMapper.getPermissionListByPostId(postId);
    }

    @Override
    public List<Permission> getAllPermission() {
        return permissionMapper.getAllPermission();
    }

    @Override
    public List<Long> getPostIdsByPermissionId() {
        return permissionMapper.getPostIdsByPermissionId();
    }


}
