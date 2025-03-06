package com.lh.baseconfig.service.impl;

import com.lh.baseconfig.domain.HomePage;
import com.lh.baseconfig.mapper.HomePageMapper;
import com.lh.baseconfig.service.HomePageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {

    @Autowired
    private HomePageMapper homePageMapper;

    @Override
    public List<HomePage> getHomePageBanner() {
        return homePageMapper.getHomePageBanner();
    }

    @Override
    public HomePage getCompanyIntroduction() {
        return homePageMapper.getCompanyIntroduction();
    }

    @Override
    public List<HomePage> getRotationPic() {
        return homePageMapper.getRotationPic();
    }

    @Override
    public int updateHomePage(HomePage homePage) {
        return homePageMapper.updateHomePage(homePage);
    }

    @Override
    public int insertHomePage(HomePage homePage) {
        return homePageMapper.insertHomePage(homePage);
    }

    @Override
    public int deleteHomePage(Long id) {
        return homePageMapper.deleteHomePage(id);
    }

    @Override
    public HomePage getHomePageById(Long id) {
        return homePageMapper.getHomePageById(id);
    }
}
