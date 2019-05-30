package com.my.test.service;

import com.github.pagehelper.Page;
import com.my.test.pojo.AuthorityCategory;

import java.util.List;

/**
 * Created by wang on 2018/11/30.
 */
public interface AuthorityCategoryService {

    List<AuthorityCategory> queryAuthorityCategoryList(AuthorityCategory authorityCategory);

    void addAuthorityCategory(AuthorityCategory authorityCategory);

    AuthorityCategory queryAuthorityCategory(AuthorityCategory authorityCategory);

    void delAuthorityCategory(Long[] authorityCategoryIds);
}
