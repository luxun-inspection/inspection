package com.lh.baseconfig.mapper;

import com.lh.baseconfig.domain.HomePage;

import java.util.List;

public interface HomePageMapper {

    List<HomePage> getHomePageBanner();

    HomePage getCompanyIntroduction();

    List<HomePage> getRotationPic();

    int updateHomePage(HomePage homePage);

    int insertHomePage(HomePage homePage);

    int deleteHomePage(Long id);

    HomePage getHomePageById(Long id);
}
