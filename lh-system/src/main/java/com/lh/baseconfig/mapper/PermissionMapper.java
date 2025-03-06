package com.lh.baseconfig.mapper;

import com.lh.baseconfig.domain.Permission;

import java.util.List;

public interface PermissionMapper {

    List<Permission> getPermissionListByPostId(Long postId);

    List<Permission> getAllPermission();

    List<Long> getPostIdsByPermissionId();

}
