package com.lh.baseconfig.mapper;

import com.lh.baseconfig.domain.ItemDetail;

public interface ItemDetailMapper {

    ItemDetail getItemDetailByParentId(Long id);

    int updateItemDetail(ItemDetail itemDetail);

    int insertItemDetail(ItemDetail itemDetail);
}
