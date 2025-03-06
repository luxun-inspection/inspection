package com.lh.baseconfig.service;

import com.lh.baseconfig.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> getPermissionListByPostId(Long postId);

    List<Permission> getAllPermission();

    List<Long> getPostIdsByPermissionId();


}
