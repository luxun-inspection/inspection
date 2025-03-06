package com.lh.baseconfig.service;

import java.util.List;
import com.lh.baseconfig.domain.BaseTextContent;

/**
 * 文本管理Service接口
 *
 * @author lh
 * @date 2022-10-28
 */
public interface IBaseTextContentService
{
    /**
     * 查询文本管理
     *
     * @param id 文本管理主键
     * @return 文本管理
     */
    public BaseTextContent selectBaseTextContentById(Long id);

    /**
     * 查询文本管理列表
     *
     * @param baseTextContent 文本管理
     * @return 文本管理集合
     */
    public List<BaseTextContent> selectBaseTextContentList(BaseTextContent baseTextContent);

    /**
     * 新增文本管理
     *
     * @param baseTextContent 文本管理
     * @return 结果
     */
    public int insertBaseTextContent(BaseTextContent baseTextContent);

    /**
     * 修改文本管理
     *
     * @param baseTextContent 文本管理
     * @return 结果
     */
    public int updateBaseTextContent(BaseTextContent baseTextContent);

    /**
     * 批量删除文本管理
     *
     * @param ids 需要删除的文本管理主键集合
     * @return 结果
     */
    public int deleteBaseTextContentByIds(Long[] ids);

    /**
     * 删除文本管理信息
     *
     * @param id 文本管理主键
     * @return 结果
     */
    public int deleteBaseTextContentById(Long id);
}
