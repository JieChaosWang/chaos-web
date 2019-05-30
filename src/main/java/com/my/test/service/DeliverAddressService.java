package com.my.test.service;

import com.my.test.pojo.DeliverAddress;

import java.util.List;

public interface DeliverAddressService {
    /**
     *
     * 设置默认收货地址
     * 2016年9月22日
     * @param deliverAddress
     */
	public void toDefault(DeliverAddress deliverAddress) throws Exception;

    public void addDeliverAddress(DeliverAddress deliverAddress) throws Exception;

    public DeliverAddress queryDeliverAddressInfo(DeliverAddress deliverAddress) throws Exception;
    
    public List<DeliverAddress> queryDeliverAddressList(DeliverAddress deliverAddress) throws Exception;
    
    public void updateDeliverAddress(DeliverAddress deliverAddress) throws Exception;
    
    public void deleteDeliverAddress(DeliverAddress deliverAddress) throws Exception;
    
    /**
     * 
     * 查询收货地址数量
     * 2016年9月22日
     * @param deliverAddress
     * @throws Exception
     */
    public Integer queryDeliverAddressCount(DeliverAddress deliverAddress) throws Exception;
}