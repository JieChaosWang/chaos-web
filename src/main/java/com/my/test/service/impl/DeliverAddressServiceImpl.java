package com.my.test.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.my.test.mapper.DeliverAddressMapper;
import com.my.test.pojo.DeliverAddress;
import com.my.test.service.DeliverAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliverAddressServiceImpl implements DeliverAddressService {
	
	protected transient final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private DeliverAddressMapper deliverAddressMapper;
    
    /**
     * 设置默认快递
     * By wangchaojie
     */
    @Override
    @Transactional
    public void toDefault(DeliverAddress deliverAddress) throws Exception{
        try {
            //查询出之前的默认快递
			DeliverAddress beforeDefault = new DeliverAddress();
			beforeDefault.setIsDefaultAdd("Y");// 默认快递
			beforeDefault.setCustSeq(deliverAddress.getCustSeq());
			beforeDefault.setMerFlag(deliverAddress.getMerFlag());
			
			DeliverAddress queryDeliverAddress = deliverAddressMapper.queryDeliverAddressInfo(beforeDefault);
			
			if (null != queryDeliverAddress) {
				//将原来的默认快递设置为非默认
				queryDeliverAddress.setIsDefaultAdd("N");
				queryDeliverAddress.setAlterTime(new Timestamp(System.currentTimeMillis()));
				deliverAddressMapper.updateDeliverAddress(queryDeliverAddress);
			}
			
			//将目标快递设为默认
			deliverAddress.setIsDefaultAdd("Y");

			if (StringUtils.isEmpty(deliverAddress.getDeliverSeq())) {
                deliverAddress.setCreateTime(new Timestamp(System.currentTimeMillis()));
	            deliverAddressMapper.addDeliverAddress(deliverAddress);
			}else {
                deliverAddress.setAlterTime(new Timestamp(System.currentTimeMillis()));
				deliverAddressMapper.updateDeliverAddress(deliverAddress);
			}
			
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new Exception("修改默认快递失败：" + e.getMessage());
        }
    }
    
    
    @Override
    @Transactional
    public void addDeliverAddress(DeliverAddress deliverAddress) throws Exception{
        try {
            deliverAddress.setCreateTime(new Timestamp(System.currentTimeMillis()));
            deliverAddressMapper.addDeliverAddress(deliverAddress);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("添加送货地址信息失败：" + e.getMessage());
        }
    }
    @Override
    public DeliverAddress queryDeliverAddressInfo (DeliverAddress deliverAddress) throws Exception{
        try {
            deliverAddress = deliverAddressMapper.queryDeliverAddressInfo(deliverAddress);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new Exception("查询送货地址信息失败：" + e.getMessage());
        }
        return deliverAddress;
    }
    @Override
    public List<DeliverAddress> queryDeliverAddressList(DeliverAddress deliverAddress) throws Exception{
        List<DeliverAddress> elist = new ArrayList<>();
        try {
            elist = deliverAddressMapper.queryDeliverAddressListPage(deliverAddress);
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new Exception("查询送货地址信息失败：" + e.getMessage());
        }
        return elist;
    }
    @Override
    @Transactional
    public void updateDeliverAddress(DeliverAddress deliverAddress) throws Exception{
        try {
            deliverAddress.setAlterTime(new Timestamp(System.currentTimeMillis()));
            deliverAddressMapper.updateDeliverAddress(deliverAddress);
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new Exception("更新送货地址信息失败：" + e.getMessage());
        }
    }
    @Override
    @Transactional
    public void deleteDeliverAddress(DeliverAddress deliverAddress) throws Exception{
        try {
            deliverAddressMapper.deleteDeliverAddress(deliverAddress);
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new Exception("删除送货地址信息失败：" + e.getMessage());
        }
    }


    @Override
    public Integer queryDeliverAddressCount(DeliverAddress deliverAddress) throws Exception {
         try {
            if(deliverAddress==null || StringUtils.isEmpty(deliverAddress.getCustSeq())){
                throw new Exception("查询收货地址数量失败");
            }
            return deliverAddressMapper.queryDeliverAddressCount(deliverAddress);
        } catch (Exception e) {
        	log.error(e.getMessage());
            throw new Exception("查询收货地址数量失败：" + e.getMessage());
        }
        
    }
}