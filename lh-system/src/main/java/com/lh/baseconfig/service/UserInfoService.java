package com.lh.baseconfig.service;

import com.lh.baseconfig.domain.UserInfo;

import java.util.List;

public interface UserInfoService {

    UserInfo getUserInfoByOpenId(String openId);

    int addUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(Long id);

    int updateUserInfo(UserInfo userInfo);

    List<UserInfo> getEngineerList();

    List<UserInfo> getEngineerNameList();

    List<String>  getEngineerName(String [] engineerIdss);

    /**
     * 根据查询条件获取用户信息
     */
    List<UserInfo> getUserInfoByParms(UserInfo userInfo);


    List<UserInfo> getManagerUserList();

    List<UserInfo> getUserInfoByIds(String engineeId);
}
