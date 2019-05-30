package com.my.test.controller;

import com.github.pagehelper.PageInfo;
import com.my.test.pojo.DeliverAddress;
import com.my.test.pojo.Province;
import com.my.test.pojo.QueryProvincesAndCitysResponse;
import com.my.test.service.DeliverAddressService;
import com.my.test.service.ProvinceService;
import com.my.test.util.BeeUtils;
import com.my.test.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/authorityCategory")
public class DeliverAddressController extends BaseController {
    @Autowired
    private DeliverAddressService deliverAddressService;
    @Autowired
    private ProvinceService provinceService;

    /**
     * 设置默认收货地址
     *
     * @author wangchaojie
     * @param deliverAddress
     * @param model
     * @return
     */
    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.POST)
    public String setDefaultAddress(DeliverAddress deliverAddress, Model model) {
        try {
            String custSeq = deliverAddress.getCustSeq();

            deliverAddress.setCustSeq(custSeq);
            deliverAddressService.toDefault(deliverAddress);

            DeliverAddress deliverAddressForPage = new DeliverAddress();
            deliverAddressForPage.setCustSeq(custSeq);

            QueryProvincesAndCitysResponse queryProvinceResponse = provinceService.queryProvinceList();
            List<Province> provinces = queryProvinceResponse.getProvinceList();
            // 返回收货地址集合
            model.addAttribute("provinces", provinces);
            // 执行查询
            List<DeliverAddress> deliverAddressList = deliverAddressService.queryDeliverAddressList(deliverAddressForPage);

            model.addAttribute("list", deliverAddressList);
        }catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }

    /**
     * 查询收货地址列表
     *
     * @author wangchaojie
     * @param model
     * @return
     */
    @RequestMapping(value = "/getDeliverAddressList", method = RequestMethod.POST)
    public @ResponseBody
    String getDeliverAddressList(Model model, String custSeq, PageInfo pageInfo) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            DeliverAddress deliverAddress = new DeliverAddress();
            deliverAddress.setCustSeq(custSeq);

            // 执行查询
            List<DeliverAddress> deliverAddressList = deliverAddressService.queryDeliverAddressList(deliverAddress);

            // 返回收货地址集合
            model.addAttribute("deliverAddressList", deliverAddressList);
            model.addAttribute("addressCount", deliverAddressList.size());

//          model.addAttribute("pager", pager);

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }

    /**
     * 添加收货地址
     *
     * @author wangchaojie
     * @param deliverAddress
     * @param model
     * @return
     */
    @RequestMapping(value = "/addDeliverAddress", method = RequestMethod.POST)
    public @ResponseBody
    String addDeliverAddress(DeliverAddress deliverAddress, Model model) {
        try {
            String custSeq = deliverAddress.getCustSeq();
            // 验证非空
            BeeUtils.isEmpty("收货人", deliverAddress.getContact());
            BeeUtils.isEmpty("手机号码", deliverAddress.getTelephone());
            BeeUtils.isEmpty("省份", deliverAddress.getProvinceid());
            BeeUtils.isEmpty("城市", deliverAddress.getCityid());
            BeeUtils.isEmpty("地区", deliverAddress.getAreaid());
            BeeUtils.isEmpty("街道地址", deliverAddress.getAddressZh());
            BeeUtils.isEmpty("默认地址选择", deliverAddress.getIsDefaultAdd());

            if (null == deliverAddress.getTownId() || 0 == deliverAddress.getTownId() ) {
                deliverAddress.setTownName(null);
            }

//            if (!BeeUtils.checkMobileNumber(deliverAddress.getTelephone())) {
//                throw new Exception("");
//            }
            deliverAddress.setCustSeq(custSeq);

            DeliverAddress deliverCount = new DeliverAddress();
            deliverCount.setCustSeq(custSeq);
            deliverCount.setMerFlag(deliverAddress.getMerFlag());
            // 新增之前查询地址数量
            Integer count = deliverAddressService.queryDeliverAddressCount(deliverCount);
            if (count != null && count >= 5) {
                throw new Exception("您的蜂贝收货地址已达上限");
            }

            // 数据库没有该用户收货地址,设置为默认地址;
            if (count <= 0) {
                deliverAddress.setIsDefaultAdd(Constants.IS_DEFAULT_ADDRESS_Y);
            }

            if (!Constants.IS_DEFAULT_ADDRESS_Y.equals(deliverAddress.getIsDefaultAdd())) {
                // 执行添加
                deliverAddressService.addDeliverAddress(deliverAddress);

            } else {
                // 执行默认快递添加
                deliverAddressService.toDefault(deliverAddress);
            }

            // 重新获取快递列表
            DeliverAddress deliverAddressForPage = new DeliverAddress();
            deliverAddressForPage.setCustSeq(custSeq);
            deliverAddressForPage.setMerFlag(deliverAddress.getMerFlag());

            List<DeliverAddress> deliverAddressList = deliverAddressService.queryDeliverAddressList(deliverAddressForPage);

            // 返回收货地址集合
            model.addAttribute("list", deliverAddressList);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }

    /**
     * 修改收货地址
     *
     * @author wangchaojie
     * @param deliverAddress
     * @return
     */
    @RequestMapping(value = "/updateDeliverAddress", method = RequestMethod.POST)
    public @ResponseBody
    String updateDeliverAddress(DeliverAddress deliverAddress, Model model) {
        try {
            String custSeq = deliverAddress.getCustSeq();
            // 验证非空
            BeeUtils.isEmpty("收货地址编号", deliverAddress.getDeliverSeq());
            BeeUtils.isEmpty("收货人", deliverAddress.getContact());
            BeeUtils.isEmpty("手机号码", deliverAddress.getTelephone());
            BeeUtils.isEmpty("省份", deliverAddress.getProvinceid());
            BeeUtils.isEmpty("城市", deliverAddress.getCityid());
            BeeUtils.isEmpty("地区", deliverAddress.getAreaid());
            BeeUtils.isEmpty("街道地址", deliverAddress.getAddressZh());

            if (null == deliverAddress.getTownId() || 0 == deliverAddress.getTownId() ) {
                deliverAddress.setTownName(null);
            }

            // 验证信息填写是否正确
            // 验证手机号码
            if (!BeeUtils.checkMobileNumber(deliverAddress.getTelephone())) {
                throw new Exception("手机号码格式不正确");
            }
            deliverAddress.setIsDefaultAdd(null);

            if(deliverAddress.getTownId()==null){
                deliverAddress.setTownId(0);
            }
            // 执行修改
            deliverAddressService.updateDeliverAddress(deliverAddress);

            // 重新获取快递列表
            DeliverAddress deliverAddressForPage = new DeliverAddress();
            deliverAddressForPage.setCustSeq(custSeq);

            List<DeliverAddress> deliverAddressList = deliverAddressService.queryDeliverAddressList(deliverAddressForPage);

            // 返回收货地址集合
            model.addAttribute("list", deliverAddressList);
        } catch (Exception e) {
        }
        return "";
    }

    /**
     * 删除收货地址
     *
     * @author wangchaojie
     * @param deliverAddress
     * @return
     */
    @RequestMapping(value = "/deleteDeliverAddress", method = RequestMethod.POST)
    public @ResponseBody
    String deleteDeliverAddress(DeliverAddress deliverAddress, Model model) {
        try {
            String custSeq = deliverAddress.getCustSeq();
            // 删除
            deliverAddressService.deleteDeliverAddress(deliverAddress);

            // 重新获取快递列表
            DeliverAddress deliverAddressForPage = new DeliverAddress();
            deliverAddressForPage.setCustSeq(custSeq);
            deliverAddressForPage.setMerFlag(deliverAddress.getMerFlag());

            List<DeliverAddress> deliverAddressList = deliverAddressService.queryDeliverAddressList(deliverAddressForPage);
            int size = deliverAddressList.size();
            if (size==1) {
                deliverAddressList.get(0).setIsDefaultAdd(Constants.IS_DEFAULT_ADDRESS_Y);
                deliverAddressService.updateDeliverAddress(deliverAddressList.get(0));
            }

            deliverAddressList = deliverAddressService.queryDeliverAddressList(deliverAddressForPage);

            // 返回收货地址集合
            model.addAttribute("list", deliverAddressList);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return "";
    }

}
