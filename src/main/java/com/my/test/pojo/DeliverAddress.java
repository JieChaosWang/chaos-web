package com.my.test.pojo;

import java.sql.Timestamp;

public class DeliverAddress {
    
    private String deliverSeq;//收货地址编号
    private String custSeq;//个人唯一标识
    private Integer provinceid;
    private Integer cityid;
    private Integer areaid;
    private String isDefaultAdd;//是否默认地址  Y是N否
    private String addressZh;//详细地址
    private String contact;//收货人姓名
    private String telephone;
    private String postcode;
    private String merFlag;// 商户标识 JD-京东 MER-商户平台
    private Integer townId;//乡镇Id
    private String provinceName;// 省份名称
    private String cityName;// 城市名称
    private String areaName;// 区域名称
    private String townName;//乡镇名称
    private String isNewAddr;
    private String isDel;
    private Timestamp createTime;//创建时间
    private Timestamp alterTime;//修改时间

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(Timestamp alterTime) {
        this.alterTime = alterTime;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getIsNewAddr() {
        return isNewAddr;
    }

    public void setIsNewAddr(String isNewAddr) {
        this.isNewAddr = isNewAddr;
    }
    
    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }
    public String getMerFlag() {
        return merFlag;
    }
    public void setMerFlag(String merFlag) {
        this.merFlag = merFlag;
    } 
    public String getDeliverSeq(){
        return deliverSeq;
    }
    public void setDeliverSeq(String deliverSeq){
        this.deliverSeq = deliverSeq;    }
    public String getCustSeq(){
        return custSeq;
    }
    public void setCustSeq(String custSeq){
        this.custSeq = custSeq;    }
    public Integer getProvinceid(){
        return provinceid;
    }
    public void setProvinceid(Integer provinceid){
        this.provinceid = provinceid;    }
    public Integer getCityid(){
        return cityid;
    }
    public void setCityid(Integer cityid){
        this.cityid = cityid;    }
    public Integer getAreaid(){
        return areaid;
    }
    public void setAreaid(Integer areaid){
        this.areaid = areaid;    }
    public String getIsDefaultAdd(){
        return isDefaultAdd;
    }
    public void setIsDefaultAdd(String isDefaultAdd){
        this.isDefaultAdd = isDefaultAdd;    }
    public String getAddressZh(){
        return addressZh;
    }
    public void setAddressZh(String addressZh){
        this.addressZh = addressZh;    }
    public String getContact(){
        return contact;
    }
    public void setContact(String contact){
        this.contact = contact;    }
    public String getTelephone(){
        return telephone;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;    }
    public String getPostcode(){
        return postcode;
    }
    public void setPostcode(String postcode){
        this.postcode = postcode;    }
}