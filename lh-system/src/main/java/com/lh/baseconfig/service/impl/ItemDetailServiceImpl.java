package com.lh.baseconfig.service.impl;

import com.lh.baseconfig.domain.ItemDetail;
import com.lh.baseconfig.mapper.ItemDetailMapper;
import com.lh.baseconfig.service.ItemDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDetailServiceImpl implements ItemDetailService {

    @Autowired
    private ItemDetailMapper itemDetailMapper;

    @Override
    public ItemDetail getItemDetailByParentId(Long id) {
        return itemDetailMapper.getItemDetailByParentId(id);
    }

    @Override
    public int updateItemDetail(ItemDetail itemDetail) {
        return itemDetailMapper.updateItemDetail(itemDetail);
    }
}
