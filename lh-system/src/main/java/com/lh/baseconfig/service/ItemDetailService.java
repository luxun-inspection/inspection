package com.lh.baseconfig.service;

import com.lh.baseconfig.domain.ItemDetail;

public interface ItemDetailService {

    ItemDetail getItemDetailByParentId(Long id);

    int updateItemDetail(ItemDetail itemDetail);
}
