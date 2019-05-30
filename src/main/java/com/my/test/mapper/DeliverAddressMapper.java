package com.my.test.mapper;

import com.my.test.pojo.DeliverAddress;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliverAddressMapper {
    public void addDeliverAddress(DeliverAddress entity);
    
    public DeliverAddress queryDeliverAddressInfo(DeliverAddress entity);
    
    public List<DeliverAddress> queryDeliverAddressListPage(DeliverAddress entity);
    
    public void updateDeliverAddress(DeliverAddress entity);
    
    public void deleteDeliverAddress(DeliverAddress entity);
    
    public Integer queryDeliverAddressCount(DeliverAddress entity);
}