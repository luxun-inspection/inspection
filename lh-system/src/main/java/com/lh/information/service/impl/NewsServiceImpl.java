package com.lh.information.service.impl;

import com.lh.information.domain.News;
import com.lh.information.mapper.NewsMapper;
import  com.lh.information.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsMapper newsMapper;

    @Override
    public List<News> getNewsList(News news) {
        return newsMapper.getNewsList(news);
    }

    @Override
    public News getNewsById(Long id) {
        return newsMapper.getNewsById(id);
    }

    @Override
    public int insertNews(News news) {
        return newsMapper.insertNews(news);
    }

    @Override
    public int updateNews(News news) {
        return newsMapper.updateNews(news);
    }

    @Override
    public int deleteNewsById(Long id) {
        return newsMapper.deleteNewsById(id);
    }

    @Override
    public int getMaxSort() {
        return newsMapper.getMaxSort();
    }
}
