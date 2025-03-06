package com.lh.baseconfig.service;

import com.lh.baseconfig.domain.ServiceItem;

import java.util.List;

public interface ServiceItemService {

    List<ServiceItem> getServiceItem();

    List<ServiceItem> getServiceItemListByParentId(Long id);

    int updateServiceItem(ServiceItem serviceItem);

    int insertServiceItem(ServiceItem serviceItem);

    int deleteServiceItem(ServiceItem serviceItem);

}
