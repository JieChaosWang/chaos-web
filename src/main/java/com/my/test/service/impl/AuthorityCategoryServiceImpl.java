package com.my.test.service.impl;

import com.github.pagehelper.Page;
import com.my.test.mapper.AuthorityCategoryMapper;
import com.my.test.pojo.AuthorityCategory;
import com.my.test.service.AuthorityCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wang on 2018/11/30.
 */
@Service
public class AuthorityCategoryServiceImpl implements AuthorityCategoryService {

    @Autowired
    private AuthorityCategoryMapper authorityCategoryMapper;
    @Override
    @Transactional
    public Page<AuthorityCategory> findAll() {
        return authorityCategoryMapper.findAll();
    }

    /**
     * 查询权限分类
     * @param id
     */
    @Transactional(readOnly = true)
    public AuthorityCategory getOne(Long id) {
        return authorityCategoryMapper.getOne(id);
    }
}
