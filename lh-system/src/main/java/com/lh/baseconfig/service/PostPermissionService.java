package com.lh.baseconfig.service;

import com.lh.baseconfig.domain.Permission;

import java.util.List;

public interface PostPermissionService {

    List<Permission> getPermissionListByPostId(Long postId);

    int deleteAuthorityByPostId(Long postId);

    void dealAuthorityByPostId(Long postId,String ids);

}
