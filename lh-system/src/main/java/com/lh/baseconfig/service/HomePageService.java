package com.lh.baseconfig.service;

import com.lh.baseconfig.domain.HomePage;

import java.util.List;

public interface HomePageService {
    List<HomePage> getHomePageBanner();

    HomePage getCompanyIntroduction();

    /**
     * 获取轮播图
     */
    List<HomePage> getRotationPic();

    int updateHomePage(HomePage homePage);

    int insertHomePage(HomePage homePage);

    int deleteHomePage(Long id);

    HomePage getHomePageById(Long id);
}
