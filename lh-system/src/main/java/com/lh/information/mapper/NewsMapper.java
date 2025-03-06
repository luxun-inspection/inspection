package com.lh.information.mapper;

import com.lh.information.domain.News;

import java.util.List;

public interface NewsMapper {

    List<News> getNewsList(News news);

    News getNewsById(Long id);

    int insertNews(News news);

    int updateNews(News news);

    int deleteNewsById(Long id);

    int getMaxSort();
}
