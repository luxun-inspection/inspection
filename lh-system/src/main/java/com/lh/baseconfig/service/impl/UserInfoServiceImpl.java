package com.lh.baseconfig.service.impl;

import com.lh.baseconfig.domain.UserInfo;
import com.lh.baseconfig.mapper.UserInfoMapper;
import com.lh.baseconfig.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo getUserInfoByOpenId(String openId) {
        return userInfoMapper.getUserInfoByOpenId(openId);
    }

    @Override
    public int addUserInfo(UserInfo userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }

    @Override
    public UserInfo getUserInfoById(Long id) {
        return userInfoMapper.getUserInfoById(id);
    }

    @Override
    public int updateUserInfo(UserInfo userInfo) {
        return userInfoMapper.updateUserInfo(userInfo);
    }

    @Override
    public List<UserInfo> getEngineerList() {
        return userInfoMapper.getEngineerList();
    }

    @Override
    public List<UserInfo> getEngineerNameList() {
        return userInfoMapper.getEngineerNameList();
    }

    @Override
    public List<String> getEngineerName(String [] engineerIds) {
        return userInfoMapper.getEngineerName(engineerIds);
    }

    @Override
    public List<UserInfo> getUserInfoByParms(UserInfo userInfo) {
        return userInfoMapper.getUserInfoByParms(userInfo);
    }

    @Override
    public List<UserInfo> getManagerUserList() {
        return userInfoMapper.getManagerUserList();
    }

    @Override
    public List<UserInfo> getUserInfoByIds(String engineeId) {
        return userInfoMapper.getUserInfoByIds(engineeId);
    }
}
