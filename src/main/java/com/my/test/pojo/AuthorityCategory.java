package com.my.test.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by wang on 2018/11/28.
 */
public class AuthorityCategory {

    private Long authorityCategoryId;
    private Date createDate;
    private Date modifyDate;
    private Integer orders;
    private String authorityCategoryName;
    private List<Authority> authorities;

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

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

    public String getAuthorityCategoryName() {
        return authorityCategoryName;
    }

    public void setAuthorityCategoryName(String authorityCategoryName) {
        this.authorityCategoryName = authorityCategoryName;
    }
}
