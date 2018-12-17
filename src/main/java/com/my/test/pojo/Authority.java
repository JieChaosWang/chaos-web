package com.my.test.pojo;

import java.util.Date;
import java.util.List;

/**
 * Created by wang on 2018/11/27.
 */
public class Authority {

    private  Long id;
    private Date createDate;
    private  Date modifyDate;
    private  String name;
    private String path;
    private  String  authorityValue;
    private Long authorityCategoryId;
    private AuthorityCategory authorityCategory;
    private List<Role> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getAuthorityValue() {
        return authorityValue;
    }

    public void setAuthorityValue(String authorityValue) {
        this.authorityValue = authorityValue;
    }

    public Long getAuthorityCategoryId() {
        return authorityCategoryId;
    }

    public void setAuthorityCategoryId(Long authorityCategoryId) {
        this.authorityCategoryId = authorityCategoryId;
    }

    public AuthorityCategory getAuthorityCategory() {
        return authorityCategory;
    }

    public void setAuthorityCategory(AuthorityCategory authorityCategory) {
        this.authorityCategory = authorityCategory;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
