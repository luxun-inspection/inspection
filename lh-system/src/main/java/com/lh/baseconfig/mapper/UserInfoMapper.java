package com.lh.baseconfig.mapper;

import com.lh.baseconfig.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInfoMapper {

    UserInfo getUserInfoByOpenId(String openId);

    int insertUserInfo(UserInfo userInfo);

    UserInfo getUserInfoById(Long id);

    int updateUserInfo(UserInfo userInfo);

    List<UserInfo> getEngineerList();

    List<UserInfo> getEngineerNameList();

    List<String> getEngineerName(@Param("engineerId") String[] engineerId);

    List<UserInfo> getUserInfoByParms(UserInfo userInfo);

    List<UserInfo> getManagerUserList();

    List<UserInfo> getUserInfoByIds(@Param("engineeId") String engineeId);
}
