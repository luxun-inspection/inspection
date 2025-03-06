package com.lh.information.service;

import com.lh.information.domain.News;

import java.util.List;

public interface NewsService {

    List<News> getNewsList(News news);

    News getNewsById(Long id);

    int insertNews(News news);

    int updateNews(News news);

    int deleteNewsById(Long id);

    int getMaxSort();
}
