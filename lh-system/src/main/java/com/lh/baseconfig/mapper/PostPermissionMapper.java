package com.lh.baseconfig.mapper;

import com.lh.baseconfig.domain.Permission;
import com.lh.baseconfig.domain.PostPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostPermissionMapper {

    List<Permission> getPermissionListByPostId(@Param("postId") Long postId);

    int deleteAuthorityByPostId(@Param("postId") Long postId);

    int insertPostPermission(PostPermission postPermission);
}
