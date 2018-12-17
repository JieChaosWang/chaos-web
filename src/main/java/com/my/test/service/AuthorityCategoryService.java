package com.my.test.service;

import com.github.pagehelper.Page;
import com.my.test.pojo.AuthorityCategory;

/**
 * Created by wang on 2018/11/30.
 */
public interface AuthorityCategoryService {

   Page<AuthorityCategory> findAll();

    AuthorityCategory getOne(Long id);
}
