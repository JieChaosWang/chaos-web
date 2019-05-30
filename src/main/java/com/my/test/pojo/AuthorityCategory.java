package com.my.test.pojo;

import java.util.Date;

/**
 * Created by wang on 2018/11/28.
 */
public class AuthorityCategory {

    private Long authorityCategoryId;
    private Date createDate;
    private Date modifyDate;
    private Integer orders;
    private String name;

    public Long getAuthorityCategoryId() {
        return authorityCategoryId;
    }

    public void setAuthorityCategoryId(Long authorityCategoryId) {
        this.authorityCategoryId = authorityCategoryId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
