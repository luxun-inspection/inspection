package com.lh.baseconfig.service.impl;

import com.lh.baseconfig.domain.ItemDetail;
import com.lh.baseconfig.domain.ServiceItem;
import com.lh.baseconfig.mapper.ItemDetailMapper;
import com.lh.baseconfig.mapper.ServiceItemMapper;
import com.lh.baseconfig.service.ServiceItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ServiceItemServiceImpl implements ServiceItemService {

    @Autowired
    private ServiceItemMapper serviceItemMapper;
    @Autowired
    private ItemDetailMapper itemDetailMapper;

    @Override
    public List<ServiceItem> getServiceItem() {
        return serviceItemMapper.getServiceItem();
    }

    @Override
    public List<ServiceItem> getServiceItemListByParentId(Long id) {
        return serviceItemMapper.getServiceItemListByParentId(id);
    }

    @Override
    public int updateServiceItem(ServiceItem serviceItem) {
        return serviceItemMapper.updateServiceItem(serviceItem);
    }

    @Override
    @Transactional
    public int insertServiceItem(ServiceItem serviceItem) {
        //新增详情内容
        serviceItemMapper.insertServiceItem(serviceItem);
        ItemDetail itemDetail = new ItemDetail();
        itemDetail.setCreateTime(new Date());
        itemDetail.setParentId(serviceItem.getId());
        return itemDetailMapper.insertItemDetail(itemDetail);
    }

    @Override
    public int deleteServiceItem(ServiceItem serviceItem) {
        return serviceItemMapper.deleteServiceItem(serviceItem);
    }
}
